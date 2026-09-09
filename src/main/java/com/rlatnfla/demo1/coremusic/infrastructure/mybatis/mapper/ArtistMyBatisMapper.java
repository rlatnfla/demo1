package com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper;

import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.entity.ArtistMyBatisEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistMyBatisMapper {

    void insert(@Param("entity") ArtistMyBatisEntity entity);

    void update(@Param("entity") ArtistMyBatisEntity entity);

    Optional<ArtistMyBatisEntity> findById(Long id);

    List<ArtistMyBatisEntity> findAllByName(String name);

}
