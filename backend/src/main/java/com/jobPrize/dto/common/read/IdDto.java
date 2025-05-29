package com.jobPrize.dto.common.read;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IdDto {
	
    @NotNull
    private Long id;

}
