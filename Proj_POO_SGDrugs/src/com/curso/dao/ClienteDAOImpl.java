package com.curso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.ProblemaSaude;

public class ClienteDAOImpl implements ClienteDAO{

	EnderecoDAOImpl edi = new EnderecoDAOImpl();
	ProblemaSaudeDAOImpl psdi = new ProblemaSaudeDAOImpl();
	
	@Override
	public void inserir(Cliente cl) throws DAOException {
		try {
			int idEndereco = 0;
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbcliente "
					+ "(nome, cpf, rg, sexo, telefone, email, cartaoSus) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cl.getPrimeiroNome());
			stmt.setLong(2, cl.getCpf());
			stmt.setLong(3, cl.getRg());
			stmt.setString(4, Character.toString(cl.getSexo()));
			stmt.setInt(5, idEndereco);
			stmt.setString(6, cl.getEmail());
			stmt.setLong(7, cl.getCartaoSUS());
			stmt.executeUpdate();
			edi.inserir(cl.getEnd());
			psdi.inserirProbCliente(cl);
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}


	@Override
	public Cliente pesquisarPorCliente(long cpf) throws DAOException {
		Cliente cl = new Cliente();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbcliente where cpf like ?";
			String sql = "SELECT c.nome, c.cpf, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbcliente as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where c.cpf like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cpf + "%");
			ResultSet  rs = stmt.executeQuery();		 
			cl.setPrimeiroNome(rs.getString("nome"));
			cl.setCpf(rs.getLong("cpf"));
			cl.setRg(rs.getLong("rg"));
			cl.setSexo(rs.getString("sexo").charAt(0));
			cl.setTelefone(rs.getInt("telefone"));
			cl.setEmail(rs.getString("email"));
			cl.setCartaoSUS(rs.getLong("cartaoSus"));
			Endereco end = new Endereco();
			end.setIdEndereco(rs.getInt("idEndereco"));
			end.setCep(rs.getString("cep"));
			end.setNumero(rs.getInt("numero"));
			end.setRua(rs.getString("rua"));
			end.setBairro(rs.getString("bairro"));
			end.setCidade(rs.getString("cidade"));
			end.setUf(rs.getString("UF"));
			cl.setEnd(end);
			cl.setProblemasSaude(psdi.pesquisarPorProblemas(cl.getCpf()));
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return cl;
	}

	@Override
	public List<Cliente> pesquisarPorCliente(String nome, String tipo) throws DAOException {
		List<Cliente> lista = new ArrayList<>();
		String sql = "";
		
		if(tipo.equals("CIDADE")) {
			sql = "SELECT c.nome, c.cpf, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbcliente as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where e.cidade like ?";
			
		}else {
			sql = "SELECT c.nome, c.cpf, c.rg, c.sexo, c.telefone, c.email, c.cartaoSus, e.idEndereco, e.CEP, e.numero,"
					+ "e.rua, e.bairro, e.cidade, e.UF from tbcliente as c "
					+ "inner join tbendereco as e on e.idEndereco=c.idEndereco where c.nome like ?";
		}
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Cliente cl = new Cliente();
				cl.setPrimeiroNome(rs.getString("nome"));
				cl.setCpf(rs.getLong("cpf"));
				cl.setRg(rs.getLong("rg"));
				cl.setSexo(rs.getString("sexo").charAt(0));
				cl.setTelefone(rs.getInt("telefone"));
				cl.setEmail(rs.getString("email"));
				cl.setCartaoSUS(rs.getLong("cartaoSus"));
				Endereco end = new Endereco();
				end.setIdEndereco(rs.getInt("idEndereco"));
				end.setCep(rs.getString("cep"));
				end.setNumero(rs.getInt("numero"));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setUf(rs.getString("UF"));
				cl.setEnd(end);
				cl.setProblemasSaude(psdi.pesquisarPorProblemas(cl.getCpf()));
				lista.add(cl);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}


	@Override
	public void alterar(Cliente cliente) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "update tbcliente "
					+ "set nome=?, cpf=?, rg=?, sexo=?, telefone=?, email=?, cartaoSus=? "
					+ " where cpf=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getPrimeiroNome());
			stmt.setLong(2, cliente.getCpf());
			stmt.setLong(3, cliente.getRg());
			stmt.setString(4, Character.toString(cliente.getSexo()));
			stmt.setLong(5, cliente.getTelefone());
			stmt.setString(7, cliente.getEmail());
			stmt.setLong(8, cliente.getCartaoSUS());
			stmt.setLong(9, cliente.getCpf());
			stmt.executeUpdate();
			con.close();
			edi.alterar(cliente.getEnd());
			for(ProblemaSaude ps : cliente.getProblemasSaude()) {
				psdi.alterar(ps);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}


	@Override
	public void remover(long cpf) throws DAOException {
		try {
			Cliente cl = pesquisarPorCliente(cpf);
			for(ProblemaSaude ps : cl.getProblemasSaude()) {
				psdi.removerProblemaCliente(cpf, ps.getId_problema());
			}
			edi.remover(cl.getEnd());
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbcliente "
					+ " where cpf=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cpf);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
