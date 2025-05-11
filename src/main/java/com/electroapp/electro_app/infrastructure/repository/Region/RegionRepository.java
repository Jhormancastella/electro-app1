package com.electroapp.electro_app.infrastructure.repository.Region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electroapp.electro_app.domain.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);
    List<Region> findByCountryId_Id(Long countryId);
}