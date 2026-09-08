package com.rlatnfla.demo1.coremusic.application;

import com.rlatnfla.demo1.common.exception.LocalDataNotFoundException;
import com.rlatnfla.demo1.coremusic.application.dto.AlbumDetailDto;
import com.rlatnfla.demo1.coremusic.application.dto.ArtistDetailDto;
import com.rlatnfla.demo1.coremusic.application.dto.LocalSearchResultDto;
import com.rlatnfla.demo1.coremusic.application.dto.TrackDetailDto;

public interface MusicSearchService {

    LocalSearchResultDto search(String keyword) throws LocalDataNotFoundException;

    ArtistDetailDto getArtistDetail(Long artistId);

    AlbumDetailDto getAlbumDetail(Long albumId);

    TrackDetailDto getTrackDetail(Long trackId);

}