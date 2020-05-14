package com.codesquad.baseball05.domain.game.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScoreBoardDTO {
    private String teamName;
    private int totalScore;
    private List<Integer> inningScore;

    public ScoreBoardDTO(String teamName) {
        this.teamName = teamName;
    }
}
