package com.wep.iftube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wep.iftube.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
