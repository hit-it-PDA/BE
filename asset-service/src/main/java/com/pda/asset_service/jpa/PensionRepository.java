package com.pda.asset_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PensionRepository extends JpaRepository<Pension, String> {
    Optional<List<Pension>> findByAssetUserId(int userId);
}