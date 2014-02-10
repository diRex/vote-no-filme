package br.com.bluesoft.votenofilme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bluesoft.votenofilme.component.votacao.Opcao;

@Entity
@Table(name = "FILME")
public class Filme implements Opcao, Serializable {
    
    private static final long serialVersionUID = 3779873935310307323L;
    
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
    
    
    public Filme() {
        
    }
    
    
    public Filme(Long id, String nome, String imagemUrl, Long totalVotos) {
        super();
        this.id = id;
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.totalVotos = totalVotos;
    }
    
    
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
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Filme other = (Filme) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
}
