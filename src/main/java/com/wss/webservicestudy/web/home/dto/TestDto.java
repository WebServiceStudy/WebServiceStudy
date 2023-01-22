package com.wss.webservicestudy.web.home.dto;

import com.wss.webservicestudy.web.home.entity.TestEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class TestDto {
    private Long seq;
    private String teamMember;

    @Builder
    public TestDto(Long seq, String teamMember) {
        this.seq = seq;
        this.teamMember = teamMember;
    }

    public TestEntity toEntity() {
        return TestEntity.builder()
                .seq(this.seq)
                .teamMember(this.teamMember)
                .build();
    }

}
