package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.ProblemaSaude;

public class ProblemaSaudeDAOImpl implements ProblemaSaudeDAO{

	@Override
	public void inserir(ProblemaSaude ps) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbproblemasaude "
					+ "(descProblema, tipo) "
					+ " VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ps.getDesc_problema());
			stmt.setString(2, ps.getTipo());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void inserirProbCliente(Cliente cl) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql;
			PreparedStatement stmt;
			for( ProblemaSaude ps : cl.getProblemasSaude()) {
				sql = "INSERT INTO tbconjproblemas "
						+ "(idProblema, cpfCliente) "
						+ " VALUES (?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, ps.getId_problema());
				stmt.setLong(2, cl.getCpf());
				stmt.executeUpdate();
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	@Override
	public ProblemaSaude pesquisarPorProblema(int id) throws DAOException {
		ProblemaSaude ps = new ProblemaSaude();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbproblemasaude where idProblema like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet  rs = stmt.executeQuery();		 
			ps.setId_problema(rs.getInt("idProblema"));
			ps.setDesc_problema(rs.getString("descProblema"));
			ps.setTipo(rs.getString("tipo"));
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return ps;
	}

	@Override
	public List<ProblemaSaude> pesquisarPorProblemas(long cpf) throws DAOException {
		List<ProblemaSaude> lista = new ArrayList<ProblemaSaude>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT p.idProblema, p.descProblema, p.tipo from tbconjproblemas as c"
					+ " inner join tbproblemasaude as p on p.idProblema=c.idProblema where c.cpfCliente like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cpf + "%");
			ResultSet  rs = stmt.executeQuery();
			ProblemaSaude ps;
			while(rs.next()) {
				ps = new ProblemaSaude();
				ps.setId_problema(rs.getInt("idProblema"));
				ps.setDesc_problema(rs.getString("descProblema"));
				ps.setTipo(rs.getString("tipo"));
				lista.add(ps);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}	
	
	public ProblemaSaude pesquisarPorProblemas(String desc) throws DAOException {
		ProblemaSaude ps = new ProblemaSaude();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbproblemasaude where descProblema like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + desc + "%");
			ResultSet  rs = stmt.executeQuery();
			while(rs.next()) {
				ps = new ProblemaSaude();
				ps.setId_problema(rs.getInt("idProblema"));
				ps.setDesc_problema(rs.getString("descProblema"));
				ps.setTipo(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return ps;
	}
	
	@Override
	public void alterar(ProblemaSaude problema) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbproblemasaude "
					+ "set descProblema=?, tipo=?"
					+ " where idProblema=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, problema.getDesc_problema());
			stmt.setString(2, problema.getTipo());
			stmt.setInt(3, problema.getId_problema());
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
			String sql = "remove from tbproblemasaude where id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	@Override
	public void removerProblemaCliente(long cpf, int id) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbconjproblemas where cpfCliente=? and idProblema=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cpf);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	

	

}
