package com.curso.entity;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Fornecedor {

	private long cnpj;
	private String nome_fantasia;
	private Endereco endereco;
	private long telefone;
	private int ID;
	private Farmacia farmacia;
	
	private Button btnEditar;
	private Button btnExcluir;
	
	public Fornecedor() {
		
		this.btnEditar = new Button("Editar");
		this.btnExcluir = new Button("Excluir");
	}
	
	public Button getBtnEditar() {
		return btnEditar;
	}
	public Button getBtnExcluir() {
		return btnExcluir;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public long getCnpj() {
		return cnpj;
	}
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	@Override
	public String toString() {
		
		return getNome_fantasia();
	}	

}
