package com.curso.dao;

import java.util.List;
import com.curso.entity.Produto;

public interface ProdutoDAO {

	void inserirProduto(Produto p) throws DAOException;
	List<Produto> consultarProduto(String desc) throws DAOException;
	void alterarProduto(Produto p) throws DAOException;
	void excluirProduto(Produto p) throws DAOException;
	Produto consultarProduto(int d) throws DAOException;
}
