package com.jobPrize.util;

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
		
		try {
		    return webClient
		        .post()
		        .uri(path)
		        .bodyValue(requestResponseDto)
		        .retrieve()
		        .bodyToMono(AiConsultingCreateDto.class)
		        .block();
		} catch (Exception e) {
		    throw new RuntimeException("Python 서버 통신 실패", e);
		}
	}
	

}
