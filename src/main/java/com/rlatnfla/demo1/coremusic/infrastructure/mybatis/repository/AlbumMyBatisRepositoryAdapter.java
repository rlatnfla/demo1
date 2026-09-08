package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.AlbumMyBatisEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.ArtistMyBatisEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.AlbumMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.AlbumRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlbumMyBatisRepositoryAdapter implements AlbumRepository {

    private final AlbumMyBatisMapper albumMyBatisMapper;

    @Override
    public Optional<Album> findAlbumById(Long id) {
        return albumMyBatisMapper.findById(id)
            .map(AlbumMyBatisEntity::toDomain);
    }

    @Override
    public List<Album> findAllAlbumsByTitle(String title) {
        return albumMyBatisMapper.findAllByTitle(title)
            .stream()
            .map(AlbumMyBatisEntity::toDomain)
            .toList();

    }

    @Override
    public List<Album> findAllAlbumsByArtist(Artist artist) {
        return albumMyBatisMapper.findAllByArtistId(artist.getId())
            .stream()
            .map(AlbumMyBatisEntity::toDomain)
            .toList();
    }

    @Override
    public Album save(Album album) {
        AlbumMyBatisEntity entity = AlbumMyBatisEntity.from(album);

        if (entity.getId() == null) {
            albumMyBatisMapper.insert(entity);
        } else {
            albumMyBatisMapper.update(entity);
        }

        return entity.toDomain();
    }
}
