package com.curso.dao;

import java.util.List;

import com.curso.entity.Endereco;

public interface EnderecoDAO {

	boolean inserir(Endereco end) throws DAOException ;
	Endereco pesquisarEnderecoCliente(long cpf) throws DAOException ;
	Endereco pesquisarEnderecoFornecedor(long cnpj) throws DAOException ;
	Endereco pesquisarEnderecoFarmacia(int id) throws DAOException ;
	Endereco pesquisarEnderecoFuncionario(long cpf) throws DAOException ;
	Endereco pesquisarEnderecoFuncionario(String nome) throws DAOException;
	List<Endereco> pesquisarEndereco(String cep, int num, String rua, String bairro) throws DAOException;
	void alterar(Endereco end) throws DAOException ;
	void remover(Endereco end) throws DAOException ;
	
}
