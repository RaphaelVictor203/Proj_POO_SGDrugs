package com.curso.dao;

import com.curso.entity.Cliente;
import com.curso.entity.Venda;

public interface VendaDAO {

	void inserir(Venda obj) throws DAOException ;
	Venda pesquisarPorUltVenda() throws DAOException ;
	
}
