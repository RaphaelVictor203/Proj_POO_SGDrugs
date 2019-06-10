package com.curso.dao;

import java.util.List;

import com.curso.entity.Sessao;

public interface SessaoDAO {

	void inserir(Sessao obj) throws DAOException ;
	Sessao pesquisarPorSessao(String desc) throws DAOException ;
	Sessao pesquisarPorSessao(int id) throws DAOException ;
	void alterar(Sessao sessao) throws DAOException ;
	void remover(int id) throws DAOException ;
	public List<Sessao> pesquisarPorSessoes() throws DAOException;
	
}
