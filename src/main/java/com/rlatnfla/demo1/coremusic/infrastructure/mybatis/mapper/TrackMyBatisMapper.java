package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper;

import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.TrackMyBatisEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TrackMyBatisMapper {

    void insert(@Param("entity") TrackMyBatisEntity entity);

    void update(@Param("entity") TrackMyBatisEntity entity);

    Optional<TrackMyBatisEntity> findById(Long id);

    List<TrackMyBatisEntity> findAllByTitle(String title);

    List<TrackMyBatisEntity> findAllByAlbumId(Long albumId);

    List<TrackMyBatisEntity> findAllByArtistId(Long artistId);

}
