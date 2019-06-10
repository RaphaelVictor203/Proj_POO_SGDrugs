package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Grupo;
import com.curso.entity.Sessao;

public class GrupoDAOImpl implements GrupoDAO {

	@Override
	public void inserir(Grupo g) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbgrupo "
					+ "(nomeGrupo) "
					+ " VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, g.getDesc_grupo());
			stmt.executeUpdate();

			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Grupo pesquisarPorGrupo(String desc) throws DAOException {
		Grupo g = null;
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbsecao where nomeGrupo=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + desc + "%");
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				g = new Grupo(rs.getInt("idSecao"), rs.getString("descSecao"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return g;
	}

	@Override
	public void alterar(Grupo grupo) throws DAOException {
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbgrupo "
					+ "set nomeGrupo=?"
					+ " where idGrupo=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, grupo.getDesc_grupo());
			stmt.setLong(2, grupo.getId_grupo());
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
			String sql = "delete from tbgrupo "
					+ " where idGrupo=?";
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
	public List<Grupo> pesquisarPorGrupos() throws DAOException {
		List<Grupo> lista = new ArrayList<Grupo>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbgrupo";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();		 
			while (rs.next()) { 
				lista.add(new Grupo(rs.getInt("idGrupo"), rs.getString("nomeGrupo")));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public Grupo pesquisarPorGrupo(int id) throws DAOException {
		Grupo g = null;
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbgrupo where idGrupo=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				g = new Grupo(rs.getInt("idSecao"), rs.getString("descSecao"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return g;
	}

}
