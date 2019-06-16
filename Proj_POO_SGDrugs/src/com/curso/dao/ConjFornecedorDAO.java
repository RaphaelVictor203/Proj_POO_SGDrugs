package com.curso.dao;

import java.util.List;

import com.curso.entity.Endereco;
import com.curso.entity.Fornecedor;

public interface ConjFornecedorDAO {

	void inserir(int idFornecedor, int idFarmacia) throws DAOException ;
	void alterar(int idFornecedor, int idFarmacia) throws DAOException ;
	void remover(int idFornecedor, int idFarmacia) throws DAOException ;
	
}
