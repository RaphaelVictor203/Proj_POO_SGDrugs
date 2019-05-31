package com.curso.entity;

public class FarmaciaProduto {

	private Produto produto;
	//private Grupo grupo;
	private int qntdEstoque;
	private double preco;
	private Farmacia farmacia;
	
	public FarmaciaProduto() {
		this.produto = new Produto();
		this.farmacia = new Farmacia();
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
