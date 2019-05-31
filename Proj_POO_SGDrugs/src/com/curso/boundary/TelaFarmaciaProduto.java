package com.curso.boundary;

import java.io.FileInputStream;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

import com.curso.control.ControlProdutos;
import com.curso.entity.Fornecedor;
import com.curso.entity.ProblemaSaude;
import com.curso.entity.Produto;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TelaFarmaciaProduto extends Application implements EventHandler<MouseEvent>{

	private Button btnCadProd;
	private Button btnMantProd, btnCadastro, btnPesquisaProd;
	private BorderPane painelCad;
	private BorderPane painelMant;
	private TextField txtQntd;
	private TextField txtPrecoUnit;
	private TextField txtPesquisaProd;
	private ComboBox<String> cmbGrupo;
	private ComboBox<String> cmbSessao;
	private ComboBox<String> cmbTipoPesquisa;
	private TableView<Produto> tblProduto;
	private Label lblNomeProduto, lblFornecedor;
	private HBox menuTop;
	private ControlProdutos cp;
	
	@Override
	public void start(Stage stage) throws Exception {
		
//INICIO FORMULARIO CADASTRO PRODUTO FARMACIA---------------------------------------

		txtQntd = new TextField();
		txtQntd.setPromptText("Qntd");
		txtPrecoUnit = new TextField();
		txtPrecoUnit.setPromptText("Preço unitario");
		cmbGrupo = new ComboBox<String>();
		cmbGrupo.setPromptText("Grupo");
		cmbSessao = new ComboBox<String>();
		cmbSessao.setPromptText("Sessão");
		btnCadastro = new Button("CADASTRAR");
		ImageView iv = new ImageView(new Image(new FileInputStream("imgs\\icon.png")));
		iv.setFitHeight(22);
		iv.setFitWidth(22);
		btnPesquisaProd = new Button("", iv);
		txtPesquisaProd = new TextField();
		cmbTipoPesquisa = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"ID", "NOME", "CATEGORIA", "FORNECEDOR"}));
		cmbTipoPesquisa.setPromptText("Tipo");
		tblProduto = new TableView<Produto>();
		cp = new ControlProdutos();
		lblNomeProduto = new Label();
		lblFornecedor = new Label();
		
		HBox hb = new HBox(80,
				new VBox(10,
						new HBox(new Label("Produto selecionado: "), lblNomeProduto),
						new HBox(new Label("Fornecedor: "), lblFornecedor),
						new HBox(10, txtQntd, txtPrecoUnit),
						new HBox(10, cmbGrupo, cmbSessao),
						btnCadastro
				),
				new VBox(10,
						new Label("Produtos cadastrados"),
						new Separator(),
						new HBox(10, cmbTipoPesquisa, new HBox(txtPesquisaProd, btnPesquisaProd)),
						tblProduto
				)
		);
		hb.setStyle("-fx-font-size: 15px;");
		
//FIM FORMULARIO CADASTRO PRODUTO FARMACIA------------------------------------------		

//INICIO TELA GERENCIMENTO----------------------------------------------------------
		
		VBox vb = new VBox(
				new HBox(new ComboBox(), new TextField(), new Button()),
				new TableView<>()
		);
		
		
