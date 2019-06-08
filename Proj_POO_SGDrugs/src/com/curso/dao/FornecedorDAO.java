package com.curso.dao;

import java.util.List;

import com.curso.entity.Fornecedor;


public interface FornecedorDAO {
	void inserir(Fornecedor fornecedor) throws DAOException ;
	Fornecedor pesquisarPorFornecedor(long cpf) throws DAOException ;
	List<Fornecedor> pesquisarPorFornecedor(String nome, long Cnpj, String uf, String cidade) throws DAOException;
	void alterar(Fornecedor fornecedor) throws DAOException ;
	void remover(long cpf) throws DAOException ;
}
