package com.arthur.applistacompras.model;

import java.util.ArrayList;

public class Lista {
    private String nome;
    private ArrayList<String> itens;

    public Lista(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<String>();
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public void removerItem(String item) {
        itens.remove(item);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getItens() {
        return itens;
    }
}
