package com.curso.control;

import com.curso.entity.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;

import com.curso.dao.DAOException;
import com.curso.dao.FuncionarioDAOImpl;
import com.curso.dao.LoginDAO;
import com.curso.dao.LoginDAOImpl;;

public class ControlFuncionario {
	FuncionarioDAOImpl dao = new FuncionarioDAOImpl();;
	public  ObservableList<Funcionario> dataList = FXCollections.observableArrayList();
	public  List<Funcionario> funcio = new ArrayList<>();
	public static Funcionario funcSel = new Funcionario();
	
	public void inserir() {
		this.funcSel.getEnd().setBairro("");
		try {
			if(!ValidarCadastror(funcSel.getCpf())) {
				ClassList.funcionario.add(funcSel);
				funcio.add(funcSel);
				dao.inserir(funcSel);
				dataList.clear();
				dataList.addAll(funcio);
				Alert a = new Alert(AlertType.INFORMATION, "Usu�rio  cadastrado Com Sucesso!!!");
				
				a.showAndWait();
				return;
			}
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addLogUsuario(String nome, String senha, String nivel) {
		LoginDAO ldi = new LoginDAOImpl();
		try {
			ldi.inserir(dao.pesquisarFuncionario(funcSel.getCpf()).getID(), nome, senha, nivel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean ValidarCadastror (long cpf) {
		
		if(ClassList.funcionario.isEmpty()) {
			return false;
		}
		for(Funcionario n : ClassList.funcionario) {
			if(n.getCpf()==cpf) {
				Alert a = new Alert(AlertType.WARNING, "Usu�rio J� cadastrado!!!");
				a.showAndWait();;
				return true;
		}
			
	}
		return false;
		
		
	}

	public void alterar(Funcionario fn) {
		// TODO Auto-generated method stub
		try {
			 dao.alterar(fn);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public ObservableList<Funcionario> getDataList() {
		return dataList;
	}

	public void setDataList(ObservableList<Funcionario> dataList) {
		this.dataList = dataList;
	}
	
	public void attTableFuncionario() {
		this.dataList.clear();
		try {
			this.dataList.addAll(dao.pesquisarPorFuncionarios());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.dataList.addAll(ClassList.funcionario);
		//System.out.println(ClassList.funcionario.size()+" kkkkkkk");
	}

	public void pesquisarFuncionario(long cpf) {
		this.dataList.clear();
		try {
			this.dataList.add(dao.pesquisarFuncionario(cpf));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
