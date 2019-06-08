package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Endereco;

public class EnderecoDAOImpl implements EnderecoDAO{

	@Override
	public void inserir(Endereco end) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbendereco "
					+ "(cidade, UF, CEP, numero, rua, bairro) "
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getCidade());
			stmt.setString(2, end.getUf());
			stmt.setString(3, end.getCep());
			stmt.setInt(4, end.getNumero());
			stmt.setString(5, end.getRua());
			stmt.setString(6, end.getBairro());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	@Override
	public Endereco pesquisarEnderecoCliente(long cpf) throws DAOException {
		Endereco end = new Endereco();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT e.CEP, e.rua, e.numero, e.bairro, e.cidade, e.UF from tbendereco as e"
					+ "inner join tbcliente as c on c.idEndereco=e.idEndereco where cpf like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cpf + "%");
			ResultSet  rs = stmt.executeQuery();		
			end.setCep(rs.getString("CEP"));
			end.setRua(rs.getString("rua"));
			end.setNumero(rs.getInt("numero"));
			end.setBairro(rs.getString("Bairro"));
			end.setCidade(rs.getString("cidade"));
			end.setUf(rs.getString("UF"));
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return end;
	}

	@Override
	public List<Endereco> pesquisarEndereco(long cep, int num, String rua, String bairro) throws DAOException {
		List<Endereco> lista = new ArrayList<Endereco>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbendereco"
					+ " where cep like ? and num like ? and rua like ? and bairro like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cep + "%");
			stmt.setString(2, "%" + num + "%");
			stmt.setString(3, "%" + rua + "%");
			stmt.setString(4, "%" + bairro + "%");
			ResultSet  rs = stmt.executeQuery();
			Endereco end;
			while(rs.next()) {
				end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("CEP"));
				end.setRua(rs.getString("rua"));
				end.setNumero(rs.getInt("numero"));
				end.setBairro(rs.getString("Bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setUf(rs.getString("UF"));
				lista.add(end);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void alterar(Endereco end) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbendereco "
					+ "set CEP=?, rua=?, numero=?, bairro=?, cidade=?, UF=?"
					+ " where idEndereco=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getCep());
			stmt.setString(2, end.getRua());
			stmt.setLong(3, end.getNumero());
			stmt.setString(4, end.getBairro());
			stmt.setString(5, end.getCidade());
			stmt.setString(6, end.getUf());
			stmt.setInt(7, end.getIdEndereco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void remover(Endereco end) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbendereco "
					+ " where idEndereco=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, end.getIdEndereco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
