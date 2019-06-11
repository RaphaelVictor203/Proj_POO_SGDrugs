
package com.curso.dao;
import java.util.List;
import com.curso.entity.Farmacia;
import com.curso.entity.Fornecedor;

public interface FornecedorDAO {
	void inserir(Fornecedor fornecedor) throws DAOException ;
	Fornecedor pesquisarPorFornecedor(long cnpj) throws DAOException ;
	List<Fornecedor> pesquisarPorFornecedor(String nome) throws DAOException;
	List<Fornecedor> pesquisarPorFornecedor() throws DAOException;
	Fornecedor pesquisarPorFornecedor(int id) throws DAOException;
	void alterar(Fornecedor fornecedor) throws DAOException ;
	void remover(long cnpj) throws DAOException ;
}

