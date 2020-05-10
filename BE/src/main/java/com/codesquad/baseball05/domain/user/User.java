package com.codesquad.baseball05.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    private Long id;

    private Long team_id;

    private String userId;

    private String email;
}
