package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.AlbumJpaRepositoryAdapter;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.ArtistJpaRepositoryAdapter;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.JpaAlbumRepository;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.JpaArtistRepository;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.JpaTrackRepository;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository.TrackJpaRepositoryAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@ConditionalOnProperty(name = "app.repository.type", havingValue = "jpa", matchIfMissing = true)
public class TestJpaRepositoryConfig {

    @Bean
    public ArtistRepository artistJpaRepository(JpaArtistRepository jpaArtistRepository) {
        return new ArtistJpaRepositoryAdapter(jpaArtistRepository);
    }

    @Bean
    public AlbumRepository albumJpaRepository(JpaAlbumRepository jpaAlbumRepository) {
        return new AlbumJpaRepositoryAdapter(jpaAlbumRepository);
    }

    @Bean
    public TrackRepository trackJpaRepository(JpaTrackRepository jpaTrackRepository) {
        return new TrackJpaRepositoryAdapter(jpaTrackRepository);
    }

}
