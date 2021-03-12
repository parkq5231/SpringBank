package com.company.common;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieAPI {
	// 영화정보조회
	public Map getBoxOffice() {
		String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20210311";
		// 넘어온 결과를 json parsing까지 다 해줌
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Map.class);
	}// end of getBoxOffice

	// 해당 영화의 출연진(배우)출력
	public Map getMovieInfo() {
		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20205144";
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Map.class);
	}

	// 그날 날짜 메소드 생성

}
