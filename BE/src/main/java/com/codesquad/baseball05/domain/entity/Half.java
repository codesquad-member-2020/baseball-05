package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@AllArgsConstructor
public class Half {

    @Id
    private Long id;

    private int numberOfLastBatPlayer;

    private int totalPlate;

    private int outs;

    private int hit;

    private int point;

    private List<Plate> plates;
}
