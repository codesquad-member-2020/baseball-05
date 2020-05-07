package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class MatchTable {

    @Id
    private Long id;

    private String homeTeam;

    private String awayTeam;

    private Boolean isSelected;
}
