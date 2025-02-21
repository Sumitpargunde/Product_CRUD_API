package com.example.product_category_crud_api.dto;

import java.util.List;

public class CustomPageResponse<T> {
    private List<T> content;
    private int pageNumber;

    public CustomPageResponse() {}

    public CustomPageResponse(List<T> content, int pageNumber) {
        this.content = content;
        this.pageNumber = pageNumber;
    }

    // Getters and Setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
