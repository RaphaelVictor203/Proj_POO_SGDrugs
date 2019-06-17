package com.curso.control;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.curso.dao.DAOException;
import com.curso.dao.FarmaciaProdutoDAO;
import com.curso.dao.FarmaciaProdutoDAOImpl;
import com.curso.dao.VendaDAO;
import com.curso.dao.VendaDAOImpl;
import com.curso.entity.Cliente;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.FormaPagto;
import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlVendas {

	private Venda vendaAtual;
	private List<FarmaciaProduto> produtos;
	private ObservableList<ItemVenda> dataItens = FXCollections.observableArrayList();
	private List<Venda> vendasRealizadas;
	private VendaDAO vdi = new VendaDAOImpl();
	private FarmaciaProdutoDAO fpdi = new FarmaciaProdutoDAOImpl();
	
	public ControlVendas() {
	   
	   vendaAtual = new Venda();

	   this.produtos = new ArrayList<FarmaciaProduto>();
	   this.vendasRealizadas = new ArrayList<Venda>();
	   
	   FarmaciaProduto produto1 = new FarmaciaProduto();
	   produto1.getProduto().setId_produto(1);
	   produto1.getProduto().setNome("Diporona Sódica 10ml");
	   produto1.getProduto().setCategoria("genérico");
	   produto1.setPreco(0.99);
	   produto1.getFarmacia().setId(10);
	   produto1.setQntdEstoque(150);
	   
	   FarmaciaProduto produto2 = new FarmaciaProduto();
	   produto2.getProduto().setId_produto(2);
	   produto2.getProduto().setNome("Xarope");
	   produto2.getProduto().setCategoria("controlado");
	   produto2.setPreco(0.99);
	   produto2.getFarmacia().setId(11);
	   produto2.setQntdEstoque(50);
	   
	   FarmaciaProduto produto3 = new FarmaciaProduto();
	   produto3.getProduto().setId_produto(3);
	   produto3.getProduto().setNome("Antialergico");
	   produto3.getProduto().setCategoria("genérico");
	   produto3.setPreco(1.99);
	   produto3.getFarmacia().setId(11);
	   produto3.setQntdEstoque(50);
	   
	   produtos.add(produto1);
	   produtos.add(produto2);
	   produtos.add(produto3);
	   
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
	
	public FarmaciaProduto pesquisaProd(int cod) throws DAOException{
		/*for(FarmaciaProduto prod : produtos) {
			if(prod.getProduto().getId_produto() == cod) {
				return prod;
			}
		}*/
		try {
			return fpdi.pesquisarFarmaciaProduto(cod);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addItemVenda(ItemVenda iv) {
		this.vendaAtual.addItem(iv);
		attDataItens();
	}
	
	public void delItemVenda(ItemVenda iv) {
		this.vendaAtual.getItems().remove(iv);
		attDataItens();
	}
	
	public void attDataItens() {
		this.dataItens.clear();
		this.dataItens.addAll(this.vendaAtual.getItems());
	}
	
	public void addVenda() {
		this.vendasRealizadas.add(this.getVendaAtual());
		//this.vendaAtual.setId_venda(this.vendasRealizadas.size());
		try {
			vdi.inserir(this.vendaAtual);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.vendaAtual = new Venda();
		printVendas();
		attDataItens();
	}
	
	public void resetFarmaciaProdutos() {
		for(FarmaciaProduto fp : produtos) {
			fp.getBtnIsencao().setText("SUS");
		}
	}
	
	public void printVendas() {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		System.out.println("Venda: -------------------------------------------------");
		for(Venda v : vendasRealizadas) {
			System.out.println("NUM.:" + v.getId_venda() + " - CLIENTE:" + v.getCliente().getPrimeiroNome() + " - FUNCIONARIO:" + v.getFuncionario().getPrimeiroNome() + "\n");
				for(ItemVenda iv : v.getItems()) {
					System.out.println("        " + iv.getProduto().getProduto().getNome() + " - " + iv.getProduto().getPreco() + " - " + iv.getSubtotal());
				}
				System.out.println("\nTOTAL:R$" + df.format(v.returnPrecoTotal()));
				System.out.println("FORMAS DE PAGAMENTO------------------------------------");
				for(FormaPagto fp : v.getFormasPagto()) {
					System.out.println("        " + fp.getFormaPagamento() + " - " + fp.getValor());
				}
			System.out.println("-----------------------------------------------------------");
		}
	}
	
}