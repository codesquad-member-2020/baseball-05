package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchesDTO {

    private Long id;

    private boolean selectable;

    private MatchesTeamDTO homeTeam;

    private MatchesTeamDTO awayTeam;
}
