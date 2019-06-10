package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.Grupo;

public class FarmaciaProdutoDAOImpl implements FarmaciaProdutoDAO{

	@Override
	public void inserir(FarmaciaProduto fp) throws DAOException {
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbfarmaciaproduto "
					+ "(idFarmacia, idProduto, idGrupo, idSecao, qntd, precoUnit) "
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, fp.getFarmacia().getId());
			stmt.setInt(2, fp.getProduto().getId_produto());
			stmt.setInt(3, fp.getGrupo().getId_grupo());
			stmt.setInt(4, fp.getSessao().getId_sessao());
			stmt.setInt(5, fp.getQntdEstoque());
			stmt.setDouble(6, fp.getPreco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public FarmaciaProduto pesquisarFarmaciaProduto(int id_produto) throws DAOException {
		System.out.println("p - " + id_produto);
		GrupoDAOImpl gdi = new GrupoDAOImpl();
		SessaoDAOImpl sdi = new SessaoDAOImpl();
		ProdutoDAOImpl pdi = new ProdutoDAOImpl();
		FarmaciaDAOImpl fdi = new FarmaciaDAOImpl();
		FarmaciaProduto fp = new FarmaciaProduto();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbcliente where cpf like ?";
			String sql = "select * from tbfarmaciaproduto where idFarmaciaProduto=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id_produto);
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				fp.setFarmacia(fdi.pesquisarFarmacia(rs.getInt("idFarmacia")));
				fp.setIdFarmaciaProd(rs.getInt("idFarmaciaProduto"));
				fp.setGrupo(gdi.pesquisarPorGrupo(rs.getInt("idGrupo")));
				fp.setSessao(sdi.pesquisarPorSessao(rs.getInt("idSecao")));
				fp.setPreco(rs.getDouble("precoUnit"));
				fp.setProduto(pdi.consultarProduto(rs.getInt("idProduto")));
				fp.setQntdEstoque(rs.getInt("qntd"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		System.out.println("farmacia produto id - " + fp.getIdFarmaciaProd());
		return fp;
	}

	@Override
	public List<FarmaciaProduto> pesquisarFarmaciaProduto(String cont, String tipo) throws DAOException {
		System.out.println(cont);
		GrupoDAOImpl gdi = new GrupoDAOImpl();
		SessaoDAOImpl sdi = new SessaoDAOImpl();
		ProdutoDAOImpl pdi = new ProdutoDAOImpl();
		FarmaciaDAOImpl fdi = new FarmaciaDAOImpl();
		List<FarmaciaProduto> lista = new ArrayList<>();
		String sql = "";
		if(tipo.equals("NOME")) {
			sql = "select fp.idFarmaciaProduto, p.idProduto, p.categoria, p.descricao, fp.qntd, fp.precoUnit, fp.idGrupo, fp.idSecao from tbfarmaciaproduto as fp"
					+ " inner join tbproduto as p on p.idProduto=fp.idProduto where p.descricao=?";
		}else
		if(tipo.equals("CATEGORIA")) {
			sql = "select fp.idFarmaciaProduto, p.idProduto, p.categoria, p.descricao, fp.qntd, fp.precoUnit, fp.idGrupo, fp.idSecao from tbfarmaciaproduto as fp"
					+ " inner join tbproduto as p on p.idProduto=fp.idProduto where p.categoria=?";
		}else
		if(tipo.equals("FORNECEDOR")) {
			sql = "select fp.idFarmaciaProduto, p.idProduto, p.categoria, p.descricao, fp.qntd, fp.precoUnit, fp.idGrupo, fp.idSecao from tbfarmaciaproduto as fp"
					+ " inner join tbproduto as p on p.idProduto=fp.idProduto inner join tbfornecedor as f"
					+ " on f.idFornecedor=p.idFornecedor where f.nomeFantasia=?";
		}
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbcliente where cpf like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			//stmt.setString(1, "%" + cont + "%");
			stmt.setString(1, cont);
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				FarmaciaProduto fp = new FarmaciaProduto();
				//fp.setFarmacia(fdi.pesquisarFarmacia(rs.getInt("idFarmacia")));
				fp.setIdFarmaciaProd(rs.getInt("idFarmaciaProduto"));
				fp.setGrupo(gdi.pesquisarPorGrupo(rs.getInt("idGrupo")));
				fp.setSessao(sdi.pesquisarPorSessao(rs.getInt("idSecao")));
				fp.setPreco(rs.getDouble("precoUnit"));
				fp.setProduto(pdi.consultarProduto(rs.getInt("idProduto")));
				fp.setQntdEstoque(rs.getInt("qntd"));
				lista.add(fp);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}
	
	@Override
	public List<FarmaciaProduto> pesquisarFarmaciaProdutos() throws DAOException {
		GrupoDAOImpl gdi = new GrupoDAOImpl();
		SessaoDAOImpl sdi = new SessaoDAOImpl();
		ProdutoDAOImpl pdi = new ProdutoDAOImpl();
		FarmaciaDAOImpl fdi = new FarmaciaDAOImpl();
		List<FarmaciaProduto> lista = new ArrayList<>();
		String sql = "select fp.idFarmaciaProduto, p.idProduto, p.categoria, p.descricao, fp.qntd, fp.precoUnit, fp.idGrupo, fp.idSecao from tbfarmaciaproduto as fp"
					+ " inner join tbproduto as p on p.idProduto=fp.idProduto";
		
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				FarmaciaProduto fp = new FarmaciaProduto();
				//fp.setFarmacia(fdi.pesquisarFarmacia(rs.getInt("idFarmacia")));
				fp.setIdFarmaciaProd(rs.getInt("idFarmaciaProduto"));
				fp.setGrupo(gdi.pesquisarPorGrupo(rs.getInt("idGrupo")));
				fp.setSessao(sdi.pesquisarPorSessao(rs.getInt("idSecao")));
				fp.setPreco(rs.getDouble("precoUnit"));
				fp.setProduto(pdi.consultarProduto(rs.getInt("idProduto")));
				fp.setQntdEstoque(rs.getInt("qntd"));
				lista.add(fp);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void alterar(FarmaciaProduto fp) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(int id_produto) throws DAOException {
		System.out.println(id_produto);
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "delete from tbfarmaciaproduto where idFarmaciaProduto=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id_produto);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	

}
