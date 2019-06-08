package com.curso.control;

import java.util.ArrayList;
import java.util.List;

import com.curso.entity.FarmaciaProduto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFarmaciaProduto {

	private ObservableList<FarmaciaProduto> dataListProdFarm = FXCollections.observableArrayList();
	private List<FarmaciaProduto> listaProdFarm;

	public ControlFarmaciaProduto() {
		
		listaProdFarm = new ArrayList<FarmaciaProduto>();
		
		/*Produto prod = new Produto();
		prod.setCategoria("Perecivel");
		prod.setFornecedor(new Fornecedor());
		prod.setId_produto(1);
		prod.setNome("Coca-cola");
		
		FarmaciaProduto fp = new FarmaciaProduto();
		fp.setFarmacia(new Farmacia("Unidade Leste"));
		fp.setPreco(10.00);
		fp.setProduto(prod);
		fp.setQntdEstoque(15);
		
		listaProdFarm.add(fp);
//		attTableProdutoFarm();
		dataListProdFarm.add(fp);*/
		
	}
	
	public List<FarmaciaProduto> getListaProdFarm() {
		return listaProdFarm;
	}

	public void setListaProdFarm(List<FarmaciaProduto> listaProdFarm) {
		this.listaProdFarm = listaProdFarm;
	}

	public ObservableList<FarmaciaProduto> getDataListProdFarm() {
		return dataListProdFarm;
	}
	
	public FarmaciaProduto pesquisarFarmaciaProd(int id_prod) {
		for(FarmaciaProduto fp : listaProdFarm) {
			if(fp.getProduto().getId_produto() == id_prod) {
				return fp;
			}
		}
		return null;
	}
	
	public void pesquisarFarmaciaProd(String cont, String tipo) {
		this.dataListProdFarm.clear();
		for(FarmaciaProduto fp : listaProdFarm) {
			if(tipo.equals("NOME")) {
				if(fp.getProduto().getNome().equals(cont)) {
					dataListProdFarm.add(fp);
				}
			}else
			if(tipo.equals("CATEGORIA")) {
				if(fp.getProduto().getCategoria().equals(cont)) {
					dataListProdFarm.add(fp);
				}
			}else {
				if(fp.getProduto().getFornecedor().equals(cont)) {
					dataListProdFarm.add(fp);
				}
			}
		}
	}
	
	public void removerProdutoFarm(FarmaciaProduto fp) {
		this.listaProdFarm.remove(fp);
		this.attTableProdutoFarm();
	}
	
	public void attProdutoFarm(FarmaciaProduto f) {
		for(FarmaciaProduto fp : listaProdFarm) {
			if(fp.getProduto().getId_produto() == f.getProduto().getId_produto()) {
				fp = f;
				this.attTableProdutoFarm();
			}
		}
	}
	
	public void inserir(FarmaciaProduto fp) {
		if(pesquisarFarmaciaProd(fp.getProduto().getId_produto()) == null) {
			this.listaProdFarm.add(fp);
			this.attTableProdutoFarm();
		}
	}
	
	public void attTableProdutoFarm() {
		this.dataListProdFarm.clear();
		this.dataListProdFarm.addAll(listaProdFarm);
	}
	
	public void attTableProdutoFarm(FarmaciaProduto fp) {
		this.dataListProdFarm.clear();
		this.dataListProdFarm.addAll(fp);
	}
	
}
