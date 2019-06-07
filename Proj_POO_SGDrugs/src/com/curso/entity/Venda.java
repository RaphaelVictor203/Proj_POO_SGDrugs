package com.curso.entity;

import java.util.List;

public class Venda {
	
	private int id_venda;
	private Cliente cliente;
	private Funcionario funcionario;
	private List<ItemVenda> items;
	
	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItemVenda> getItems() {
		return items;
	}

	public void setItems(List<ItemVenda> items) {
		this.items = items;
	}

	public double returnPrecoTotal() {
		double total = 0;
		for(ItemVenda item : items) {
			total += (item.getQntd() * item.getProduto().getPreco());
		}
		return total;
	}
	
}
