package com.jobPrize.repository.admin.editPrompt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.admin.EditPrompt;

public interface EditPromptRepository extends JpaRepository<EditPrompt, Long>, EditPromptRepositoryCustom{

}
