package dao;

import model.*;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class CarrinhoDAO {
    
    public CarrinhoModel BuscarCarrinho(Usuario usuario){
        String sql = "SELECT * FROM carrinho WHERE idusuario = " + usuario.getId();
        
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if(!rs.next())
                return null;
            
            CarrinhoModel carrinho = new CarrinhoModel();
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

    public List<Jogo> BuscarJogosCarrinho(Usuario usuario){
        String sql = """
                SELECT jogo.* FROM jogo
                INNER JOIN carrinhoitens
                ON carrinhoitens.idjogo = jogo.id
                INNER JOIN carrinho
                ON carrinho.id = carrinhoitens.idcarrinho
                WHERE carrinho.idusuario = ?
                """;;
        
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, usuario.getId());
            
            ResultSet rs = ps.executeQuery();

            List<Jogo> jogosNoCarrinho = new ArrayList<Jogo>();
            Jogo jogoAux = new Jogo();
            while(rs.next()){
                jogoAux.setId(rs.getInt("id"));
                jogoAux.setTitulo((rs.getString("titulo")));
                jogoAux.setSubtitulo(rs.getString("subtitulo"));
                jogoAux.setDescricao(rs.getString("descricao"));
                jogoAux.setPrecoPadrao(rs.getBigDecimal("precopadrao"));
                jogoAux.setPrecoPromocao(rs.getBigDecimal("precopromocao"));
                jogoAux.setEstaEmPromocao(rs.getBoolean("estaempromocao"));
                jogoAux.setDataLancamento(rs.getObject("datalancamento", LocalDateTime.class));
                jogoAux.setIdDistribuidor(rs.getInt("iddistribuidor"));
                jogoAux.setIdCriador(rs.getInt("idcriador"));

                jogosNoCarrinho.add(jogoAux);
            }
            
            return jogosNoCarrinho;
            
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

    public int CriarCarrinhoCasoExista(int usuarioid){

        String sqlCarrinho = "INSERT INTO carrinho(idusuario, datacriacao, ultimaatualizacao)\n" +
            "VALUES (?, NOW(), NOW())\n" +
            "ON CONFLICT (idusuario) DO NOTHING\n" +
            "RETURNING id;";
        
        try(Connection conn = Conexao.obterConexao(); PreparedStatement ps = conn.prepareStatement(sqlCarrinho)){
            ps.setInt(1, usuarioid);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return 0;
                }
                return rs.getInt("id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar: " + e.getMessage());
            return 0;
        } 
    }

    //adiciona um jogo no carrinho
    public boolean AdicionarJogo(Usuario usuario, Jogo jogoSelecionado){
        //verifica se o existe um carrinho para o usuário e cria um caso não exista
        int idCarrinho = CriarCarrinhoCasoExista(usuario.getId());
        
        //caso já exista um carrinho, busca o carrinho no banco
        if(idCarrinho == 0){
            var carrinho = BuscarCarrinho(usuario);
            //caso o carrinho não exista...
            if(carrinho == null)
                return false;
            idCarrinho = carrinho.getId();
        }

        String sql = """
            INSERT INTO carrinhoitens(idcarrinho, idjogo)
            VALUES(?, ?)
            ON CONFLICT (idcarrinho, idjogo) DO NOTHING
                    """;
        
        try(Connection conn = Conexao.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idCarrinho);
            ps.setInt(2, jogoSelecionado.getId());

            int linhasafetadas = ps.executeUpdate();
            
            if(linhasafetadas == 0)
                return true;

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
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
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
