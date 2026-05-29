package service;

import dao.CarrinhoDAO;
import model.*;
public class CarrinhoService {
    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    public Carrinho BuscarCarrinho(Usuario usuario){
        return carrinhoDAO.BuscarCarrinho(usuario);
    } 

    public void CriarCarrinho(Usuario usuario){
        carrinhoDAO.CriarCarrinho(usuario);
    }

    public void AdicionarJogoCarrinho(){

    }

    public void RemoverJogoCarrinho(){
        
    }
}
