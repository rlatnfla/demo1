package com.rlatnfla.demo1.coremusic.infrastructure.jpa.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.domain.Track;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.AlbumJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.ArtistJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.jpa.entity.TrackJpaEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.TrackRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrackJpaRepositoryAdapter implements TrackRepository {

    private final JpaTrackRepository jpaTrackRepository;

    @Override
    public Optional<Track> findTrackById(Long id) {
        return jpaTrackRepository.findById(id)
            .map(TrackJpaEntity::toDomain);
    }

    @Override
    public List<Track> findAllTracksByTitle(String title) {
        return jpaTrackRepository.findAllByTitle(title)
            .stream()
            .map(TrackJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Track> findAllTracksByAlbum(Album album) {
        return jpaTrackRepository.findAllByAlbum(AlbumJpaEntity.from(album))
            .stream()
            .map(TrackJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Track> findAllTracksByArtist(Artist artist) {
        return jpaTrackRepository.findAllByArtist(ArtistJpaEntity.from(artist))
            .stream()
            .map(TrackJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Track save(Track track) {
        TrackJpaEntity entity = TrackJpaEntity.from(track);
        TrackJpaEntity save = jpaTrackRepository.save(entity);
        return save.toDomain();
    }
}
