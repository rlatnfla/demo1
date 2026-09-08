package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Import({TestJpaRepositoryConfig.class, TestMyBatisRepositoryConfig.class})
@Transactional
class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist1;
    private Artist artist2;
    private Artist artist3;

    @BeforeEach
    void setUp() {
        artist1 = new Artist();
        artist1.setName("artist1");

        artist2 = new Artist();
        artist2.setName("artist2");

        artist3 = new Artist();
        artist3.setName("artist3");
    }

    private void saveFixtures() {
        artist1 = artistRepository.save(artist1);
        artist2 = artistRepository.save(artist2);
        artist3 = artistRepository.save(artist3);
    }

    @Test
    @DisplayName("새로운 아티스트 저장 성공")
    void saveArtist() throws Exception {
        // given
        Artist artist = new Artist();
        artist.setName("B-FREE");

        // when
        Artist save = artistRepository.save(artist);

        // then
        assertThat(save.getId()).isNotNull();
        assertThat(save.getName()).isEqualTo(artist.getName());
    }
    
    @Test
    @DisplayName("기존 아티스트 수정 후 save() 호출 시 업데이트 성공")
    void updateArtist() throws Exception {
        // given
        saveFixtures();
        artist1.setName("Parannoul");

        // when
        Artist updated = artistRepository.save(artist1);

        // then
        assertThat(updated.getName()).isEqualTo(artist1.getName());
    }

    @Test
    @DisplayName("아티스트 id 조회 성공")
    void findArtistById() throws Exception {
        // given
        saveFixtures();

        // when
        Optional<Artist> foundArtist = artistRepository.findArtistById(artist1.getId());

        // then
        assertThat(foundArtist)
            .isPresent()
            .hasValueSatisfying(result -> {
                assertThat(result.getId()).isEqualTo(artist1.getId());
                assertThat(result.getName()).isEqualTo(artist1.getName());
            });
    }

    @Test
    @DisplayName("아티스트 id 조회 결과 없음")
    void findArtistByIdReturnsEmptyResult() throws Exception {
        // when
        Optional<Artist> emptyArtist = artistRepository.findArtistById(9999L);

        // then
        assertThat(emptyArtist).isEmpty();
    }

    @Test
    @DisplayName("아티스트 name 조회 성공")
    void findAllArtistsByName() throws Exception {
        // given
        saveFixtures();

        // when
        List<Artist> results = artistRepository.findAllArtistsByName(artist1.getName());

        // then
        assertThat(results).hasSize(1)
            .extracting(Artist::getName)
            .containsExactlyInAnyOrder(artist1.getName());
    }
    
    @Test
    @DisplayName("아티스트 name 조회 시 동명의 name 아티스트 조회 성공")
    void findAllArtistsByNameWithSameNameArtists() throws Exception {
        // given
        artist1.setName("Sultan of the Disco");
        artist2.setName("Sultan of the Disco");
        artist3.setName("Sultan of the Disco");
        saveFixtures();
        
        // when
        List<Artist> results = artistRepository.findAllArtistsByName(
            "Sultan of the Disco");

        // then
        assertThat(results).hasSize(3)
            .extracting(Artist::getId, Artist::getName)
            .containsExactlyInAnyOrder(
                tuple(artist1.getId(), artist1.getName()),
                tuple(artist2.getId(), artist2.getName()),
                tuple(artist3.getId(), artist3.getName())
            );
    }
    
    @Test
    @DisplayName("아티스트 name 조회 결과 없음")
    void findAllArtistsByNameReturnsEmptyResult() throws Exception {
        // when
        List<Artist> results = artistRepository.findAllArtistsByName("E SENS");

        // then
        assertThat(results).isEmpty();
    }



    


}