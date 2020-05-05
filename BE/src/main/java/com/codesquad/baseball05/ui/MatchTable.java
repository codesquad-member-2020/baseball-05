package com.codesquad.baseball05.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
public class MatchTable {

    @Id
    private Long id;

    private String homeTeam;

    private String awayTeam;

    private Boolean selectable;
}
