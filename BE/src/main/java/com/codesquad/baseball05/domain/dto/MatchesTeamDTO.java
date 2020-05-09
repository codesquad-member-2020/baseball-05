package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MatchesTeamDTO {

    private String teamName;

    private String userName;
}
