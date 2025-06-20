package com.jobPrize.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.jobPrize.dto.memToCon.request.RequestResponseDto;
import com.jobPrize.dto.member.aiConsulting.AiConsultingCreateDto;

@Component
public class WebClientUtil {

	private final WebClient webClient;

	public WebClientUtil(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:9000").build();
	}

	public AiConsultingCreateDto postRequestToPython(RequestResponseDto requestResponseDto) {
		
		String type = requestResponseDto.getConsultingType();

		String path = type.equals("첨삭") ? "/guide/edit" : "/guide/feedback";
		
		Map<String, Object> systemPropmt = new HashMap<>();
		Map<String, Object> userPropmt = new HashMap<>();
		List<Map<String, Object>> promptList = new ArrayList<>();
		
		
		systemPropmt.put("role", "system");
		systemPropmt.put("content", "너는 컨설팅 전문가야 앞으로 들어온 회원의 정보 및 희망하는 기업과 직무를 보고 판단해서" + type + "을 해줘");
		userPropmt.put("role", "user");
		userPropmt.put("content", requestResponseDto);
		promptList.add(systemPropmt);
		promptList.add(userPropmt);
		
		try {
		    return webClient
		        .post()
		        .uri(path)
		        .bodyValue(promptList)
		        .retrieve()
		        .bodyToMono(AiConsultingCreateDto.class)
		        .block();
		} catch (Exception e) {
		    throw new RuntimeException("Python 서버 통신 실패", e);
		}
	}
	

}
