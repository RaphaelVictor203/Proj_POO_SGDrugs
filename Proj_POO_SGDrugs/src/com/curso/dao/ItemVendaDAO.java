package com.curso.dao;

import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;

public interface ItemVendaDAO {

	void inserir(ItemVenda obj, Venda v) throws DAOException ;
	
}
