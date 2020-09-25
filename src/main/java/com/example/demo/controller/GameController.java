package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MovePayload;
import com.example.demo.service.GameService;

@RestController
@RequestMapping(GameController.ENDPOINT)
public class GameController {
	
	public static final String ENDPOINT="move";
	
	@Autowired
	public GameService gameService;
	
	@PostMapping
	public ResponseEntity<?> gameMove( @RequestBody MovePayload movePayload ){
		
		return ResponseEntity.ok(gameService.move(movePayload));
		
	}

}
