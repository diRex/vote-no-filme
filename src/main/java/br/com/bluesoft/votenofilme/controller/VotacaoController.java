package br.com.bluesoft.votenofilme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl.Comparacao;
import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.service.FilmeService;

@Controller
@RequestMapping("/votacao")
public class VotacaoController {
    
    @Autowired
    private FilmeService filmeService;
    
    
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Comparacao iniciar(HttpServletRequest request) {
        this.filmeService.loadOpcoes();
        return this.filmeService.obterComparacao();
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public void votar(Long filmeId) {
        this.filmeService.votarEm(filmeId);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/comparar", method = RequestMethod.POST)
    public Comparacao obterComparacaoCom(Long filmeId) {
        return this.filmeService.obterComparacaoCom(filmeId);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public List<Filme> listarRanking() {
        
        return this.filmeService.listRankingFilmes();
        
    }
    
}
