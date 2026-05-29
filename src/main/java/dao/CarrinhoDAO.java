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

    public boolean CriarCarrinho(Usuario usuario){
        String sql = "INSERT INTO carrinho(idusuario, datacriacao, ultimaatualizacao) VALUES( ? , ? , ? );";

        try (Connection conn = Conexao.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) 
        {
            ps.setInt(1, usuario.getId());
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int linhas = ps.executeUpdate();

            if(linhas == 0){
                return false;
            }
            return true;
        }
        catch (SQLException e) {
            System.out.println("Erro: " +e.getMessage());
            return false;
        }
    }
}
