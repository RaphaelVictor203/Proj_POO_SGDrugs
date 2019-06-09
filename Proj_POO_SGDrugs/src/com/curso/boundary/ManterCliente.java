package com.curso.boundary;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import com.curso.control.ControlClientes;
import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.ProblemaSaude;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ManterCliente extends Application implements EventHandler<MouseEvent> {

	private Button btnCadCli;
	private Button btnMantCli;
	private Pane painelCad;
	private BorderPane painelMant;
	private HBox menuTop;
	private TextField txtNome;
	private ComboBox<String> cmbDia, cmbMes, cmbAno;
	private TextField txtRG, txtCPF;
	private TextField txtTelefone, txtEmail;
	private TextField txtCartaoSus;
	private TextField txtCEP;
	private TextField txtRua, txtNum, txtBairro;
	private ComboBox<String> cmbCid, cmbUF, cmbPesquisa;
	private TextField txtClientePesquisa;
	private TextField txtPesquisa;
	private TextField txtCPFPesquisa;
	private TextField txtUFPesquisa;
	private TextField txtCidadePesquisa;
	private ComboBox<String> cmbSexo;
	private TableView<Cliente> tblCli;
	private TableView<ProblemaSaude> tblProb;
	private List<ProblemaSaude> ps;
	private Button btnAddProb, btnLimpaCampos, btnCadastrar, btnPesquisaProb, btnPesquisa;

	ControlClientes cc;

	@Override
	public void start(Stage stage) throws Exception {

		cc = new ControlClientes();
		painelCad = new Pane();
		// painelMant = new BorderPane();

		// INICIO PAINEL
		// CADASTRO-------------------------------------------------------------------------------

		ps = new ArrayList<ProblemaSaude>();

		txtNome = new TextField();
		cmbDia = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1, 31)));
		cmbMes = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1, 12)));
		cmbAno = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1990, 2019)));
		txtRG = new TextField();
		txtCPF = new TextField();
		txtTelefone = new TextField();
		txtEmail = new TextField();
		txtCartaoSus = new TextField();

		txtCEP = new TextField();
		txtRua = new TextField();
		txtBairro = new TextField();
		txtNum = new TextField();
		cmbCid = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"São Paulo", "Ribeirão", "Botucatu", "Piracicaba", "Santos", "Franca", "Araçatuba"}));
		cmbUF = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"SP"}));
		cmbSexo = new ComboBox<String>(FXCollections.observableArrayList(new String[]{"M", "F"}));
		cmbSexo = new ComboBox<String>(FXCollections.observableArrayList(new String[] { "M", "F" }));
		cmbSexo.getSelectionModel().select(0);

		txtPesquisa = new TextField();
		txtPesquisa.setPromptText("Insira a descrição do problema");

		btnLimpaCampos = new Button("LIMPAR CAMPOS");
		btnCadastrar = new Button("CADASTRAR");
		btnAddProb = new Button("ADICIONAR");
		tblProb = new TableView<ProblemaSaude>();
		tblProb.setMaxWidth(625);
		ImageView iv = new ImageView(new Image(new FileInputStream("imgs\\icon.png")));
		iv.setFitHeight(22);
		iv.setFitWidth(22);
		btnPesquisaProb = new Button("", iv);

		BorderPane pane = new BorderPane();

		btnCadCli = new Button("CADASTRO");
		btnMantCli = new Button("GERENCIAMENTO");
		menuTop = new HBox(btnCadCli, btnMantCli);

		VBox entradaInfoCli = new VBox(new Label("INFORMAÇÕES RELACIONADAS AO CLIENTE"), new Separator(),
				new HBox(10, new Label("Nome: "), txtNome),
				new HBox(10, new Label("Data Nascimento: "), cmbDia, new Label("/"), cmbMes, new Label("/"),cmbAno),
				new HBox(10, new Label("RG: "), txtRG, new Label("CPF: "), txtCPF),
				new HBox(10, new Label("Telefone: "), txtTelefone, new Label("Email:"), txtEmail),
				new HBox(10, new Label("Cartão SUS: "), txtCartaoSus, new Label("Sexo: "), cmbSexo)
		);
		entradaInfoCli.setSpacing(10);
		entradaInfoCli.setStyle("-fx-min-width: 50%; -fx-font-size: 15px;");
		entradaInfoCli.setPadding(new Insets(40));

		VBox entradaInfoEnd = new VBox(new Label("INFORMAÇÕES DE ENDEREÇO"), new Separator(),
				new HBox(10, new Label("CEP.:"), txtCEP),
				new HBox(10, new Label("Rua:"), txtRua, new Label("Número:"), txtNum),
				new HBox(10, new Label("Bairro:"), txtBairro),
				new HBox(10, new Label("Cidade:"), cmbCid, new Label("UF.:"), cmbUF)
		);
		entradaInfoEnd.setSpacing(10);
		entradaInfoEnd.setStyle("-fx-min-width: 50%; -fx-font-size: 15px");
		entradaInfoEnd.setPadding(new Insets(0, 40, 40, 40));

		Label tituloProb = new Label("PROBLEMAS DE SAÚDE DO CLIENTE");
		tituloProb.setStyle(" -fx-font-size: 15px");
		VBox entradaTab = new VBox(tituloProb, new Separator(), new HBox(txtPesquisa, btnPesquisaProb), btnAddProb,
				tblProb);
		entradaTab.setPadding(new Insets(39, 40, 40, 40));
		entradaTab.setSpacing(10);

		HBox hbBtns = new HBox(btnLimpaCampos, btnCadastrar);
		hbBtns.setPadding(new Insets(7, 0, 0, 0));
		hbBtns.setStyle("-fx-min-width: 50%; -fx-font-size: 15px");
		hbBtns.setAlignment(Pos.BASELINE_CENTER);
		hbBtns.setSpacing(20);

		HBox entradaInfoGeral = new HBox(new VBox(entradaInfoCli, entradaInfoEnd, hbBtns), entradaTab);
		entradaInfoGeral.setPadding(new Insets(20, 0, 0, 0));

		painelCad.getChildren().add(entradaInfoGeral);

		// FIM PAINEL
		// CADASTRO----------------------------------------------------------------------------------

		// INICIO PAINEL
		// GERENCIAMENTO--------------------------------------------------------------------------

		//txtNomePesquisa = new TextField();
		txtCPFPesquisa = new TextField();
		txtClientePesquisa = new TextField();
		//txtUFPesquisa = new TextField();
		//txtCidadePesquisa = new TextField();
		btnPesquisa = new Button("PESQUISAR");
		tblCli = new TableView<Cliente>();
		tblCli.setMinWidth(600);
		cmbPesquisa = new ComboBox<String>(FXCollections.observableArrayList(new String[]{"CPF", "NOME", "CIDADE"}));
		cmbPesquisa.setPromptText("TIPO");

		Label lblTitulo = new Label("PESQUISA CLIENTE");
		/*HBox hb = new HBox(80,
				new VBox(10, lblTitulo, new Separator(), new HBox(10, new Label("Nome: "), cmbPesquisa),
						new HBox(10, new Label("CPF.: "), txtCPFPesquisa, new Label("UF.: "), txtUFPesquisa),
						new HBox(10, new Label("Cidade: "), txtCidadePesquisa, btnPesquisa)),
				tblCli);*/
		VBox hb = new VBox(10,lblTitulo,new Separator(),
				new HBox(5, cmbPesquisa, new HBox(txtClientePesquisa, btnPesquisa)),
						tblCli);
		hb.setStyle("-fx-font-size: 15px;");
		painelMant = new BorderPane(hb);

		// FIM PAINEL
		// GERENCIAMENTO-----------------------------------------------------------------------------

		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelMant, painelCad);
		pane.setCenter(painels);

		stage.setMaximized(true);
		Scene scene = new Scene(pane, stage.getWidth(),stage.getHeight());
		//Scene scene = new Scene(pane, 1100, 600);
		stage.setScene(scene);
		stage.setTitle("Manter Clientes");
		stage.show();

		btnCadCli.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnMantCli.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCadastrar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnAddProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnLimpaCampos.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisaProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisa.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		// txtNome.addEventFilter(MouseEvent.MOUSE_CLICKED, this);

		addEventosFoco();
		startStyle();
		btnSelected(0);
		createTableColumnsProb();
		createTableColumnsClientes();

	}

	private void addEventosFoco() {
		txtNome.focusedProperty().addListener(e -> {
			txtNome.setStyle(txtNome.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;");
		});
		cmbDia.focusedProperty().addListener(e -> {
			cmbDia.setStyle(cmbDia.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;");
		});
		cmbMes.focusedProperty().addListener(e -> {
			cmbMes.setStyle(cmbMes.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;");
		});
		cmbAno.focusedProperty().addListener(e -> {
			cmbAno.setStyle(cmbAno.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;");
		});
		txtRG.focusedProperty().addListener(e -> {
			txtRG.setStyle(txtRG.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtCPF.focusedProperty().addListener(e -> {
			txtCPF.setStyle(txtCPF.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtCEP.focusedProperty().addListener(e -> {
			txtCEP.setStyle(txtCEP.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtRua.focusedProperty().addListener(e -> {
			txtRua.setStyle(txtRua.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtBairro.focusedProperty().addListener(e -> {
			txtBairro.setStyle(txtBairro.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtNum.focusedProperty().addListener(e -> {
			txtNum.setStyle(txtNum.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbCid.focusedProperty().addListener(e -> {
			cmbCid.setStyle(cmbCid.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbUF.focusedProperty().addListener(e -> {
			cmbUF.setStyle(cmbUF.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
	}

	@SuppressWarnings("unchecked")
	public void createTableColumnsProb() {

		tblProb.setItems(cc.getDataListPS());

		TableColumn<ProblemaSaude, Number> id_problema = new TableColumn<>("ID problema");
		id_problema.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getId_problema()));

		TableColumn<ProblemaSaude, String> tipo = new TableColumn<>("Tipo");
		tipo.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getTipo()));

		TableColumn<ProblemaSaude, String> desc = new TableColumn<>("Descrição");
		desc.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getDesc_problema()));

		TableColumn<ProblemaSaude, Button> btnExcluir = new TableColumn<>("Excluir");
		btnExcluir.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getBtnExcluir()));

		tblProb.getColumns().addAll(id_problema, tipo, desc, btnExcluir);
		setFunctionProbButtons();
	}

	private void setFunctionProbButtons() {
		for (int i = 0; i < tblProb.getItems().size(); i++) {

			final int l = i;

			tblProb.getItems().get(i).getBtnExcluir().setOnAction(e -> {
				System.out.println("teste");
				cc.removerProb(tblProb.getItems().get(l).getId_problema());
				setFunctionProbButtons();
			});

		}
	}

	@SuppressWarnings("unchecked")
	public void createTableColumnsClientes() {

		TableColumn<Cliente, String> nomeCompleto = new TableColumn<>("Nome completo");
		nomeCompleto.setPrefWidth(284);
		nomeCompleto.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getPrimeiroNome()));

		TableColumn<Cliente, Number> cpf = new TableColumn<>("CPF");
		cpf.setStyle("-fx-alignment: CENTER;");
		cpf.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getCpf()));

		TableColumn<Cliente, String> uf = new TableColumn<>("UF");
		uf.setPrefWidth(50);
		uf.setStyle("-fx-alignment: CENTER;");
		uf.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getEnd().getUf()));

		TableColumn<Cliente, Button> columnEditar = new TableColumn<>("Editar");
		columnEditar.setPrefWidth(81);
		columnEditar.setStyle("-fx-alignment: CENTER;");
		columnEditar.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getBtnEditar()));

		TableColumn<Cliente, Button> columnExcluir = new TableColumn<>("Excluir");
		columnExcluir.setPrefWidth(81);
		columnExcluir.setStyle("-fx-alignment: CENTER;");
		columnExcluir.setCellValueFactory(item -> new ReadOnlyObjectWrapper<>(item.getValue().getBtnExcluir()));

		tblCli.getColumns().addAll(nomeCompleto, cpf, uf, columnEditar, columnExcluir);
		tblCli.setItems(cc.getDataListClientes());
		setFunctionCliButtons();

	}

	private void setFunctionCliButtons() {
		for (int i = 0; i < tblCli.getItems().size(); i++) {

			final int l = i;

			tblCli.getItems().get(i).getBtnEditar().setOnAction(e -> {
				limparCampos();
				Cliente c = cc.pesquisarCliente((long) tblCli.getItems().get(l).getCpf());
				ControlClientes.clientSel = c;
				btnSelected(0);
				clienteToBoundary(c);
				painelCad.toFront();
				cc.attTableProb(c.getProblemasSaude());
				tblProb.refresh();
				setFunctionProbButtons();
				btnCadCli.setText("ALTERAÇÃO");
				btnCadastrar.setText("ALTERAR");
				btnLimpaCampos.setText("CANCELAR ALTERAÇÃO");
			});

			tblCli.getItems().get(i).getBtnExcluir().setOnAction(e -> {
				limparCampos();
				Cliente c = cc.pesquisarCliente((long) tblCli.getItems().get(l).getCpf());
				ControlClientes.clientSel = c;
				cc.removerCliente();
				cc.attTableCliente();
				setFunctionCliButtons();
				btnSelected(1);
			});
		}
	}

	@SuppressWarnings("deprecation")
	public void clienteToBoundary(Cliente c) {

		this.txtNome.setText(c.getPrimeiroNome());
		this.cmbDia.getSelectionModel().select(Integer.toString(c.getDt_nasc().getDate()));
		this.cmbMes.getSelectionModel().select(Integer.toString(c.getDt_nasc().getMonth()));
		this.cmbAno.getSelectionModel().select(Integer.toString(c.getDt_nasc().getYear()));
		this.txtRG.setText(Long.toString(c.getRg()));
		this.txtCPF.setText(Long.toString(c.getCpf()));
		this.txtTelefone.setText(Long.toString(c.getTelefone()));
		this.txtEmail.setText(c.getEmail());
		this.txtCartaoSus.setText(Long.toString(c.getCartaoSUS()));
		Endereco ed = c.getEnd();
		this.txtCEP.setText(ed.getCep());
		this.txtRua.setText(ed.getRua());
		this.txtBairro.setText(ed.getBairro());
		this.txtNum.setText(Integer.toString(ed.getNumero()));
		this.cmbCid.getSelectionModel().select(ed.getCidade());
		this.cmbUF.getSelectionModel().select(ed.getUf());
		this.cmbSexo.getSelectionModel().select(String.valueOf(c.getSexo()));
	}

	@SuppressWarnings("deprecation")
	public Cliente boundaryToCliente() {

		Cliente c = new Cliente();
		c.setPrimeiroNome(this.txtNome.getText());
		c.setDt_nasc(new java.sql.Date(Integer.parseInt(this.cmbAno.getSelectionModel().getSelectedItem()), Integer.parseInt(this.cmbMes.getSelectionModel().getSelectedItem()), Integer.parseInt(this.cmbDia.getSelectionModel().getSelectedItem())));
		c.setRg(Long.parseLong(this.txtRG.getText()));
		c.setCpf(Long.parseLong(this.txtCPF.getText()));
		if (!this.txtTelefone.getText().equals("")) {
			c.setTelefone(Long.parseLong(this.txtTelefone.getText()));
		}
		c.setEmail(this.txtEmail.getText());
		if (!this.txtCartaoSus.getText().equals("")) {
			c.setCartaoSUS(Long.parseLong(this.txtCartaoSus.getText()));
		}
		System.out.println(cmbSexo.getSelectionModel().getSelectedItem().charAt(0));
		c.setSexo(cmbSexo.getSelectionModel().getSelectedItem().charAt(0));

		Endereco ed = new Endereco();
		ed.setCep(this.txtCEP.getText());
		ed.setRua(this.txtRua.getText());
		ed.setNumero(Integer.parseInt(this.txtNum.getText()));
		ed.setCidade(this.cmbCid.getSelectionModel().getSelectedItem());
		ed.setUf(this.cmbUF.getSelectionModel().getSelectedItem());
		ed.setBairro(this.txtBairro.getText());
		ed.setIdEndereco(ControlClientes.clientSel.getEnd().getIdEndereco());
		c.setEnd(ed);

		c.setProblemasSaude(ControlClientes.clientSel.getProblemasSaude());
		return c;
	}

	public void startStyle() {

		DropShadow dp = new DropShadow(4, 0, 0, Color.GRAY);
		
		String styleBtnPesquisa = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;"
				+ "-fx-background-radius: 0px 8px 8px 0px;" + "-fx-min-width: 240px;" + "-fx-max-height: 30px;" + "-fx-cursor: hand;";

		String styleBtns = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;" + "-fx-background-radius: 7;"
				+ "-fx-min-width: 275px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;";

		String styleBtnAddProb = "-fx-min-width: 625px;" + "-fx-background-color: #007F0E;" + "-fx-text-fill: white;"
				+ "-fx-font-size: 15px;" + "-fx-background-radius: 8;" + "-fx-cursor: hand;";

		String styleBtn = "-fx-background-radius: none;" + "-fx-min-width: 130px;" + "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;" + "-fx-font-color: #545452;" + "-fx-font-weight: bold;";

		String stylePainel = "-fx-background-color: #FEFFFA;" + "-fx-padding: 50, 50, 50, 50;";

		String styleMenuTop = "-fx-background-color: #E0DACE";

		String styleEntradaDataNasc = "-fx-max-width: 130px;";

		String styleEntradas = "-fx-background-radius: 8;";

		String styleEntradaPesquisa = "-fx-background-radius: 8px 0px 0px 8px;" + "-fx-min-width: 587px;";

		String stylePesquisaProb = "-fx-min-height: 30px;" + "-fx-min-width: 30px;"
				+ "-fx-background-radius: 0px 8px 8px 0px;" + "-fx-background-color: #0095FE;" + "-fx-cursor: hand;";

		String comboStyle = "-fx-background-radius: 8;" + "-fx-background-color: #FEFFFA;" + "-fx-cursor: hand;";

		btnCadCli.setStyle(styleBtn);
		btnMantCli.setStyle(styleBtn);
		painelCad.setStyle(stylePainel);
		painelMant.setStyle(stylePainel);
		menuTop.setStyle(styleMenuTop);
		txtNome.setStyle("-fx-min-width: 515px;" + styleEntradas);
		cmbDia.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbDia.setEffect(dp);
		cmbMes.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbMes.setEffect(dp);
		cmbAno.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbAno.setEffect(dp);
		txtRG.setStyle("-fx-min-width: 225px;" + styleEntradas);
		txtCPF.setStyle("-fx-min-width: 259px;" + styleEntradas);
		txtTelefone.setStyle("-fx-max-width: 186px;" + styleEntradas);
		txtEmail.setStyle("-fx-min-width: 253px;" + styleEntradas);
		txtCartaoSus.setStyle("-fx-min-width: 355px;" + styleEntradas);
		txtCEP.setStyle("-fx-min-width: 530px; " + styleEntradas);
		txtRua.setStyle("-fx-min-width: 228px; " + styleEntradas);
		txtBairro.setStyle("-fx-min-width: 500px; " + styleEntradas);
		txtNum.setStyle("-fx-min-width: 227px; " + styleEntradas);
		txtPesquisa.setStyle(styleEntradaPesquisa + "-fx-font-size: 15px");
		btnAddProb.setStyle(styleBtnAddProb);
		btnLimpaCampos.setStyle(styleBtns);
		btnCadastrar.setStyle(styleBtns);
		btnPesquisaProb.setStyle(stylePesquisaProb);
		cmbPesquisa.setStyle("-fx-min-width: 200px;" + styleEntradas);
		btnPesquisa.setStyle(styleBtnPesquisa + "-fx-font-size: 14px;");
		cmbSexo.setStyle(comboStyle);
		cmbSexo.setEffect(new DropShadow(4, 0, 0, Color.GRAY));
		cmbCid.setStyle("-fx-min-width: 207px; " + styleEntradas + comboStyle);
		cmbCid.setEffect(dp);
		cmbUF.setStyle("-fx-min-width: 259px; " + styleEntradas + comboStyle);
		cmbUF.setEffect(dp);
		cmbPesquisa.setStyle(comboStyle + "-fx-min-width: 100px;");
		cmbPesquisa.setEffect(dp);
		txtClientePesquisa.setStyle(styleEntradaPesquisa + "-fx-min-width: 920px;");
	}

	public void btnSelected(int btn) {
		String CadSelected = "";
		String MantSelected = "";
		if (btn == 0) {
			CadSelected = "#FEFFFA";
			MantSelected = "#EAEAEA";
		} else {
			CadSelected = "#EAEAEA";
			MantSelected = "#FEFFFA";
		}
		btnCadCli.setStyle("-fx-background-color: " + CadSelected + ";" + "-fx-background-radius: none;"
				+ "-fx-min-width: 130px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");
		btnMantCli.setStyle("-fx-background-color: " + MantSelected + ";" + "-fx-background-radius: none;"
				+ "-fx-min-width: 130px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void handle(MouseEvent e) {
		if (e.getSource() == btnCadCli) {
			painelCad.toFront();
			btnSelected(0);
		} else

		if (e.getSource() == btnMantCli) {
			painelMant.toFront();
			btnSelected(1);
		} else

		if (e.getSource() == btnCadastrar) {
			if (btnCadastrar.getText().equals("CADASTRAR") && camposValidos()) {
				cc.attClienteToCad(boundaryToCliente());
				Alert a = new Alert(AlertType.INFORMATION, "Cadastro realizado com sucesso !!!");
				a.show();
				if (cc.cadCliente(boundaryToCliente())) {
					cc.cadProbCliente();
					limparCampos();
				}
				tblCli.refresh();
				setFunctionCliButtons();
			} else if (btnCadastrar.getText().equals("ALTERAR") && camposValidos()) {
				cc.attCliente(boundaryToCliente());
				Alert a = new Alert(AlertType.INFORMATION, "Alterações realizadas com sucesso !!!");
				a.show();
				tblCli.refresh();
				limparCampos();
				setFunctionCliButtons();
			}
			btnSelected(0);
		} else if (e.getSource() == btnAddProb) {
			ProblemaSaude ps = cc.pesquisarProb(this.txtPesquisa.getText());
			if (ControlClientes.clientSel == null) {
				ControlClientes.clientSel = new Cliente();
			}
			if (!ControlClientes.clientSel.existProb(ps.getId_problema())) {
				ControlClientes.clientSel.getProblemasSaude().add(ps);
			}
			cc.attTableProb();
			setFunctionProbButtons();
		} else if (e.getSource() == btnLimpaCampos) {
			limparCampos();
			btnSelected(0);
		} else if (e.getSource() == btnPesquisaProb) {
			String prob = txtPesquisa.getText();
			ProblemaSaude ps1 = cc.pesquisarProb(this.txtPesquisa.getText());
			if (ps1 != null) {
				if (ControlClientes.clientSel == null) {
					ControlClientes.clientSel = new Cliente();
				}
				if (!ControlClientes.clientSel.existProb(ps1.getId_problema())) {
					ControlClientes.clientSel.getProblemasSaude().add(ps1);
				}
				cc.attTableProb();
				setFunctionProbButtons();
			} else {
				JOptionPane.showMessageDialog(null, "Problema não encontrado");
			}
		}else {
			if(e.getSource() == btnPesquisa) {
				if(txtClientePesquisa.getText().equals("")) {
					cc.attTableCliente();
				}else {
					Cliente cl = null;
					if(cmbPesquisa.getSelectionModel().getSelectedItem() != null) {
						switch(cmbPesquisa.getSelectionModel().getSelectedItem()) {
							case "CPF":
								long cpf = Long.parseLong(txtClientePesquisa.getText());
								cl = cc.pesquisarCliente(cpf);
								if(cl != null) {
									cc.attTableCliente(cl);
								}
								break;
							case "NOME":
								String nome = txtClientePesquisa.getText();
								cc.pesquisarCliente(nome, "NOME");
								break;
							case "CIDADE":
								String cidade = txtClientePesquisa.getText();
								cc.pesquisarCliente(cidade, "CIDADE");
								System.out.println("CIDADE SELECIONADO");
								break;							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Atenção, é preciso determinar o tipo de pesquisa que sera realizada", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				setFunctionCliButtons();
			}
		}
	}

	public void limparCampos() {
		btnCadastrar.setText("CADASTRAR");
		btnCadCli.setText("CADASTRO");
		btnLimpaCampos.setText("LIMPAR CAMPOS");
		this.txtNome.setText("");
		this.cmbDia.getSelectionModel().select(-1);
		this.cmbMes.getSelectionModel().select(-1);
		this.cmbAno.getSelectionModel().select(-1);
		this.txtRG.setText("");
		this.txtCPF.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
		this.txtCartaoSus.setText("");
		this.txtCEP.setText("");
		this.txtRua.setText("");
		this.txtBairro.setText("");
		this.txtNum.setText("");
		this.cmbCid.getSelectionModel().select(-1);
		this.cmbUF.getSelectionModel().select(-1);
		this.txtPesquisa.setText("");
		cc.attTableProb(new ArrayList<ProblemaSaude>());
		ControlClientes.clientSel = new Cliente();
		startStyle();
	}

	private boolean camposValidos() {
		boolean isValid = true;
		if (this.txtNome.getText().equals("")) {
			this.txtNome.setStyle(this.txtNome.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbDia.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbDia.setStyle(this.cmbDia.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;");
			isValid = false;
		}
		if(this.cmbMes.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbMes.setStyle(this.cmbMes.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;");
			isValid = false;
		}
		if(this.cmbAno.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbAno.setStyle(this.cmbAno.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;");
			isValid = false;
		}
		if (this.txtRG.getText().equals("")) {
			this.txtRG.setStyle(this.txtRG.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtCPF.getText().equals("")) {
			this.txtCPF.setStyle(this.txtCPF.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtCEP.getText().equals("")) {
			this.txtCEP.setStyle(this.txtCEP.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtRua.getText().equals("")) {
			this.txtRua.setStyle(this.txtRua.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtBairro.getText().equals("")) {
			this.txtBairro.setStyle(this.txtBairro.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtNum.getText().equals("")) {
			this.txtNum.setStyle(this.txtNum.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.cmbCid.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbCid.setStyle(this.cmbCid.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.cmbUF.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbUF.setStyle(this.cmbUF.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (!isValid) {
			JOptionPane.showMessageDialog(null,
					"Alguns campos obrigatórios nao foram preenchidos.\nPor favor preencha os campos com borda vermelha.",
					"Ops...", JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}

}