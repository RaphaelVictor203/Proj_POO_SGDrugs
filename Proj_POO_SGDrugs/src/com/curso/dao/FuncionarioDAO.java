package com.curso.dao;

import java.util.List;

import com.curso.entity.Funcionario;;

public interface FuncionarioDAO {
	void inserir(Funcionario obj) throws DAOException ;
	Funcionario pesquisarFuncionario(long cpf) throws DAOException;
	Funcionario pesquisarFuncionario(String nome) throws DAOException;
	Funcionario pesquisarFuncionario(int id) throws DAOException ;
	List<Funcionario> pesquisarPorFuncionario(String nome, String cpf) throws DAOException;
	List<Funcionario> pesquisarPorFuncionarios() throws DAOException;
	void alterar(Funcionario cliente) throws DAOException ;
	void remover(long cpf) throws DAOException ;
}
