package com.rlatnfla.demo1.coremusic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private Long id;
    private Artist artist;
    private String title;
    private String imageUrl;

}
