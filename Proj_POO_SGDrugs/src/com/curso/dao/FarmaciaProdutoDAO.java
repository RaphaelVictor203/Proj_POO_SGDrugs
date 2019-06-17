package com.curso.dao;

import java.util.List;

import com.curso.entity.FarmaciaProduto;

public interface FarmaciaProdutoDAO {

	void inserir(FarmaciaProduto fp) throws DAOException ;
	FarmaciaProduto pesquisarFarmaciaProduto(int id_produto) throws DAOException ;
	List<FarmaciaProduto> pesquisarFarmaciaProduto(String cont, String tipo) throws DAOException;
	List<FarmaciaProduto> pesquisarFarmaciaProdutos() throws DAOException;
	void alterar(FarmaciaProduto fp) throws DAOException ;
	void remover(int id_produto) throws DAOException ;
	public void attQntdProd(int qntd, int id_produto) throws DAOException;
	
}
