package service;

import java.util.List;

import dao.CarrinhoDAO;
import model.*;
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

    public void RemoverJogoCarrinho(CarrinhoModel carrinho, Jogo jogoSelecionado){
        carrinhoDAO.RemoverJogoCarrinho(carrinho.getId(), jogoSelecionado.getId());
    }

    public List<Jogo> BuscarJogosCarrinho(Usuario usuario){
        return carrinhoDAO.BuscarJogosCarrinho(usuario);
    }
}
