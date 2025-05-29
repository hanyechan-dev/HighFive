package com.jobPrize.dto.common.id;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IdDto {
	
    @NotNull
    private Long id;

}
