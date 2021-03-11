package com.company.bank;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.common.BankAPI;

@Controller
public class BankController {

	@Autowired
	BankAPI bankAPI;

	@RequestMapping("/auth")
	public String auth() throws Exception {
		String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/authorize_account";
		String response_type = "response_type=code";//
		String client_id = "&client_id=a61c3366-5f48-421e-a297-56620fedd3e6";//
		String redirect_uri = "&redirect_uri=http://localhost/bank2/callback";//
		String scope = "&scope=login inquiry transfer";//
		String state = "&state=12345678901234567890123456789012";//
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
		String access_token = (String) session.getAttribute("access_token");
		String user_num = "1100770536";
		Map<String, Object> map = bankAPI.getAccountList(access_token, user_num);
		System.out.println("userAccountList: " + map);
		model.addAttribute("list", map);
		return "bank/getAccountList";
	}
	// userInfo받기

}// end of class
