package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matches {

    @Id
    private Long id;

    private Long userAid;

    private Long userBid;

    private String homeTeam;

    private String awayTeam;
}
