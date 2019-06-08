package com.curso.entity;

public class Sessao {

	private int id_sessao;
	private String desc_sessao;
	
	public Sessao(int id, String desc) {
		this.id_sessao = id;
		this.desc_sessao = desc;
	}

	
	
	public int getId_sessao() {
		return id_sessao;
	}



	public void setId_sessao(int id_sessao) {
		this.id_sessao = id_sessao;
	}



	public String getDesc_sessao() {
		return desc_sessao;
	}



	public void setDesc_sessao(String desc_sessao) {
		this.desc_sessao = desc_sessao;
	}



	@Override
	public String toString() {
		return desc_sessao;
	}
	
}
