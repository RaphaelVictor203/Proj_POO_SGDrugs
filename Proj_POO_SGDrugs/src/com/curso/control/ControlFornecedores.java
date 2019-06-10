package com.curso.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.entity.Fornecedor;
import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;
import com.curso.entity.ProblemaSaude;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlFornecedores {
	
	private List<Fornecedor> FornecedoresCadastrados;
	private List<Endereco> enderecoCadastrados;
	private ObservableList<Fornecedor> dataList = FXCollections.observableArrayList();
	public static Fornecedor fornecSel = new Fornecedor();
	
	public ControlFornecedores() {
		this.FornecedoresCadastrados = new ArrayList<Fornecedor>();
		this.enderecoCadastrados = new ArrayList<Endereco>();
	}
	
	public ObservableList<Fornecedor> getDataListFornecedores(){
		return this.dataList;
	}
	
//MANTER Fornecedor ------------------------------------------------------
	
	public boolean cadFornecedor(Fornecedor fr) {
		if(!existFornecedor(fr.getCnpj())) {
			this.FornecedoresCadastrados.add(fr);
			attTableFornecedor();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Fornecedor já esta cadastrado no sistema !!!");
			return false;
		}
	}
	
	public void pesquisarFornecedor(String nome, long Cnpj, String uf, String cidade) {
		dataList.clear();
		if(!nome.equals("")) {
			for(Fornecedor f : FornecedoresCadastrados) {
				if(f.getCnpj() == Cnpj) {
					dataList.add(f);
				}
			}
		}
	}
	
	public Fornecedor pesquisarFornecedor(long Cnpj) {
		for(Fornecedor f : FornecedoresCadastrados) {
			if(f.getCnpj() == Cnpj) {
				return f;
			}
		}
		return null;
	}
	
	private boolean existFornecedor(long Cnpj) {
		for(Fornecedor f : FornecedoresCadastrados) {
			if(f.getCnpj() == Cnpj) {
				return true;
			}
		}
		return false;
	}
	
	public void removerFornecedor() {
		this.FornecedoresCadastrados.remove(pesquisarFornecedor(fornecSel.getCnpj()));
		this.attTableFornecedor();
	}
	
	public void attFornecedor(Fornecedor fr) {
		for(Fornecedor f : FornecedoresCadastrados) {
			if(f.getCnpj() == fornecSel.getCnpj()) {
				f.setNome_fantasia(fr.getNome_fantasia());
				f.setCnpj(fr.getCnpj());
				f.setTelefone(fr.getTelefone());
				f.getFarmacia().setUnidade(fr.getFarmacia().getUnidade());
				f.getEndereco().setCep(fr.getEndereco().getCep());
				f.getEndereco().setRua(fr.getEndereco().getRua());
				f.getEndereco().setNumero(fr.getEndereco().getNumero());
				f.getEndereco().setCidade(fr.getEndereco().getCidade());
				f.getEndereco().setUf(fr.getEndereco().getUf());
			}
		}
	}
	
//FIM MANTER Fornecedor-----------------------------------------------------------------
	
	
//MANTER TABELAS---------------------------------------------------------------------

	public void attTableFornecedor() {
		this.dataList.clear();
		this.dataList.addAll(FornecedoresCadastrados);
		
	}
	
	public void attTableFornecedor(Fornecedor cl) {
		this.dataList.clear();
		this.dataList.add(cl);
	}
	
	
//FIM MANTER TABELAS-----------------------------------------------------------------
	
//MANTER ENDEREÇO--------------------------------------------------------------------
	
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

//FIM MANTER ENDEREÇO----------------------------------------------------------------
	
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
