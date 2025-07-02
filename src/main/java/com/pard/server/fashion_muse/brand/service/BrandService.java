package com.pard.server.fashion_muse.brand.service;

import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.user.repository.UserRepository;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import com.pard.server.fashion_muse.userscrap.repository.UserScrapRepository;
import com.pard.server.fashion_muse.brand.repository.Brandrepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final Brandrepository brandrepository;
    private final UserRepository userrepository;
    private final UserScrapRepository userscraprepository;


}
