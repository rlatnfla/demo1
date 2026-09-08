package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper;

import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.TrackMyBatisEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrackMyBatisMapper {

    void insert(TrackMyBatisEntity entity);

    void update(TrackMyBatisEntity entity);

    Optional<TrackMyBatisEntity> findById(Long id);

    List<TrackMyBatisEntity> findAllByTitle(String title);

    List<TrackMyBatisEntity> findAllByAlbumId(Long albumId);

    List<TrackMyBatisEntity> findAllByArtistId(Long artistId);

}
