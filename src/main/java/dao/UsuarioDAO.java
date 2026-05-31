package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.Conexao;

public class UsuarioDAO {
    public Usuario BuscarUsuario(Usuario usuario) {

    String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

    try (
        Connection conn = Conexao.obterConexao();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getSenha());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Usuario usuarioEncontrado = new Usuario();

            usuarioEncontrado.setId(rs.getInt("id"));
            usuarioEncontrado.setNome(rs.getString("nome"));
            usuarioEncontrado.setSenha(rs.getString("senha"));

            usuarioEncontrado.setAdministrador(
                    rs.getBoolean("administrador"));

            return usuarioEncontrado;
        }

        return null;

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
}
