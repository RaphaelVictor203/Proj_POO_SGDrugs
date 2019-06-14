package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.curso.entity.FormaPagto;
import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;

public class FormaPagtoDAOImpl implements FormaPagtoDAO {

	@Override
	public void inserir(FormaPagto fp, int idVenda) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbformapagto "
					+ "(idVenda, valor, formapagto) "
					+ " VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, idVenda);
			stmt.setDouble(2, fp.getValor());
			stmt.setString(3, fp.getFormaPagamento());
			stmt.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
