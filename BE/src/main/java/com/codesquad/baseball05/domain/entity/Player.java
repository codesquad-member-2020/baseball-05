package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@AllArgsConstructor
public class Player {

    @Id
    private Long id;

    private String name;

    private Double battingAverage;

    private Boolean isPitcher;

    private List<Record> records;
}
