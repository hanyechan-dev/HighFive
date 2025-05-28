package com.jobPrize.dto.common;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteIdDto {
	
    @NotNull
    private Long id;

}