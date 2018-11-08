package com.wep.iftube.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wep.iftube.model.Video;
import com.wep.iftube.repositories.VideoRepository;

@RestController
public class VideoController {

	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping("/video")
	public Page<Video> getVideo(Pageable pageable){
		return videoRepository.findAll(pageable);
	}
	
	@PostMapping("/video")
	public Video createVideo(@Valid @RequestBody Video video) {
		return videoRepository.save(video);
	}
	
	
	
}
