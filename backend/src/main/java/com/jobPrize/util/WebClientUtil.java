package com.jobPrize.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.jobPrize.dto.member.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.enumerate.ConsultingType;

@Component
public class WebClientUtil {

	private final WebClient webClient;

	public WebClientUtil(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:9000").build();
	}

	public AiConsultingCreateDto postRequestToPython(List<Map<String, String>> promptList, ConsultingType consultingType) {

		String path = ConsultingType.첨삭.equals(consultingType) ? "/edits" : "/feedbacks";
		
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
