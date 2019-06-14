package com.curso.dao;

import com.curso.entity.FormaPagto;

public interface FormaPagtoDAO {

	void inserir(FormaPagto fp, int idVenda) throws DAOException ;
	
}
