package com.curso.dao;

import java.util.List;

import com.curso.entity.Farmacia;

public interface FarmaciaDAO {
	boolean inserir(Farmacia frm) throws DAOException ;
	Farmacia pesquisarFarmaciaFornecedor(long cnpj) throws DAOException ;
	List<Farmacia> pesquisarFarmacia(String nome) throws DAOException;
	Farmacia pesquisarFarmacia(int id) throws DAOException;
	void alterar(Farmacia frm) throws DAOException ;
	void remover(Farmacia frm) throws DAOException ;
}
