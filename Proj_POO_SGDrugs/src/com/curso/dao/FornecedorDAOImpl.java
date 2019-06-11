
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
	FarmaciaDAOImpl far = new FarmaciaDAOImpl();
	@Override
	public void inserir(Fornecedor fr) throws DAOException {
		try {
			System.out.println(edi.pesquisarEndereco(fr.getEndereco().getCep(), fr.getEndereco().getNumero(), fr.getEndereco().getRua(), fr.getEndereco().getBairro()).size());
			Endereco ed = null;
			edi.inserir(fr.getEndereco());
			System.out.println(fr.getEndereco().getCep() + " - " + fr.getEndereco().getNumero() + " - " + fr.getEndereco().getRua() + " - " + fr.getEndereco().getBairro());
			ed = edi.pesquisarEndereco(fr.getEndereco().getCep(), fr.getEndereco().getNumero(), fr.getEndereco().getRua(), fr.getEndereco().getBairro()).get(0);
			
			System.out.println(edi.pesquisarEndereco(fr.getEndereco().getCep(), fr.getEndereco().getNumero(), fr.getEndereco().getRua(), fr.getEndereco().getBairro()).size());
			Farmacia fa = null;
			far.inserir(fr.getFarmacia());
			System.out.println(fr.getFarmacia().getEndereco()+ " - " + fr.getFarmacia().getUnidade() + " - " + fr.getFarmacia().getStatus());
			fa = far.pesquisarFarmaciaFornecedor(fr.getCnpj());
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbFornecedor "
					+ "(nome_fantasia,cnpj,telefone,id_farmacia,id_endereco) "
					+ " VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, fr.getNome_fantasia());
			stmt.setLong(2, fr.getCnpj());
			stmt.setLong(3, fr.getTelefone());
			stmt.setInt(4, fa.getId());
			stmt.setInt(5, ed.getIdEndereco());
			stmt.executeUpdate();
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
			String sql = "SELECT f.idFornecedor,f.nomeFantasia,f.telefone,f.cnpj,cf.idFarmacia,"
					+ " efrm.idEndereco idEndFarm, efrm.cep cepFarm, efrm.numero numFarm, efrm.rua ruaFarm,"
					+ " efrm.bairro bairroFarm, efrm.cidade cidadeFarm, efrm.uf ufFarm,"
					+ " e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.cidade,e.uf"
					+ " from tbFornecedor f inner join tbendereco e on e.idEndereco=f.idEndereco"
					+ " inner join tbConjFornecedor cf on cf.idFornecedor = f.idFornecedor"
					+ " inner join tbFarmacia fr on fr.idFarmacia = cf.idFarmacia"
					+ " inner join tbEndereco efrm on efrm.idEndereco = fr.idEndereco"
					+ " where f.cnpj = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cnpj );
			ResultSet  rs = stmt.executeQuery();		 
			fr.setNome_fantasia(rs.getString("nomeFantasia"));
			fr.setCnpj(rs.getLong("cnpj"));
			fr.setTelefone(rs.getLong("telefone"));

			Farmacia frm = new Farmacia();
			frm.setId(rs.getInt("idFarmacia"));
			Endereco endFarm = new Endereco();
			endFarm.setIdEndereco(rs.getInt("idEndFarm"));
			endFarm.setCep(rs.getString("cepFarm"));
			endFarm.setNumero(rs.getInt("numFarm"));
			endFarm.setRua(rs.getString("ruaFarm"));
			endFarm.setBairro(rs.getString("bairroFarm"));
			endFarm.setCidade(rs.getString("cidadeFarm"));
			endFarm.setUf(rs.getString("ufFarm"));
			frm.setEndereco(endFarm);
			fr.setFarmacia(frm);
			
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
	public List<Fornecedor> pesquisarPorFornecedor(String nome) throws DAOException {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "SELECT f.idFornecedor,f.nomeFantasia,f.telefone,f.cnpj,cf.idFarmacia,"
				+ " efrm.idEndereco idEndFarm, efrm.cep cepFarm, efrm.numero numFarm, efrm.rua ruaFarm,"
				+ " efrm.bairro bairroFarm, efrm.cidade cidadeFarm, efrm.uf ufFarm,"
				+ " e.idEndereco,e.cep,e.numero,e.rua,e.bairro,e.cidade,e.uf"
				+ " from tbFornecedor f inner join tbendereco e on e.idEndereco=f.idEndereco"
				+ " inner join tbConjFornecedor cf on cf.idFornecedor = f.idFornecedor"
				+ " inner join tbFarmacia fr on fr.idFarmacia = cf.idFarmacia"
				+ " inner join tbEndereco efrm on efrm.idEndereco = fr.idEndereco"
				+ " where f.nomeFantasia like ?";
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Fornecedor fr = new Fornecedor();
				fr.setNome_fantasia(rs.getString("nomeFantasia"));
				fr.setCnpj(rs.getLong("cnpj"));
				fr.setTelefone(rs.getLong("telefone"));

				Farmacia frm = new Farmacia();
				frm.setId(rs.getInt("idFarmacia"));
				Endereco endFarm = new Endereco();
				endFarm.setIdEndereco(rs.getInt("idEndFarm"));
				endFarm.setCep(rs.getString("cepFarm"));
				endFarm.setNumero(rs.getInt("numFarm"));
				endFarm.setRua(rs.getString("ruaFarm"));
				endFarm.setBairro(rs.getString("bairroFarm"));
				endFarm.setCidade(rs.getString("cidadeFarm"));
				endFarm.setUf(rs.getString("ufFarm"));
				frm.setEndereco(endFarm);
				fr.setFarmacia(frm);
				
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
					+ "set nomeFantasia=?, telefone=?"
					+ " where cnpj=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Fornecedor.getNome_fantasia());
			stmt.setLong(2,Fornecedor.getTelefone());
			stmt.setLong(4, Fornecedor.getCnpj());
			
			stmt.executeUpdate();
			con.close();
			edi.alterar(Fornecedor.getEndereco());
			far.alterar(Fornecedor.getFarmacia());
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
			far.remover(fr.getFarmacia());
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


	@Override
	public Fornecedor pesquisarPorFornecedor(int id) throws DAOException {
		Fornecedor fr = new Fornecedor();
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * from tbfornecedor where idFornecedor=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				fr.setNome_fantasia(rs.getString("nomeFantasia"));
				fr.setCnpj(rs.getLong("cnpj"));
				fr.setTelefone(rs.getLong("telefone"));
        fr.setEndereco(edi.pesquisarEnderecoFornecedor(rs.getInt("idEndereco")));      
			}
      
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return fr;
	}

}

