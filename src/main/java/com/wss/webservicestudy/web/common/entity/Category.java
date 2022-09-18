package com.wss.webservicestudy.web.common.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateId;

    @Column(nullable = false)
    private String cateNm;

    public Category() {}

    @Builder
    public Category(Long cateId, String cateNm) {
        this.cateId = cateId;
        this.cateNm = cateNm;
    }
}
