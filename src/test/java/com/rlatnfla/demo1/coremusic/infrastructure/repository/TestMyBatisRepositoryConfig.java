package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.AlbumMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.ArtistMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.mapper.TrackMyBatisMapper;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository.AlbumMyBatisRepositoryAdapter;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository.ArtistMyBatisRepositoryAdapter;
import com.rlatnfla.demo1.coremusic.infrastructure.mybatis.repository.TrackMyBatisRepositoryAdapter;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@TestConfiguration
@ConditionalOnProperty(name = "app.repository.type", havingValue = "mybatis")
@MapperScan("com.rlatnfla.demo1.**.infrastructure.mybatis.mapper")
public class TestMyBatisRepositoryConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/**/*.xml")
        );

        return factoryBean.getObject();
    }

    @Bean
    public ArtistRepository artistMyBatisRepository(ArtistMyBatisMapper artistMyBatisMapper) {
        return new ArtistMyBatisRepositoryAdapter(artistMyBatisMapper);
    }

    @Bean
    public AlbumRepository albumMyBatisRepository(AlbumMyBatisMapper albumMyBatisMapper) {
        return new AlbumMyBatisRepositoryAdapter(albumMyBatisMapper);
    }

    @Bean
    public TrackRepository trackMyBatisRepository(TrackMyBatisMapper trackMyBatisMapper) {
        return new TrackMyBatisRepositoryAdapter(trackMyBatisMapper);
    }

}
