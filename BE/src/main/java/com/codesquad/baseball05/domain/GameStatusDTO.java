package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameStatusDTO {

    private Team homeTeam;

    private Team awayTeam;

    private Inning inning;
}
