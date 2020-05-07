package com.codesquad.baseball05.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ScoreBoardDto {

    @JsonProperty
    private String team;

    @JsonProperty
    private int totalScore;

    @JsonProperty
    private List<Integer> inningScore = new ArrayList<>();


    public ScoreBoardDto(String team) {
        this.team = team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setInningScore(List<Integer> inningScore) {
        this.inningScore = inningScore;
    }

    public void score(int teamScore) {
        inningScore.add(teamScore);
        totalScore += teamScore;
    }
}
