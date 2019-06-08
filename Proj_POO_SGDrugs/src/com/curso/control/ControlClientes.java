package com.curso.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.curso.dao.ClienteDAOImpl;
import com.curso.dao.DAOException;
import com.curso.dao.ProblemaSaudeDAOImpl;
import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.ProblemaSaude;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlClientes {
	
	private List<Cliente> clientesCadastrados;
	private List<Endereco> enderecoCadastrados;
	private List<ProblemaSaude> problemasCadastrados;
	private ObservableList<Cliente> dataList = FXCollections.observableArrayList();
	private ObservableList<ProblemaSaude> dataListPS = FXCollections.observableArrayList();
	public static Cliente clientSel = new Cliente();
	private ClienteDAOImpl cdi = new ClienteDAOImpl();
	private ProblemaSaudeDAOImpl psdi = new ProblemaSaudeDAOImpl();
	
	public ControlClientes() {
		this.clientesCadastrados = new ArrayList<Cliente>();
		this.enderecoCadastrados = new ArrayList<Endereco>();
		this.problemasCadastrados = new ArrayList<ProblemaSaude>();
		this.problemasCadastrados.add(new ProblemaSaude(1, "Alergia", "cigarro"));
		this.problemasCadastrados.add(new ProblemaSaude(2, "Doença", "HIV"));
		this.problemasCadastrados.add(new ProblemaSaude(3, "Alergia", "glutén"));
		this.problemasCadastrados.add(new ProblemaSaude(4, "Corpo", "osteoporose"));
		
		/*Endereco ed = new Endereco();
		ed.setUf("SP");
		ed.setCep("08370190");
		ed.setRua("Jardim São Gonçalo, Estrada da Colônia");
		ed.setNumero(20);
		ed.setCidade("São Paulo");
		
		List<ProblemaSaude> listPS = new ArrayList<ProblemaSaude>();
		listPS.add(new ProblemaSaude(1, "Alergia", "gluten"));
		listPS.add(new ProblemaSaude(2, "Doença", "osteoporose"));
		listPS.add(new ProblemaSaude(3, "Alergia", "orégano"));
		
		Cliente cl = new Cliente();
		cl.setPrimeiroNome("Gabriel Queiroz");
		cl.setDt_nasc(new Date(1997, 3, 20));
		cl.setCpf(47893115890L);
		cl.setRg(530734084);
		cl.setEmail("raphael.victor204@gmail.com");
		cl.setTelefone(11985188623L);
		cl.setCartaoSUS(123124124124L);
		cl.setEnd(ed);
		cl.setProblemasSaude(listPS);
		clientesCadastrados.add(cl);
		this.dataList.add(cl);
		
		ed = new Endereco();
		ed.setUf("SP");
		ed.setCep("08370190");
		ed.setRua("Jardim São Gonçalo, Estrada da Colônia");
		ed.setNumero(20);
		ed.setCidade("São Paulo");
		
		listPS = new ArrayList<ProblemaSaude>();
		listPS.add(new ProblemaSaude(1, "Alergia", "cigarro"));
		listPS.add(new ProblemaSaude(2, "Doença", "HIV"));
		listPS.add(new ProblemaSaude(3, "Alergia", "trigo"));
		
		cl = new Cliente();
		cl.setPrimeiroNome("Raphael");
		cl.setDt_nasc(new Date(1997, 3, 20));
		cl.setCpf(38472812990L);
		cl.setRg(530734084);
		cl.setEmail("raphael.victor204@gmail.com");
		cl.setTelefone(11985188623L);
		cl.setCartaoSUS(123124124124L);
		cl.setEnd(ed);
		cl.setProblemasSaude(listPS);
		clientesCadastrados.add(cl);
		this.dataList.add(cl);*/
	}
	
	public ObservableList<Cliente> getDataListClientes(){
		return this.dataList;
	}
	
	public ObservableList<ProblemaSaude> getDataListPS(){
		return this.dataListPS;
	}

//MANTER CLIENTE ------------------------------------------------------
	
	public boolean cadCliente(Cliente cl) {
		if(!existCliente(cl.getCpf())) {
			this.clientesCadastrados.add(cl);
			try {
				this.dataList.add(cl);
				this.cdi.inserir(cl);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attTableCliente();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Cliente já esta cadastrado no sistema !!!");
			return false;
		}
	}
	
	public void pesquisarCliente(String cont, String tipo) {
		dataList.clear();
		if(!cont.equals("")) {
			//try {
				if(tipo.equals("CIDADE")) {
					for(Cliente c : clientesCadastrados) {
						if(c.getEnd().getCidade().contains(cont)) {
							this.dataList.add(c);
						}
					}
				}else {
					for(Cliente c : clientesCadastrados) {
						if(c.getPrimeiroNome().contains(cont)) {
							this.dataList.add(c);
						}
					}
				}
				//dataList.addAll(cdi.pesquisarPorCliente(cont, tipo));
			/*} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
	public Cliente pesquisarCliente(long cpf) {
		//try {
			//return cdi.pesquisarPorCliente(cpf);
			for(Cliente c : clientesCadastrados) {
				if(c.getCpf() == cpf) {
					return c;
				}
			}
		/*} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return null;
	}
	
	private boolean existCliente(long cpf) {
		for(Cliente c : clientesCadastrados) {
			if(c.getCpf() == cpf) {
				return true;
			}
		}
		/*try {
			return (cdi.pesquisarPorCliente(cpf) != null) ? true : false;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return false;
	}
	
	public void removerCliente() {
		this.clientesCadastrados.remove(pesquisarCliente(clientSel.getCpf()));
		/*try {
			cdi.remover(clientSel.getCpf());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.attTableCliente();
	}
	
	public void attCliente(Cliente cl) {
		for(Cliente c : clientesCadastrados) {
			if(c.getCpf() == clientSel.getCpf()) {
				c.setPrimeiroNome(cl.getPrimeiroNome());
				c.setDt_nasc(cl.getDt_nasc());
				c.setRg(cl.getRg());
				c.setCpf(cl.getCpf());
				c.setTelefone(cl.getTelefone());
				c.setEmail(cl.getEmail());
				c.setCartaoSUS(cl.getCartaoSUS());
				c.getEnd().setCep(cl.getEnd().getCep());
				c.getEnd().setRua(cl.getEnd().getRua());
				c.getEnd().setBairro(cl.getEnd().getBairro());
				c.getEnd().setNumero(cl.getEnd().getNumero());
				c.getEnd().setCidade(cl.getEnd().getCidade());
				c.getEnd().setUf(cl.getEnd().getUf());
				c.setProblemasSaude(cl.getProblemasSaude());
				c.setSexo(cl.getSexo());
			}
		}
		/*try {
			cdi.alterar(cl);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void attClienteToCad(Cliente cl) {
		clientSel.setPrimeiroNome(cl.getPrimeiroNome());
		clientSel.setDt_nasc(cl.getDt_nasc());
		clientSel.setRg(cl.getRg());
		clientSel.setCpf(cl.getCpf());
		clientSel.setTelefone(cl.getTelefone());
		clientSel.setEmail(cl.getEmail());
		clientSel.setCartaoSUS(cl.getCartaoSUS());
		clientSel.getEnd().setCep(cl.getEnd().getCep());
		clientSel.getEnd().setRua(cl.getEnd().getRua());
		clientSel.getEnd().setBairro(cl.getEnd().getBairro());
		clientSel.getEnd().setNumero(cl.getEnd().getNumero());
		clientSel.getEnd().setCidade(cl.getEnd().getCidade());
		clientSel.getEnd().setUf(cl.getEnd().getUf());
		clientSel.setProblemasSaude(cl.getProblemasSaude());
		clientSel.setSexo(cl.getSexo());
	}
	
//FIM MANTER CLIENTE-----------------------------------------------------------------
	
//MANTER PROBLEMA--------------------------------------------------------------------
	
	public void cadProbCliente() {
		//if(!existProb(ps.getId_problema())) {
			try {
				psdi.inserirProbCliente(this.clientSel);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}else {
			
		//}
	}
	
	private boolean existProb(int id) {
		for(ProblemaSaude ps : problemasCadastrados) {
			if(ps.getId_problema() == id) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public ProblemaSaude pesquisarProb(String desc) {
		for(ProblemaSaude ps : problemasCadastrados) {
			if(ps.getDesc_problema().equals(desc)) {
				return ps;
			}
		}
		return null;
	}
	
	public void addProblema(ProblemaSaude ps) {
		this.problemasCadastrados.add(ps);
	}
	
	public void procurarProblema(ProblemaSaude ps) {
		
		boolean addPrb = true;
		for(ProblemaSaude p : problemasCadastrados) {
			if(p.getDesc_problema() == ps.getDesc_problema()) {
				addPrb = false;
				break;
			}
		}
		if(addPrb) {
			addProblema(ps);
		}
		dataListPS.add(ps);
	}
	
	public void removerProb(int id) {
		try {
			for(ProblemaSaude ps : ControlClientes.clientSel.getProblemasSaude()) {
				if(ps.getId_problema() == id) {
					ControlClientes.clientSel.getProblemasSaude().remove(ps);
				}
			}
			try {
				psdi.removerProblemaCliente(ControlClientes.clientSel.getCpf(), id);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attTableProb();	
		}catch(ConcurrentModificationException ex) {
			for(ProblemaSaude ps : ControlClientes.clientSel.getProblemasSaude()) {
				if(ps.getId_problema() == id) {
					ControlClientes.clientSel.getProblemasSaude().remove(ps);
				}
			}
			attTableProb();
		}
	}
	
//FIM MANTER PROBLEMA----------------------------------------------------------------
	
//MANTER TABELAS---------------------------------------------------------------------

	public void attTableCliente() {
		this.dataList.clear();
		this.dataList.addAll(clientesCadastrados);
	}
	
	public void attTableCliente(Cliente cl) {
		this.dataList.clear();
		this.dataList.add(cl);
	}
	
	public void attTableCliente(List<Cliente> cls) {
		this.dataList.clear();
		this.dataList.addAll(cls);
	}
	
	public void attTableProb(List<ProblemaSaude> list) {
		this.dataListPS.clear();
		this.dataListPS.addAll(list);
	}
	
	public void attTableProb() {
		this.dataListPS.clear();
		this.dataListPS.addAll(clientSel.getProblemasSaude());
	}
	
//FIM MANTER TABELAS-----------------------------------------------------------------
	
//MANTER ENDEREÇO--------------------------------------------------------------------
	
	public void addEndereco(Endereco ed) {
		this.enderecoCadastrados.add(ed);
	}
	
	
	
	public Endereco procurarEndereco(Endereco ed) {
		boolean addEd = true;
		for(Endereco e : enderecoCadastrados) {
			if(e.getCep() == ed.getCep()) {
				addEd = false;
				break;
			}
		}
		if(addEd) {
			addEndereco(ed);
		}
		return ed;
	}

//FIM MANTER ENDEREÇO----------------------------------------------------------------
	
	public String[] gerarArrayNum(int init, int fim) {
		int tam = (fim-init) + 1;
		int vl = init;
		String[] arrayNum = new String[tam];
		for(int i=0; i<tam; i++) {
			arrayNum[i] = Integer.toString(vl);
			vl++;
		}
		return arrayNum;
	}
	
}