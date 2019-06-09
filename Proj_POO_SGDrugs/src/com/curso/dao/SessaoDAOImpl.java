package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.ProblemaSaude;
import com.curso.entity.Sessao;

public class SessaoDAOImpl implements SessaoDAO {

	@Override
	public void inserir(Sessao s) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbsecao "
					+ "(descSecao) "
					+ " VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, s.getDesc_sessao());
			stmt.executeUpdate();

			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Sessao pesquisarPorSessao(String desc) throws DAOException {
		Sessao s = null;
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbsecao where descSecao=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + desc + "%");
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				s = new Sessao(rs.getInt("idSecao"), rs.getString("descSecao"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return s;
	}

	@Override
	public void alterar(Sessao sessao) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbsecao "
					+ "set descSecao=?"
					+ " where idSecao=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sessao.getDesc_sessao());
			stmt.setLong(2, sessao.getId_sessao());
			stmt.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void remover(int id) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbsessao "
					+ " where idSessao=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public List<Sessao> pesquisarPorSessoes() throws DAOException {
		List<Sessao> lista = new ArrayList<Sessao>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbsecao";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();		 
			while (rs.next()) { 
				lista.add(new Sessao(rs.getInt("idSecao"), rs.getString("descSecao")));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

}
