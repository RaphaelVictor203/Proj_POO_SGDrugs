package com.curso.dao;

import java.util.List;

import com.curso.entity.Grupo;

public interface GrupoDAO {

	void inserir(Grupo obj) throws DAOException ;
	Grupo pesquisarPorGrupo(String desc) throws DAOException ;
	void alterar(Grupo grupo) throws DAOException ;
	void remover(int id) throws DAOException ;
	public List<Grupo> pesquisarPorGrupos() throws DAOException;
	
}
