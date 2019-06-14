package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;

public class FarmaciaDAOImpl implements FarmaciaDAO{
	EnderecoDAOImpl edi = new EnderecoDAOImpl();
	
	@Override
	public boolean inserir(Farmacia frm) throws DAOException {
		try {
			System.out.println(edi.pesquisarEndereco(frm.getEndereco().getCep(), frm.getEndereco().getNumero(), frm.getEndereco().getRua(), frm.getEndereco().getBairro()).size());
			Endereco ed = null;
			edi.inserir(frm.getEndereco());
			System.out.println(frm.getEndereco().getCep() + " - " + frm.getEndereco().getNumero() + " - " + frm.getEndereco().getRua() + " - " + frm.getEndereco().getBairro());
			ed = edi.pesquisarEndereco(frm.getEndereco().getCep(), frm.getEndereco().getNumero(), frm.getEndereco().getRua(), frm.getEndereco().getBairro()).get(0);
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbFarmacia "
					+ "(idEndereco, unidade, statusFarmacia) "
					+ " VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, ed.getIdEndereco());
			stmt.setString(2, frm.getUnidade());
			stmt.setString(3,frm.getStatus());
			
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
	public Farmacia pesquisarFarmaciaFornecedor(long cnpj) throws DAOException {
		Farmacia frm = new Farmacia();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			/*String sql = "SELECT f.idFarmacia,f.unidade,f.statusFarmacia,e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.estado,e.cidade "
					+ "from tbFarmacia  f inner join tbEndereco  e on f.idEndereco=e.idEndereco inner join tbConjFornecedor cf on "
					+ "cf.idFarmacia = f.idFarmacia inner join tbFornecedor tf on tf.idFornecedor = cf.idFornecedor"
					+ "where tf.cnpj = ?";*/
			String sql = "SELECT f.idFarmacia,f.unidade,f.statusFarmacia,e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.estado,e.cidade "
					+ "from tbFarmacia  f inner join tbEndereco  e on f.idEndereco=e.idEndereco inner join tbConjFornecedor cf on "
					+ "cf.idFarmacia = f.idFarmacia inner join tbFornecedor tf on tf.idFornecedor = cf.idFornecedor "
					+ "where tf.cnpj = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cnpj );
			ResultSet  rs = stmt.executeQuery();
			while(rs.next()){
				frm.setId(rs.getInt("idFarmacia"));
				frm.setUnidade(rs.getString("unidade"));
				frm.setStatus(rs.getString("statusFarmacia"));
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("cep"));
				end.setNumero(rs.getInt("numero"));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
				frm.setEndereco(end);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return frm;
	}

	@Override
	public List<Farmacia> pesquisarFarmacia(String nome) throws DAOException {
		List<Farmacia> lista = new ArrayList<Farmacia>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT f.idFarmacia,f.unidade,f.statusFarmacia,e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.estado,e.cidade"
					+ "from tbFarmacia f inner join tbEndereco e on f.idEndereco=e.idEndereco "
					+ "where f.unidade like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet  rs = stmt.executeQuery();
			Farmacia frm;
			while(rs.next()) {
				frm = new Farmacia();
				frm.setId(rs.getInt("idFarmacia"));
				
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("cep"));
				end.setNumero(rs.getInt("numero"));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
				frm.setEndereco(end);
				frm.setUnidade(rs.getString("unidade"));
				frm.setStatus(rs.getString("statusFarmacia"));
			
				lista.add(frm);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void alterar(Farmacia frm) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbFarmacia "
					+ "set idEndereco=?, unidade=? , statusFarmacia=?"
					+ " where idFarmacia=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, frm.getEndereco().getIdEndereco());
			stmt.setString(2, frm.getUnidade());
			stmt.setString(3, frm.getStatus());
			stmt.setInt(4, frm.getId());

			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}

	@Override
	public void remover(Farmacia frm) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbFarmacia "
					+ " where idFarmacia=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, frm.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Farmacia pesquisarFarmacia(int id) throws DAOException {
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		Farmacia frm = new Farmacia();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbfarmacia where idFarmacia=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, id );
			ResultSet  rs = stmt.executeQuery();	
			while(rs.next()) {
				frm.setId(rs.getInt("idFarmacia"));
				frm.setUnidade(rs.getString("unidade"));
				frm.setStatus(rs.getString("statusFarmacia"));
				frm.setEndereco(edi.pesquisarEnderecoFarmacia(rs.getInt("idFarmacia")));
			}			
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return frm;
	}

	@Override
	public List<Farmacia> pesquisarFarmacia() throws DAOException {
		List<Farmacia> lista = new ArrayList<Farmacia>();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT f.idFarmacia,f.unidade,f.statusFarmacia,e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.estado,e.cidade"
					+ " from tbFarmacia f inner join tbEndereco e on f.idEndereco=e.idEndereco";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			Farmacia frm;
			while(rs.next()) {
				frm = new Farmacia();
				frm.setId(rs.getInt("idFarmacia"));
				
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("cep"));
				end.setNumero(rs.getInt("numero"));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("estado"));
				end.setUf(rs.getString("cidade"));
				frm.setEndereco(end);
				frm.setUnidade(rs.getString("unidade"));
				frm.setStatus(rs.getString("statusFarmacia"));
			
				lista.add(frm);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

}
