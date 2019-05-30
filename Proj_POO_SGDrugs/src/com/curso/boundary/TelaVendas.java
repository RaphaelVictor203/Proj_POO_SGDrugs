package com.curso.boundary;
import java.io.FileInputStream;
import com.curso.control.ControlVendas;
import com.curso.entity.ItemVenda;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

public class TelaVendas extends Application implements EventHandler<MouseEvent> {
	private Pane painelVenda;
	private Pane painelPosVenda;
	private Button btnPesquisar;
	private Button btnAdicionar;
	private Button btnFinalizar;
	private Label lblValorUnt;
	private Label lblQuantidade;
	private Label lblTotal;
	private Label lblVenda;
	private Label lblCompra;
	private Label lblPagamento;
	private CheckBox cbCartaoCredito;
	private CheckBox cbCartaoDebito;
	private CheckBox cbDinheiro;
	private TextField txtPesquisa;
	private TextField txtQuantidade;
	private TableView<ItemVenda> tblItens;
    private Button btnPOSVenda;
    private Button btnVenda;
    private HBox menutop;
	private Label lblNomeCliente;
	private Label lblInformacoesGerais;
	private Label lblNomeVendedor;
	private Label lblFormaPagamento;
	private Button btnVoltar; 
	private TextField txtCardCredito;
	private TextField txtCardDebito;
	private TextField txtDinheiro;
	private Button btnFinalizaPOS;
	
	
	ControlVendas cv;

