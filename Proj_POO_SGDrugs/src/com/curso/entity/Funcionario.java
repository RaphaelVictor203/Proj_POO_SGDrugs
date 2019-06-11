package com.curso.entity;

import javafx.scene.control.Button;

public class Funcionario extends Pessoa{
	
	private long cpf;
	private String nome;
	private String sobrenome;
	private Funcao funcao;
	private Farmacia farmacia;
	private Endereco endereco;
	private float salario;
	private Button btnExcluir = new Button("Excluir");
	
	
	
	public Button getBtnExcluir() {
		return btnExcluir;
	}
	public void setBtnExcluir(Button btnExcluir) {
		this.btnExcluir = btnExcluir;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	
	
}
