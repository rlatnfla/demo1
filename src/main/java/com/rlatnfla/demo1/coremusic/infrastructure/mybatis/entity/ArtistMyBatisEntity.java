package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistMyBatisEntity {

    private Long id;
    private String name;
    private String imageUrl;

    public static ArtistMyBatisEntity from(Artist domain) {
        if (domain == null) {
            return null;
        }
        return new ArtistMyBatisEntity(
            domain.getId(),
            domain.getName(),
            domain.getImageUrl()
        );
    }

    public Artist toDomain() {
        return new Artist(
            this.id,
            this.name,
            this.imageUrl
        );
    }
}
