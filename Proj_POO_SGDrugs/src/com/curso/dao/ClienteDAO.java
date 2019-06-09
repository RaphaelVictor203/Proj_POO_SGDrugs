package com.curso.dao;

import java.util.List;

import com.curso.entity.Cliente;

public interface ClienteDAO {
	void inserir(Cliente obj) throws DAOException ;
	Cliente pesquisarPorCliente(long cpf) throws DAOException ;
	List<Cliente> pesquisarPorCliente(String nome, String tipo) throws DAOException;
	void alterar(Cliente cliente) throws DAOException ;
	void remover(long cpf) throws DAOException ;
	public List<Cliente> pesquisarPorClientes() throws DAOException;
}