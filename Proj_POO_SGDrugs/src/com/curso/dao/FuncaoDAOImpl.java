package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Funcao;

public class FuncaoDAOImpl implements FuncaoDAO {

	@Override
	public List<Funcao> pesquisarPorFuncoes() throws DAOException {
		List<Funcao> lista = new ArrayList<>();
		String sql = "select * from tbfuncao";
	
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Funcao f = new Funcao();
				f.setDescFuncao(rs.getString("descFuncao"));
				f.setIdFuncao(rs.getInt("idFuncao"));
				lista.add(f);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public Funcao pesquisarPorFuncao(int id) throws DAOException {
		Funcao f = null;
		String sql = "select * from tbfuncao where idFuncao=?";
	
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				f = new Funcao();
				f.setDescFuncao(rs.getString("descFuncao"));
				f.setIdFuncao(rs.getInt("idFuncao"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return f;
	}

}
