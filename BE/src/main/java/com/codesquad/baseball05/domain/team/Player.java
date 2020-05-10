package com.codesquad.baseball05.domain.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    private Long id;

    private String name;

    private Double battingAverage;

    private Boolean isPitcher;

    private List<Record> records;
}
