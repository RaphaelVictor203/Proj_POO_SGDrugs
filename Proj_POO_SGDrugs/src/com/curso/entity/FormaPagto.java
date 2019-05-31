package com.curso.entity;

public class FormaPagto {

	private String formaPagamento;
	private double valor;
	
	public FormaPagto(String formaPagamento, double valor) {
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public double getValor() {
		return valor;
	}
	
	
	
}
