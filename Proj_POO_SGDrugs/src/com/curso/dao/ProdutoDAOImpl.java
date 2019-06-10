package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;

public class ProdutoDAOImpl implements ProdutoDAO {


	@Override
	public void inserirProduto(Produto p) throws DAOException {
			
		try {
	
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbProduto (descricao, categoria, idFornecedor) VALUES(?, ?, ?)";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, p.getNome());
			cmd.setString(2, p.getCategoria());
			cmd.setInt(3, p.getFornecedor().getID());
			cmd.executeUpdate();
			cmd.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na Conex�o");
			throw new DAOException(e);
		}
	}

	@Override
	public List<Produto> consultarProduto(String desc) throws DAOException {
		
		List<Produto> lista = new ArrayList<>();
		
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT idProduto, descricao, categoria FROM tbProduto Where descricao like ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, "%" + desc + "%");
	
			ResultSet  rs = cmd.executeQuery();	
			while(rs.next()) {
				Produto p = new Produto();
				
				p.setId_produto(rs.getInt("idProduto"));
				p.setNome(rs.getString("descricao"));
				p.setCategoria(rs.getString("categoria"));
				lista.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro no Produto");
			throw new DAOException(e);
		}
			
		return lista;
	}
	

	@Override
	public void alterarProduto(Produto p) {

	}

	@Override
	public void excluirProduto(Produto p) {

	}

	@Override
	public Produto consultarProduto(int id) throws DAOException {
		FornecedorDAOImpl fdi = new FornecedorDAOImpl();
		Produto p = new Produto();
		String sql = "select * from tbproduto;";
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				p.setId_produto(rs.getInt("idProduto"));
				p.setNome(rs.getString("descricao"));
				p.setFornecedor(fdi.pesquisarPorFornecedor(rs.getInt("idFornecedor")));
				p.setCategoria("categoria");
			}
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return p;
	}

	@Override
	public List<Produto> consultarProdutosCad() throws DAOException {
		FornecedorDAOImpl fdi = new FornecedorDAOImpl();
		List<Produto> lista = new ArrayList<>();
		String sql = "select * from tbproduto;";
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Produto p = new Produto();
				p.setId_produto(rs.getInt("idProduto"));
				p.setNome(rs.getString("descricao"));
				p.setFornecedor(fdi.pesquisarPorFornecedor(rs.getInt("idFornecedor")));
				p.setCategoria("categoria");
				lista.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conex�o no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}


}
