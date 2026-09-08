package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.domain.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackMyBatisEntity {

    private Long id;
    private String title;
    private String imageUrl;
    private Long albumId;
    private Long artistId;

    // join 쿼리 album 정보
    private String albumTitle;
    private String albumImageUrl;

    // join 쿼리 artist 정보
    private String artistName;
    private String artistImageUrl;

    public static TrackMyBatisEntity from(Track domain) {
        if (domain == null) {
            return null;
        }

        TrackMyBatisEntity entity = new TrackMyBatisEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setImageUrl(domain.getImageUrl());

        if (domain.getAlbum() != null) {
            entity.setAlbumId(domain.getAlbum().getId());
            entity.setAlbumTitle(domain.getAlbum().getTitle());
            entity.setAlbumImageUrl(domain.getAlbum().getImageUrl());
        }

        if (domain.getArtist() != null) {
            entity.setArtistId(domain.getArtist().getId());
            entity.setArtistName(domain.getArtist().getName());
            entity.setArtistImageUrl(domain.getArtist().getImageUrl());
        }

        return entity;
    }

    public Track toDomain() {
        Artist artist = null;
        if (this.artistId != null) {
            artist = new Artist(
                this.artistId,
                this.artistName,
                this.artistImageUrl
            );
        }

        Album album = null;
        if (this.albumId != null) {
            album = new Album(
                this.albumId,
                artist,
                this.albumTitle,
                this.albumImageUrl
            );
        }

        return new Track(
            this.id,
            this.title,
            this.imageUrl,
            album,
            artist
        );
    }

}
