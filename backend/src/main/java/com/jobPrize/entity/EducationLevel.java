package com.jobPrize.entity;

public enum EducationLevel {
    HIGH_SCHOOL_GRADUATION("고등학교 졸업"),
    ASSOCIATE_DEGREE("전문학사"),
    BACHELOR("학사"),
    MASTER("석사"),
    DOCTOR("박사");

    private final String label;

    EducationLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}