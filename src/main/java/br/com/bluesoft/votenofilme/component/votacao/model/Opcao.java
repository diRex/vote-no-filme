package br.com.bluesoft.votenofilme.component.votacao.model;

import java.util.HashSet;
import java.util.Set;

public class Opcao {
    
    private long id;
    
    private String label;
    
    private Set<Opcao> comparados;
    
    
    public Opcao(final long id, final String label) {
        this.id = id;
        this.label = label;
        this.comparados = new HashSet<Opcao>();
        this.comparados.add(this);
    }
    
    
    public void setComparadoCom(final Opcao opcao) {
        this.comparados.add(opcao);
    }
    
    
    public long getId() {
        return this.id;
    }
    
    
    public String getLabel() {
        return this.label;
    }
    
    
    public Set<Opcao> getComparados() {
        return this.comparados;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.id ^ (this.id >>> 32));
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
        Opcao other = (Opcao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
