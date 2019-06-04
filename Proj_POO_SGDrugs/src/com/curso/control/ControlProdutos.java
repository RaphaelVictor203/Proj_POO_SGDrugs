package com.curso.control;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.curso.entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private List<Produto> ltProdutos = new ArrayList<>();
	private ObservableList<Produto> obsProdutos = FXCollections.observableArrayList();

	
	public ControlProdutos() {
		try {
			obsProdutos.clear();
			selecionarProduto(ltProdutos);
			obsProdutos.addAll(ltProdutos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String arquivo = "Produtos.txt";

	public ObservableList<Produto> getGetListProdutos() {
		return obsProdutos;
	}

	public void setGetListProdutos(ObservableList<Produto> getListProdutos) {
		this.obsProdutos = getListProdutos;
	}

	public void gravarProduto(Produto produto) throws IOException {

		PrintStream write = new PrintStream(new FileOutputStream(arquivo, true));

		write.println(Integer.toString(produto.getId_produto()) + "\n" 
									 + produto.getNome() + "\n" 
									 + produto.getCategoria() + "\n"
									 + produto.getFornecedor().getNome_fantasia());
		write.close();


	}
	public List<Produto> selecionarProduto(List<Produto> produtos) throws IOException{
		   
		BufferedReader read = new BufferedReader(new FileReader(arquivo));	
		for(Produto p : produtos) {
			
			p.setId_produto(Integer.parseInt(read.readLine()));
			p.setNome(read.readLine());
			p.setCategoria(read.readLine());
			p.getFornecedor().setNome_fantasia((read.readLine()));
		}
		read.close();
		return produtos;

	}
	
	public void adicionarProduto(Produto produto) {

		ltProdutos.add(produto);
	}

	public void addNaLista() {
		try {
			obsProdutos.clear();
			obsProdutos.addAll(ltProdutos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
