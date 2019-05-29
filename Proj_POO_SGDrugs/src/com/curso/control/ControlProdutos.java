package com.curso.control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private List<Produto> ltProdutos = new ArrayList<>();
	private String arquivo = "regProdutos.txt";
	private ObservableList<Produto> dataListProds = FXCollections.observableArrayList();
	
	public ControlProdutos() {
		Produto p = new Produto();
		p.setId_produto(1);
		p.setNome("Remedio");
		p.setCategoria("Rem");
		Fornecedor f = new Fornecedor();
		f.setNome_fantasia("Fornecedor202");
		p.setFornecedor(f);
		ltProdutos.add(p);
	}

	public void inserirProduto(Produto produto) throws IOException {

		BufferedWriter write = new BufferedWriter(new FileWriter(arquivo));

		for (Produto p : ltProdutos) {
			
			write.write(p.getNome());
			write.newLine();
			write.write(p.getCategoria());
			write.newLine();
		}

		write.close();

	}

	public void adicionarProduto(Produto p) {
			ltProdutos.add(p);
	}

	public ObservableList<Produto> getDataListProds() {
		return this.dataListProds;
	}
	
	public void attTableProds() {
		this.dataListProds.clear();
		this.dataListProds.addAll(ltProdutos);
	}
	

}
