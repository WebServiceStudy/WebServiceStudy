package com.wss.webservicestudy.web.category.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicUpdate
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int depth;

    private int sort;

    public Category() {
    }

    @Builder
    public Category(Long id, String name, int depth, int sort) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.sort = sort;
    }
}
