package com.curso.control;

import com.curso.dao.DAOException;
import com.curso.dao.ProdutoDAOImpl;
import com.curso.entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
	private ObservableList<Produto> dataListProds = FXCollections.observableArrayList();
	private ObservableList<Produto> ProdutosCAD = FXCollections.observableArrayList();

	public ObservableList<Produto> getProdutosCAD() {
		return ProdutosCAD;
	}

	public void setProdutosCAD(ObservableList<Produto> produtosCAD) {
		ProdutosCAD = produtosCAD;
	}

	public void AdicionarProduto(Produto produto) throws DAOException {

		try {
			produtoDAO.inserirProduto(produto);
			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(""));
		} catch (DAOException e) {

			e.printStackTrace();
		}

	}
	public void AlterarProduto(Produto produto) throws DAOException {

		try {
			produtoDAO.alterarProduto(produto);
			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(""));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void ExcluirProduto(Produto produto) throws DAOException {

		try {
			produtoDAO.excluirProduto(produto);
			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(""));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void CarregarProdutos() throws DAOException {

			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(""));
	}

	public void SearchProdutoCadastro(String desc) throws DAOException {
			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(desc));

	}
	
	public void SearchProdutoCadastro(int id) throws DAOException{
		
			ProdutosCAD.clear();
			ProdutosCAD.addAll(produtoDAO.consultarProduto(id));
	}
	

	/*public Produto SearchProdutoTabela(String entrada) throws DAOException {
			
			return (Produto) produtoDAO.consultarProduto(entrada);
	}*/

	public Produto selecionarProduto(int id) {
		try {
			return produtoDAO.consultarProduto(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		/*
		 * for(Produto p : ltProdutos) { if(p.getId_produto() == id) { return p; } }
		 * return null;
		 */
		return null;
	}

	public void attTableProds() {
		this.dataListProds.clear();
		// this.dataListProds.addAll(ltProdutos);
		try {
			this.dataListProds.addAll(produtoDAO.consultarProdutosCad());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Produto> getDataListProds() {
		return this.dataListProds;
	}

}
