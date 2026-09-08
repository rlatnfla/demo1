package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.ArtistMyBatisEntity;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.ArtistMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.ArtistRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtistMyBatisRepositoryAdapter implements ArtistRepository {

    private final ArtistMyBatisMapper artistMyBatisMapper;

    @Override
    public Optional<Artist> findArtistById(Long id) {
        return artistMyBatisMapper.findById(id)
            .map(ArtistMyBatisEntity::toDomain);
    }

    @Override
    public List<Artist> findAllArtistsByName(String name) {
        return artistMyBatisMapper.findAllByName(name)
            .stream()
            .map(ArtistMyBatisEntity::toDomain)
            .toList();
    }

    @Override
    public Artist save(Artist artist) {
        ArtistMyBatisEntity entity = ArtistMyBatisEntity.from(artist);

        if (entity.getId() == null) {
            artistMyBatisMapper.insert(entity);
        } else {
            artistMyBatisMapper.update(entity);
        }

        return entity.toDomain();
    }
}
