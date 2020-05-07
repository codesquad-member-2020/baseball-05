package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Plate {

    private int plate;

    private int out;

    private Player batter;

    private Object rounds;
}
