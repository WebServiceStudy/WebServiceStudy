package com.wss.webservicestudy.web.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class CategoryRespDto {
    private Long id;

    private String name;

    private int depth;

    private int sort;

    public CategoryRespDto() {
    }

    @Builder
    public CategoryRespDto(Long id, String name, int depth, int sort) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.sort = sort;
    }
}
