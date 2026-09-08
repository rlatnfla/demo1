package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArtistRepository extends JpaRepository<ArtistJpaEntity, Long> {

    List<ArtistJpaEntity> findAllByName(String name);

}