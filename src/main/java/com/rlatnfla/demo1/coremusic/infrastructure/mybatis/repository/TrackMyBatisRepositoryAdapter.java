package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.domain.Track;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.TrackMyBatisEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.TrackMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.TrackRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrackMyBatisRepositoryAdapter implements TrackRepository {

    private final TrackMyBatisMapper trackMyBatisMapper;

    @Override
    public Optional<Track> findTrackById(Long id) {
        return trackMyBatisMapper.findById(id)
            .map(TrackMyBatisEntity::toDomain);
    }

    @Override
    public List<Track> findAllTracksByTitle(String title) {
        return trackMyBatisMapper.findAllByTitle(title)
            .stream()
            .map(TrackMyBatisEntity::toDomain)
            .toList();
    }

    @Override
    public List<Track> findAllTracksByAlbum(Album album) {
        return trackMyBatisMapper.findAllByAlbumId(album.getId())
            .stream()
            .map(TrackMyBatisEntity::toDomain)
            .toList();
    }

    @Override
    public List<Track> findAllTracksByArtist(Artist artist) {
        return trackMyBatisMapper.findAllByArtistId(artist.getId())
            .stream()
            .map(TrackMyBatisEntity::toDomain)
            .toList();
    }

    @Override
    public Track save(Track track) {
        TrackMyBatisEntity entity = TrackMyBatisEntity.from(track);

        if (entity.getId() == null) {
            trackMyBatisMapper.insert(entity);
        } else {
            trackMyBatisMapper.update(entity);
        }

        return entity.toDomain();
    }
}
