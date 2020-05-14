package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BatterDTO {

    private String name;

    private int mounts;

    private int hits;
}
