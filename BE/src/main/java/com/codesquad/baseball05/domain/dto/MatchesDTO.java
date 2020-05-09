package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchesDTO {

    private Long id;

    private boolean selectable;

    private MatchesTeamDTO homeTeam;

    private MatchesTeamDTO awayTeam;
}
