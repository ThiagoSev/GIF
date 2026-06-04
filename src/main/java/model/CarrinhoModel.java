package model;

import java.time.LocalDateTime;

public class CarrinhoModel {
    public int id;
    public int idusuario;
    public double valorTotal;
    public LocalDateTime datacriacao;
    public LocalDateTime ultimaatualizacao;

    public int getId() {
        return id;
    }
}
