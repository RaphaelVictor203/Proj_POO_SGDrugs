package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.curso.entity.Funcionario;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public void inserir(int idFuncionario, String nomeLogin, String senha, String nivel) throws DAOException {
		Connection con;
		try {
			con = ConnectionManager.getInstance().getConnection();
			String sql = "insert into tblog(idFuncionario, nomeUsuario, senha, nivel) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncionario);
			ps.setString(2, nomeLogin);
			ps.setString(3, senha);
			ps.setString(4, nivel);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Funcionario pesquisarPorConta(String nomeLogin, String senha) throws DAOException {
		Funcionario f = null;
		Connection con;
		try {
			con = ConnectionManager.getInstance().getConnection();
			String sql = "select * from tblog where nomeUsuario=? and senha=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nomeLogin);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FuncionarioDAO fdi = new FuncionarioDAOImpl();
				f = fdi.pesquisarFuncionario(rs.getInt("idFuncionario"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
		
	}

}