//FIM TELA GERENCIAMENTO------------------------------------------------------------
		
		BorderPane pane = new BorderPane();
		
		btnCadProd = new Button("CADASTRO");
		btnMantProd = new Button("GERENCIAMENTO");
		painelCad = new BorderPane(hb);
		painelMant = new BorderPane(vb);
		
		menuTop = new HBox(btnCadProd, btnMantProd);
		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelMant, painelCad);
		pane.setCenter(painels);
		
		createTableColumnsProds();
		cp.attTableProds();
		
		Scene scene = new Scene(pane, 1100,600);
		stage.setScene(scene);
		stage.setTitle("Manter Clientes");
		stage.show();
		
		startStyle();
		btnSelected(0);
		
		btnCadProd.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnMantProd.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
	}
	
	public void startStyle() {
		
		DropShadow dp = new DropShadow(4, 0, 0, Color.GRAY);
		
		String styleBtnPesquisa = 
				"-fx-background-color: #0095FE;"
				+ "-fx-text-fill: white;"
				+ "-fx-background-radius: 7;"
				+ "-fx-min-width: 240px;"
				+ "-fx-min-height: 30px;"
				+ "-fx-cursor: hand;";
		
		String styleBtns = 
				"-fx-background-color: #0095FE;"
				+ "-fx-text-fill: white;"
				+ "-fx-background-radius: 7;"
				+ "-fx-min-width: 275px;"
				+ "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;";
		
		String styleBtnAddProb =
				"-fx-min-width: 625px;"
				+ "-fx-background-color: #007F0E;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 15px;"
				+ "-fx-background-radius: 8;"
				+ "-fx-cursor: hand;";
		
		String styleBtn =
				"-fx-background-radius: none;"
				+ "-fx-min-width: 130px;"
				+ "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;"
				+ "-fx-font-color: #545452;"
				+ "-fx-font-weight: bold;";
		
		String stylePainel = 
				"-fx-background-color: #FEFFFA;"
				+ "-fx-padding: 50, 50, 50, 50;";
		
		String styleMenuTop = "-fx-background-color: #E0DACE";
		
		String styleEntradaDataNasc = "-fx-min-width: 130px;";
		
		String styleEntradas = "-fx-background-radius: 8;";
		
		String styleEntradaPesquisa = "-fx-background-radius: 8px 0px 0px 8px;"
				+ "-fx-min-width: 537px;";
		
		String stylePesquisaProb = "-fx-min-height: 30px;"
				+ "-fx-min-width: 30px;"
				+ "-fx-background-radius: 0px 8px 8px 0px;"
				+ "-fx-background-color: #0095FE;"
				+ "-fx-cursor: hand;";
		
		String comboStyle = "-fx-background-radius: 8;"
				+ "-fx-background-color: #FEFFFA;"
				+ "-fx-cursor: hand;";
		
		btnCadProd.setStyle(styleBtn);
		btnMantProd.setStyle(styleBtn);
		painelCad.setStyle(stylePainel);
		painelMant.setStyle(stylePainel);
		menuTop.setStyle(styleMenuTop);
		txtPesquisaProd.setStyle(styleEntradaPesquisa + "-fx-font-size: 15.1px");
		btnPesquisaProd.setStyle(stylePesquisaProb);
		cmbTipoPesquisa.setStyle(comboStyle + "-fx-font-size: 15.1px");
		cmbTipoPesquisa.setEffect(dp);
		cmbGrupo.setStyle(comboStyle + "-fx-font-size: 20px; -fx-min-width: 248px;");
		cmbGrupo.setEffect(dp);
		cmbSessao.setStyle(comboStyle + "-fx-font-size: 20px; -fx-min-width: 248px;");
		cmbSessao.setEffect(dp);
		txtQntd.setStyle(styleEntradas + "-fx-font-size: 20px;");
		txtPrecoUnit.setStyle(styleEntradas + "-fx-font-size: 20px;");
		btnCadastro.setStyle(styleBtns + "-fx-font-size: 15px; -fx-min-width: 506;");

	}
	
	public void btnSelected(int btn) {
		String CadSelected = "";
		String MantSelected = "";
		if(btn == 0) {
			CadSelected = "#FEFFFA";
			MantSelected = "#EAEAEA";
		}else {
			CadSelected = "#EAEAEA";
			MantSelected = "#FEFFFA";
		}
		btnCadProd.setStyle(
				"-fx-background-color: " + CadSelected + ";"
				+ "-fx-background-radius: none;"
				+ "-fx-min-width: 130px;"
				+ "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;"
		);
		btnMantProd.setStyle(
				"-fx-background-color: " + MantSelected + ";"
				+ "-fx-background-radius: none;"
				+ "-fx-min-width: 130px;"
				+ "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;"
		);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void handle(MouseEvent e) {
		if(e.getSource() == btnCadProd) {
			btnSelected(0);
			painelCad.toFront();
		}else
		if(e.getSource() == btnMantProd) {
			btnSelected(1);
			painelMant.toFront();
		}
	}

	@SuppressWarnings("unchecked")
	public void createTableColumnsProds() {

		tblProduto.setItems(cp.getDataListProds());
		
		tblProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {

			@Override
			public void changed(ObservableValue<? extends Produto> observable, Produto p1, Produto p2) {
				produtoToBoundary(p2);				
			}
			
		});
		
		TableColumn<Produto, Number> id_produto = new TableColumn<>("ID produto");
		id_produto.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getId_produto()));
		
		TableColumn<Produto, String> categoria = new TableColumn<>("Categoria");
		categoria.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getCategoria()));
		
		TableColumn<Produto, String> nome = new TableColumn<>("Produto");
		nome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));
		
		TableColumn<Produto, String> nomeF = new TableColumn<>("Fornecedor");
		nomeF.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getFornecedor().getNome_fantasia()));
		
		tblProduto.getColumns().addAll(id_produto, nome, categoria, nomeF);
		
	}
	
	public void produtoToBoundary(Produto p) {
		this.lblNomeProduto.setText(p.getNome());
		this.lblFornecedor.setText(p.getFornecedor().getNome_fantasia());
	}
	
}
