package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.AlbumJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.AlbumRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlbumJpaRepositoryAdapter implements AlbumRepository {

    private final JpaAlbumRepository jpaAlbumRepository;

    @Override
    public Optional<Album> findAlbumById(Long id) {
        return jpaAlbumRepository.findById(id)
            .map(AlbumJpaEntity::toDomain);
    }

    @Override
    public List<Album> findAllAlbumsByTitle(String title) {
        return jpaAlbumRepository.findAllByTitle(title)
            .stream()
            .map(AlbumJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Album> findAllAlbumsByArtist(Artist artist) {
        return jpaAlbumRepository.findAllByArtist(ArtistJpaEntity.from(artist))
            .stream()
            .map(AlbumJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Album save(Album album) {
        AlbumJpaEntity entity = AlbumJpaEntity.from(album);
        AlbumJpaEntity save = jpaAlbumRepository.save(entity);
        return save.toDomain();
    }
}
