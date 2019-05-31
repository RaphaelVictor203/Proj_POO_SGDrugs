package com.curso.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	public ControlClientes() {
		this.clientesCadastrados = new ArrayList<Cliente>();
		this.enderecoCadastrados = new ArrayList<Endereco>();
		this.problemasCadastrados = new ArrayList<ProblemaSaude>();
		this.problemasCadastrados.add(new ProblemaSaude(1, "Alergia", "cigarro"));
		this.problemasCadastrados.add(new ProblemaSaude(2, "Doen�a", "HIV"));
		this.problemasCadastrados.add(new ProblemaSaude(3, "Alergia", "glut�n"));
		this.problemasCadastrados.add(new ProblemaSaude(4, "Corpo", "osteoporose"));
		
		/*Endereco ed = new Endereco();
		ed.setUf("SP");
		ed.setCep("08370190");
		ed.setRua("Jardim S�o Gon�alo, Estrada da Col�nia");
		ed.setNumero(20);
		ed.setCidade("S�o Paulo");
		
		List<ProblemaSaude> listPS = new ArrayList<ProblemaSaude>();
		listPS.add(new ProblemaSaude(1, "Alergia", "gluten"));
		listPS.add(new ProblemaSaude(2, "Doen�a", "osteoporose"));
		listPS.add(new ProblemaSaude(3, "Alergia", "or�gano"));
		
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
		ed.setRua("Jardim S�o Gon�alo, Estrada da Col�nia");
		ed.setNumero(20);
		ed.setCidade("S�o Paulo");
		
		listPS = new ArrayList<ProblemaSaude>();
		listPS.add(new ProblemaSaude(1, "Alergia", "cigarro"));
		listPS.add(new ProblemaSaude(2, "Doen�a", "HIV"));
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
			attTableCliente();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Cliente j� esta cadastrado no sistema !!!");
			return false;
		}
	}
	
	public void pesquisarCliente(String cont, String tipo) {
		dataList.clear();
		if(!cont.equals("")) {
			for(Cliente c : clientesCadastrados) {
				if(tipo.equals("CIDADE")) {
					if(c.getEnd().getCidade().contains(cont)) {
						dataList.add(c);
					}
				}else {
					if(c.getPrimeiroNome().contains(cont)) {
						dataList.add(c);
					}
				}
			}
		}
	}
	
	public Cliente pesquisarCliente(long cpf) {
		for(Cliente c : clientesCadastrados) {
			if(c.getCpf() == cpf) {
				return c;
			}
		}
		return null;
	}
	
	private boolean existCliente(long cpf) {
		for(Cliente c : clientesCadastrados) {
			if(c.getCpf() == cpf) {
				return true;
			}
		}
		return false;
	}
	
	public void removerCliente() {
		this.clientesCadastrados.remove(pesquisarCliente(clientSel.getCpf()));
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
				c.getEnd().setNumero(cl.getEnd().getNumero());
				c.getEnd().setCidade(cl.getEnd().getCidade());
				c.getEnd().setUf(cl.getEnd().getUf());
				c.setProblemasSaude(cl.getProblemasSaude());
				c.setSexo(cl.getSexo());
			}
		}
	}
	
//FIM MANTER CLIENTE-----------------------------------------------------------------
	
//MANTER PROBLEMA--------------------------------------------------------------------
	
	public void cadProb(ProblemaSaude ps) {
		if(!existProb(ps.getId_problema())) {
			
		}else {
			
		}
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
	
//MANTER ENDERE�O--------------------------------------------------------------------
	
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

//FIM MANTER ENDERE�O----------------------------------------------------------------
	
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
