package com.curso.control;

import java.util.List;

import com.curso.entity.FarmaciaProduto;

import javafx.collections.ObservableList;

public class ControlFarmaciaProduto {

	private List<FarmaciaProduto> prodsFarmacia;
	private ObservableList<FarmaciaProduto> datalistFP;
	
	public void addProdFarm(FarmaciaProduto fp) {
		this.prodsFarmacia.add(fp);
	}
	
	public ObservableList<FarmaciaProduto> getDatalistFP() {
		return datalistFP;
	}
	
	public void attProdFarm() {
		this.datalistFP.clear();
		this.datalistFP.addAll(this.prodsFarmacia);
	}
	
}
