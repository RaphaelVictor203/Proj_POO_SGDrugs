package com.curso.dao;

import java.util.List;

import com.curso.entity.Endereco;

public interface EnderecoDAO {

	void inserir(Endereco end) throws DAOException ;
	Endereco pesquisarEnderecoCliente(long cpf) throws DAOException ;
	List<Endereco> pesquisarEndereco(long cep, int num, String rua, String bairro) throws DAOException;
	void alterar(Endereco end) throws DAOException ;
	void remover(Endereco end) throws DAOException ;
	
}
