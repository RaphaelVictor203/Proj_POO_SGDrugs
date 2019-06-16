package com.curso.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.entity.Fornecedor;
import com.curso.dao.ConjFornecedorDAO;
import com.curso.dao.ConjFornecedorDAOImpl;
import com.curso.dao.DAOException;
import com.curso.dao.EnderecoDAO;
import com.curso.dao.EnderecoDAOImpl;
import com.curso.dao.FarmaciaDAOImpl;
import com.curso.dao.FornecedorDAO;
import com.curso.dao.FornecedorDAOImpl;
import com.curso.entity.Cliente;
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
	private FornecedorDAOImpl fornecDAO = new FornecedorDAOImpl();
	private FarmaciaDAOImpl farmDAO = new FarmaciaDAOImpl();
	
	public ControlFornecedores() {
		this.FornecedoresCadastrados = new ArrayList<Fornecedor>();
		this.enderecoCadastrados = new ArrayList<Endereco>();
	}
	
	public ObservableList<Fornecedor> getDataListFornecedores(){
		return this.dataList;
	}

//MANTER Fornecedor ------------------------------------------------------
	
	public boolean cadFornecedor(Fornecedor fr) {
		if(existFornecedor(fr.getCnpj())) {
			try {
				this.dataList.add(fr);
				this.fornecDAO.inserir(fr);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attTableFornecedor();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Fornecedor j� esta cadastrado no sistema !!!");
			return false;
		}
	}
	
	public List<Fornecedor> pesquisarFornecedor(Fornecedor f){
		
		List<Fornecedor> fornecedores = new ArrayList<>();
		try {
			
			for(Fornecedor forn : fornecDAO.pesquisarPorFornecedor()) {
				 forn.getNome_fantasia();
				 fornecedores.add(forn);
			}
			
		} catch(DAOException e) {
			e.printStackTrace();
		}

		return fornecedores;	
	}
	public void pesquisarFornecedor(String nome) {
		dataList.clear();
		if(!nome.equals("")) {
			try {
				dataList.addAll(fornecDAO.pesquisarPorFornecedor(nome));
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Fornecedor pesquisarFornecedor(long Cnpj) {
		try {
			return fornecDAO.pesquisarPorFornecedor(Cnpj);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean existFornecedor(long Cnpj) {
		try {
			return (fornecDAO.pesquisarPorFornecedor(Cnpj) != null) ? true : false;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void removerFornecedor() {
		ConjFornecedorDAO cfdi = new ConjFornecedorDAOImpl();
		FornecedorDAO fornec = new FornecedorDAOImpl();
		EnderecoDAO	endfor = new EnderecoDAOImpl();
		try {
			this.fornecSel = this.fornecDAO.pesquisarPorFornecedor(fornecSel.getCnpj());
			cfdi.remover(fornecSel.getID(), fornecSel.getFarmacia().getId());
			fornec.remover(fornecSel.getCnpj());
			endfor.remover(fornecSel.getEndereco());
			//this.fornecDAO.remover(fornecSel.getCnpj());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.attTableFornecedor();
	}
	
	public void attFornecedor(Fornecedor fr) {
		//fr.getEndereco().setIdEndereco(fornecSel.getEndereco().getIdEndereco());
		//fr.getFarmacia().setId(fornecSel.getFarmacia().getId());
		try {
			fornecDAO.alterar(fr);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//FIM MANTER Fornecedor-----------------------------------------------------------------
	
	
//MANTER TABELAS---------------------------------------------------------------------

	public void attTableFornecedor() {
		this.dataList.clear();
		try {
			this.dataList.addAll(fornecDAO.pesquisarPorFornecedor());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void attTableFornecedor(Fornecedor fr) {
		this.dataList.clear();
		this.dataList.add(fr);
	}
	public void attTableFornecedor(List<Fornecedor> frs) {
		this.dataList.clear();
		this.dataList.addAll(frs);
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
	//COMBO FARMACIA ------------------------
		public List<Farmacia> comboFarm (){
			try {
				return farmDAO.pesquisarFarmacia();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}