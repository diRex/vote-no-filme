package br.com.bluesoft.votenofilme.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.service.FilmeService;

@Controller
@RequestMapping("/votacao")
public class VotacaoController {
    
    private static final String OPCOES = "opcoes";
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private VotacaoComponent<Filme> votacaoComponent;
    
    
    @ResponseBody
    @RequestMapping(value = "/load_filmes", method = RequestMethod.POST)
    public List<Filme> loadFilmes(HttpServletRequest request) {
        List<Filme> filmes = this.getSessionFilmes(request);
        
        if (filmes == null || filmes.isEmpty()) {
            filmes = this.filmeService.listRankingFilmes();
            request.getSession().setAttribute(OPCOES, filmes);
        }
        return filmes;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/obter_comparacao", method = RequestMethod.POST)
    public Map<Integer, Filme> obterComparacao(HttpServletRequest request) {
        
        List<Filme> filmes = this.getSessionFilmes(request);
        if (filmes == null) {
            throw new RuntimeException("A votação não foi iniciada");
        }
        
        Filme randomFilme = (Filme) this.votacaoComponent.getRandomOpcao(filmes);
        
        return (Map<Integer, Filme>) this.votacaoComponent.getNovaComparacaoCom(randomFilme, filmes);
        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/comparar", method = RequestMethod.POST)
    public void obterComparacaoCom(@RequestBody Filme filme, HttpServletRequest request) {
        this.votacaoComponent.getNovaComparacaoCom(filme, this.getSessionFilmes(request));
    }
    
    
    @SuppressWarnings("unchecked")
    private List<Filme> getSessionFilmes(HttpServletRequest request) {
        return (List<Filme>) request.getSession().getAttribute(OPCOES);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public void votar(@RequestBody Filme filme) {
        this.filmeService.votarEm(filme);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public List<Filme> listarRanking() {
        return this.filmeService.listRankingFilmes();
    }
    
}
