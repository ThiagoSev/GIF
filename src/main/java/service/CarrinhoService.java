package service;

import dao.CarrinhoDAO;
import model.*;
public class CarrinhoService {
    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    public Carrinho BuscarCarrinho(Usuario usuario){
        return carrinhoDAO.BuscarCarrinho(usuario);
    } 

    public void CriarCarrinho(Usuario usuario, Jogo jogoSelecionado){
        carrinhoDAO.CriarCarrinho(usuario, jogoSelecionado);
    }

    public void AdicionarJogoCarrinho(Carrinho carrinho, Jogo jogoSelecionado){
        carrinhoDAO.AdicionarJogo(carrinho, jogoSelecionado);
    }

    public void RemoverJogoCarrinho(Carrinho carrinho, Jogo jogoSelecionado){
        carrinhoDAO.RemoverJogoCarrinho(carrinho.getId(), jogoSelecionado.getId());
    }
}
