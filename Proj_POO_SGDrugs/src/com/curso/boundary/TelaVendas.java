package com.curso.boundary;
import java.io.FileInputStream;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import com.curso.control.ControlClientes;
import com.curso.control.ControlVendas;
import com.curso.entity.Cliente;
import com.curso.entity.FarmaciaProduto;
import com.curso.entity.FormaPagto;
import com.curso.entity.Funcionario;
import com.curso.entity.ItemVenda;
import com.curso.entity.Venda;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
	private Label lblNomeProd;
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
   // private Button btnPOSVenda;
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
	DecimalFormat df = new DecimalFormat("#,##0.00");
	
	ControlVendas cv;
	
	//TESTE
	Cliente cl = new Cliente();
	Funcionario func = new Funcionario();

	@Override
	public void start(Stage stage) throws Exception {
		
		cl.setPrimeiroNome("Raphael");
		func.setPrimeiroNome("Adalberto");

		cv = new ControlVendas();
		
		painelVenda = new Pane();
		painelPosVenda = new Pane();
		
		//btnPOSVenda = new Button("POS-VENDA");
		btnVenda = new Button("VENDA");
		//menutop = new HBox(btnVenda, btnPOSVenda);
		menutop = new HBox(btnVenda);

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
		lblTotal.setMaxSize(500, 40);
		lblTotal.setStyle("-fx-font-size: 30px;");
		lblTotal.relocate(20, 600);

		lblNomeProd = new Label("Nome produto: Nenhum produto selecionado");
		lblValorUnt = new Label("preço unitáro: R$0,00");
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

		pane.setTop(menutop);
		StackPane painels = new StackPane(painelPosVenda , painelVenda);
		pane.setCenter(painels);

		VBox vbEntradaProduto = new VBox(new Label("Adicionar Produto"), new Separator(),
				new HBox(10, txtPesquisa, btnPesquisar), 
				new HBox(10, txtQuantidade, btnAdicionar),
				new HBox(10, lblNomeProd),
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
		hbGeralVenda.setStyle("-fx-background-color: rgb(242,242,242);");

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

		painelPosVenda.setStyle("-fx-background-color: rgba(105,105,105, 0.6)");
		painelPosVenda.getChildren().addAll(hbInforsGerais, lblInformacoesGerais, hbEfetuarPagamento, lblPagamento);
		
		stage.setMaximized(true);
		Scene scene = new Scene(pane, stage.getWidth(),stage.getHeight());
		stage.setScene(scene);
		stage.setTitle("Tela de Vendas");
		stage.show();
		
		btnVenda.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		//btnPOSVenda.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnFinalizar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnVoltar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnFinalizaPOS.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAdicionar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
		createTableColumnsItensVenda();
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
		//btnPOSVenda.setStyle(styleMeuBtn);
		menutop.setStyle(styleMenuTop);
		btnVoltar.setStyle(styleBtnVoltar);
		txtDinheiro.setStyle(styleEntradasPagamento);
		txtCardCredito.setStyle(styleEntradasPagamento);
		txtCardDebito.setStyle(styleEntradasPagamento);
		btnFinalizaPOS.setStyle(styleBtnFinaliza);

	}

	@SuppressWarnings("unchecked")
	public void createTableColumnsItensVenda() {
		
		tblItens.setItems(cv.getDataItens());
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

		TableColumn<ItemVenda, String> sub_total = new TableColumn<>("Subtotal");
		sub_total.setCellValueFactory(
				item -> new ReadOnlyStringWrapper(df.format(item.getValue().getSubtotal())));
		
		TableColumn<ItemVenda, Button> btnIsencao = new TableColumn<>("Isenção");
		btnIsencao.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getProduto().getBtnIsencao()));
		btnIsencao.setStyle("-fx-alignment: CENTER");
		
		TableColumn<ItemVenda, Button> btnExcluir = new TableColumn<>("Excluir");
		btnExcluir.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getProduto().getBtnExcluir()));
		btnExcluir.setStyle("-fx-alignment: CENTER");

		tblItens.getColumns().addAll(id_produto, desc_produto, valor_produto, quant_produto, sub_total, btnIsencao, btnExcluir);
		setFunctionItensButtons();
		
	}

	private void setFunctionItensButtons() {
		for(int i=0; i<tblItens.getItems().size(); i++) {		
			
			final int l = i;
			
			if(tblItens.getItems().get(i).getProduto().getProduto().getCategoria().equals("genérico")) {
				tblItens.getItems().get(i).getProduto().getBtnIsencao().setOnAction(e -> {
					if(tblItens.getItems().get(l).getProduto().getBtnIsencao().getText().equals("SUS")) {
						tblItens.getItems().get(l).aplicarIsencao();
						tblItens.getItems().get(l).getProduto().getBtnIsencao().setText("X");
					}else {
						tblItens.getItems().get(l).removerIsencao();
						tblItens.getItems().get(l).getProduto().getBtnIsencao().setText("SUS");
					}
					atualizarTotal();
					setFunctionItensButtons();
					cv.attDataItens();
				});
			}else {
				tblItens.getItems().get(i).getProduto().getBtnIsencao().setVisible(false);
			}
			
			tblItens.getItems().get(i).getProduto().getBtnExcluir().setOnAction(e -> {
				cv.resetFarmaciaProdutos();
				cv.delItemVenda(tblItens.getItems().get(l));
				atualizarTotal();
				setFunctionItensButtons();
			});
		}
	}
	
	public void btnSelected(int btn) {
		String SelectVENDA = "";
		SelectVENDA = "rgb(242, 242, 242);";
		btnVenda.setStyle("-fx-background-color: " + SelectVENDA + ";"
				+ "-fx-background-radius: none;"
				+ "-fx-min-width: 140px;"
				+ "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;");
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

	public void atualizarTotal() {
		lblTotal.setText("TOTAL: R$" + df.format(cv.getVendaAtual().returnPrecoTotal()));
	}

	@Override
	public void handle(MouseEvent event) {
		
		if (event.getSource() == btnVenda) {
			painelVenda.toFront();
			btnSelected(0);
		}else
		if (event.getSource() == btnPesquisar) {
			FarmaciaProduto fp = cv.pesquisaProd(Integer.parseInt(txtPesquisa.getText()));
			if(fp != null) {
				this.lblValorUnt.setText("preço unitáro: R$" + df.format(fp.getPreco()));
				this.lblQuantidade.setText("Qtd. total no estoque:" + fp.getQntdEstoque());
				this.lblNomeProd.setText("Nome produto: " + fp.getProduto().getNome());
			}else {
				JOptionPane.showMessageDialog(null, "Produto não encontrado !!!", "Ops...", JOptionPane.ERROR_MESSAGE);
				limparCamposPesquisa();
			}
		}else
		if (event.getSource() == btnAdicionar) {
			FarmaciaProduto fp = cv.pesquisaProd(Integer.parseInt(txtPesquisa.getText()));
			if(!cv.getVendaAtual().existItem(fp.getProduto())) {
				ItemVenda iv = new ItemVenda();
				iv.setProduto(fp);
				iv.setQntd(Integer.parseInt(txtQuantidade.getText()));
				iv.calcSubTotal();
				cv.addItemVenda(iv);
				atualizarTotal();
				setFunctionItensButtons();
				limparCamposPesquisa();
			}
		}
		else
		if (event.getSource() == btnFinalizar) {
			if((cbCartaoCredito.isSelected() || cbCartaoDebito.isSelected() || cbDinheiro.isSelected())
					&& cv.getVendaAtual().getItems().size() != 0) {
				painelPosVenda.toFront();
				configEntradaFormaPagamento();
				
			}else {
				if(cv.getVendaAtual().getItems().size() == 0) {
					JOptionPane.showMessageDialog(null, "Não é possivel realizar a transação pois nenhum produto\nfoi adicionado.", "Problema na transação", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Selecione ao menos uma forma de pagamento", "Problema na transação", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		if(event.getSource() == btnVoltar) {
			painelVenda.toFront();
		}else
		if(event.getSource() == btnFinalizaPOS) {
			//finalizar a compra tela pos venda
			cv.getVendaAtual().setCliente(cl);
			cv.getVendaAtual().setFuncionario(func);
			cv.addVenda();
			JOptionPane.showMessageDialog(null, "Compra realiza com sucesso !!!", "Transação finalizada", JOptionPane.INFORMATION_MESSAGE);
			painelVenda.toFront();
			limparCampos();
			cv.resetFarmaciaProdutos();
		}

	}
	
	public void configEntradaFormaPagamento() {
		boolean cred = false, deb = false, din = false;
		txtCardCredito.setDisable(true);
		txtCardDebito.setDisable(true);
		txtDinheiro.setDisable(true);
		lblFormaPagamento.setText("");
		if(cbCartaoCredito.isSelected()) {
			txtCardCredito.setDisable(false);
			lblFormaPagamento.setText(lblFormaPagamento.getText() + "Crédito\n");
			cred = true;
		}
		if(cbCartaoDebito.isSelected()) {
			txtCardDebito.setDisable(false);
			lblFormaPagamento.setText(lblFormaPagamento.getText() + "Debito\n");
			deb = true;
		}
		if(cbDinheiro.isSelected()) {
			txtDinheiro.setDisable(false);
			lblFormaPagamento.setText(lblFormaPagamento.getText() + "Dinheiro\n");
			din = true;
		}
		distribuiValor(cred, deb, din);
	}
	
	private void distribuiValor(boolean cred, boolean deb, boolean din) {
		cv.getVendaAtual().resetListFormasPagto();
		double vl = 0;
		txtCardCredito.setText("");
		txtCardDebito.setText("");
		txtDinheiro.setText("");
		if(cred && deb && din) {
			vl = cv.getVendaAtual().returnPrecoTotal() / 3;
			txtCardCredito.setText(df.format(vl));
			txtCardDebito.setText(df.format(vl));
			txtDinheiro.setText(df.format(vl));
		}else
		if(cred && deb) {
			vl = cv.getVendaAtual().returnPrecoTotal() / 2;
			txtCardCredito.setText(df.format(vl));
			txtCardDebito.setText(df.format(vl));
		}else 
		if(cred && din){
			vl = cv.getVendaAtual().returnPrecoTotal() / 2;
			txtCardCredito.setText(df.format(vl));
			txtDinheiro.setText(df.format(vl));
		}else
		if(deb && din) {
			vl = cv.getVendaAtual().returnPrecoTotal() / 2;
			txtCardDebito.setText(df.format(vl));
			txtDinheiro.setText(df.format(vl));
		}else
		if(cred) {
			vl = cv.getVendaAtual().returnPrecoTotal();
			txtCardCredito.setText(df.format(vl));
		}else
		if(deb) {
			vl = cv.getVendaAtual().returnPrecoTotal();
			txtCardDebito.setText(df.format(vl));
		}else
		if(din) {
			vl = cv.getVendaAtual().returnPrecoTotal();
			txtDinheiro.setText(df.format(vl));
		}
		
		if(cred) {
			cv.getVendaAtual().getFormasPagto().add(new FormaPagto("Crédito", vl));
		}
		if(deb) {
			cv.getVendaAtual().getFormasPagto().add(new FormaPagto("Dédito", vl));
		}
		if(din) {
			cv.getVendaAtual().getFormasPagto().add(new FormaPagto("Dinheiro", vl));
		}
			
	}
	
	public void limparCampos(){
		this.txtPesquisa.setText("");
		this.txtQuantidade.setText("");
		this.cbCartaoCredito.setSelected(false);
		this.cbCartaoDebito.setSelected(false);
		this.cbDinheiro.setSelected(false);
		limparCamposPesquisa();
		atualizarTotal();
	}
	
	public void limparCamposPesquisa() {
		this.lblValorUnt.setText("preço unitáro: R$0,00");
		this.lblQuantidade.setText("Qtd. total no estoque: 0");
		this.lblNomeProd.setText("Nome produto: Nenhum produto selecionado");
	}

}