package com.curso.boundary;

import java.io.FileInputStream;
import java.io.IOException;
 
import java.util.ArrayList;
import java.util.List;
 

import com.curso.control.ControlProdutos;
import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManterProduto extends Application implements EventHandler<MouseEvent> {

	private Pane painelCADProdutos;
	private Pane painelCTRProdutos;
	private Button btnCADProdutos;
	private Button btnCTRProdutos;
	private HBox menuTop;

	private TextField txtDescricao;
	private ComboBox<String> cmbCategoria;
	private List<String> ltCategorias;
	private ObservableList<String> obsCategorias;
	private ComboBox<String> cmbFornecedor;
	private List<String> ltFornecedores;
	private ObservableList<String> obsFornecedores;
	private Button btnInserir;
	private Button btnCancelar;
	private Button btnPesquisar;
	private TextField txtPesquisar;
	private TableView<Produto> tblProdutos;
	private Produto produto;

	private Button btnInfo = new Button("Mostrar diálogo de informação");
	private Button btnErro = new Button("Mostrar diálogo de error");

	ControlProdutos cp = new ControlProdutos();

	@Override
	public void start(Stage stage) throws Exception {

		painelCADProdutos = new Pane();
		painelCTRProdutos = new Pane();

		btnCADProdutos = new Button("INCLUSÃO");
		btnCTRProdutos = new Button("CONTROLE");

		menuTop = new HBox(btnCADProdutos, btnCTRProdutos);
		menuTop.setSpacing(1);

		txtDescricao = new TextField();
		txtDescricao.setPrefSize(280, 20);
		txtDescricao.setPromptText("Nome do Produto");
		txtPesquisar = new TextField();
		txtPesquisar.setPrefSize(280, 20);
		txtPesquisar.setPromptText("ID ou Nome do Produto");
		cmbCategoria = new ComboBox<>();
		cmbCategoria.setPromptText("Selecione");
		cmbCategoria.setPrefSize(280, 20);
		cmbFornecedor = new ComboBox<>();
		cmbFornecedor.setPrefSize(270, 20);
		cmbFornecedor.setPromptText("Selecione");
		btnInserir = new Button("Inserir");
		btnInserir.setPrefSize(220, 40);
		btnCancelar = new Button("Cancelar");

		ImageView img = new ImageView(new Image(new FileInputStream("imgs\\search.png")));
		img.setFitWidth(20);
		img.setFitHeight(20);

		btnCancelar.setPrefSize(220, 40);
		btnPesquisar = new Button("", img);
		btnPesquisar.setPrefSize(35, 20);

		ltCategorias = new ArrayList<>();
		obsCategorias = FXCollections.observableArrayList();
		ltFornecedores = new ArrayList<>();
		obsFornecedores = FXCollections.observableArrayList();

		adicionarCategoria(ltCategorias);
		obsCategorias.addAll(ltCategorias);
		cmbCategoria.setItems(obsCategorias);

		adicionarFornecedor(ltFornecedores);
		obsFornecedores.addAll(ltFornecedores);
		cmbFornecedor.setItems(obsFornecedores);

		tblProdutos = new TableView<Produto>();
		tblProdutos.setPrefWidth(600);
		tblProdutos.setPrefHeight(400);

		loadtable();

		BorderPane pane = new BorderPane();

		VBox vbCadastro = new VBox(new Label("ADICIONAR PRODUTOS"), new Separator(),
				new HBox(10, new Label("Descrição: "), txtDescricao),
				new HBox(10, new Label("Categoria: "), cmbCategoria),
				new HBox(10, new Label("Fornecedor: "), cmbFornecedor));
		vbCadastro.setSpacing(8);
		vbCadastro.setPadding(new Insets(39, 40, 40, 80));
		vbCadastro.setStyle("-fx-font-size: 15px;");

		VBox vbPesquisa = new VBox(new Label("PESQUISA"), new Separator(), new HBox(txtPesquisar, btnPesquisar));
		vbPesquisa.setSpacing(8);
		vbPesquisa.setPadding(new Insets(39, 0, 20, 0));
		vbPesquisa.setStyle("-fx-font-size: 15px;");

		VBox vbTabela = new VBox(new HBox(new Label("LISTA DE PRODUTOS")), new Separator(), new HBox(tblProdutos));
		vbTabela.setPadding(new Insets(20, 0, 0, 0));
		vbTabela.setSpacing(10);
		vbTabela.setStyle("-fx-font-size: 15px;");

		HBox hbButtons = new HBox(btnInserir, btnCancelar);
		hbButtons.setSpacing(20);
		hbButtons.setPadding(new Insets(7, 0, 0, 40));
		hbButtons.setAlignment(Pos.BASELINE_CENTER);

		HBox hbGeral = new HBox();
		hbGeral.setStyle("-fx-background-color: rgb(237,237,237);");
		hbGeral.setSpacing(80);
		hbGeral.setPadding(new Insets(40, 1000, 80, 40));
		hbGeral.getChildren().addAll(new VBox(vbCadastro, hbButtons), new VBox(vbPesquisa, vbTabela));
		painelCADProdutos.getChildren().add(hbGeral);

		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelCTRProdutos, painelCADProdutos);
		pane.setCenter(painels);

		Scene cena = new Scene(pane, 1250, 700);
		stage.setScene(cena);
		stage.setTitle("Produtos");
		stage.show();

		btnInserir.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCADProdutos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCTRProdutos.addEventFilter(MouseEvent.MOUSE_CLICKED, this);

		loadstyles();
		btnSelected(0);

	}

	@SuppressWarnings("unchecked")
	public void loadtable() {

		TableColumn<Produto, Number> id_produto = new TableColumn<>("ID");
		id_produto.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getId_produto()));

		TableColumn<Produto, String> desc_produto = new TableColumn<>("Descrição");
		desc_produto.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));
		desc_produto.setPrefWidth(180);

		TableColumn<Produto, String> categoria_produto = new TableColumn<>("Categoria");
		categoria_produto.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getCategoria()));
		categoria_produto.setPrefWidth(160);

		TableColumn<Produto, String> fornecedor_produto = new TableColumn<>("Fornecedor");
		fornecedor_produto.setCellValueFactory(
				item -> new ReadOnlyStringWrapper(item.getValue().getFornecedor().getNome_fantasia()));
		fornecedor_produto.setPrefWidth(150);

		// tblProdutos.setStyle("-fx-background-color:#0095FE;");
		tblProdutos.getColumns().addAll(id_produto, desc_produto, categoria_produto, fornecedor_produto);
	}

	public void loadstyles() {

		String styleButtons = "-fx-background-radius: 7;" + "-fx-font-size: 15px;" + "-fx-text-fill: white;"
				+ "-fx-background-color: #0095FE;"
				+ "-fx-cursor: hand;";

		String styleBtnPesquisa = "-fx-background-radius: 8;" + "-fx-background-color: #0095FE;";

		String styleEntradas = "-fx-background-radius: 8;" + "-fx-right: 20px;";

		String styleMenuTop = "-fx-background-color: rgb(242, 242, 242);";

		String styleMeuBtn = "-fx-background-radius: none;" + "-fx-min-width: 130px;" + "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;" + "-fx-font-size: 15px;";

		menuTop.setStyle(styleMenuTop);
		txtDescricao.setStyle(styleEntradas);
		txtPesquisar.setStyle(styleEntradas);
		cmbCategoria.setStyle(styleEntradas);
		cmbFornecedor.setStyle(styleEntradas);
		btnInserir.setStyle(styleButtons);
		btnCancelar.setStyle(styleButtons);
		btnPesquisar.setStyle(styleBtnPesquisa);
		btnCADProdutos.setStyle(styleMeuBtn);
		btnCTRProdutos.setStyle(styleMeuBtn);
	}

	public void btnSelected(int btn) {

		String SelectCAD = "";
		String SelectCTR = "";
		if (btn == 0) {
			SelectCAD = "rgb(237, 237, 237)";
			SelectCTR = "rgb(242, 242, 242)";
		} else {
			SelectCAD = "rgb(242, 242, 242)";
			SelectCTR = "rgb(237, 237, 237)";
		}
		btnCADProdutos.setStyle("-fx-background-color: " + SelectCAD + ";" + "-fx-background-radius: none;"
				+ "-fx-min-width: 140px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");

		btnCTRProdutos.setStyle("-fx-background-color: " + SelectCTR + ";" + "-fx-background-redius: none;"
				+ "-fx-min-width: 140px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");
	}

	public List<String> adicionarCategoria(List<String> categoria) {

		categoria.add("Cosméticos");
		categoria.add("Equipamentos Médicos");
		categoria.add("Medicamentos Genéricos");
		categoria.add("Medicamentos de Referência");
		categoria.add("Medicamenros Similares");
		categoria.add("Suplementos e Vitaminas");

		return categoria;
	}

	public List<String> adicionarFornecedor(List<String> fornecedores) {

		Fornecedor fornecedor1 = new Fornecedor();
		fornecedor1.setNome_fantasia("Jequiti");

		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setNome_fantasia("Ultrafarma");

		Fornecedor fornecedor3 = new Fornecedor();
		fornecedor3.setNome_fantasia("Drogaria SP");

		Fornecedor fornecedor4 = new Fornecedor();
		fornecedor4.setNome_fantasia("Drogasil");

		fornecedores.add(fornecedor1.getNome_fantasia());
		fornecedores.add(fornecedor2.getNome_fantasia());
		fornecedores.add(fornecedor3.getNome_fantasia());
		fornecedores.add(fornecedor4.getNome_fantasia());

		return fornecedores;
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getSource() == btnCADProdutos) {
			painelCADProdutos.toFront();
			btnSelected(0);
		} else if (event.getSource() == btnCTRProdutos) {
			painelCTRProdutos.toFront();
			btnSelected(1);
		}
		
		if (event.getSource() == btnInserir) {
			
			produto = new Produto();
			produto.setNome(txtDescricao.getText());
			
			try {
				produto.setCategoria(cmbCategoria.getSelectionModel().getSelectedItem().toString());
				//produto.getFornecedor().setNome_fantasia((cmbFornecedor.getSelectionModel().getSelectedItem().toString()));	
				
				if (txtDescricao.getText().isEmpty() || cmbCategoria.getSelectionModel().getSelectedIndex() == -1
						|| cmbFornecedor.getSelectionModel().getSelectedIndex() == -1) {

					btnErro.setOnAction(e -> {
						Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
						dialogoErro.setTitle("Cadastro Não Efetuado");
						dialogoErro.setHeaderText("Erro de Entrada");
						dialogoErro.setContentText("Complete todos os Campos");
						dialogoErro.showAndWait();
					});

				} else {

					try {

						cp.adicionarProduto(produto);
						cp.inserirProduto(produto);
						
						btnInfo.setOnAction(e -> {
							Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
							dialogoInfo.setTitle("Cadastro Efetuado com Sucesso");
							dialogoInfo.setHeaderText("Um Novo Produto foi Inserido");
							dialogoInfo.setContentText(
									"\nNome: " + produto.getNome() + "\nCategoria: " + produto.getCategoria()
											+ "\nFornecedor: " + produto.getFornecedor().getNome_fantasia());
							dialogoInfo.showAndWait();
						});

					} catch (IOException e) {

						e.printStackTrace();
					}
				}
				
			} catch (Exception erro) {}
		}
	}
}
