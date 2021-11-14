package com.euro.prospects.dao;

import com.euro.prospects.entities.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, String> {
}
