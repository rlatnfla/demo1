package com.rlatnfla.demo1.coremusic.infrastructure;

import com.rlatnfla.demo1.coremusic.infrastructure.dto.MusicMetadataDto;

public interface MusicDataSupplier {

    MusicMetadataDto fetchMusicData(String keyword);

}
