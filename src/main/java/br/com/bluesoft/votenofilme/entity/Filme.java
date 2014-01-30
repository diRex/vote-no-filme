package br.com.bluesoft.votenofilme.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Filme implements Serializable {
	
	private static final long serialVersionUID = 7139577710335943106L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String imagemUrl;
	
	private Long totalVotos;
	
	public void incrementarVotosEm(Integer quantidade) {
		this.totalVotos += quantidade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public Long getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(Long totalVotos) {
		this.totalVotos = totalVotos;
	}
}
