package com.rlatnfla.demo1.coremusic.application;

import com.rlatnfla.demo1.coremusic.application.dto.MusicSearchResultDto;

public interface MusicSearchAndSyncUseCase {

    MusicSearchResultDto searchAndSync(String keyword);

}
