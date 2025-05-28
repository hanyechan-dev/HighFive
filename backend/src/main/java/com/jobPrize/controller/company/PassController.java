package com.jobPrize.controller.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.memToCom.pass.PassService;
import com.jobPrize.util.SecurityUtil;

@RestController
@RequestMapping("/pass")
public class PassController {

    private final PassService passService;

    public PassController(PassService passService) {
        this.passService = passService;
    }

    @PostMapping("/approve")
    public ResponseEntity<Void> approveApplication(@RequestParam Long applicationId) {
        Long companyId = SecurityUtil.getId(); 
        UserType userType = SecurityUtil.getUserType(); 

        passService.createPass(companyId, userType, applicationId); 

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
    }
}