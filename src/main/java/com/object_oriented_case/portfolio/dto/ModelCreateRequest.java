package com.object_oriented_case.portfolio.dto;

public class ModelCreateRequest {
    private String name;
    private Long markId;

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
