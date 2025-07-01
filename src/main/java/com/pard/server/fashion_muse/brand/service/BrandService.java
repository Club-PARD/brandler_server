package com.pard.server.fashion_muse.brand.service;

import com.pard.server.fashion_muse.User.repository.UserRepository;
import com.pard.server.fashion_muse.UserScrap.repository.UserScrapRepository;
import com.pard.server.fashion_muse.brand.repository.Brandrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final Brandrepository brandrepository;
    private final UserRepository userrepository;
    private final UserScrapRepository userscraprepository;


}
