package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Round {

    private String decision;

    private int strike;

    private int ball;
}
