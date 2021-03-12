package com.company.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.common.MovieAPI;

@Controller
public class MovieController {

	@Autowired
	MovieAPI movieAPI;

	// 영화정보조회
	@RequestMapping("/boxOffice")
	@ResponseBody // 넘겨줄때 자동으로 json형태로 넘겨줌
	public List<String> boxOffice() {
		List<String> list = new ArrayList<String>();
		// Map 안의 Map 추출
		Map map = movieAPI.getBoxOffice();
		// Map 안의 Map 추출
		Map boxOfficeResult = (Map) map.get("boxOfficeResult");
		// Map 안의 List추출
		List<Map> dailyBoxOfficeList = (List<Map>) boxOfficeResult.get("dailyBoxOfficeList");
		// 영화제목+영화코드 추출
		for (Map movie : dailyBoxOfficeList) {
			list.add((String) movie.get("movieNm") + ":" + (String) movie.get("movieCd"));
		}
		return list;
	}// end of boxOffice

	@RequestMapping("/movieActor")
	@ResponseBody // 넘겨줄때 자동으로 json형태로 넘겨줌
	public List<String> movieActor() {
		List<String> list = new ArrayList<String>();
		Map map = movieAPI.getMovieInfo();
		// Map 안의 Map 추출
		Map movieInfoResult = (Map) map.get("movieInfoResult");
		// Map 안의 Map 추출
		Map movieInfo = (Map) movieInfoResult.get("movieInfo");
		// Map 안의 List(Map타입) 추출
		List<Map> actors = (List<Map>) movieInfo.get("actors");
		// List에서 배우명 추출 후 String변환
		for (Map act : actors) {
			list.add((String) act.get("peopleNm"));
		}
		return list;
	}// end of boxOffice

}// end of class
