package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.Conexao;

public class UsuarioDAO {
    public boolean BuscarUsuario(Usuario usuario){
            String sql = "SELECT * FROM usuario WHERE nome = '" + usuario.getNome() + "' AND senha = '" + usuario.getSenha()+"'";
        
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                        
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Cadastrar(Usuario usuario){

        String sql = """
                     INSERT INTO usuario
                     (nome, senha, apelido, datanascimento)
                     VALUES (?, ?, ?, ?)
                     """;

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getApelido());

            stmt.setDate(
                    4,
                    new java.sql.Date(usuario.getDataNascimento().getTime())
            );

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
