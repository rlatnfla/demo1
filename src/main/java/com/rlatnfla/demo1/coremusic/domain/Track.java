package com.rlatnfla.demo1.coremusic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    private Long id;
    private String title;
    private String imageUrl;
    private Album album;
    private Artist artist;



}
