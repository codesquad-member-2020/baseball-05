package com.codesquad.baseball05.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonPropertyOrder({"Team", "inningScore", "totalScore"})
public class ScoreBoard {
    private String Team;
    private List<Integer> inningScore = new ArrayList<>();
    private int totalScore;

    public ScoreBoard(String team) {
        Team = team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public void setInningScore(List<Integer> inningScore) {
        this.inningScore = inningScore;
    }

    public void score(int teamScore) {
        inningScore.add(teamScore);
        totalScore += teamScore;
    }

//    public int getTotalScore() {
//        return inningScore.stream().mapToInt(Integer::intValue).sum();
//    }
}
