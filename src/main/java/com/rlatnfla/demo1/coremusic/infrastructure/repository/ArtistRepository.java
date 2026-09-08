package com.rlatnfla.demo1.coremusic.infrastructure.repository;

import com.rlatnfla.demo1.coremusic.domain.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistRepository {

    Optional<Artist> findArtistById(Long id);

    List<Artist> findAllArtistsByName(String name);

    Artist save(Artist artist);

}
