package com.curso.entity;

public class Farmacia {
	private String Unidade;
	private Endereco endereco;
	private boolean status;
	private int id;
	
	public Farmacia() {
		this("");
	}
	
	public Farmacia(String _unidade) {
		this.Unidade = _unidade;
	}
	
	public String getUnidade() {
		return Unidade;
	}
	public void setUnidade(String unidade) {
		Unidade = unidade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
