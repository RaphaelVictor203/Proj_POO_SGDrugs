package com.curso.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearMonthDV;

import java.sql.Date;
import java.time.YearMonth;

import javafx.scene.control.Button;

public class Cliente {
	
	private String primeiroNome;
	private Date dt_nasc;
	private long rg, cpf, telefone;
	private String email;
	private long cartaoSUS;
	private char sexo;
	private Endereco end;
	private List<ProblemaSaude> problemasSaude;
	private int id;
	
	private Button btnEditar;
	private Button btnExcluir;
	
	public Cliente() {
		this.btnEditar = new Button("Editar");
		this.btnExcluir = new Button("Excluir");
		this.problemasSaude = new ArrayList<ProblemaSaude>();
		this.end = new Endereco();
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public Button getBtnEditar() {
		return btnEditar;
	}
	public Button getBtnExcluir() {
		return btnExcluir;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
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
	public long getCartaoSUS() {
		return cartaoSUS;
	}
	public void setCartaoSUS(long cartaoSUS) {
		this.cartaoSUS = cartaoSUS;
	}
	public Endereco getEnd() {
		return end;
	}
	public void setEnd(Endereco end) {
		this.end = end;
	}
	public List<ProblemaSaude> getProblemasSaude() {
		return this.problemasSaude;
	}
	public void setProblemasSaude(List<ProblemaSaude> problemasSaude) {
		if(problemasSaude != null) {
			this.problemasSaude = problemasSaude;
		}
	}
	
	public boolean existProb(int id) {
		for(ProblemaSaude ps : problemasSaude) {
			if(ps.getId_problema() == id) {
				return true;
			}
		}
		return false;
	}
	
	public int calcIdade() {
		 return (YearMonth.now().getYear() - dt_nasc.getYear());
	}
	
}
