package com.wep.iftube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wep.iftube.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
