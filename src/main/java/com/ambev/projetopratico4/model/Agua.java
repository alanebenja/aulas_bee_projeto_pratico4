package com.ambev.projetopratico4.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aguas")
public class Agua extends Produto{
    private boolean comGas;

    public Agua(String id, String nome ,String descricao, Double preco, boolean comGas) {
        super(id, nome, descricao, preco);
        this.comGas = comGas;
    }

    public boolean ComGas() {
        return comGas;
    }

    public void setComGas(boolean comGas) {
        this.comGas = comGas;
    }

    @Override
    public String toString() {
        return "Agua{" +
                "comGas=" + comGas +
                '}';
    }
}
