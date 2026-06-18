package model;

import java.util.Date;

public class Usuario {
    private int Id;
    private String Nome;
    private String Senha;
    private String Apelido;
    private Date DataNascimento;

    public Usuario(int Id, String Nome, String Senha){
        this.Id = Id;
        this.Nome = Nome;
        this.Senha = Senha;
    }

    public Usuario(String Nome, String Senha){
        this.Nome = Nome;
        this.Senha = Senha;
    }

    public Usuario(String Nome, String Senha, String Apelido, Date DataNascimento){
        this.Nome = Nome;
        this.Senha = Senha;
        this.Apelido = Apelido;
        this.DataNascimento = DataNascimento;
    }
    
    public int getId() {
        return Id;
    }

    public String getNome() {
        return Nome;
    }

    public String getSenha() {
        return Senha;
    }

    public String getApelido() {
        return Apelido;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }
}
