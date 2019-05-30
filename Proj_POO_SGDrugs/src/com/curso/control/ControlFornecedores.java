package com.curso.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.entity.Fornecedor;
import com.curso.entity.Endereco;
import com.curso.entity.ProblemaSaude;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFornecedores {
	
	private List<Fornecedor> FornecedorsCadastrados;
	private List<Endereco> enderecoCadastrados;
	private ObservableList<Fornecedor> dataList = FXCollections.observableArrayList();
	public static Fornecedor fornecSel = new Fornecedor();
	
	public ControlFornecedores() {
		this.FornecedorsCadastrados = new ArrayList<Fornecedor>();
		this.enderecoCadastrados = new ArrayList<Endereco>();
		
		/*Endereco ed = new Endereco();
		ed.setUf("SP");
		ed.setCep("08370190");
		ed.setRua("Jardim S�o Gon�alo, Estrada da Col�nia");
		ed.setNumero(20);
		ed.setCidade("S�o Paulo");
		
		
		Fornecedor cl = new Fornecedor();
		cl.setPrimeiroNome("Gabriel Queiroz");
		cl.setDt_nasc(new Date(1997, 3, 20));
		cl.setCnpj(47893115890L);
		cl.setRg(530734084);
		cl.setEmail("raphael.victor204@gmail.com");
		cl.setTelefone(11985188623L);
		cl.setCartaoSUS(123124124124L);
		cl.setEnd(ed);
		cl.setProblemasSaude(listPS);
		FornecedorsCadastrados.add(cl);
		this.dataList.add(cl);
		
		ed = new Endereco();
		ed.setUf("SP");
		ed.setCep("08370190");
		ed.setRua("Jardim S�o Gon�alo, Estrada da Col�nia");
		ed.setNumero(20);
		ed.setCidade("S�o Paulo");
		
		
		cl = new Fornecedor();
		cl.setPrimeiroNome("Raphael");
		cl.setDt_nasc(new Date(1997, 3, 20));
		cl.setCnpj(38472812990L);
		cl.setRg(530734084);
		cl.setEmail("raphael.victor204@gmail.com");
		cl.setTelefone(11985188623L);
		cl.setCartaoSUS(123124124124L);
		cl.setEnd(ed);
		cl.setProblemasSaude(listPS);
		FornecedorsCadastrados.add(cl);
		this.dataList.add(cl);*/
	}
	
	public ObservableList<Fornecedor> getDataListFornecedors(){
		return this.dataList;
	}
	
//MANTER Fornecedor ------------------------------------------------------
	
	public boolean cadFornecedor(Fornecedor cl) {
		if(!existFornecedor(cl.getCnpj())) {
			this.FornecedorsCadastrados.add(cl);
			attTableFornecedor();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Fornecedor j� esta cadastrado no sistema !!!");
			return false;
		}
	}
	
	public void pesquisarFornecedor(String nome, long Cnpj, String uf, String cidade) {
		dataList.clear();
		if(!nome.equals("")) {
			for(Fornecedor f : FornecedorsCadastrados) {
				if(f.getCnpj() == Cnpj) {
					dataList.add(f);
				}
			}
		}
	}
	
	public Fornecedor pesquisarFornecedor(long Cnpj) {
		for(Fornecedor f : FornecedorsCadastrados) {
			if(f.getCnpj() == Cnpj) {
				return f;
			}
		}
		return null;
	}
	
	private boolean existFornecedor(long Cnpj) {
		for(Fornecedor f : FornecedorsCadastrados) {
			if(f.getCnpj() == Cnpj) {
				return true;
			}
		}
		return false;
	}
	
	public void removerFornecedor() {
		this.FornecedorsCadastrados.remove(pesquisarFornecedor(fornecSel.getCnpj()));
		this.attTableFornecedor();
	}
	
	public void attFornecedor(Fornecedor cl) {
		for(Fornecedor f : FornecedorsCadastrados) {
			if(f.getCnpj() == fornecSel.getCnpj()) {
				f.setNome_fantasia(cl.getNome_fantasia());
				f.setCnpj(cl.getCnpj());
				f.setTelefone(cl.getTelefone());
				f.getEndereco().setCep(cl.getEndereco().getCep());
				f.getEndereco().setRua(cl.getEndereco().getRua());
				f.getEndereco().setNumero(cl.getEndereco().getNumero());
				f.getEndereco().setCidade(cl.getEndereco().getCidade());
				f.getEndereco().setUf(cl.getEndereco().getUf());
			}
		}
	}
	
//FIM MANTER Fornecedor-----------------------------------------------------------------
	
	
//MANTER TABELAS---------------------------------------------------------------------

	public void attTableFornecedor() {
		this.dataList.clear();
		this.dataList.addAll(FornecedorsCadastrados);
	}
	
	public void attTableFornecedor(Fornecedor cl) {
		this.dataList.clear();
		this.dataList.add(cl);
	}
	
	
//FIM MANTER TABELAS-----------------------------------------------------------------
	
//MANTER ENDERE�O--------------------------------------------------------------------
	
	public void addEndereco(Endereco ed) {
		this.enderecoCadastrados.add(ed);
	}
	
	
	
	public Endereco procurarEndereco(Endereco ed) {
		boolean addEd = true;
		for(Endereco e : enderecoCadastrados) {
			if(e.getCep() == ed.getCep()) {
				addEd = false;
				break;
			}
		}
		if(addEd) {
			addEndereco(ed);
		}
		return ed;
	}

//FIM MANTER ENDERE�O----------------------------------------------------------------
	
	public String[] gerarArrayNum(int init, int fim) {
		int tam = (fim-init) + 1;
		int vl = init;
		String[] arrayNum = new String[tam];
		for(int i=0; i<tam; i++) {
			arrayNum[i] = Integer.toString(vl);
			vl++;
		}
		return arrayNum;
	}
	
}