	@Override
	public void start(Stage stage) throws Exception {

		painelVenda = new Pane();
		painelPosVenda = new Pane();
		
		btnPOSVenda = new Button("POS-VENDA");
		btnVenda = new Button("VENDA");
		menutop = new HBox(btnVenda, btnPOSVenda);

		BorderPane pane = new BorderPane();

		lblCompra = new Label("COMPRA");
		lblCompra.setStyle("-fx-font-size: 35px;");
		lblCompra.setPrefSize(170, 50);
		lblCompra.relocate(1040, 40);

		lblVenda = new Label("ITENS DA VENDA");
		lblVenda.setStyle("-fx-font-size: 35px;");
		lblVenda.setPrefSize(270, 50);
		lblVenda.relocate(270, 40);

		lblTotal = new Label("TOTAL: R$0,00");
		lblTotal.setMaxSize(200, 40);
		lblTotal.setStyle("-fx-font-size: 30px;");
		lblTotal.relocate(270, 600);

		lblValorUnt = new Label("preço unitáro: R$: 0,00");
		lblQuantidade = new Label("Qtd. total no estoque: 0");
		
		ImageView iv = new ImageView(new Image(new FileInputStream("imgs\\search.png")));
		iv.setFitHeight(20);
		iv.setFitWidth(20);

		btnPesquisar = new Button("", iv);
		btnPesquisar.setPrefSize(30, 25);

		btnAdicionar = new Button("ADICIONAR");
		btnAdicionar.setPrefSize(140, 25);

		btnFinalizar = new Button("FINALIZAR COMPRA");
		btnFinalizar.setPrefSize(516, 105);

		txtPesquisa = new TextField();
		txtPesquisa.setPrefSize(200, 25);
		txtPesquisa.setPromptText("codigo ou descrição");

		txtQuantidade = new TextField();
		txtQuantidade.setPrefSize(190, 25);
		txtQuantidade.setPromptText("insira a quantidade");

		cbCartaoCredito = new CheckBox("cartão de crédito");
		cbCartaoDebito = new CheckBox("cartão de débito");
		cbDinheiro = new CheckBox("dinheiro");

		tblItens = new TableView<ItemVenda>();
		tblItens.setPrefWidth(600);
		tblItens.setPrefHeight(400);

		createTableColumnsProb();

		pane.setTop(menutop);
		StackPane painels = new StackPane(painelPosVenda , painelVenda);
		pane.setCenter(painels);

		VBox vbEntradaProduto = new VBox(new Label("Adicionar Produto"), new Separator(),
				new HBox(10, txtPesquisa, btnPesquisar), 
				new HBox(10, txtQuantidade, btnAdicionar),
				new HBox(10, lblValorUnt), 
				new HBox(10, lblQuantidade));
		vbEntradaProduto.setPadding(new Insets(130, 60, 50, 20));
		vbEntradaProduto.setSpacing(10);
		vbEntradaProduto.setStyle("-fx-min-width: 50%; -fx-font-size: 15px; ");

		VBox vbFormaPagamento = new VBox(new Label("Forma de Pagamento"), new Separator(), new HBox(10, cbCartaoCredito),
				new HBox(10, cbCartaoDebito), new HBox(10, cbDinheiro));
		vbFormaPagamento.setPadding(new Insets(20, 60, 40, 20));
		vbFormaPagamento.setSpacing(10);
		vbFormaPagamento.setStyle("-fx-min-width: 50%; -fx-font-size: 15px; ");

		HBox hbFinalizar = new HBox(btnFinalizar);
		hbFinalizar.setPadding(new Insets(0, 0, 20, 0));
		hbFinalizar.setStyle("-fx-min-width: 50%; -fx-font-size: 15px; ");

		HBox hbCompra = new HBox(new VBox(vbEntradaProduto, vbFormaPagamento, hbFinalizar));
		hbCompra.setStyle("-fx-background-color: #EEE5DE;");
		hbCompra.setPadding(new Insets(0, 60, 25, 45));

		VBox vbTabelaItens = new VBox(new HBox(tblItens));
		vbTabelaItens.setPadding(new Insets(140, 0, 100, 50));
		
		HBox hbTabelaItens = new HBox(new VBox(vbTabelaItens));
		hbTabelaItens.setStyle("-fx-background-color: rgb(242,242,242)");
		hbTabelaItens.setPadding(new Insets(0, 100, 100, 50));

		HBox hbGeralVenda = new HBox(new VBox(hbTabelaItens), new VBox(hbCompra, hbFinalizar));
		hbGeralVenda.setPadding(new Insets(0, 1280, 0, 0));
		hbGeralVenda.setSpacing(50);
		hbGeralVenda.setStyle("-fx-background-color: rgb(237,237,237);");

		painelVenda.getChildren().addAll(hbGeralVenda, lblCompra, lblVenda, lblTotal);
		
		
		//-----------------------------------Fim do Painel Venda---------------------------------------------
		
		
		lblInformacoesGerais = new Label("INFORMAÇÕES GERAIS");
		lblInformacoesGerais.setPrefSize(250, 30);
		lblInformacoesGerais.setStyle("-fx-font-size: 20px;");
		lblInformacoesGerais.relocate(320, 185);
		
		lblNomeCliente = new Label("_______________________");
		lblNomeVendedor = new Label("_______________________");
		lblFormaPagamento = new Label("_______________________");
		
		btnVoltar = new Button("VOLTAR");
		btnVoltar.setPrefSize(350, 40);
		
		lblPagamento = new Label("EFETUAR PAGAMENTO");
		lblPagamento.setPrefSize(360, 35);
	    lblPagamento.setStyle("-fx-font-size: 35px;");
	    lblPagamento.relocate(940, 45);
		
		txtCardDebito = new TextField();
		txtCardCredito = new TextField();
		txtDinheiro = new TextField();
	
		btnFinalizaPOS = new Button("FINALIZAR COMPRA");
		btnFinalizaPOS.setPrefSize(516, 105);
		
		VBox vbInfosGerais = new VBox(new HBox(10, new Label("Nome do Cliente:")), 
		new HBox(10,  lblNomeCliente),
		new HBox(10, new Label("Nome do Vendedor:")),
		new HBox(10,  lblNomeVendedor),
		new HBox(10, new Label("Formas de Pagamento: ")), 
		new HBox(10, lblFormaPagamento));
		
		vbInfosGerais.setSpacing(7);
		vbInfosGerais.setPadding(new Insets(35, 150, 25, 40));
        vbInfosGerais.setStyle("-fx-background-color: rgb(237,237,237);"
        					 + "-fx-font-size: 15px;");
				
        HBox hbVoltar = new HBox(btnVoltar);
        hbVoltar.setSpacing(20);
        hbVoltar.setAlignment(Pos.BASELINE_CENTER);
    
        
		HBox hbInforsGerais = new HBox();
		hbInforsGerais.setPadding(new Insets(50, 0, 0, 0));
		hbInforsGerais.setSpacing(50);
		hbInforsGerais.setStyle("-fx-background-color: rgb(237,237,237);");
		hbInforsGerais.getChildren().addAll(new VBox(50, vbInfosGerais, hbVoltar));		
		hbInforsGerais.relocate(245, 170);
	
		
		VBox vbPagamento = new VBox(
		new HBox(new Label("Cart. crédito: ")),
		new HBox(txtCardCredito),
		new HBox(new Label("Cart. débito:")),
		new HBox(txtCardDebito),
		new HBox(new Label("Dinheiro")),
		new HBox(txtDinheiro)
		); 
		vbPagamento.setPadding(new Insets(150, 0, 50, 155));
		vbPagamento.setStyle("-fx-font-size: 20px;");
		vbPagamento.setSpacing(5);
		
		HBox hbFinalizaCompra = new HBox(btnFinalizaPOS);
		hbFinalizaCompra.setPadding(new Insets(125, 0, 0, 0));
		hbFinalizaCompra.setStyle("-fx-min-width: 50%; -fx-font-size: 25px; ");

		HBox hbEfetuarPagamento = new HBox(new VBox(vbPagamento, hbFinalizaCompra));
		
		hbEfetuarPagamento.setStyle("-fx-background-color: #EEE5DE;");
		hbEfetuarPagamento.setPadding(new Insets(40, 0, 0, 0));
		hbEfetuarPagamento.relocate(850, 0);

		
		painelPosVenda.getChildren().addAll(hbInforsGerais, lblInformacoesGerais, hbEfetuarPagamento, lblPagamento);
		
		Scene scene = new Scene(pane, 1360, 700);
		stage.setScene(scene);
		stage.setTitle("Controle de Vendas");
		stage.show();
		
		btnVenda.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnPOSVenda.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
		
		loadStyles();
		btnSelected(0);

	}

