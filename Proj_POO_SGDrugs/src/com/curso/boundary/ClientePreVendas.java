package com.curso.boundary;

import javax.swing.JOptionPane;

import com.curso.dao.ClienteDAOImpl;
import com.curso.dao.DAOException;
import com.curso.entity.Cliente;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientePreVendas extends Application implements EventHandler<MouseEvent>{
	// Paineis Realizar Vendas(RV) InfoCliente(IC), Grid
	private Pane PaneRV,PaneIC;
	private GridPane panegrid;
	// boxÂ´s verticais / horizontais
	private VBox btnAvancarInfo,RV,Infos;
	private GridPane IC;
	private HBox btnsRV;
	// realizar venda
	private TextField cpfCliente;
	private Button btnPesquisar,btnAvancar;
	private Label RealVendas,InfoAvancar;
	// info cliente
	private Label InfoCliente,nameClient,cpfClient,idadeClient,sexClient;
	private Label ruaClient,nClient,bairroClient;
	private Button btnAvancarSDef;
	private Stage stg;
	
	private ClienteDAOImpl cc = new ClienteDAOImpl();
	@Override
	public void start(Stage stgPreV) throws Exception {
		stg = stgPreV;
		// Box REALIZAR VENDA
		RealVendas 			= new Label("REALIZAR VENDA");
		cpfCliente 			= new TextField();
		cpfCliente.setPromptText("Insira o CPF do Cliente");//Funciona como um placeholder
		btnPesquisar 		= new Button("PESQUISAR");
		btnAvancar 			= new Button("AVANÇAR");
		InfoAvancar 		= new Label("Necessario definir um cliente");
		// Box Info Cliente
		InfoCliente 		= new Label("INFO. CLIENTE");
		nameClient 			= new Label();
		cpfClient 			= new Label();
		idadeClient 		= new Label();
		sexClient 			= new Label();
		ruaClient 			= new Label();
		nClient 			= new Label();
		bairroClient 		= new Label();
		btnAvancarSDef 		= new Button("AVANÇAR SEM DEFINIR CLIENTE");
		// Paineis Realizar Venda, InformaÃ§Ã£o Cliente e o grid
		panegrid 			= new GridPane();
		PaneRV 				= new Pane();
		PaneRV.setStyle("-fx-background-color: green;");

		PaneIC 				= new Pane();
		// Adicionando no grid os dois paineis
		//panegrid.add(PaneRV, 1,0);
		//panegrid.add(PaneIC, 2,0);
//													INICIO REALIZAR VENDAS

		btnAvancarInfo 		= new VBox(5, btnAvancar,InfoAvancar);
		btnAvancarInfo.setAlignment(Pos.TOP_CENTER);
		btnsRV 				= new HBox(btnPesquisar,btnAvancarInfo);
		VBox entradaPesq = new VBox(10, cpfCliente,btnsRV);
		entradaPesq.setAlignment(Pos.CENTER);
		RV 					= new VBox(RealVendas, entradaPesq);
		PaneRV.getChildren().add(RV);
		
//													FIM REALIZAR VENDAS
		
//													INICIO INFORMAÃ‡ÃƒO CLIENTE
		
		Infos 				= new VBox(new HBox(10,new Label("Nome: "),nameClient)
				        	 ,new HBox(10,new Label("CPF: "),cpfClient)
				  	         ,new HBox(10,new Label("Idade: "),idadeClient
				  				         ,new Label(" Sexo: "),sexClient)
				  	         ,new HBox(10,new Label("Endereço: "),ruaClient
				  	         ,new Label(", N°"),nClient
				  	         ,new Label(" "),bairroClient));
		//IC 					= new VBox(InfoCliente,Infos,btnAvancarSDef);
		IC 					= new GridPane();
		IC.add(InfoCliente, 0, 0);
		IC.add(Infos, 0, 1);
		IC.add(btnAvancarSDef, 0, 2);
		PaneIC.getChildren().add(IC);
		
		panegrid.add(RV, 0, 0);
		panegrid.add(IC, 1, 0);
		
//													FIM INFORMAÃ‡ÃƒO CLIENTE
		
		Styles();
		stgPreV.setMaximized(true);
		//Scene scene = new Scene(pane, stgPreV.getWidth(),stage.getHeight());
		Scene mainScene = new Scene(panegrid, stgPreV.getWidth(),stgPreV.getHeight());
		stgPreV.setScene(mainScene);
		stgPreV.setTitle("Seleção de Cliente - Pré Tela de Vendas");
		stgPreV.show();
		
		RV.setPrefHeight(panegrid.getHeight());
		RV.setPrefWidth(panegrid.getWidth() * 0.67);
		IC.setPrefHeight(panegrid.getHeight());
		IC.setPrefWidth(panegrid.getWidth() * 0.33);
		
		btnAvancarSDef.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAvancar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
	}
	
	public void Styles() {
		// styles
			//									STYLE REALIZAR VENDA
				PaneRV.setPrefWidth(600);
				btnsRV.setStyle("-fx-min-width: 800px; -fx-font-size: 15px;");
				btnsRV.setAlignment(Pos.BASELINE_CENTER);
				btnsRV.setSpacing(30);
				RV.setPadding(new Insets(10));
				RV.setStyle("-fx-font-family:'Arial'; -fx-background-color: #FFFFFF");
				RV.setAlignment(Pos.BASELINE_CENTER);
				RV.setSpacing(190);
				
				RealVendas.setStyle("-fx-font-size: 24px;");
				//RealVendas.setPadding(new Insets(20,0,0,0));
				cpfCliente.setStyle("-fx-max-width:800px;"+
								    "-fx-min-height:35px;"+
								    "-fx-font-size:15px;"
								    + "-fx-background-radius: 8;");
				btnPesquisar.setStyle("-fx-background-color:#00BFFF;"+
									  "-fx-background-radius:8px;"+
									  "-fx-min-width:386px;"+
									  "-fx-min-height:35px;"+
									  "-fx-font-size:15px;"+
									  "-fx-text-fill: white;"
									  + "-fx-cursor: hand;"+
									  "-fx-border-radius:8px;");
				btnAvancar.setStyle("-fx-background-color:#228B22;"+
									"-fx-background-radius:8px;"+
									"-fx-min-width:386px;"+
									"-fx-min-height:35px;"+
									"-fx-font-size:15px;"+
									"-fx-text-fill: white;"
									+ "-fx-cursor: hand;"+
									"-fx-border-radius:8px;");
				InfoAvancar.setStyle("-fx-font-size:12px;");
		   //				                    STYLE INFORMAÃ‡ÃƒO CLIENTE
				PaneIC.setPrefWidth(300);
				Infos.setPadding(new Insets(90,0,56,20));
				Infos.setSpacing(40);
				Infos.setMinHeight(590);
				Infos.setStyle("-fx-font-size: 20px; -fx-min-width: 450px;");
				IC.setStyle("-fx-background-color:#EEE5DE;"+
							"-fx-font-family:'Arial';"+
							"-fx-font-size:15;");
				IC.setAlignment(Pos.TOP_CENTER);
				IC.setPadding(new Insets(10, 0, 0, 0));
				InfoCliente.setStyle("-fx-font-size:24px; -fx-min-width: 450px;");
				InfoCliente.setAlignment(Pos.CENTER);
				//InfoCliente.setPadding(new Insets(20,0,0,60));
				btnAvancarSDef.setStyle("-fx-background-color:#228B22;"+
										"-fx-min-width: 450px;"+
										"-fx-min-height:74px;"+
									    "-fx-font-size: 15px;"+
										"-fx-text-fill: white;"
										+ "-fx-cursor: hand;"
										+ "-fx-border-radius: none;");
				
	}
	public static void main(String[]args) {
		Application.launch(args);
	}
	@Override
	public void handle(MouseEvent event) {
		if(event.getSource()==btnPesquisar) {
			try {
				cc.pesquisarPorCliente(Long.parseLong(cpfCliente.getText()));
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.getSource()==btnAvancar) {
			try {
				Cliente cl = cc.pesquisarPorCliente(Long.parseLong(cpfCliente.getText()));
				if (!cl.equals(null)) {
					TelaVendas tv = new TelaVendas();
					try {
						tv.cliente = cl;
						tv.start(stg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					Alert a = new Alert(AlertType.INFORMATION, "Fornecedor inválido !");
					a.show();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		if(event.getSource() == btnAvancarSDef) {
			TelaVendas tv = new TelaVendas();
			try {
				tv.start(stg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
