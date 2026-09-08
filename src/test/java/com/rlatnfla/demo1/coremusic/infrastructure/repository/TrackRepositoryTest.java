package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.domain.Track;
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
class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    private Track track1;
    private Track track2;
    private Track track3;
    private Artist defaultArtist;
    private Album defaultAlbum;

    @BeforeEach
    void setUp() {
        Artist artist = new Artist();
        artist.setName("Default Artist");
        defaultArtist = artistRepository.save(artist);

        Album album = new Album();
        album.setTitle("Default Album");
        album.setArtist(defaultArtist);
        defaultAlbum = albumRepository.save(album);

        track1 = new Track();
        track1.setTitle("track1");
        track1.setArtist(defaultArtist);
        track1.setAlbum(defaultAlbum);

        track2 = new Track();
        track2.setTitle("track2");
        track2.setArtist(defaultArtist);
        track2.setAlbum(defaultAlbum);

        track3 = new Track();
        track3.setTitle("track3");
        track3.setArtist(defaultArtist);
        track3.setAlbum(defaultAlbum);
    }

    private void saveFixtures() {
        track1 = trackRepository.save(track1);
        track2 = trackRepository.save(track2);
        track3 = trackRepository.save(track3);
    }

    @Test
    @DisplayName("새로운 트랙 저장 성공")
    void saveTrack() throws Exception {
        // given
        Track track = new Track();
        track.setArtist(defaultArtist);
        track.setAlbum(defaultAlbum);
        track.setTitle("Giorgio by Moroder");

        // when
        Track save = trackRepository.save(track);

        // then
        assertThat(save.getId()).isNotNull();
        assertThat(save.getTitle()).isEqualTo(track.getTitle());
    }

    @Test
    @DisplayName("기존 트랙 수정 후 save() 호출 시 update 성공")
    void updateTrack() throws Exception {
        // given
        saveFixtures();

        track1.setTitle("updatedTitle");

        // when
        Track updated = trackRepository.save(track1);

        // then
        assertThat(updated.getId()).isEqualTo(track1.getId());
        assertThat(updated.getTitle()).isEqualTo(track1.getTitle());
    }


    @Test
    @DisplayName("트랙 id 조회 성공")
    void findTrackById() throws Exception {
        // given
        saveFixtures();

        // when + then
        Optional<Track> foundTrack = trackRepository.findTrackById(track1.getId());

        assertThat(foundTrack)
            .isPresent()
            .hasValueSatisfying(result -> {
                assertThat(result.getId()).isEqualTo(track1.getId());
                assertThat(result.getTitle()).isEqualTo(track1.getTitle());
            } );
    }
    
    @Test
    @DisplayName("트랙 id 조회 결과 없음")
    void findTrackByIdReturnsEmptyResult() throws Exception {
        // when + then
        Optional<Track> emptyTrack = trackRepository.findTrackById(9999L);

        assertThat(emptyTrack).isEmpty();
    }

    @Test
    @DisplayName("트랙 title 조회 성공")
    void findAllTracksByTitle() throws Exception {
        // given
        saveFixtures();

        // when
        List<Track> results = trackRepository.findAllTracksByTitle(track1.getTitle());

        // then
        assertThat(results).hasSize(1)
            .extracting(Track::getTitle)
            .containsExactlyInAnyOrder(track1.getTitle());

    }

    @Test
    @DisplayName("트랙 title 조회 시 동명의 title 트랙 조회 성공")
    void findAllTracksByTitleWithSameTitleTracks() throws Exception {
        // given
        track1.setTitle("나의 그늘");
        track2.setTitle("나의 그늘");
        track3.setTitle("나의 그늘");

        saveFixtures();

        // when
        List<Track> results = trackRepository.findAllTracksByTitle("나의 그늘");

        // then
        assertThat(results).hasSize(3)
            .extracting(Track::getId, Track::getTitle)
            .containsExactlyInAnyOrder(
                tuple(track1.getId(), track1.getTitle()),
                tuple(track2.getId(), track2.getTitle()),
                tuple(track3.getId(), track3.getTitle())
            );
    }


    @Test
    @DisplayName("트랙 title 조회 결과 없음")
    void findTrackByTitleReturnsEmptyResult() throws Exception {
        // when
        List<Track> results = trackRepository.findAllTracksByTitle("windmill");

        // then
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Album 기준 조회 성공")
    void findAllTracksByAlbum() throws Exception {
        // given
        Artist artist = new Artist();
        artist.setName("Daft Punk");
        Artist targetArtist = artistRepository.save(artist);

        Album album = new Album();
        album.setTitle("Random Access Memories");
        album.setArtist(targetArtist);
        Album targetAlbum = albumRepository.save(album);

        track1.setAlbum(targetAlbum);
        track3.setAlbum(targetAlbum);

        saveFixtures();

        // when
        List<Track> results = trackRepository.findAllTracksByAlbum(targetAlbum);

        // then
        assertThat(results).hasSize(2)
            .extracting(Track::getId)
            .containsExactlyInAnyOrder(track1.getId(), track3.getId());
    }

    @Test
    @DisplayName("Artist 기준 조회 성공")
    void findAllTracksByArtist() throws Exception {
        // given
        Artist artist = new Artist();
        artist.setName("Daft Punk");
        Artist targetArtist = artistRepository.save(artist);

        track1.setArtist(targetArtist);
        track3.setArtist(targetArtist);

        saveFixtures();

        // when
        List<Track> results = trackRepository.findAllTracksByArtist(targetArtist);

        // then
        assertThat(results).hasSize(2)
            .extracting(Track::getId)
            .containsExactlyInAnyOrder(
                track1.getId(),
                track3.getId()
            );
    }




}