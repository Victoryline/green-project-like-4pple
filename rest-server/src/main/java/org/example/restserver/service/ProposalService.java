package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.entity.Proposal;
import org.example.restserver.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : ProposalService
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    //제안서 저장
    public Proposal saveProposal(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

}
