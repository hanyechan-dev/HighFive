package com.jobPrize.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
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
		
		Map<String, Object> body = Map.of("prompt_list", promptList);
		
		try {
		    return webClient
		        .post()
		        .uri(path)
		        .bodyValue(body)
		        .retrieve()
		        .bodyToMono(AiConsultingCreateDto.class)
		        .block();
		} catch (Exception e) {
		    throw new RuntimeException("Python 서버 통신 실패", e);
		}
	}
	
	public String sendEmbeddingRequestJobPosting(String metadata, List<MultipartFile> images) {
	    var builder = new org.springframework.util.LinkedMultiValueMap<String, Object>();

	    // metadata
	    builder.add("metadata", metadata);

	    // images
	    for (MultipartFile file : images) {
	        ByteArrayResource resource = new ByteArrayResource(getBytes(file)) {
	            @Override
	            public String getFilename() {
	                return file.getOriginalFilename();
	            }
	        };
	        builder.add("images", resource);
	    }

	    return webClient.post()
	        .uri("/embedding-job-posting") // 파이썬 엔드포인트
	        .contentType(MediaType.MULTIPART_FORM_DATA)
	        .body(BodyInserters.fromMultipartData(builder))
	        .retrieve()
	        .bodyToMono(String.class)
	        .block();
	}
	
	public String sendEmbeddingRequestMember(String data) {
		
		return webClient
        .post()
        .uri("/embedding-member")
        .bodyValue(data)
        .retrieve()
        .bodyToMono(String.class)
        .block();
		
	}

	// MultipartFile -> byte[]
	private byte[] getBytes(MultipartFile file) {
	    try {
	        return file.getBytes();
	    } catch (IOException e) {
	        throw new RuntimeException("파일 읽기 실패", e);
	    }
	}
	

}
