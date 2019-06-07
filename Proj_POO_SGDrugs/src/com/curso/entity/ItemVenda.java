package com.curso.entity;

public class ItemVenda {
	
	private int qntd;
	private FarmaciaProduto produto;
	private double subtotal;
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public int getQntd() {
		return qntd;
	}
	public void setQntd(int qntd) {
		this.qntd = qntd;
	}
	public FarmaciaProduto getProduto() {
		return produto;
	}
	public void setProduto(FarmaciaProduto produto) {
		this.produto = produto;
	}
	
	public void aplicarIsencao() {
		this.subtotal -= this.produto.getPreco();
	}
	
	public void removerIsencao() {
		this.subtotal += this.produto.getPreco();
	}
	
	public void calcSubTotal() {
		subtotal = this.produto.getPreco() * this.qntd;
	}
	
}