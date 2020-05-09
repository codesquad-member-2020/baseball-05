package com.codesquad.baseball05.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Matches {

    @Id
    private Long id;

    private Long aUserId;

    private Long bUserId;

    private String homeTeam;

    private String awayTeam;
}