	public void loadStyles() {

		String stylePainelVendas = "-fx-background-color:white;" + "-fx-padding: 50, 50, 50, 50";

		String styleEntradas = "-fx-background-radius: 8;"
							 + "-fx-font-size: 15px;";

		String styleBtnPesq = 	"-fx-background-color: #0095FE;"
							+ 	"-fx-cursor: hand;";
		
		String styleBtnAdd = 	"-fx-background-color: #007F0E;" 
						   + 	"-fx-text-fill: white;"
						   + 	"-fx-cursor: hand;";

		String styleBtnFinaliza = "-fx-background-color: #007F0E;" 
								+ "-fx-text-fill: white;"
								+ "-fx-font-size: 35px;"
								+ "-fx-background-radius: none;"
								+ "-fx-cursor: hand;";
				
		String styleMeuBtn =	  "-fx-background-radius: none;" 
								+ "-fx-min-width: 130px;" 
								+ "-fx-min-height: 40px;"
								+ "-fx-cursor: hand;" 
								+ "-fx-font-size: 15px;";
		
		String styleMenuTop = "-fx-background-color: #E0DACE";
		
		String styleBtnVoltar = "-fx-background-color: red;"
							  + "-fx-text-fill: white;"
							  + "-fx-background-radius: none;"
							  + "-fx-cursor: hand;"
							  + "-fx-font-size: 15px;";
		
		String styleEntradasPagamento = "-fx-background-radius: 8;"
									  + "-fx-font-size: 15px;";

		btnPesquisar.setStyle(styleBtnPesq);
		btnAdicionar.setStyle(styleBtnAdd);
		btnFinalizar.setStyle(styleBtnFinaliza);
		painelVenda.setStyle(stylePainelVendas);
		txtPesquisa.setStyle(styleEntradas);
		txtQuantidade.setStyle(styleEntradas);
		btnVenda.setStyle(styleMeuBtn);
		btnPOSVenda.setStyle(styleMeuBtn);
		menutop.setStyle(styleMenuTop);
		btnVoltar.setStyle(styleBtnVoltar);
		txtDinheiro.setStyle(styleEntradasPagamento);
		txtCardCredito.setStyle(styleEntradasPagamento);
		txtCardDebito.setStyle(styleEntradasPagamento);
		btnFinalizaPOS.setStyle(styleBtnFinaliza);

	}

	@SuppressWarnings("unchecked")
	public void createTableColumnsProb() {
		
		TableColumn<ItemVenda, Number> id_produto = new TableColumn<>("ID");
		id_produto.setCellValueFactory(
				item -> new ReadOnlyIntegerWrapper(item.getValue().getProduto().getProduto().getId_produto()));

		TableColumn<ItemVenda, String> desc_produto = new TableColumn<>("Descrição");
		desc_produto.setCellValueFactory(
				item -> new ReadOnlyStringWrapper(item.getValue().getProduto().getProduto().getNome()));

		TableColumn<ItemVenda, Number> valor_produto = new TableColumn<>("Preço");
		valor_produto.setCellValueFactory(item -> new ReadOnlyDoubleWrapper(item.getValue().getProduto().getPreco()));

		TableColumn<ItemVenda, Number> quant_produto = new TableColumn<>("Quant.");
		quant_produto.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getQntd()));

		TableColumn<ItemVenda, Number> sub_total = new TableColumn<>("Subtotal");
		sub_total.setCellValueFactory(
				item -> new ReadOnlyDoubleWrapper(item.getValue().getProduto().getPreco() * item.getValue().getQntd()));

		tblItens.getColumns().addAll(id_produto, desc_produto, valor_produto, quant_produto, sub_total);
	}

	public void btnSelected(int btn) {
		String SelectVENDA = "";
		String SelectPOS = "";
		if(btn == 0) {
			SelectVENDA = "rgb(242, 242, 242);";
			SelectPOS = "rgb(237, 237, 237);";
		} else {
			SelectPOS = "rgb(242, 242, 242);";
			SelectVENDA = "rgb(237, 237, 237);";	
		}
		btnVenda.setStyle("-fx-background-color: " + SelectVENDA + ";"
						+ "-fx-background-radius: none;"
						+ "-fx-min-width: 140px;"
						+ "-fx-min-height: 40px;"
						+ "-fx-cursor: hand;");
		btnPOSVenda.setStyle("-fx-background-color: " + SelectPOS + ";"
						   + "-fx-background-radius: none;"
						   + "-fx-min-width: 140px;"
						   + "-fx-min-height: 40px;"
						   + "-fx-cursor: hand;");
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

	public void finalizarCompra() {

	}

	@Override
	public void handle(MouseEvent event) {
		
		if (event.getSource() == btnVenda) {
			painelVenda.toFront();
			btnSelected(0);
		} else if(event.getSource() == btnPOSVenda) {
			painelPosVenda.toFront();
			btnSelected(1);
		}
		
		if (event.getSource() == btnPesquisar) {

		}

		if (event.getSource() == btnAdicionar) {

		}
		
		if (event.getSource() == btnFinalizar) {

		}

	}

}
