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
					+ "(idFornecedor,idFarmacia) "
					+ " VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idFornecedor);
			stmt.setInt(2, idFarmacia);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void remover(int idFornecedor, int idFarmacia) throws DAOException {
		//EnderecoDAO edi = new EnderecoDAOImpl();
		FornecedorDAO fdi = new FornecedorDAOImpl();
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
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
