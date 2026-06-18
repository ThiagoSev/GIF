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


public class JogoDAO {
    public boolean salvar(Jogo jogo, Usuario usuario) {
        
        if (!usuario.isAdministrador()) {
            System.out.println("Acesso negado. Apenas administradores podem cadastrar jogos.");
            return false;
        }

        String sql = """
            INSERT INTO jogo
            (titulo, subtitulo, descricao, precopadrao,
             precopromocao, estaempromocao,
             datalancamento, iddistribuidor, idcriador, imagem)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            
        try (
            Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, jogo.getTitulo());
            stmt.setString(2, jogo.getSubtitulo());
            stmt.setString(3, jogo.getDescricao());
            stmt.setBigDecimal(4, jogo.getPrecoPadrao());
            stmt.setBigDecimal(5, jogo.getPrecoPromocao());
            stmt.setBoolean(6, jogo.isEstaEmPromocao());

            stmt.setTimestamp(
                7,
                java.sql.Timestamp.valueOf(jogo.getDataLancamento())
            );

            stmt.setInt(8, jogo.getIdDistribuidor());
            stmt.setInt(9, jogo.getIdCriador());
            stmt.setString(10, jogo.getImagem());
            
            stmt.executeUpdate();

            System.out.println("Jogo salvo com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }
    public void remover(int idJogo, Usuario usuario) {

        if (!usuario.isAdministrador()) {
            System.out.println("Acesso negado. Apenas administradores podem remover jogos.");
            return;
        }

        String sql = "DELETE FROM jogo WHERE id = ?";

        try (
            Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idJogo);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Jogo removido com sucesso!");
            } else {
                System.out.println("Jogo não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public List<Jogo> listarJogos() {

        List<Jogo> jogos = new ArrayList<>();

        String sql = "SELECT * FROM jogo";

        try (
            Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Jogo jogo = new Jogo();

                jogo.setId(rs.getInt("id"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setSubtitulo(rs.getString("subtitulo"));
                jogo.setPrecoPadrao(rs.getBigDecimal("precopadrao"));

                jogos.add(jogo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jogos;
    }
    
    public Jogo buscarPorId(int id) {

    String sql = "SELECT * FROM jogo WHERE id = ?";

    try (
        Connection conn = Conexao.obterConexao();
        PreparedStatement stmt = conn.prepareStatement(sql)
    ) {

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            Jogo jogo = new Jogo();

            jogo.setId(rs.getInt("id"));
            jogo.setTitulo(rs.getString("titulo"));
            jogo.setSubtitulo(rs.getString("subtitulo"));
            jogo.setDescricao(rs.getString("descricao"));
            jogo.setPrecoPadrao(rs.getBigDecimal("precopadrao"));
            Timestamp timestamp = rs.getTimestamp("datalancamento");
            jogo.setImagem(rs.getString("imagem"));
            
        if (timestamp != null) {
            jogo.setDataLancamento(
                timestamp.toLocalDateTime()
            );
        }

            return jogo;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}
