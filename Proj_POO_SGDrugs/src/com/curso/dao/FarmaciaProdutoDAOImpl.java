package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.curso.entity.FarmaciaProduto;

public class FarmaciaProdutoDAOImpl implements FarmaciaProdutoDAO{

	@Override
	public void inserir(FarmaciaProduto fp) throws DAOException {
		/*try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbfarmaciaproduto "
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
		}*/
	}

	@Override
	public FarmaciaProduto pesquisarFarmaciaProduto(int id_produto) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FarmaciaProduto> pesquisarFarmaciaProduto(String cont, String tipo) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(FarmaciaProduto fp) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(int id_produto) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
