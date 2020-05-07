package com.codesquad.baseball05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {

    private String name;

    private int mount;

    private int hit;

    private boolean isPitcher;
}
