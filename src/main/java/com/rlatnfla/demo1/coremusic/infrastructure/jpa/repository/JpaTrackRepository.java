package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.AlbumJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.TrackJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTrackRepository extends JpaRepository<TrackJpaEntity, Long> {

    @EntityGraph(attributePaths = {"artist", "album"})
    Optional<TrackJpaEntity> findById(Long id);

    @EntityGraph(attributePaths = {"artist", "album"})
    List<TrackJpaEntity> findAllByTitle(String title);

    @EntityGraph(attributePaths = {"artist", "album"})
    List<TrackJpaEntity> findAllByAlbum(AlbumJpaEntity album);

    @EntityGraph(attributePaths = {"artist", "album"})
    List<TrackJpaEntity> findAllByArtist(ArtistJpaEntity artist);

}
