package com.curso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Fornecedor;
import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;
import com.curso.entity.ProblemaSaude;

public class FornecedorDAOImpl implements FornecedorDAO{
	EnderecoDAOImpl edi = new EnderecoDAOImpl();
	
	@Override
	public void inserir(Fornecedor fr) throws DAOException {
		try {
			int idEndereco = 0;
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbFornecedor "
					+ "(nome_fantasia,cnpj,telefone,farmacia,id_endereco) "
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, fr.getNome_fantasia());
			stmt.setLong(2, fr.getCnpj());
			stmt.setLong(3, fr.getTelefone());
			stmt.setString(4, fr.getFarmacia().getUnidade());
			stmt.setInt(5, idEndereco);
			stmt.executeUpdate();
			edi.inserir(fr.getEndereco());
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}


	@Override
	public Fornecedor pesquisarPorFornecedor(long cnpj) throws DAOException {
		Fornecedor fr = new Fornecedor();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbFornecedor where cnpj like ?";
			String sql = "SELECT c.nome, c.cnpj, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbFornecedor as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where c.cnpj like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cnpj + "%");
			ResultSet  rs = stmt.executeQuery();		 
			fr.setNome_fantasia(rs.getString("nome_fantasia"));
			fr.setCnpj(rs.getLong("cnpj"));
			fr.setTelefone(rs.getLong("telefone"));
			fr.setFarmacia(new Farmacia(rs.getString("farmacia")));
			Endereco end = new Endereco();
			end.setIdEndereco(rs.getInt("idEndereco"));
			end.setCep(rs.getString("cep"));
			end.setNumero(rs.getInt("numero"));
			end.setRua(rs.getString("rua"));
			end.setBairro(rs.getString("bairro"));
			end.setCidade(rs.getString("cidade"));
			end.setUf(rs.getString("UF"));
			fr.setEndereco(end);
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return fr;
	}

	@Override
	public List<Fornecedor> pesquisarPorFornecedor(String nome, long Cnpj, String uf, String cidade) throws DAOException {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "";
		
		/*if(tipo.equals("CIDADE")) {
			sql = "SELECT c.nome, c.cnpj, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbFornecedor as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where e.cidade like ?";
			
		}else {
			sql = "SELECT c.nome, c.cnpj, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbFornecedor as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where c.nome like ?";
		}*/
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Fornecedor fr = new Fornecedor();
				fr.setNome_fantasia(rs.getString("nome_fantasia"));
				fr.setCnpj(rs.getLong("cnpj"));
				fr.setTelefone(rs.getLong("telefone"));
				//Farmacia frm = new Farmacia();
				//frm.setId(rs.getInt("id_farmacia"));
				//fr.setFarmacia(frm);
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("cep"));
				end.setNumero(rs.getInt("numero"));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setUf(rs.getString("UF"));
				fr.setEndereco(end);

				lista.add(fr);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}


	@Override
	public void alterar(Fornecedor Fornecedor) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbFornecedor "
					+ "set nome_fantasia=?, cnpj=? "
					+ " where cnpj=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Fornecedor.getNome_fantasia());
			stmt.setLong(2, Fornecedor.getCnpj());

			stmt.executeUpdate();
			con.close();
			edi.alterar(Fornecedor.getEndereco());
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}


	@Override
	public void remover(long cnpj) throws DAOException {
		try {
			Fornecedor fr = pesquisarPorFornecedor(cnpj);
			edi.remover(fr.getEndereco());
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbFornecedor "
					+ " where cnpj=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cnpj);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
