package com.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.FormaPagto;
import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;

public class VendaDAOImpl implements VendaDAO {

	@Override
	public void inserir(Venda v) throws DAOException {
		ItemVendaDAO ivo = new ItemVendaDAOImpl();
		FormaPagtoDAO fpi = new FormaPagtoDAOImpl();
		FarmaciaProdutoDAO fpdi = new FarmaciaProdutoDAOImpl();
		try {
			
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbvenda "
					+ "(precoTotal, idCliente, idFuncionario, idFarmacia) "
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, v.returnPrecoTotal());
			stmt.setInt(2, v.getCliente().getId());
			stmt.setInt(3, v.getFuncionario().getID());
			stmt.setInt(4, v.getFuncionario().getFarmacia().getId());
			stmt.executeUpdate();
		
			Venda ultV = pesquisarPorUltVenda();
			
			for(ItemVenda iv : v.getItems()) {
				ivo.inserir(iv, ultV);
				fpdi.attQntdProd(iv.getQntd(), iv.getProduto().getIdFarmaciaProd());
			}
			
			for(FormaPagto fp : v.getFormasPagto()) {
				fpi.inserir(fp, ultV.getId_venda());
			}
			
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Venda pesquisarPorUltVenda() throws DAOException {
		Venda v = null;
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbcliente where cpf like ?";
			String sql = "SELECT * from tbvenda where idVenda=(select max(idVenda) from tbvenda)";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();		
			while(rs.next()) {
				v = new Venda();
				v.setId_venda(rs.getInt("idVenda"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return v;
	}

}
