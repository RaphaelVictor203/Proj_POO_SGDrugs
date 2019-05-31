package com.curso.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.entity.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlProdutos {

	private List<Produto> ltProdutos = new ArrayList<>();
	private ObservableList<Produto> obsProdutos = FXCollections.observableArrayList();
	private String arquivo = "regProdutos.txt";

	public ObservableList<Produto> getGetListProdutos() {
		return obsProdutos;
	}

	public void setGetListProdutos(ObservableList<Produto> getListProdutos) {
		this.obsProdutos = getListProdutos;
	}

	public void inserirProduto(Produto produto) throws IOException {

		BufferedWriter write = new BufferedWriter(new FileWriter(arquivo));

		for (Produto p : ltProdutos) {

			write.write(Integer.toString(p.getId_produto()));
			write.newLine();
			write.write(p.getNome());
			write.newLine();
			write.write(p.getCategoria());
			write.newLine();
			write.write(p.getFornecedor().getNome_fantasia());
			write.newLine();
		}

		write.close();
		addNaLista();

	}

	public void adicionarProduto(Produto produto) {

		ltProdutos.add(produto);
	}

	public List<Produto> lerProduto(List<Produto> produto) throws IOException {

		BufferedReader read = new BufferedReader(new FileReader(arquivo));

		for (Produto p : ltProdutos) {
			
			p.setId_produto(Integer.parseInt(read.readLine()));
			p.setNome(read.readLine());
			p.setCategoria(read.readLine());
			p.getFornecedor().setNome_fantasia(read.readLine());
		}
		read.close();
		return produto;
	}

	public void addNaLista() {
		try {
			obsProdutos.clear();
			lerProduto(ltProdutos);
			obsProdutos.addAll(ltProdutos);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
