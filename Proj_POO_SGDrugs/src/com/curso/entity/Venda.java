package com.curso.entity;

import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	private int id_venda;
	private Cliente cliente;
	private Funcionario funcionario;
	private List<ItemVenda> items;
	private List<FormaPagto> formasPagto;
	
	public Venda() {
		this.items = new ArrayList<ItemVenda>();
		this.formasPagto = new ArrayList<FormaPagto>();
	}
	
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

	public void addItem(ItemVenda item) {
		this.items.add(item);
	}
	
	public List<ItemVenda> getItems() {
		return items;
	}

	public List<FormaPagto> getFormasPagto(){
		return this.formasPagto;
	}
	
	public void resetListFormasPagto() {
		this.formasPagto = new ArrayList<FormaPagto>();
	}
	
	public void addFormaPagto(FormaPagto fp) {
		this.formasPagto.add(fp);
	}

	public boolean existItem(Produto p) {
		for(ItemVenda i : items) {
			if(i.getProduto().getProduto().getId_produto() == p.getId_produto()) {
				return true;
			}
		}
		return false;
	}
	
	public double returnPrecoTotal() {
		double total = 0;
		for(ItemVenda item : items) {
			total += item.getSubtotal();
		}
		return total;
	}
	
}
