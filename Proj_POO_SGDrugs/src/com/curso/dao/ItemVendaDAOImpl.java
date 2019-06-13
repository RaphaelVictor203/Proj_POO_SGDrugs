package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;

public class ItemVendaDAOImpl implements ItemVendaDAO {

	@Override
	public void inserir(ItemVenda obj, Venda v) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbitemvenda "
					+ "(idFarmaciaProduto, quantidade, subtotal, idVenda) "
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, obj.getProduto().getIdFarmaciaProd());
			stmt.setInt(2, obj.getQntd());
			stmt.setDouble(3, obj.getSubtotal());
			stmt.setInt(4, v.getId_venda());
			stmt.executeUpdate();
		
			
			
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}	
	}

}
