package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import com.rlatnfla.demo1.coremusic.domain.Album;
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
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    private Album album1;
    private Album album2;
    private Album album3;
    private Artist defaultArtist;

    @BeforeEach
    void setUp() {
        Artist artist = new Artist();
        artist.setName("Default Artist");
        defaultArtist = artistRepository.save(artist);

        album1 = new Album();
        album1.setTitle("album1");
        album1.setArtist(defaultArtist);

        album2 = new Album();
        album2.setTitle("album2");
        album2.setArtist(defaultArtist);

        album3 = new Album();
        album3.setTitle("album3");
        album3.setArtist(defaultArtist);
    }

    private void saveFixtures() {
        album1 = albumRepository.save(album1);
        album2 = albumRepository.save(album2);
        album3 = albumRepository.save(album3);
    }

    @Test
    @DisplayName("새로운 앨범 저장 성공")
    void saveAlbum() throws Exception {
        // given
        Album album = new Album();
        album.setTitle("TRPP");
        album.setArtist(defaultArtist);

        // when
        Album save = albumRepository.save(album);

        // then
        assertThat(save.getId()).isNotNull();
        assertThat(save.getTitle()).isEqualTo(album.getTitle());
    }

    @Test
    @DisplayName("기존 앨범 수정 후 save() 호출 시 update 성공")
    void updateAlbum() throws Exception {
        // given
        saveFixtures();
        album1.setTitle("updatedTitle");

        // when
        Album updated = albumRepository.save(album1);

        // then
        assertThat(updated.getTitle()).isEqualTo(album1.getTitle());
    }

    @Test
    @DisplayName("앨범 id 조회 성공")
    void findAlbumById() throws Exception {
        // given
        saveFixtures();

        // when
        Optional<Album> foundAlbum = albumRepository.findAlbumById(album1.getId());

        // then
        assertThat(foundAlbum)
            .isPresent()
            .hasValueSatisfying(result -> {
                assertThat(result.getId()).isEqualTo(album1.getId());
                assertThat(result.getTitle()).isEqualTo(album1.getTitle());
            });
    }

    @Test
    @DisplayName("앨범 id 조회 결과 없음")
    void findAlbumByIdReturnsEmptyResult() throws Exception {
        // when
        Optional<Album> emptyAlbum = albumRepository.findAlbumById(9999L);

        // then
        assertThat(emptyAlbum).isEmpty();
    }

    @Test
    @DisplayName("앨범 title 조회 성공")
    void findAllAlbumsByTitle() throws Exception {
        // given
        saveFixtures();

        // when
        List<Album> results = albumRepository.findAllAlbumsByTitle(album1.getTitle());

        // then
        assertThat(results).hasSize(1)
            .extracting(Album::getTitle)
            .containsExactlyInAnyOrder(album1.getTitle());
    }

    @Test
    @DisplayName("앨범 title 조회 시 동명의 title 앨범 조회 성공")
    void findAllAlbumsByTitleWithSameTitleAlbums() throws Exception {
        // given
        album1.setTitle("TRPP");
        album2.setTitle("TRPP");
        album3.setTitle("TRPP");
        saveFixtures();

        // when
        List<Album> results = albumRepository.findAllAlbumsByTitle("TRPP");

        // then
        assertThat(results).hasSize(3)
            .extracting(Album::getId, Album::getTitle)
            .containsExactlyInAnyOrder(
                tuple(album1.getId(), album1.getTitle()),
                tuple(album2.getId(), album2.getTitle()),
                tuple(album3.getId(), album3.getTitle())
            );
    }

    @Test
    @DisplayName("앨범 title 조회 결과 없음")
    void findAllAlbumsByTitleReturnsEmptyResult() throws Exception {
        // when
        List<Album> results = albumRepository.findAllAlbumsByTitle("TRPP");

        // then
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Artist 기준 조회 성공")
    void findAllAlbumsByArtist() throws Exception {
        // given
        Artist artist = new Artist();
        artist.setName("Kanye West");
        Artist targetArtist = artistRepository.save(artist);
        album1.setArtist(targetArtist);
        album3.setArtist(targetArtist);

        saveFixtures();

        // when
        List<Album> results = albumRepository.findAllAlbumsByArtist(targetArtist);

        // then
        assertThat(results).hasSize(2)
            .extracting(Album::getId)
            .containsExactlyInAnyOrder(
                album1.getId(),
                album3.getId()
            );
    }

}