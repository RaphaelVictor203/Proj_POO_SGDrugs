package com.curso.entity;

public class Funcao {

	private int idFuncao;
	private String descFuncao;
	
	public int getIdFuncao() {
		return idFuncao;
	}
	public void setIdFuncao(int idFuncao) {
		this.idFuncao = idFuncao;
	}
	public String getDescFuncao() {
		return descFuncao;
	}
	public void setDescFuncao(String descFuncao) {
		this.descFuncao = descFuncao;
	}
	@Override
	public String toString() {
		return descFuncao;
	}
	
	
	
}
