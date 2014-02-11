package br.com.bluesoft.votenofilme.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.service.VotacaoFilmeService;

@Controller
@RequestMapping("/votacao")
public class VotacaoFilmeController {
    
    @Autowired
    private VotacaoFilmeService votacaoFilmeService;
    
    
    @ResponseBody
    @RequestMapping(value = "/load_filmes", method = RequestMethod.POST)
    public void loadFilmes() {
        votacaoFilmeService.loadOpcoesFilmes();
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/obter_comparacao", method = RequestMethod.POST)
    public Map<Integer, Filme> obterComparacao(@RequestBody Filme filme) {
        if (filme == null || filme.getId() == null) {
            return votacaoFilmeService.getNovaComparacao();
        }
        return votacaoFilmeService.getNovaComparacaoCom(filme);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public void votar(@RequestBody Filme filme) {
        votacaoFilmeService.votarEm(filme);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public List<Filme> listarRanking() {
        return votacaoFilmeService.listRankingFilmes();
    }
    
}
