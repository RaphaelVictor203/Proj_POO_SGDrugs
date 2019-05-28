package com.curso.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.Button;

public class Cliente extends Pessoa{
	
	
	private long cartaoSUS;
	
	private List<ProblemaSaude> problemasSaude;
	
	private Button btnEditar;
	private Button btnExcluir;
	public Cliente() {
		
		this.btnEditar = new Button("Editar");
		this.btnExcluir = new Button("Excluir");
		this.problemasSaude = new ArrayList<ProblemaSaude>();
	}
	

	
	public Button getBtnEditar() {
		return btnEditar;
	}
	public Button getBtnExcluir() {
		return btnExcluir;
	}
	
	
	public long getCartaoSUS() {
		return cartaoSUS;
	}
	public void setCartaoSUS(long cartaoSUS) {
		this.cartaoSUS = cartaoSUS;
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
	
	
}
