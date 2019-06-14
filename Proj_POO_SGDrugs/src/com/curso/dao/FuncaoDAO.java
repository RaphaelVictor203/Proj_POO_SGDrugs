package com.curso.dao;

import java.util.List;

import com.curso.entity.Funcao;

public interface FuncaoDAO {

	List<Funcao> pesquisarPorFuncoes() throws DAOException;
	Funcao pesquisarPorFuncao(int id) throws DAOException;
	
}
