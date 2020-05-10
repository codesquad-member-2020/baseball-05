package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GameStatusDTO {

    private Team homeTeam;

    private Team awayTeam;

    private Player pitcher;

    private Inning inning;

    private List<Plate> plates;
}
