package com.codesquad.baseball05.domain.team.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player {

    @Id
    private Long id;

    private int lineUp;

    private String name;

    private Double battingAverage;

    private Boolean isPitcher;

    private List<Record> records;
}
