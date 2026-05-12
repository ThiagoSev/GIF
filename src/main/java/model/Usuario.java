package model;

public class Usuario {
    private int Id;
    private String Nome;
    private String Senha;    

    public Usuario(int Id, String Nome, String Senha){
        this.Id = Id;
        this.Nome = Nome;
        this.Senha = Senha;
    }

    public Usuario(String Nome, String Senha){
        this.Nome = Nome;
        this.Senha = Senha;
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
}
