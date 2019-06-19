package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;
import com.curso.entity.Fornecedor;
import com.curso.entity.ProblemaSaude;

public class ConjFornecedorDAOImpl implements ConjFornecedorDAO {

	@Override
	public void inserir(int idFornecedor, int idFarmacia) throws DAOException {
		Connection con;
		try {
			con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbConjFornecedor "
					+ "(idFornecedor, idFarmacia) "
					+ " VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idFornecedor);
			stmt.setInt(2, idFarmacia);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void alterar(int idFornecedor, int idFarmacia) throws DAOException {
		System.out.println("id fornec :"+idFornecedor);
		System.out.println("id fornec :"+idFarmacia);
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbconjfornecedor"
					+ " set idFarmacia=?"
					+ " where idFornecedor=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idFarmacia);
			stmt.setInt(2, idFornecedor);
			stmt.executeUpdate();
			//edi.remover(fdi.pesquisarPorFornecedor(idFornecedor).getEndereco());
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}
	
	@Override
	public void remover(int idFornecedor, int idFarmacia) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbconjfornecedor "
					+ " where idFornecedor=? and idFarmacia=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, idFornecedor);
			stmt.setLong(2, idFarmacia);
			stmt.executeUpdate();
			//edi.remover(fdi.pesquisarPorFornecedor(idFornecedor).getEndereco());
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}



}
