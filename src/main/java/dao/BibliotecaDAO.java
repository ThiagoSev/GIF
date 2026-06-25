package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.Conexao;
import java.sql.Timestamp;

public class BibliotecaDAO {
    

    public boolean inserirJogosBiblioteca(List<Jogo> listaJogos, Usuario usuario) {

        String sql = """
            INSERT INTO bibliotecajogos
            (iddono, idjogo, dataaquisicao, tempodejogo)
            VALUES (?, ?, ?, ?)
            ON CONFLICT (iddono, idjogo) DO NOTHING;
            """;

        try (
            Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            for (Jogo jogo : listaJogos) {

                stmt.setInt(1, usuario.getId());
                stmt.setInt(2, jogo.getId());
                stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(4, 0);

                stmt.addBatch();
            }

            stmt.executeBatch();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
