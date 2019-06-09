package com.curso.entity;

import javafx.scene.control.Button;

public class FarmaciaProduto {

	private Produto produto;
	private Grupo grupo;
	private Sessao sessao;
	private int qntdEstoque;
	private double preco;
	private Farmacia farmacia;
	
	private Button btnIsencao;
	private Button btnExcluir;

	public FarmaciaProduto() {
		this.produto = new Produto();
		this.farmacia = new Farmacia();
		this.btnExcluir = new Button("Excluir");
		this.btnIsencao = new Button("SUS");
	}	
	
	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo g) {
		this.grupo = g;
	}

	public Button getBtnIsencao() {
		return btnIsencao;
	}

	public Button getBtnExcluir() {
		return btnExcluir;
	}
	
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQntdEstoque() {
		return qntdEstoque;
	}
	public void setQntdEstoque(int qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
