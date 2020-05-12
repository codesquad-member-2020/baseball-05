package com.codesquad.baseball05.domain;

import com.codesquad.baseball05.domain.dto.BatterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Plate {

    private int id;

    private int outs;

    private BatterDTO batter;

    private Object rounds;
}
