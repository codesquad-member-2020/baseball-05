package com.codesquad.baseball05.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Team {

    private String name;

    private int score;

    @JsonProperty("isOffense")
    private boolean isOffense;
}
