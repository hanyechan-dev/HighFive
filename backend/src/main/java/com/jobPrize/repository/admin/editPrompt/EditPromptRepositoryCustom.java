package com.jobPrize.repository.admin.editPrompt;

import java.util.Optional;

import com.jobPrize.entity.admin.EditPrompt;

public interface EditPromptRepositoryCustom {
	Optional<EditPrompt> findAppliedPrompt();		

}
