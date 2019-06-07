package com.curso.control;
import java.util.List;
import com.curso.entity.Cliente;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlVendas {

	private Venda vendaAtual;
	private List<FarmaciaProduto> produtos;
	private List<Cliente> clientes;
	private List<ItemVenda> itensVenda;
	private ObservableList<ItemVenda> dataItens = FXCollections.observableArrayList();
	
	public ControlVendas() {
		
	   FarmaciaProduto produto1 = new FarmaciaProduto();
	   produto1.getProduto().setId_produto(1);
	   produto1.getProduto().setNome("Diporona Sódica 10ml");
	   produto1.getProduto().setCategoria("genérico");
	   produto1.setPreco(0.99);
	   produto1.getFarmacia().setId(10);
	   produto1.setQntdEstoque(150);
	   

	   
	}
	
	
	
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public ObservableList<ItemVenda> getDataItens() {
		return dataItens;
	}
	public void setDataItens(ObservableList<ItemVenda> dataItens) {
		this.dataItens = dataItens;
	}
	
	public Venda getVendaAtual() {
		return vendaAtual;
	}
	public void setVendaAtual(Venda vendaAtual) {
		this.vendaAtual = vendaAtual;
	}
	public List<FarmaciaProduto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<FarmaciaProduto> produtos) {
		this.produtos = produtos;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
}
