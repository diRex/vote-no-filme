package br.com.bluesoft.votenofilme.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILME")
public class Filme implements Serializable {
    
    private static final long serialVersionUID = 7139577710335943106L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOME", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "IMAGEM_URL", nullable = false, length = 255)
    private String imagemUrl;
    
    @Column(name = "TOTAL_VOTOS", nullable = false)
    private Long totalVotos = 0L;
    
    
    public void incrementarVotosEm(Integer quantidade) {
        
        this.totalVotos += quantidade;
    }
    
    
    public Long getId() {
        
        return this.id;
    }
    
    
    public void setId(Long id) {
        
        this.id = id;
    }
    
    
    public String getNome() {
        
        return this.nome;
    }
    
    
    public void setNome(String nome) {
        
        this.nome = nome;
    }
    
    
    public String getImagemUrl() {
        
        return this.imagemUrl;
    }
    
    
    public void setImagemUrl(String imagemUrl) {
        
        this.imagemUrl = imagemUrl;
    }
    
    
    public Long getTotalVotos() {
        
        return this.totalVotos;
    }
    
    
    public void setTotalVotos(Long totalVotos) {
        
        this.totalVotos = totalVotos;
    }
}
