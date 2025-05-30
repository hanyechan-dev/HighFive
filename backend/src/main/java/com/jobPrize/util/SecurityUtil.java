package com.jobPrize.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jobPrize.entity.common.SecurityUser;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public class SecurityUtil {

    public static SecurityUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (SecurityUser) auth.getPrincipal();
    }

    public static Long getId() {
        return getCurrentUser().getId();
    }
    
    public static UserType getUserType() {
        return getCurrentUser().getUserType();
    }
    
    public static ApprovalStatus getApprovalStatus() {
        return getCurrentUser().getApprovalStatus();
    }
    
    public static boolean isSubscribed() {
        return getCurrentUser().isSubscribed();
    }
}