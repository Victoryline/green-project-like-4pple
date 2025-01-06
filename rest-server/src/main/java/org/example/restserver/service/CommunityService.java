package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.repository.CommunityRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;



}
