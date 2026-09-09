package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper;

import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.AlbumMyBatisEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AlbumMyBatisMapper {

    void insert(@Param("entity") AlbumMyBatisEntity entity);

    void update(@Param("entity") AlbumMyBatisEntity entity);

    Optional<AlbumMyBatisEntity> findById(Long id);

    List<AlbumMyBatisEntity> findAllByTitle(String title);

    List<AlbumMyBatisEntity> findAllByArtistId(Long id);


}
