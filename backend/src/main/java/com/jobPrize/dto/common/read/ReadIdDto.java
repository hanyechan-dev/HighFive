package com.jobPrize.dto.common.read;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReadIdDto {
	
    @NotNull
    private Long id;

}
