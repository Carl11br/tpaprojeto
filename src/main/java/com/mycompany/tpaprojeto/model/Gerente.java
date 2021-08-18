package com.mycompany.tpaprojeto.model;
public class Gerente extends Caixa {
    private String senha;
    public Gerente(String senha, int matricula, String nome) {
        super(matricula, nome);
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
