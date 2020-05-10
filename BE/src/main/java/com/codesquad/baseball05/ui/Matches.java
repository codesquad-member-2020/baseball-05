package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.domain.dto.MatchTeamDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
public class Matches {

    @Id
    private Long id;

    private Boolean selectable;

    private MatchTeamDTO homeTeam;

    private MatchTeamDTO awayTeam;
}
