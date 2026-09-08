package com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArtistJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    public static ArtistJpaEntity from(Artist domain) {
        if (domain == null) {
            return null;
        }
        return new ArtistJpaEntity(
            domain.getId(),
            domain.getName(),
            domain.getImageUrl()
        );
    }

    public Artist toDomain() {
        return new Artist(
            this.id,
            this.name,
            this.imageUrl);
    }
}
