package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Inning {

    private final int inning;

    private final String half;

    private final String offenseOrDefense;
}
