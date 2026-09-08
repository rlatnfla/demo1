package com.rlatnfla.demo1.coremusic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    private Long id;
    private String name;
    private String imageUrl;

}
