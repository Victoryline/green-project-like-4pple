package org.example.restserver.repository;


import org.example.restserver.entity.Benefit;
import org.example.restserver.entity.BenefitId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, BenefitId> {
}
