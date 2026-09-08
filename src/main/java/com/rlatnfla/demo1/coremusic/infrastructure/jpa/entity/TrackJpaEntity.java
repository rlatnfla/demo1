package com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity;

import com.rlatnfla.demo1.coremusic.domain.Track;
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
@Table(name = "track")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TrackJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private AlbumJpaEntity album;

    public static TrackJpaEntity from(Track domain) {
        if (domain == null) {
            return null;
        }
        return new TrackJpaEntity(
            domain.getId(),
            domain.getTitle(),
            domain.getImageUrl(),
            ArtistJpaEntity.from(domain.getArtist()),
            AlbumJpaEntity.from(domain.getAlbum())
        );
    }

    public Track toDomain() {
        return new Track(
            this.id,
            this.title,
            this.imageUrl,
            this.album.toDomain(),
            this.artist.toDomain()
        );
    }
}
