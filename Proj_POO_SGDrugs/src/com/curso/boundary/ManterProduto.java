package com.curso.boundary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.curso.control.ControlProdutos;
import com.curso.dao.DAOException;
import com.curso.dao.FornecedorDAO;
import com.curso.dao.FornecedorDAOImpl;
import com.curso.entity.Fornecedor;
import com.curso.entity.Produto;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	private Pane CAD_painelProdutos;
	private Button CAD_btnProdutos;
	private HBox CAD_menuTop;
	private TextField CAD_txtDescricao;
	private ComboBox<String> CAD_cmbCategoria;
	private ComboBox<Fornecedor> CAD_cmbFornecedor;
	private Button CAD_btnInserir;
	private Button CAD_btnCancelar;
	private Button CAD_btnPesquisar;
	private Button CAD_btnAlterar;
	private Button CAD_btnExcluir;
	private TextField CAD_txtPesquisar;
	private TableView<Produto> CAD_tblProdutos;
	private Button CAD_btnPrimeiro, CAD_btnAnterior, CAD_btnProximo, CAD_btnUltimo;
	private Produto p;
	private ControlProdutos cp = new ControlProdutos();
	private ComboBox<String> CAD_cmbPesquisa;

	@Override
	public void start(Stage stage) throws Exception {

		CAD_painelProdutos = new Pane();
		CAD_btnProdutos = new Button("INCLUSÃO");
		CAD_btnProdutos.setPrefWidth(200);

		CAD_txtDescricao = new TextField();
		CAD_txtDescricao.setPrefSize(280, 20);
		CAD_txtDescricao.setPromptText("Nome do Produto");

		CAD_txtPesquisar = new TextField();
		CAD_txtPesquisar.setPrefSize(280, 20);
		CAD_txtPesquisar.setPromptText("ID ou Nome do Produto");
		CAD_txtPesquisar.requestFocus();

		CAD_cmbCategoria = new ComboBox<>(FXCollections.observableArrayList(adicionarCategoria()));
		CAD_cmbCategoria.setPromptText("Selecione");
		CAD_cmbCategoria.setPrefSize(280, 20);

		FornecedorDAO fdi = new FornecedorDAOImpl();
		CAD_cmbFornecedor = new ComboBox<>(FXCollections.observableArrayList(fdi.pesquisarPorFornecedor()));
		CAD_cmbFornecedor.setPrefSize(270, 20);
		CAD_cmbFornecedor.setPromptText("Selecione");

		CAD_btnInserir = new Button("Inserir");
		CAD_btnInserir.setPrefSize(220, 40);

		CAD_btnCancelar = new Button("Cancelar");
		CAD_btnCancelar.setPrefSize(220, 40);

		CAD_btnAlterar = new Button("Alterar");
		CAD_btnAlterar.setPrefSize(220, 40);

		CAD_btnExcluir = new Button("Excluir");
		CAD_btnExcluir.setPrefSize(220, 40);

		CAD_cmbPesquisa = new ComboBox<>(FXCollections.observableArrayList(selecionarTipoPesquisa()));
		CAD_cmbPesquisa.setPromptText("Selecione");
		CAD_cmbPesquisa.setPrefSize(120, 20);

		ImageView search1 = new ImageView(new Image(new FileInputStream("imgs\\search.png")));
		search1.setFitWidth(20);
		search1.setFitHeight(20);

		CAD_btnPesquisar = new Button("", search1);
		CAD_btnPesquisar.setPrefSize(35, 20);
		CAD_tblProdutos = new TableView<Produto>();
		CAD_tblProdutos.setPrefWidth(600);
		CAD_tblProdutos.setPrefHeight(400);

		ImageView first = new ImageView(new Image(new FileInputStream("imgs\\first.png")));
		first.setFitWidth(25);
		first.setFitHeight(15);

		ImageView previous = new ImageView(new Image(new FileInputStream("imgs\\previous.png")));
		previous.setFitWidth(25);
		previous.setFitHeight(15);

		ImageView next = new ImageView(new Image(new FileInputStream("imgs\\next.png")));
		next.setFitWidth(25);
		next.setFitHeight(15);

		ImageView last = new ImageView(new Image(new FileInputStream("imgs\\last.png")));
		last.setFitWidth(25);
		last.setFitHeight(15);

		CAD_btnPrimeiro = new Button("", first);
		CAD_btnPrimeiro.setPrefSize(40, 20);
		CAD_btnAnterior = new Button("", previous);
		CAD_btnAnterior.setPrefSize(40, 20);
		CAD_btnProximo = new Button("", next);
		CAD_btnProximo.setPrefSize(40, 20);
		CAD_btnUltimo = new Button("", last);
		CAD_btnUltimo.setPrefSize(40, 20);

		loadtableInsert();

		BorderPane pane = new BorderPane();

		VBox vbCadastro = new VBox(new Label("ADICIONAR PRODUTOS"), new Separator(),
				new HBox(10, new Label("Descrição: "), CAD_txtDescricao),
				new HBox(10, new Label("Categoria: "), CAD_cmbCategoria),
				new HBox(10, new Label("Fornecedor: "), CAD_cmbFornecedor));
		vbCadastro.setSpacing(8);
		vbCadastro.setPadding(new Insets(16, 40, 40, 80));
		vbCadastro.setStyle("-fx-font-size: 15px;");

		VBox vbPesquisaCAD = new VBox(new Label("PESQUISA"), new Separator(),
				new HBox(5, CAD_cmbPesquisa, CAD_txtPesquisar, CAD_btnPesquisar));
		vbPesquisaCAD.setSpacing(8);
		vbPesquisaCAD.setPadding(new Insets(16, 0, 20, 0));
		vbPesquisaCAD.setStyle("-fx-font-size: 15px;");

		VBox vbTabela = new VBox(new HBox(new Label("LISTA DE PRODUTOS DISPONÍVEIS")), new Separator(),
				new HBox(5, CAD_btnPrimeiro, CAD_btnAnterior, CAD_btnProximo, CAD_btnUltimo),
				new HBox(CAD_tblProdutos));
		vbTabela.setPadding(new Insets(10, 0, 0, 0));
		vbTabela.setSpacing(10);
		vbTabela.setStyle("-fx-font-size: 15px;");

		HBox hbButtons = new HBox(new VBox(5, CAD_btnInserir, CAD_btnCancelar),
				new VBox(5, CAD_btnAlterar, CAD_btnExcluir));
		hbButtons.setSpacing(8);
		hbButtons.setPadding(new Insets(7, 0, 0, 40));
		hbButtons.setAlignment(Pos.BASELINE_CENTER);

		HBox hbGeralCAD = new HBox();
		hbGeralCAD.setStyle("-fx-background-color: rgb(237,237,237);");
		hbGeralCAD.setSpacing(80);
		hbGeralCAD.setPadding(new Insets(40, 1000, 80, 40));
		hbGeralCAD.getChildren().addAll(new VBox(vbCadastro, hbButtons), new VBox(vbPesquisaCAD, vbTabela));
		CAD_painelProdutos.getChildren().add(hbGeralCAD);

		// -----------------------------------------------------------------------------------------------------//

		CAD_menuTop = new HBox(CAD_btnProdutos);
		CAD_menuTop.setSpacing(1);
		pane.setTop(CAD_menuTop);
		StackPane painels = new StackPane(CAD_painelProdutos);
		pane.setCenter(painels);

		stage.setMaximized(true);
		Scene cena = new Scene(pane, stage.getWidth(), stage.getHeight());
		stage.setScene(cena);
		stage.setTitle("Manter Produtos");
		stage.show();

		CAD_btnInserir.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnAlterar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnExcluir.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		CAD_btnPesquisar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnProdutos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		CAD_btnPrimeiro.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnAnterior.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnProximo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		CAD_btnUltimo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		CAD_tblProdutos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		loadstyles();
		btnSelected(0);

	}

	@SuppressWarnings("unchecked")
	public void loadtableInsert() throws DAOException {

		cp = new ControlProdutos();
		CAD_tblProdutos.setItems(cp.getProdutosCAD());

		CAD_tblProdutos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {

			@Override
			public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {

				if (newValue != null) {
					produtoToBoundary(newValue);
				}
			}
		});

		TableColumn<Produto, Number> id_produto = new TableColumn<>("ID");
		id_produto.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getId_produto()));
		id_produto.setPrefWidth(50);

		TableColumn<Produto, String> desc_produto = new TableColumn<>("Descrição");
		desc_produto.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));
		desc_produto.setPrefWidth(190);

		TableColumn<Produto, String> categoria_produto = new TableColumn<>("Categoria");
		categoria_produto.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getCategoria()));
		categoria_produto.setPrefWidth(210);

		TableColumn<Produto, String> fornecedor_produto = new TableColumn<>("Fornecedor");
		fornecedor_produto.setCellValueFactory(
				item -> new ReadOnlyStringWrapper(item.getValue().getFornecedor().getNome_fantasia()));
		fornecedor_produto.setPrefWidth(150);

		cp.CarregarProdutos();
		CAD_tblProdutos.getColumns().addAll(id_produto, desc_produto, categoria_produto, fornecedor_produto);

	}

	public void loadstyles() {

		String styleButtons = "-fx-background-radius: 7;" + "-fx-font-size: 15px;" + "-fx-text-fill: white;"
				+ "-fx-background-color: #0095FE;" + "-fx-cursor: hand;";

		String styleBtnPesquisa = "-fx-background-radius: 8;" + "-fx-background-color: #0095FE;" + "-fx-cursor: hand;";

		String styleEntradas = "-fx-background-radius: 8;" + "-fx-right: 20px;";

		String styleMenuTop = "-fx-background-color: #E0DACE;";

		String styleMeuBtn = "-fx-background-radius: none;" + "-fx-min-width: 130px;" + "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;" + "-fx-font-size: 15px;";

		String styleNavButtons = "-fx-background-radius: 10px;" + "-fx-text-fill: black;"
				+ "-fx-background-color: #0095FE;" + "-fx-cursor: hand;";

		CAD_menuTop.setStyle(styleMenuTop);
		CAD_txtDescricao.setStyle(styleEntradas);
		CAD_txtPesquisar.setStyle(styleEntradas);
		CAD_cmbCategoria.setStyle(styleEntradas);
		CAD_cmbFornecedor.setStyle(styleEntradas);
		CAD_cmbPesquisa.setStyle(styleEntradas);
		CAD_btnInserir.setStyle(styleButtons);
		CAD_btnCancelar.setStyle(styleButtons);
		CAD_btnAlterar.setStyle(styleButtons);
		CAD_btnExcluir.setStyle(styleButtons);
		CAD_btnPesquisar.setStyle(styleBtnPesquisa);
		CAD_btnProdutos.setStyle(styleMeuBtn);
		CAD_btnPrimeiro.setStyle(styleNavButtons);
		CAD_btnAnterior.setStyle(styleNavButtons);
		CAD_btnProximo.setStyle(styleNavButtons);
		CAD_btnUltimo.setStyle(styleNavButtons);
	}

	public void btnSelected(int btn) {

		String SelectCAD = "";
		if (btn == 0) {
			SelectCAD = "rgb(237, 237, 237)";

		} else {
			SelectCAD = "rgb(242, 242, 242)";
		}
		CAD_btnProdutos.setStyle("-fx-background-color: " + SelectCAD + ";" + "-fx-background-radius: none;"
				+ "-fx-min-width: 140px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");
	}

	public List<String> adicionarCategoria() {

		List<String> categoria = new ArrayList<>();

		categoria.add(0, "Cosméticos");
		categoria.add(1, "Equipamentos Médicos");
		categoria.add(2, "Medicamentos Genéricos");
		categoria.add(3, "Medicamentos de Referência");
		categoria.add(4, "Medicamentos Similares");
		categoria.add(5, "Produtos de Higiene");
		categoria.add(6, "Suplementos e Vitaminas");

		return categoria;
	}

	public List<String> selecionarTipoPesquisa() {

		List<String> tipos = new ArrayList<>();
		tipos.add(0, "ID");
		tipos.add(1, "Descrição");
		return tipos;
	}

	public static void main(String[] args) {

		Application.launch(args);

	}

	public Produto boundaryToProduto() {

		p = new Produto();
		p.setNome(CAD_txtDescricao.getText());
		p.setCategoria(CAD_cmbCategoria.getSelectionModel().getSelectedItem());
		p.setFornecedor(CAD_cmbFornecedor.getSelectionModel().getSelectedItem());

		return p;
	}

	public void produtoToBoundary(Produto produto) {

		CAD_txtDescricao.setText(produto.getNome());
		CAD_cmbCategoria.setValue(produto.getCategoria());
		CAD_cmbFornecedor.setValue(produto.getFornecedor());
	}

	public boolean validarCampos() {

		boolean isValido = true;
		if (CAD_txtDescricao.getText().equals("") || CAD_txtDescricao.getText().equals(null)) {
			CAD_txtDescricao.setStyle(CAD_txtDescricao.getStyle() + "-fx-border-color: red;"
					+ "-fx-background-radius: 8px;" + "-fx-border-radius: 8px;");
			isValido = false;
		}
		if (CAD_cmbCategoria.getSelectionModel().getSelectedIndex() == -1) {
			CAD_cmbCategoria.setStyle(CAD_cmbCategoria.getStyle() + "-fx-border-color: red;"
					+ "-fx-background-radius: 7px;" + "-fx-border-radius: 7px;");
			isValido = false;
		}
		if (CAD_cmbFornecedor.getSelectionModel().getSelectedIndex() == -1) {
			CAD_cmbFornecedor.setStyle(CAD_cmbFornecedor.getStyle() + "-fx-border-color: red;"
					+ "-fx-background-radius: 7px;" + "-fx-border-radius: 7px;");
			isValido = false;
		}
		if (!isValido) {
			Alert alert = new Alert(AlertType.WARNING, "Complete todos os Campos Grifados em Vermelho");
			alert.setTitle("Atenção");
			alert.show();
		}
		return isValido;
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getSource() == CAD_btnProdutos) {
			CAD_painelProdutos.toFront();
			btnSelected(0);
		}

		if (event.getSource() == CAD_btnCancelar) {
			resetarCampos();
			try {
				cp.SearchProdutoCadastro("");
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == CAD_btnInserir) {

			p = boundaryToProduto();
			if (validarCampos() == true) {
				try {
					cp.AdicionarProduto(p);
					Alert alert = new Alert(AlertType.CONFIRMATION, "Produto Inserido com Êxito");
					alert.show();
					resetarCampos();
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (event.getSource() == CAD_btnPesquisar) {

			if (CAD_cmbPesquisa.getSelectionModel().getSelectedIndex() == -1) {
				Alert alert = new Alert(AlertType.WARNING, "Por Favor, informe o tipo de Pesquisa");
				alert.show();			
			} 
			else if (CAD_cmbPesquisa.getSelectionModel().getSelectedIndex() == 0) {

				try {
					cp.SearchProdutoCadastro(Integer.parseInt(CAD_txtPesquisar.getText()));
					CAD_tblProdutos.requestFocus();
					CAD_tblProdutos.getSelectionModel().clearAndSelect(0);
				} catch (Exception e) {
					CAD_txtPesquisar.clear();
					CAD_txtPesquisar.requestFocus();
				}				
			} 
			else {

				try {
					cp.SearchProdutoCadastro(CAD_txtPesquisar.getText());
					CAD_tblProdutos.requestFocus();
					CAD_tblProdutos.getSelectionModel().clearAndSelect(0);

				} catch (DAOException e) {
					e.printStackTrace();
					System.out.println("Erro de Conexão");
				}
			}
		}

		if (event.getSource() == CAD_btnAlterar) {
			p = boundaryToProduto();
			p.setId_produto(CAD_tblProdutos.getSelectionModel().getSelectedItem().getId_produto());
			try {
				if (validarCampos() == true) {
					cp.AlterarProduto(p);
					Alert alert = new Alert(AlertType.INFORMATION, "Alteração Realizada com Êxito");
					alert.show();
					resetarCampos();
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == CAD_btnExcluir) {
			p = new Produto();
			p.setId_produto(CAD_tblProdutos.getSelectionModel().getSelectedItem().getId_produto());
			try {
				cp.ExcluirProduto(p);
				Alert alert = new Alert(AlertType.INFORMATION, "Exclusão Realizada com Êxito");
				alert.show();
				resetarCampos();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == CAD_btnProximo) {

			CAD_tblProdutos.getSelectionModel().selectNext();
		}
		if (event.getSource() == CAD_btnAnterior) {

			CAD_tblProdutos.getSelectionModel().selectPrevious();
		}
		if (event.getSource() == CAD_btnPrimeiro) {

			CAD_tblProdutos.getSelectionModel().selectFirst();

		}
		if (event.getSource() == CAD_btnUltimo) {

			CAD_tblProdutos.getSelectionModel().selectLast();
		}
	}

	public void resetarCampos() {
		
		CAD_txtDescricao.setText(null);
		CAD_txtPesquisar.setText(null);
		CAD_cmbCategoria.getSelectionModel().clearAndSelect(-1);
		CAD_cmbFornecedor.getSelectionModel().clearAndSelect(-1);
		CAD_txtDescricao.requestFocus();
		CAD_cmbPesquisa.getSelectionModel().clearAndSelect(-1);
	}
}