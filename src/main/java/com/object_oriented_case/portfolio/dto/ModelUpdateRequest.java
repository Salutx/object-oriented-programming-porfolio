package com.object_oriented_case.portfolio.dto;

public class ModelUpdateRequest {
    private String name;
    private Long markId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }
}
