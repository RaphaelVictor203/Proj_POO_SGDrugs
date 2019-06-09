package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;

public class ProdutoDAOImp implements ProdutoDAO {

	Fornecedor f; 

	@Override
	public void inserirProduto(Produto p) throws DAOException {
			
		try {
	
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbProduto (descricao, categoria, idFornecedor) VALUES(?, ?, ?)";
			PreparedStatement smtp = con.prepareStatement(sql);
			smtp.setString(1, p.getNome());
			smtp.setString(2, p.getCategoria());
			smtp.setInt(3, p.getFornecedor().getID());
			smtp.executeUpdate();
			smtp.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na Conexão");
			throw new DAOException(e);
		}
	}

	@Override
	public List<Produto> consultarProduto(Produto p) {

		return null;
	}

	@Override
	public void alterarProduto(Produto p) {

	}

	@Override
	public void excluirProduto(Produto p) {

	}

}
