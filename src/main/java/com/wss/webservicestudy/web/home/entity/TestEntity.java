package com.wss.webservicestudy.web.home.entity;

import com.wss.webservicestudy.web.home.dto.TestDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "team_member")
    private String teamMember;

    @Builder
    public TestEntity(Long seq, String teamMember) {
        this.seq = seq;
        this.teamMember = teamMember;
    }

    public TestEntity() {}

    public TestDto toDto(){

        return TestDto.builder()
                .seq(this.seq)
                .teamMember(this.teamMember)
                .build();
    }
}
