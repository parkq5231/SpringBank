package com.company.bank;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.bank.service.BankVO;
import com.company.common.BankAPI;

@Controller
public class BankController {

	String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzcwNTM2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MjMyMDQzNjQsImp0aSI6ImEwNTVkN2EyLTM0ZGUtNDM2NC1iYTU0LTJkYzZmNTdiOWQzNSJ9.ZqWH0G8gzViSUf7DdNHVF2YDH7m21Kkd4X74-Y0ObNw";
	@Autowired
	BankAPI bankAPI;

	@RequestMapping("/auth")
	public String auth() throws Exception {
		String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/authorize_account";
		String response_type = "response_type=code";//
		String client_id = "&client_id=a61c3366-5f48-421e-a297-56620fedd3e6";//
		String redirect_uri = "&redirect_uri=http://localhost/bank2/callback";//
		String scope = "&scope=login inquiry transfer";//
		String state = "&state=" + bankAPI.getRand32();//
		String auth_type = "&auth_type=0";

		StringBuilder qstr = new StringBuilder();
		qstr.append(response_type)//
				.append(client_id)//
				.append(redirect_uri)//
				.append(scope)//
				.append(state)//
				.append(auth_type);
		return "redirect:" + reqURL + "?" + qstr.toString();
	}// end of auth

	@RequestMapping("/callback")
	public String callback(@RequestParam Map<String, Object> map, HttpSession session) {
		System.out.println("코드값: " + map.get("code"));
		String code = (String) map.get("code");

		// access_token받기
		String access_token = bankAPI.getAccessToken(code);
		System.out.println("토큰값: " + access_token);
		// session에 토큰값 부여
		session.setAttribute("access_token", access_token);
		return "home";
	}// end fo callback

	@RequestMapping("/getAccountList")
	public String getAccountList(HttpSession session, Model model) {
		// String access_token = (String) session.getAttribute("access_token");

		String user_num = "1100770536";
		Map<String, Object> map = bankAPI.getAccountList(access_token, user_num);
		System.out.println("userAccountList: " + map);
		model.addAttribute("list", map);
		return "bank/getAccountList";
	}
	// userInfo받기

	@RequestMapping("/getBalance")
	public String getBalance(BankVO vo, Model model) {
		vo.setAccess_token(access_token);
		Map<String, Object> map = bankAPI.getBalance(vo);
		System.out.println("잔액:" + map);
		model.addAttribute("balance", map);
		return "bank/getBalance";
	}

	// ajaxGetBalance
	@ResponseBody
	@RequestMapping("/ajaxGetBalance")
	public Map<String, Object> ajaxGetBalance(BankVO vo) {
		vo.setAccess_token(access_token);
		Map<String, Object> map = bankAPI.getBalance(vo);
		System.out.println("map값:" + map);
		return map;
	}

	// 기관인증
	@RequestMapping("/getOrgAuthorize")
	public String getOrgAuthorize() {
		Map<String, Object> map = bankAPI.getOrgAccessToken();
		System.out.println("access_token: " + map.get("access_token"));
		return "home";
	}

	// 기관인증2
	@RequestMapping("/getOrgAccessTokenRestTemplate")
	public String getOrgAuthorize2() {
		Map<String, Object> map = bankAPI.getOrgAccessTokenRestTemplate();
		System.out.println("access_token: " + map.get("access_token"));
		return "home";
	}
}// end of class
