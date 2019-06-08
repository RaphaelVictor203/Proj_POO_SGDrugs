package com.curso.entity;

public class Grupo {

	private int id_grupo;
	private String desc_grupo;
	
	public Grupo(int id, String desc) {
		this.id_grupo = id;
		this.desc_grupo = desc;
	}
	
	public int getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getDesc_grupo() {
		return desc_grupo;
	}
	public void setDesc_grupo(String desc_grupo) {
		this.desc_grupo = desc_grupo;
	}

	@Override
	public String toString() {
		return desc_grupo;
	}
	
}
