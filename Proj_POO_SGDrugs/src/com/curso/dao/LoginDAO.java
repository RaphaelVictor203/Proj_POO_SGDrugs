package com.curso.dao;

import com.curso.entity.Cliente;
import com.curso.entity.Funcionario;

public interface LoginDAO {

	void inserir(int idFuncionario, String nomeLogin, String senha, String nivel) throws DAOException ;
	Funcionario pesquisarPorConta(String nomeLogin, String senha) throws DAOException ;
	
}
