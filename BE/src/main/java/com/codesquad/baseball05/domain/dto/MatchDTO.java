package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchDTO {
    private Long id;
    public Long userA;
    public Long userB;
    private String homeTeam;
    private String awayTeam;

    public MatchDTO() {
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", userA=" + userA +
                ", userB=" + userB +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                '}';
    }
}
