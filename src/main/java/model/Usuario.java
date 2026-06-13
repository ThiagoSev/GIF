package model;

public class Usuario {
    private int Id;
    private String Nome;
    private String Senha;
    private boolean Administrador;
    
     public Usuario() {
    }

    public Usuario(int Id, String Nome, String Senha, boolean administrador){
        this.Id = Id;
        this.Nome = Nome;
        this.Senha = Senha;
        this.Administrador = administrador;
    }

    public Usuario(String Nome, String Senha){
        this.Nome = Nome;
        this.Senha = Senha;
    }
    
    public int getId() {
        return Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }
    
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSenha() {
        return Senha;
    }
    
    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    public boolean isAdministrador() {
        return Administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.Administrador = administrador;
    }
}
