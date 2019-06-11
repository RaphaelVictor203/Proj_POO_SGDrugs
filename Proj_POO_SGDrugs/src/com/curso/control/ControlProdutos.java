package com.curso.control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.curso.dao.DAOException;
import com.curso.dao.ProdutoDAOImpl;
import com.curso.entity.Cliente;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
	private List<Produto> ltProdutos = new ArrayList<>();
	private ObservableList<Produto> obsProdutos = FXCollections.observableArrayList();
	private ObservableList<Produto> dataListProds = FXCollections.observableArrayList();


	public ObservableList<Produto> getGetListProdutos() {
		return obsProdutos;
	}

	public void setGetListProdutos(ObservableList<Produto> getListProdutos) {
		this.obsProdutos = getListProdutos;
	}
	
	public void AdicionarProduto(Produto produto) throws DAOException {

		try {
			produtoDAO.inserirProduto(produto);
			obsProdutos.clear();
			obsProdutos.addAll(produtoDAO.consultarProduto(""));
		} catch (DAOException e) {
			
			e.printStackTrace();
		}

	}
	
	public void CarregarProdutos() throws DAOException{
			obsProdutos.clear();
			obsProdutos.addAll(produtoDAO.consultarProduto(""));
	}
	
	public void PesquisarProduto(String desc) throws DAOException {
			obsProdutos.clear();
			obsProdutos.addAll(produtoDAO.consultarProduto(desc));
	
	}
 
  public Produto selecionarProduto(int id) {
		try {
			return produtoDAO.consultarProduto(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(Produto p : ltProdutos) {
			if(p.getId_produto() == id) {
				return p;
			}
		}
		return null;*/
		return null;
	}
  
  public void attTableProds() {
		this.dataListProds.clear();
		//this.dataListProds.addAll(ltProdutos);
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

