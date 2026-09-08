package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import com.rlatnfla.demo1.coremusic.domain.Album;
import com.rlatnfla.demo1.coremusic.domain.Artist;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    Optional<Album> findAlbumById(Long id);

    List<Album> findAllAlbumsByTitle(String title);

    List<Album> findAllAlbumsByArtist(Artist artist);

    Album save(Album album);

}
