package com.curso.boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.curso.dao.LoginDAO;
import com.curso.dao.LoginDAOImpl;
import com.curso.entity.Funcionario;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaPrincipal extends Application{

	StackPane painelPrincipal = new StackPane();
	MenuBar menuTop = new MenuBar();
	Menu menu = new Menu();
	Menu subMenuCadastro = new Menu("Cadastros");
	MenuItem venda = new MenuItem("Venda");
	MenuItem cadCli = new MenuItem("cadastro cliente");
	MenuItem cadFarmProd = new MenuItem("cad. farmacia produto");
	MenuItem cadProduto = new MenuItem("cadastro produto");
	MenuItem cadFornecedor = new MenuItem("cadastro fornecedor");
	MenuItem cadFuncionario = new MenuItem("cadastro funcionario");
	ImageView imgView;
	BorderPane painelSubPrincipal  = new BorderPane();
	Label lblBoasVindas = new Label("Bem vindo ao SGDrugs");
	protected Funcionario func;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		BorderPane pane = new BorderPane();
		
		pane.setCenter(painelPrincipal);
		
		menuTop.getMenus().add(menu);
		pane.setTop(menuTop);
		
		listarFuncoes();
		
		painelSubPrincipal = new BorderPane();
		painelSubPrincipal.setCenter(lblBoasVindas);
		
		stage.setMaximized(true);
		
		setActionsItemsMenu(stage);
		Scene scene = new Scene(pane, stage.getWidth(),stage.getHeight());
		stage.setScene(scene);
		stage.setTitle("SGDrugs - Sistema Gerenciador de Drogarias");
		stage.show();
		
		
        imgView = new ImageView(new Image(new FileInputStream("imgs\\back_tela_principal.jpg")));
        imgView.setFitWidth(scene.getWidth());
        imgView.setFitHeight(scene.getHeight());
		painelPrincipal.getChildren().add(imgView);
		painelPrincipal.getChildren().add(painelSubPrincipal);
		
        startStyle();
		
		
//EVENTO FECHAMENTO -----------------------------------------------------------------------------
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

                stage.close();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("SGDrugs");
                alert.setHeaderText("Você deseja voltar para a tela principal ?");
                alert.setContentText("Escolha sua opção.");

                ButtonType buttonTypeSIM = new ButtonType("SIM");
                ButtonType buttonTypeNAO = new ButtonType("NÃO, desejo sair");

                alert.getButtonTypes().setAll(buttonTypeSIM, buttonTypeNAO);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeSIM){
                	TelaPrincipal tp = new TelaPrincipal();
        			try {
        				tp.func = func;
        				tp.start(stage);
        			} catch (Exception e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
                } else if (result.get() == buttonTypeNAO) {
                    System.exit(0);
                }
                
                
            }
        });
		
		
		
	}
	
	public void listarFuncoes() {
		ImageView iconMenu;
		try {
			
			if(func.getNivel().equals("gerente") || func.getNivel().equals("proprietário")) {
				subMenuCadastro.getItems().add(cadFarmProd);
				subMenuCadastro.getItems().add(cadProduto);
				subMenuCadastro.getItems().add(cadFornecedor);
				subMenuCadastro.getItems().add(cadFuncionario);
			}
			
			iconMenu = new ImageView(new Image(new FileInputStream("imgs\\icon_menu.png")));
			iconMenu.setFitWidth(30);
			iconMenu.setFitHeight(30);
			menu.setGraphic(iconMenu);
			subMenuCadastro.getItems().add(cadCli);
			menu.getItems().add(venda);
			menu.getItems().add(subMenuCadastro);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void startStyle() {
		painelPrincipal.setStyle(""
				+ " -fx-min-width: 100%; -fx-min-height: 100%;");
		menuTop.setStyle("-fx-min-width: 100%; -fx-min-height: 40px; -fx-background-color: #E0DACE;");
		menu.setStyle("-fx-min-height: 40px; -fx-min-width: 50px; -fx-font-size: 20px; -fx-cursor: hand;");
		//imgView.setStyle("-fx-max-width: 1100; -fx-max-height: 600;"); 
		painelSubPrincipal.setStyle("-fx-background-color: rgba(0, 0, 0, .7); -fx-min-width: 100%; -fx-min-height: 100%;");
		lblBoasVindas.setStyle("-fx-text-fill: white; -fx-font-size: 70");
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void setActionsItemsMenu(Stage stage) {
		venda.setOnAction(e -> {
			ClientePreVendas pv = new ClientePreVendas();
			try {
				pv.func = this.func;
				pv.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		cadCli.setOnAction(e -> {
			ManterCliente mc = new ManterCliente();
			try {
				mc.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		cadFarmProd.setOnAction(e -> {
			TelaFarmaciaProduto fp = new TelaFarmaciaProduto();
			try {
				fp.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		cadProduto.setOnAction(e -> {
			ManterProduto mp = new ManterProduto();
			try {
				mp.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		cadFornecedor.setOnAction(e -> {
			ManterFornecedor mf = new ManterFornecedor();
			try {
				mf.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		cadFuncionario.setOnAction(e -> {
			ManterFuncionario mf = new ManterFuncionario();
			try {
				mf.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

}
