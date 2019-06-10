package com.curso.control;

import java.util.ArrayList;
import java.util.List;

import com.curso.dao.DAOException;
import com.curso.dao.FarmaciaProdutoDAO;
import com.curso.dao.FarmaciaProdutoDAOImpl;
import com.curso.entity.FarmaciaProduto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFarmaciaProduto {

	private FarmaciaProdutoDAO fpdi = new FarmaciaProdutoDAOImpl();
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
		attTableProdutoFarm();
		return dataListProdFarm;
	}
	
	public FarmaciaProduto pesquisarFarmaciaProd(int id_prod) {
		/*for(FarmaciaProduto fp : listaProdFarm) {
			if(fp.getProduto().getId_produto() == id_prod) {
				return fp;
			}
		}*/
		try {
			return fpdi.pesquisarFarmaciaProduto(id_prod);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			fpdi.pesquisarFarmaciaProduto(cont, tipo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removerProdutoFarm(FarmaciaProduto fp) {
		System.out.println(fp.getIdFarmaciaProd());
		this.listaProdFarm.remove(fp);
		this.attTableProdutoFarm();
		try {
			fpdi.remover(fp.getIdFarmaciaProd());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attProdutoFarm(FarmaciaProduto f) {
		for(FarmaciaProduto fp : listaProdFarm) {
			if(fp.getProduto().getId_produto() == f.getProduto().getId_produto()) {
				fp = f;
				this.attTableProdutoFarm();
			}
		}
		try {
			fpdi.alterar(f);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inserir(FarmaciaProduto fp) {
		//if(pesquisarFarmaciaProd(fp.getProduto().getId_produto()) == null) {
			this.listaProdFarm.add(fp);
			//this.attTableProdutoFarm();
			try {
				fpdi.inserir(fp);
				this.attTableProdutoFarm();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
	
	public void attTableProdutoFarm() {
		this.dataListProdFarm.clear();
		//this.dataListProdFarm.addAll(listaProdFarm);
		try {
			this.dataListProdFarm.addAll(fpdi.pesquisarFarmaciaProdutos());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attTableProdutoFarm(FarmaciaProduto fp) {
		this.dataListProdFarm.clear();
		this.dataListProdFarm.addAll(fp);
	}
	
}
