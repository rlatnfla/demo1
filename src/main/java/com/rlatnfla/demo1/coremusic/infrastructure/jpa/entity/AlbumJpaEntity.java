package com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity;

import com.rlatnfla.demo1.coremusic.domain.Album;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "album")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AlbumJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artist;

    public static AlbumJpaEntity from(Album domain) {
        if (domain == null) {
            return null;
        }
        return new AlbumJpaEntity(
            domain.getId(),
            domain.getTitle(),
            domain.getImageUrl(),
            ArtistJpaEntity.from(domain.getArtist())
        );
    }

    public Album toDomain() {
        return new Album(
            this.id,
            this.artist.toDomain(),
            this.title,
            this.imageUrl
        );
    }
}
