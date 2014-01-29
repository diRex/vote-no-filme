package br.com.bluesoft.votenofilme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.bluesoft.votenofilme.service.FilmeService;


@Controller
public class FilmeController {
	
	@Autowired
	private FilmeService filmeService;
	
	public void votar(Long filmeId) {
		
		
		
	}
	
}
