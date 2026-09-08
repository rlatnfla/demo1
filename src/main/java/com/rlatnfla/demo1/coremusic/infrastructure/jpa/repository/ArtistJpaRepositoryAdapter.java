package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.ArtistRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtistJpaRepositoryAdapter implements ArtistRepository {

    private final JpaArtistRepository jpaArtistRepository;

    @Override
    public Optional<Artist> findArtistById(Long id) {
        return jpaArtistRepository.findById(id)
            .map(ArtistJpaEntity::toDomain);
    }

    @Override
    public List<Artist> findAllArtistsByName(String name) {
        return jpaArtistRepository.findAllByName(name)
            .stream()
            .map(ArtistJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Artist save(Artist artist) {
        ArtistJpaEntity entity = ArtistJpaEntity.from(artist);
        ArtistJpaEntity save = jpaArtistRepository.save(entity);
        return save.toDomain();
    }
}
