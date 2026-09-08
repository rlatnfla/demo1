package com.rlatnfla.demo1.coremusic.application;

import com.rlatnfla.demo1.coremusic.domain.Track;
import com.rlatnfla.demo1.coremusic.infrastructure.MusicDataSupplier;
import com.rlatnfla.demo1.coremusic.infrastructure.dto.MusicMetadataDto;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.AlbumRepository;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.ArtistRepository;
import com.rlatnfla.demo1.coremusic.infrastructure.repository.TrackRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MusicSyncServiceImpl implements MusicSyncService {

    private final MusicDataSupplier musicDataSupplier;
    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Override
    public void syncByKeyword(String keyword) {
        MusicMetadataDto musicMetadataDto = musicDataSupplier.fetchMusicData(keyword);

        saveMusicMetadata(musicMetadataDto);
    }

    private void saveMusicMetadata(MusicMetadataDto dto) {


    }



}
