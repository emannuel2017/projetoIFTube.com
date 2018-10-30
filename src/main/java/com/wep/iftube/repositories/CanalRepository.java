package com.wep.iftube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wep.iftube.model.Canal;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Long>{

}
