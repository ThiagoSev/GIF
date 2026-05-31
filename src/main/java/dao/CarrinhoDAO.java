package dao;

import model.*;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class CarrinhoDAO {
    
    public Carrinho BuscarCarrinho(Usuario usuario){
        String sql = "SELECT * FROM carrinho WHERE idusuario = " + usuario.getId();
        
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Carrinho carrinho = new Carrinho();
            carrinho.id = rs.getInt("id");
            carrinho.idusuario = rs.getInt("idusuario");
            carrinho.datacriacao = rs.getObject("datacriacao", LocalDateTime.class);
            carrinho.ultimaatualizacao = rs.getObject("ultimaatualizacao", LocalDateTime.class);
            
            return carrinho;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean CriarCarrinho(Usuario usuario, Jogo jogoSelecionado){
        
        String sqlCarrinho =
            "INSERT INTO carrinho(idusuario, datacriacao, ultimaatualizacao) " +
            "VALUES (?, ?, ?)" +
            "RETUNNING id";

        String sqlItemCarrinho =
            "INSERT INTO carrinhoitens(idcarrinho, idjogo) " +
            "VALUES (?, ?)";

        try (Connection conn = Conexao.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement psCarrinho = conn.prepareStatement(sqlCarrinho)) {

                Timestamp agora = new Timestamp(System.currentTimeMillis());

                psCarrinho.setInt(1, usuario.getId());
                psCarrinho.setTimestamp(2, agora);
                psCarrinho.setTimestamp(3, agora);

                psCarrinho.executeUpdate();

                int idCarrinho;

                try (ResultSet rs = psCarrinho.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Não foi possível obter o ID do carrinho.");
                    }

                    idCarrinho = rs.getInt("id");
                }
                try (PreparedStatement psItem = conn.prepareStatement(sqlItemCarrinho)) {

                    psItem.setInt(1, idCarrinho);
                    psItem.setInt(2, jogoSelecionado.getId());
                    
                    psCarrinho.executeUpdate();
                }

                conn.commit();
                return true;
            }
            catch (Exception e) {
                conn.rollback();
                throw e;
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
        
    }

    public void AdicionarJogo(Carrinho carrinhoSelecionado, Jogo jogoSelecionado){
        String sql = """
            INSERT INTO carrinhoitens(idcarrinho, idjogo)
            VALUES($, $)
                    """;;

        try(Connection conn = Conexao.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, carrinhoSelecionado.getId());
            ps.setInt(2, jogoSelecionado.getId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    //Remove um jogo do carrinho
    public void RemoverJogoCarrinho(int idJogo, int idCarrinho) {
        String sql = "DELETE FROM carrinhoitens WHERE idjogo = ? AND idcarrinho = ?";

        try (
            Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idJogo);
            stmt.setInt(2, idCarrinho);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Jogo removido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
