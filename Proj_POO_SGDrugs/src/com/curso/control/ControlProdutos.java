package com.curso.control;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.dao.DAOException;
import com.curso.dao.ProdutoDAOImp;
import com.curso.entity.Cliente;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.Fornecedor;

import com.curso.entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private List<Produto> ltProdutos = new ArrayList<>();

	private ObservableList<Produto> obsProdutos = FXCollections.observableArrayList();

	private ObservableList<Produto> dataListProds = FXCollections.observableArrayList();

	private String arquivo = "Produtos.txt";
	
	private ProdutoDAOImp pdi = new ProdutoDAOImp();

	public ControlProdutos() {
		
		Produto prod = new Produto();
		prod.setCategoria("Perecivel");
		prod.setFornecedor(new Fornecedor());
		prod.setId_produto(1);
		prod.setNome("Coca-cola");
		
		Produto prod1 = new Produto();
		prod1.setCategoria("Genérico");
		prod1.setFornecedor(new Fornecedor());
		prod1.setId_produto(2);
		prod1.setNome("Aspirina");
		
		ltProdutos.add(prod);
		ltProdutos.add(prod1);
		attTableProds();
		
	}
	
	public ObservableList<Produto> getGetListProdutos() {
		return obsProdutos;
	}

	public void setGetListProdutos(ObservableList<Produto> getListProdutos) {
		this.obsProdutos = getListProdutos;
	}
	
	public void gravarProduto(Produto produto) throws IOException {

		PrintStream write = new PrintStream(new FileOutputStream(arquivo, true));

		write.println(Integer.toString(produto.getId_produto()) + "\n" + produto.getNome() + "\n"
				+ produto.getCategoria() + "\n" + produto.getFornecedor().getNome_fantasia());
		write.close();
		addNaLista();

	}
	
	/*public Produto selecionarProduto(Produto p) throws IOException{
	  
	  BufferedReader read = new BufferedReader(new FileReader(arquivo));
	  
	  p.setId_produto(Integer.parseInt(read.readLine()));
	  p.setNome(read.readLine()); p.setCategoria(read.readLine());
	  p.getFornecedor().setNome_fantasia((read.readLine()));
	  
	  read.close(); 
	  return p;
	  
	}*/
	 
	public Produto selecionarProduto(int id) {
		for(Produto p : ltProdutos) {
			if(p.getId_produto() == id) {
				return p;
			}
		}
		return null;
	}
	
	public void addNaLista() {
		try {
			obsProdutos.clear();
			obsProdutos.addAll(ltProdutos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void adicionarProduto(Produto p) throws IOException {
		System.out.println(p.getCategoria()+" "+p.getCategoria()+" "+p.getFornecedor());

		ltProdutos.add(p);
	}

	public ObservableList<Produto> getDataListProds() {
		return this.dataListProds;
	}

	public void attTableProds() {
		this.dataListProds.clear();
		//this.dataListProds.addAll(ltProdutos);
		try {
			this.dataListProds.addAll(pdi.consultarProdutosCad());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
