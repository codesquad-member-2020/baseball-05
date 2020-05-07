package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Team {

    private String name;

    private int score;

    private boolean isOffense;

    private boolean isUserTeam;

    private Player pitcher;
}
