package br.com.bluesoft.votenofilme.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.votenofilme.entity.Filme;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;

@Service
public class FilmeServiceImpl implements FilmeService {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    
    @Override
    public void votar(Long filmeId) {
        Filme filme = this.filmeRepository.findById(filmeId);
        filme.incrementarVotosEm(1);
        this.filmeRepository.saveOrUpate(filme);
    }
    
    
    @Override
    public List<Filme> listFilmes() {
        return new ArrayList<Filme>();
    }
    
}
