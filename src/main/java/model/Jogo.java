/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Jogo {
   private int id;
    private String titulo;
    private String subtitulo;
    private String descricao;
    private BigDecimal precoPadrao;
    private BigDecimal precoPromocao;
    private boolean estaEmPromocao;
    private LocalDateTime dataLancamento;
    private int idDistribuidor;
    private int idCriador;
    private String imagem;
    
    public Jogo() {
    }

    public Jogo(String titulo, String descricao, BigDecimal precoPadrao) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.precoPadrao = precoPadrao;
    }
    
    public Jogo(String titulo, String subtitulo, String descricao,
                BigDecimal precoPadrao, BigDecimal precoPromocao,
                boolean estaEmPromocao, LocalDateTime dataLancamento,
                int idDistribuidor, int idCriador, String imagem) {

        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descricao = descricao;
        this.precoPadrao = precoPadrao;
        this.precoPromocao = precoPromocao;
        this.estaEmPromocao = estaEmPromocao;
        this.dataLancamento = dataLancamento;
        this.idDistribuidor = idDistribuidor;
        this.idCriador = idCriador;
        this.imagem = imagem;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoPadrao() {
        return precoPadrao;
    }

    public void setPrecoPadrao(BigDecimal precoPadrao) {
        this.precoPadrao = precoPadrao;
    }

    public BigDecimal getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(BigDecimal precoPromocao) {
        this.precoPromocao = precoPromocao;
    }

    public boolean isEstaEmPromocao() {
        return estaEmPromocao;
    }

    public void setEstaEmPromocao(boolean estaEmPromocao) {
        this.estaEmPromocao = estaEmPromocao;
    }

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public int getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(int idCriador) {
        this.idCriador = idCriador;
    }
    
       public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
