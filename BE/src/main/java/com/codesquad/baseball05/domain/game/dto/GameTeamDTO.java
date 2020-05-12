package com.codesquad.baseball05.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameTeamDTO {

    private String name;

    private int score;

    @JsonProperty("isOffense")
    private boolean isOffense;
}
