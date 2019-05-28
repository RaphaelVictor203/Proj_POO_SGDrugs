package com.curso.entity;

import java.util.Date;

public  class Pessoa {
	
	private int ID;
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	private String primeiroNome;
	private Date dt_nasc;
	private long rg, cpf, telefone;
	private String email;
	private char sexo;
	private Endereco end;
	

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getPrimeiroNome(String primeiroNome) {
		return this.primeiroNome;
	}
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public long getRg() {
		return rg;
	}
	public void setRg(long rg) {
		this.rg = rg;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
