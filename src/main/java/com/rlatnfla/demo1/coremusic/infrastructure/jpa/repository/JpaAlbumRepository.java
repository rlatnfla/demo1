package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.AlbumJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAlbumRepository extends JpaRepository<AlbumJpaEntity, Long> {

    @EntityGraph(attributePaths = {"artist"})
    Optional<AlbumJpaEntity> findById(Long id);

    @EntityGraph(attributePaths = {"artist"})
    List<AlbumJpaEntity> findAllByTitle(String title);

    @EntityGraph(attributePaths = {"artist"})
    List<AlbumJpaEntity> findAllByArtist(ArtistJpaEntity artist);



}
