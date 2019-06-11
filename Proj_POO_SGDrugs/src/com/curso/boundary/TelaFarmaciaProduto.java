package com.curso.boundary;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

import com.curso.control.ControlClientes;
import com.curso.control.ControlFarmaciaProduto;
import com.curso.control.ControlProdutos;
import com.curso.dao.GrupoDAOImpl;
import com.curso.dao.SessaoDAOImpl;
import com.curso.entity.Cliente;
import com.curso.entity.Farmacia;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.Fornecedor;
import com.curso.entity.Grupo;
import com.curso.entity.ProblemaSaude;
import com.curso.entity.Produto;
import com.curso.entity.Sessao;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
	private ComboBox<Grupo> cmbGrupo;
	private ComboBox<Sessao> cmbSessao;
	private ComboBox<String> cmbTipoPesquisa;
	private TableView<Produto> tblProduto;
	private TableView<com.curso.entity.FarmaciaProduto> tblFarmaciaProd;
	private Label lblNomeProduto, lblFornecedor;
	private HBox menuTop;
	private ControlProdutos cp;
	private ControlFarmaciaProduto cfp;
	
	private int idProdSel;
	
	@Override
	public void start(Stage stage) throws Exception {
		
//INICIO FORMULARIO CADASTRO PRODUTO FARMACIA---------------------------------------

		txtQntd = new TextField();
		txtQntd.setPromptText("Qntd");
		txtPrecoUnit = new TextField();
		txtPrecoUnit.setPromptText("Preço unitario");
		ObservableList<Grupo> obs = FXCollections.observableArrayList();
		//obs.addAll(new Grupo(1, "Utilitarios"), new Grupo(2, "Genericos"), new Grupo(3, "Pereciveis"), new Grupo(4, "Comestiveis"));
		obs.addAll(new GrupoDAOImpl().pesquisarPorGrupos());
		cmbGrupo = new ComboBox<Grupo>(obs);
		cmbGrupo.setPromptText("Grupo");
		ObservableList<Sessao> obs1 = FXCollections.observableArrayList();
		//obs1.addAll(new Sessao(1, "A"), new Sessao(2, "B"), new Sessao(3, "C"), new Sessao(4, "D"));
		obs1.addAll(new SessaoDAOImpl().pesquisarPorSessoes());
		cmbSessao = new ComboBox<Sessao>(obs1);
		cmbSessao.setPromptText("Sessão");
		btnCadastro = new Button("CADASTRAR");
		btnPesquisaProd = new Button("PESQUISAR");
		txtPesquisaProd = new TextField();
		cmbTipoPesquisa = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"ID", "NOME", "CATEGORIA", "FORNECEDOR"}));
		cmbTipoPesquisa.setPromptText("Tipo");
		tblProduto = new TableView<Produto>();
		cp = new ControlProdutos();
		cfp = new ControlFarmaciaProduto();
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
		
		tblFarmaciaProd = new TableView<com.curso.entity.FarmaciaProduto>();
		
		VBox vb = new VBox(10,
				new HBox(10, cmbTipoPesquisa, new HBox(txtPesquisaProd, btnPesquisaProd)),
				tblFarmaciaProd
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
		createTableColumnsProdsFarm();
		cp.attTableProds();
		
		stage.setMaximized(true);
		Scene scene = new Scene(pane, stage.getWidth(),stage.getHeight());
		stage.setScene(scene);
		stage.setTitle("Manter Produtos da farmacia");
		stage.show();
		
		startStyle();
		btnSelected(0);
		
		btnCadProd.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnMantProd.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCadastro.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisaProd.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
		
	}
	
	public void startStyle() {
		
		DropShadow dp = new DropShadow(4, 0, 0, Color.GRAY);
		
		String styleBtnPesquisa = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;"
				+ "-fx-background-radius: 0px 8px 8px 0px;" + "-fx-min-width: 240px;" + "-fx-max-height: 31.5px;" + "-fx-cursor: hand;";
		
		
		String styleBtns = 
				"-fx-background-color: #0095FE;"
				+ "-fx-text-fill: white;"
				+ "-fx-background-radius: 7;"
				+ "-fx-min-width: 275px;"
				+ "-fx-min-height: 40px;"
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
		
		
		String styleEntradas = "-fx-background-radius: 8;";
		
		String styleEntradaPesquisa = "-fx-background-radius: 8px 0px 0px 8px;"
				+ "-fx-min-width: 537px;";
		
		String comboStyle = "-fx-background-radius: 8;"
				+ "-fx-background-color: #FEFFFA;"
				+ "-fx-cursor: hand;";
		
		btnCadProd.setStyle(styleBtn);
		btnMantProd.setStyle(styleBtn);
		painelCad.setStyle(stylePainel);
		painelMant.setStyle(stylePainel);
		menuTop.setStyle(styleMenuTop);
		txtPesquisaProd.setStyle(styleEntradaPesquisa + "-fx-font-size: 15.1px; -fx-min-width: 920px;");
		btnPesquisaProd.setStyle(styleBtnPesquisa);
		cmbTipoPesquisa.setStyle(comboStyle + "-fx-font-size: 15.1px; -fx-min-width: 100px;");
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
		}else
		if(e.getSource() == btnCadastro) {
			cfp.inserir(boundaryToFarmaciaProd());
			setFunctionProdFarmButtons();
			Alert a = new Alert(AlertType.INFORMATION, "Cadastro realizado com sucesso !!!");
			a.show();
			limparcampos();
		}else
		if(e.getSource() == btnPesquisaProd) {
			if(txtPesquisaProd.getText().equals("")) {
				cfp.attTableProdutoFarm();
			}else {
				Cliente cl = null;
				if(cmbTipoPesquisa.getSelectionModel().getSelectedItem() != null) {
					switch(cmbTipoPesquisa.getSelectionModel().getSelectedItem()) {
						case "ID":
							FarmaciaProduto fp = cfp.pesquisarFarmaciaProd(Integer.parseInt(txtPesquisaProd.getText()));
							if(fp != null) {
								cfp.attTableProdutoFarm(fp);
							}
							break;
						case "NOME":
							cfp.pesquisarFarmaciaProd(txtPesquisaProd.getText(), "NOME");
							break;
						case "CATEGORIA":
							cfp.pesquisarFarmaciaProd(txtPesquisaProd.getText(), "CATEGORIA");
							break;		
						case "FORNECEDOR":
							cfp.pesquisarFarmaciaProd(txtPesquisaProd.getText(), "FORNECEDOR");
							break;
					}
				}else {
					JOptionPane.showMessageDialog(null, "Atenção, é preciso determinar o tipo de pesquisa que sera realizada", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			setFunctionProdFarmButtons();
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
	
	@SuppressWarnings("unchecked")
	public void createTableColumnsProdsFarm() {

		tblFarmaciaProd.setItems(cfp.getDataListProdFarm());
		
		TableColumn<FarmaciaProduto, Number> id_produto = new TableColumn<>("ID produto");
		id_produto.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getIdFarmaciaProd()));
		
		TableColumn<FarmaciaProduto, String> categoria = new TableColumn<>("Categoria");
		categoria.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getProduto().getCategoria()));
		
		TableColumn<FarmaciaProduto, String> nome = new TableColumn<>("Produto");
		nome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getProduto().getNome()));
		
		TableColumn<FarmaciaProduto, Number> qntdEstoque = new TableColumn<>("Qntd. Estoque");
		qntdEstoque.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getQntdEstoque()));
		
		TableColumn<FarmaciaProduto, Number> preco = new TableColumn<>("Preco Unit.");
		preco.setCellValueFactory(item -> new ReadOnlyDoubleWrapper(item.getValue().getPreco()));
		
		TableColumn<FarmaciaProduto, Button> excluir = new TableColumn<>("Excluir");
		excluir.setCellValueFactory(item -> new ReadOnlyObjectWrapper(item.getValue().getBtnExcluir()));
		
		tblFarmaciaProd.getColumns().addAll(id_produto, categoria, nome, qntdEstoque, preco, excluir);
		setFunctionProdFarmButtons();
		
	}
	
	private void setFunctionProdFarmButtons() {
		for (int i = 0; i < tblFarmaciaProd.getItems().size(); i++) {
			
			final int l = i;

			tblFarmaciaProd.getItems().get(i).getBtnExcluir().setOnAction(e -> {
				cfp.removerProdutoFarm(cfp.pesquisarFarmaciaProd(tblFarmaciaProd.getItems().get(l).getIdFarmaciaProd()));
				cfp.attTableProdutoFarm();
				setFunctionProdFarmButtons();
			});
		}
	}
	
	public void produtoToBoundary(Produto p) {
		this.idProdSel = p.getId_produto();
		this.lblNomeProduto.setText(p.getNome());
		this.lblFornecedor.setText(p.getFornecedor().getNome_fantasia());
	}
	
	public FarmaciaProduto boundaryToFarmaciaProd(){
		FarmaciaProduto fp = new FarmaciaProduto();
		fp.setProduto(cp.selecionarProduto(idProdSel));
		fp.setFarmacia(new Farmacia());
		fp.setPreco(Double.parseDouble(txtPrecoUnit.getText()));
		fp.setQntdEstoque(Integer.parseInt(txtQntd.getText()));
		fp.setGrupo(cmbGrupo.getValue());
		fp.setSessao(cmbSessao.getValue());
		return fp;
	}
	
	public void limparcampos() {
		this.txtPrecoUnit.setText("");
		this.txtQntd.setText("");
		this.lblFornecedor.setText("");
		this.lblNomeProduto.setText("");
		this.cmbGrupo.getSelectionModel().select(-1);
		this.cmbSessao.getSelectionModel().select(-1);
	}
	
}