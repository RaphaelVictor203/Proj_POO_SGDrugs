package com.curso.dao;

import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.ProblemaSaude;

public interface ProblemaSaudeDAO {
	void inserir(ProblemaSaude obj) throws DAOException ;
	void inserirProbCliente(Cliente cl) throws DAOException;
	ProblemaSaude pesquisarPorProblema(int id) throws DAOException ;
	public ProblemaSaude pesquisarPorProblemas(String desc) throws DAOException;
	List<ProblemaSaude> pesquisarPorProblemas(long cpf) throws DAOException ;
	void alterar(ProblemaSaude problema) throws DAOException ;
	void remover(int id) throws DAOException ;
	void removerProblemaCliente(long cpf, int id) throws DAOException ;
}
