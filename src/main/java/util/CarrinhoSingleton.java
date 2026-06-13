/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

public class CarrinhoSingleton {
    private CarrinhoSingleton carrinhoSingleton;
    public CarrinhoSingleton obterCarrinho(){
        if(carrinhoSingleton == null)
            this.carrinhoSingleton = new CarrinhoSingleton();
        return this.carrinhoSingleton;
    }
}
