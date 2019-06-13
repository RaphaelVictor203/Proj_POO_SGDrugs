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
import com.curso.entity.Farmacia;
import com.curso.entity.Funcao;
import com.curso.entity.Funcionario;

import javafx.scene.control.Alert;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	
	@Override
	public void inserir(Funcionario fn) throws DAOException {
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		try {
			Endereco ed = null;
			edi.inserir(fn.getEnd());
			System.out.println(edi.pesquisarEndereco(fn.getEnd().getCep(), fn.getEnd().getNumero(), fn.getEnd().getRua(), "").size());
			ed = edi.pesquisarEndereco(fn.getEnd().getCep(), fn.getEnd().getNumero(), fn.getEnd().getRua(), "").get(0);
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO tbFuncionario "
					+ "(nome, sobrenome,cpf, idFuncao, idFarmacia, idEndereco, salario, senha, dtnascimento, sexo, rg, telefone, email) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, fn.getNome());
			stmt.setString(2, fn.getSobrenome());
			stmt.setString(3, String.valueOf(fn.getCpf()));
			stmt.setInt(4,1);
			stmt.setInt(5, fn.getFarmacia().getId());
			stmt.setInt(6, ed.getIdEndereco());
			float salario = (float) fn.getSalario();
			stmt.setFloat(7,salario);
			stmt.setInt(8, 123);
			stmt.setDate(9, fn.getDt_nasc());
			stmt.setString(10, Character.toString(fn.getSexo()));
			stmt.setInt(11, (int) fn.getRg());
			stmt.setLong(12, fn.getTelefone());
			stmt.setString(13, fn.getEmail());
			stmt.executeUpdate();
			
			con.close();
		}catch(SQLException e) {
			System.out.println("Erro na conexão");
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}



	@Override
	public Funcionario pesquisarFuncionario(long cpf) throws DAOException {
		Funcionario f = new Funcionario();
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		FarmaciaDAOImpl fdi = new FarmaciaDAOImpl();
		try {
			Connection con = ConnectionManager.getInstance().getConnection();
			//String sql = "SELECT * from tbcliente where cpf like ?";
			String sql  = "select tbfuncionario.nome, tbfuncionario.dtnascimento, tbfuncionario.sobrenome, tbfuncionario.cpf, tbfuncionario.sexo"
					+ ", tbfuncionario.telefone, tbfuncionario.email, tbfuncionario.rg, tbfuncionario.salario, tbfuncionario.idFuncao, tbfuncionario.idFarmacia"  
					+" from tbfuncionario where cpf=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cpf);
			ResultSet  rs = stmt.executeQuery();		 
			while(rs.next()) {
				f.setPrimeiroNome(rs.getString("nome"));
				f.setDt_nasc(rs.getDate("dtnascimento"));
				System.out.println("teste - " + f.getDt_nasc().getYear());
				f.setCpf(rs.getLong("cpf"));
				f.setRg(rs.getLong("rg"));
				f.setSexo(rs.getString("sexo").charAt(0));
				f.setTelefone(rs.getInt("telefone"));
				f.setEmail(rs.getString("email"));
				f.setEnd(edi.pesquisarEnderecoFuncionario(cpf));
				f.setSobrenome(rs.getString("sobrenome"));
				f.setSalario(rs.getFloat("salario"));
				Farmacia frm = fdi.pesquisarFarmacia(rs.getInt("idFarmacia"));
				Funcao func = new Funcao(rs.getInt("IdFuncao"));
				f.setFarmacia(frm);
				f.setFuncao(func);
				
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return f;
	}

	@Override
	public List<Funcionario> pesquisarPorFuncionario(String nome, String cpf) throws DAOException {
		// TODO Auto-generated method stub
		String sql  = "select tbfuncionario.nome,tbfuncionario.sobrenome,tbfarmacia.statusFarmacia,tbendereco.cidade\r\n"  
		+" from bdfarmacia.tbfuncionario\r\n" 
		+" inner join bdfarmacia.tbendereco on tbendereco.idEndereco = tbfuncionario.idEndereco\r\n" 
		+" inner join bdfarmacia.tbfarmacia on tbfarmacia.idFarmacia = tbfuncionario.idFarmacia\r\n" +
		" inner join bdfarmacia.tbfuncao on  tbfuncao.idFuncao = tbfuncionario.idFarmacia;";
		
		return null;
	}

	@Override
	public void alterar(Funcionario fn) throws DAOException {
		// TODO Auto-generated method stub
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		try {
			Endereco end = fn.getEnd();
			edi.alterar(end);
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE tbFuncionario SET nome=?, sobrenome=?, idFuncao=?,"
					+ " idFarmacia=?, salario=?, cpf=?, dtnascimento=?, sexo=?, telefone=?, email=?, rg=?  where cpf=?;";
					
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, fn.getNome());
			stmt.setString(2, fn.getSobrenome());
			stmt.setInt(3,1);
			stmt.setInt(4, fn.getFarmacia().getId());
			float salario = (float) fn.getSalario();
			stmt.setFloat(5,salario);
			stmt.setLong(6, fn.getCpf());
			stmt.setDate(7, fn.getDt_nasc());
			stmt.setString(8, Character.toString(fn.getSexo()));
			stmt.setLong(9, fn.getTelefone());
			stmt.setString(10, fn.getEmail());
			stmt.setLong(11, fn.getRg());
			stmt.setLong(12, fn.getCpf());
			stmt.executeUpdate();
			
			con.close();
			
		}catch (SQLException e) {
			System.out.println("Erro na conexão");
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}
	@Override
	public void remover(long l) throws DAOException {
		// TODO Auto-generated method stub
		EnderecoDAOImpl edi = new EnderecoDAOImpl();
		try {
			Funcionario f = pesquisarFuncionario(l);
			Connection con = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM tbFuncionario WHERE cpf=?;";		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, l);
			stmt.executeUpdate();
			edi.remover(f.getEnd());
			con.close();
			
		}catch (SQLException e) {
			System.out.println("Erro na conexão");
			e.printStackTrace();
			throw new DAOException(e);
		}
		
		
	}



	@Override
	public List<Funcionario> pesquisarPorFuncionarios() throws DAOException {
		List<Funcionario> lista = new ArrayList<>();
		String sql = "select * from tbfuncionario";
		try {		
			Connection con = ConnectionManager.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet  rs = stmt.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setNome(rs.getString("nome"));
				f.setSobrenome(rs.getString("sobrenome"));
				f.setCpf(rs.getLong("cpf"));
				f.setNome(rs.getString("nome"));
				f.setSalario(rs.getFloat("salario"));
				f.setDt_nasc(rs.getDate("dtnascimento"));
				lista.add(f);
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão no banco de dados");
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

}
