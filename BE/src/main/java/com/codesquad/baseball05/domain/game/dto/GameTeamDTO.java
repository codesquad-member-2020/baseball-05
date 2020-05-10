package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameTeamDTO {

    private String name;

    private int score;

    private boolean isOffense;
}
