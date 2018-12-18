package com.wep.iftube.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wep.iftube.model.Canal;
import com.wep.iftube.model.Playlist;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Long>{

	public Page<Canal> findByNome(String nome,Pageable pageable);
	public  Page<Canal> findByEmail(String email,Pageable pageable);
	public	Page<Canal> findBySenha(String senha,Pageable pageable);
	
	public	Optional<Canal> findByEmail(String email);
	public	Canal findBySenha(String senha);

	public	Canal findByEmailAndSenha(String nome, String senha);


}
