package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import com.rlatnfla.demo1.coremusic.domain.Track;
import java.util.List;
import java.util.Optional;

public interface TrackRepository {

    Optional<Track> findTrackById(Long id);

    List<Track> findAllTracksByTitle(String title);

    List<Track> findAllTracksByAlbum(Album album);

    List<Track> findAllTracksByArtist(Artist artist);

    Track save(Track track);

}
