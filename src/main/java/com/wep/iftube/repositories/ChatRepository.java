package com.wep.iftube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wep.iftube.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

}
