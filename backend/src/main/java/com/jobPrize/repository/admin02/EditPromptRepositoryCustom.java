package com.jobPrize.repository.admin02;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.admin.EditPrompt;

public interface EditPromptRepositoryCustom {
	Optional<EditPrompt> findAppliedPrompt();		
	List<EditPrompt> findAll(); 
}
