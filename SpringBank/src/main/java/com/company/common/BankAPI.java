package com.company.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.company.bank.service.BankVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Component
public class BankAPI {

	String host = "https://testapi.openbanking.or.kr";
	String client_id = "a61c3366-5f48-421e-a297-56620fedd3e6";
	String client_secret = "0bdfc679-8a58-435d-a41f-c12a6bf411c0";
	String redirect_uri = "http://localhost/bank2/callback";
	// 이용기관코드
	String user_ord_code = "M202111679";

	// 기관 로그인
	public Map<String, Object> getOrgAccessToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		String reqURL = host + "/oauth/2.0/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			// parameter 수정
			sb.append("client_id=" + client_id)//
					.append("&client_secret=" + client_secret)//
					.append("&scope=oob")//
					.append("&grant_type=client_credentials");
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			// gson 이용 map에 저장하기(1줄로 가능)
			Gson gson = new Gson();
			map = gson.fromJson(result, HashMap.class);
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	// BANK 로그인
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		// URL 바꾸기
		String reqURL = host + "/oauth/2.0/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			// parameter 수정
			sb.append("code=" + authorize_code);
			sb.append("&client_id=" + client_id);
			sb.append("&client_secret=" + client_secret);
			sb.append("&redirect_uri=" + redirect_uri);
			sb.append("&grant_type=authorization_code");
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			// MAP구조로
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			// gson 이용 map에 저장하기(1줄로 가능)
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}// end of getAccessToken

	// 사용자 정보 get
	public HashMap<String, Object> getAccountList(String access_Token, String user_num) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> map = new HashMap<>();
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";
		StringBuilder qstr = new StringBuilder();
		qstr.append("user_seq_no=" + user_num)//
				.append("&include_cancel_yn=Y")//
				.append("&sort_order=D");
		try {
			URL url = new URL(reqURL + "?" + qstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			// 출력되는 값이 200이면 정상작동
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			// map에 담아 리턴
			Gson gson = new Gson();
			map = gson.fromJson(result, HashMap.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	// 잔액조회 getBalance
	public HashMap<String, Object> getBalance(BankVO vo) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> map = new HashMap<>();
		String reqURL = host + "/v2.0/account/balance/fin_num";
		StringBuilder qstr = new StringBuilder();
		qstr.append("bank_tran_id=").append(user_ord_code + "U" + getRand())//
				.append("&fintech_use_num=").append(vo.getFintech_use_num())// vo에 넣을 값 설정
				.append("&tran_dtime=").append(getDate());
		try {
			URL url = new URL(reqURL + "?" + qstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + vo.getAccess_token());

			// 출력되는 값이 200이면 정상작동
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			// map에 담아 리턴
			Gson gson = new Gson();
			map = gson.fromJson(result, HashMap.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	// Date변환
	public String getDate() {
		String str = "";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		str = format.format(date);
		return str;
	}

	// 9자리 난수
	public String getRand() {
		long time = System.currentTimeMillis();
		String str = Long.toString(time);
		return str.substring(str.length() - 9);// 9자리 이후로 자름?
	}

	// 32자리 난수 만들기
	public String getRand32() {
		long time = System.currentTimeMillis();
		String str = Long.toString(time);
		return str + str + str.substring(0, 6);
	}

}// end of class
