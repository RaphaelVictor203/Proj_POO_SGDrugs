package com.curso.dao;

import java.util.List;

import com.curso.entity.ProblemaSaude;

public interface ProblemaSaudeDAO {
	void inserir(ProblemaSaude obj) throws DAOException ;
	ProblemaSaude pesquisarPorProblema(long cpf) throws DAOException ;
	void alterar(ProblemaSaude problema) throws DAOException ;
	void remover(long cpf) throws DAOException ;
}
