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
	public boolean inserir(Endereco end) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbendereco "
					+ "(cidade, estado, CEP, numero, rua, bairro) "
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(2, end.getCidade());
			stmt.setString(1, end.getUf());
			stmt.setString(3, end.getCep());
			stmt.setInt(4, end.getNumero());
			stmt.setString(5, end.getRua());
			stmt.setString(6, end.getBairro());
			if(stmt.executeUpdate() == 1) {
				con.close();
				return true;
			}else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	@Override
	public Endereco pesquisarEnderecoCliente(long cpf) throws DAOException {
		Endereco end = new Endereco();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT e.CEP, e.rua, e.numero, e.bairro, e.cidade, e.estado from tbendereco as e"
					+ "inner join tbcliente as c on c.idEndereco=e.idEndereco where c.cpf like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cpf + "%");
			ResultSet  rs = stmt.executeQuery();		
			end.setCep(rs.getString("CEP"));
			end.setRua(rs.getString("rua"));
			end.setNumero(rs.getInt("numero"));
			end.setBairro(rs.getString("Bairro"));
			end.setCidade(rs.getString("estado"));
			end.setUf(rs.getString("cidade"));
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return end;
	}

	@Override
	public List<Endereco> pesquisarEndereco(String cep, int num, String rua, String bairro) throws DAOException {
		long cp = Long.parseLong(cep);
		List<Endereco> lista = new ArrayList<Endereco>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbendereco"
					+ " where cep like ? and numero like ? and rua like ? and bairro like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + Long.toString(cp) + "%");
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
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
				lista.add(end);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
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
					+ "set CEP=?, rua=?, numero=?, bairro=?, cidade=?, estado=?"
					+ " where idEndereco=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getCep());
			stmt.setString(2, end.getRua());
			stmt.setLong(3, end.getNumero());
			stmt.setString(4, end.getBairro());
			stmt.setString(6, end.getCidade());
			stmt.setString(5, end.getUf());
			stmt.setInt(7, end.getIdEndereco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
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
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Endereco pesquisarEnderecoFornecedor(long cnpj) throws DAOException {
		Endereco end = new Endereco();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado from tbendereco as e"
					+ " inner join tbfornecedor as f on f.idEndereco=e.idEndereco where f.cnpj like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cnpj + "%");
			ResultSet  rs = stmt.executeQuery();
			while(rs.next()) {
				end.setCep(Integer.toString(rs.getInt("cep")));
				end.setRua(rs.getString("rua"));
				end.setNumero(rs.getInt("numero"));
				end.setBairro(rs.getString("Bairro"));
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return end;
	}

	@Override
	public Endereco pesquisarEnderecoFarmacia(int id) throws DAOException {
		Endereco end = new Endereco();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado from tbendereco as e"
					+ " inner join tbfarmacia as f on f.idEndereco=e.idEndereco where f.idFarmacia like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet  rs = stmt.executeQuery();
			while(rs.next()) {
				end.setCep(Integer.toString(rs.getInt("cep")));
				end.setRua(rs.getString("rua"));
				end.setNumero(rs.getInt("numero"));
				end.setBairro(rs.getString("Bairro"));
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return end;
	}

}
