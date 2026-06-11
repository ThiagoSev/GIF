package service;

import java.util.List;

import dao.CarrinhoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.*;
import util.Conexao;
public class CarrinhoService {
    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    public CarrinhoModel BuscarCarrinho(Usuario usuario){
        return carrinhoDAO.BuscarCarrinho(usuario);
    } 

    public void CriarCarrinho(Usuario usuario, Jogo jogoSelecionado){
        carrinhoDAO.CriarCarrinho(usuario, jogoSelecionado);
    }

    public void AdicionarJogoCarrinho(Usuario usuario, Jogo jogoSelecionado){
        carrinhoDAO.AdicionarJogo(usuario, jogoSelecionado);
    }

    public boolean RemoverJogoCarrinho(CarrinhoModel carrinho, Jogo jogoSelecionado){
        return carrinhoDAO.RemoverJogoCarrinho(carrinho, jogoSelecionado);
    }

    public List<Jogo> BuscarJogosCarrinho(Usuario usuario){
        return carrinhoDAO.BuscarJogosCarrinho(usuario);
    }
    
    public boolean ComprarCarrinho(CarrinhoModel carrinho){
        try(Connection conn = Conexao.obterConexao()){
            conn.setAutoCommit(false);
            try{
                //remove os itens do carrinho
                carrinhoDAO.RemoverTodosJogosCarrinho(carrinho, conn);

                //adiciona os itens na biblioteca
                
                
                conn.commit();
                return true;
            }
            catch (Exception e) {
                conn.rollback();
                throw e;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
