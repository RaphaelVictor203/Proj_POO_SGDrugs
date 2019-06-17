package com.curso.boundary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.curso.control.ControlClientes;
import com.curso.control.ControlFuncionario;
import com.curso.dao.DAOException;
import com.curso.dao.FarmaciaDAOImpl;
import com.curso.dao.FuncaoDAO;
import com.curso.dao.FuncaoDAOImpl;
import com.curso.dao.FuncionarioDAO;
import com.curso.dao.FuncionarioDAOImpl;
import com.curso.entity.Cliente;
import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;
import com.curso.entity.Funcao;
import com.curso.entity.Funcionario;
import com.curso.entity.ProblemaSaude;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
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

public class ManterFuncionario extends Application implements EventHandler<MouseEvent> {

	private Button btnCadCli;
	private TextField txtPesquisa;
	private Pane painelCad;
	private BorderPane painelMant;
	private HBox menuTop;
	private TextField txtNome;
	private TextField txtSobrenome;
	private ComboBox<String> cmbDia, cmbMes, cmbAno;
	private TextField txtRG, txtCPF;
	private TextField txtTelefone, txtEmail;
	private ComboBox<Farmacia> cmbFarmacia;
	private TextField txtCEP;
	private TextField txtRua, txtNum;
	private ComboBox<String> cmbCid, cmbUF;
	private ComboBox<Funcao> cmbCargo;
	private TextField txtNomePesquisa;
	private TextField txtUFPesquisa;
	private TextField txtCidadePesquisa;
	private ComboBox<String> cmbSexo;
	private TableView<Funcionario> tableFuncionario;
	private Button btnAddProb, btnLimpaCampos, btnCadastrar, btnPesquisa;
	private TextField txtSalario;
	private ControlFuncionario funcio = new ControlFuncionario();
	private FuncionarioDAOImpl dao = new FuncionarioDAOImpl();
	private ComboBox<String> cmbTipoPesquisa;

	ControlClientes cc;

	@Override
	public void start(Stage stage) throws Exception {

		cc = new ControlClientes();
		painelCad = new Pane();

		// INICIO PAINEL
		// CADASTRO-------------------------------------------------------------------------------

		FarmaciaDAOImpl fdi = new FarmaciaDAOImpl();
		txtNome = new TextField();
		cmbFarmacia = new ComboBox<>(FXCollections.observableArrayList(fdi.pesquisarFarmacia()));
		cmbFarmacia.setPromptText("Selecione");

		cmbDia = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1, 31)));
		cmbDia.setPromptText("Dia");
		cmbMes = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1, 12)));
		cmbMes.setPromptText("M�s");
		cmbAno = new ComboBox<String>(FXCollections.observableArrayList(cc.gerarArrayNum(1900, 2019)));
		cmbAno.setPromptText("Ano");
		txtRG = new TextField();
		txtSobrenome = new TextField();
		txtCPF = new TextField();
		txtTelefone = new TextField();
		txtEmail = new TextField();

		FuncaoDAO funcdi = new FuncaoDAOImpl();
		cmbCargo = new ComboBox<Funcao>(FXCollections.observableArrayList(funcdi.pesquisarPorFuncoes()));
		cmbCargo.setPromptText("Selecione");

		txtSalario = new TextField();
		txtCEP = new TextField();
		txtRua = new TextField();
		txtNum = new TextField();
		cmbCid = new ComboBox<String>(FXCollections.observableArrayList(
				new String[] { "S�o Paulo", "Ribeir�o", "Botucatu", "Piracicaba", "Santos", "Franca", "Ara�atuba" }));
		cmbUF = new ComboBox<String>(FXCollections.observableArrayList(new String[] { "SP" }));
		cmbSexo = new ComboBox<String>(FXCollections.observableArrayList(new String[] { "M", "F" }));
		cmbSexo.getSelectionModel().select(0);

		txtPesquisa = new TextField();
		txtPesquisa.setPrefSize(200, 30);
		txtPesquisa.setPromptText("Insira o nome ou CPF do Funcion�rio..");

		cmbTipoPesquisa = new ComboBox<>(FXCollections.observableArrayList(new String[] { "CNPJ", "NOME" }));
		cmbTipoPesquisa.setPrefSize(130, 30);
		cmbTipoPesquisa.setPromptText("Selecione");

		btnLimpaCampos = new Button("LIMPAR CAMPOS");
		btnCadastrar = new Button("CADASTRAR");
		ImageView search = new ImageView(new Image(new FileInputStream("imgs\\search.png")));
		search.setFitWidth(20);
		search.setFitHeight(20);
		btnAddProb = new Button("", search);
		tableFuncionario = new TableView<Funcionario>();
		tableFuncionario.setMaxWidth(600);

		BorderPane pane = new BorderPane();

		btnCadCli = new Button("CADASTRO");

		menuTop = new HBox(btnCadCli);

		VBox entradaInfoCli = new VBox(new Label("INFORMA��ES RELACIONADAS AO FUNCION�RIO"), new Separator(),
				new HBox(10, new Label("Nome: "), txtNome, new Label("Sobrenome.:"), txtSobrenome),
				new HBox(10, new Label("Data Nascimento: "), cmbDia, new Label("/"), cmbMes, new Label("/"), cmbAno),
				new HBox(10, new Label("RG: "), txtRG, new Label("CPF: "), txtCPF),
				new HBox(10, new Label("Telefone: "), txtTelefone, new Label("Sexo: "), cmbSexo),
				new HBox(10, new Label("Email:"), txtEmail));
		entradaInfoCli.setSpacing(10);
		entradaInfoCli.setStyle("-fx-min-width: 50%; -fx-font-size: 15px;");
		entradaInfoCli.setPadding(new Insets(20, 0, 20, 40));

		VBox entradaInfoEnd = new VBox(new Label("INFORMA��ES DE ENDERE�O"), new Separator(),
				new HBox(10, new Label("Rua:"), txtRua),
				new HBox(10, new Label("CEP:"), txtCEP, new Label("N�mero:"), txtNum),
				new HBox(10, new Label("Cidade:"), cmbCid, new Label("UF.:"), cmbUF)

		);
		entradaInfoEnd.setSpacing(10);
		entradaInfoEnd.setStyle("-fx-min-width: 50%; -fx-font-size: 15px");
		entradaInfoEnd.setPadding(new Insets(0, 20, 20, 40));

		VBox entradaINfoWork = new VBox(new Label("LOCAL DE TRABALHO "), new Separator(), new HBox(10,
				new Label("Unidade.:"), cmbFarmacia, new Label("Cargo :"), cmbCargo, new Label("Salario"), txtSalario));
		entradaINfoWork.setSpacing(10);
		entradaINfoWork.setStyle("-fx-min-width: 50%; -fx-font-size: 15px");
		entradaINfoWork.setPadding(new Insets(0, 20, 20, 40));

		Label tituloProb = new Label("PESQUISAR FUNCION�RIO");
		tituloProb.setStyle(" -fx-font-size: 15px");
		VBox entradaTab = new VBox(tituloProb, new Separator(), new HBox(5, cmbTipoPesquisa, txtPesquisa, btnAddProb),
				tableFuncionario);
		entradaTab.setPadding(new Insets(20, 0, 40, 20));
		entradaTab.setSpacing(10);
		entradaTab.setStyle("-fx-font-size: 13px;");

		HBox hbBtns = new HBox(btnLimpaCampos, btnCadastrar);
		hbBtns.setPadding(new Insets(7, 0, 0, 0));
		hbBtns.setStyle("-fx-min-width: 50%; -fx-font-size: 15px;");
		hbBtns.setAlignment(Pos.BASELINE_CENTER);
		hbBtns.setSpacing(20);

		HBox entradaInfoGeral = new HBox(new VBox(entradaInfoCli, entradaINfoWork, entradaInfoEnd, hbBtns), entradaTab);
		entradaInfoGeral.setPadding(new Insets(20, 0, 0, 20));

		painelCad.getChildren().add(entradaInfoGeral);

		// FIM PAINEL
		// CADASTRO----------------------------------------------------------------------------------

		// INICIO PAINEL
		// GERENCIAMENTO--------------------------------------------------------------------------

		txtNomePesquisa = new TextField();
		// txtPesquisa = new TextField();
		txtUFPesquisa = new TextField();
		txtCidadePesquisa = new TextField();
		btnPesquisa = new Button("PESQUISAR");

		/*
		 * Label lblTitulo = new Label("PESQUISA CLIENTE"); HBox hb = new HBox(80, new
		 * VBox(10, lblTitulo, new Separator(), new HBox(10, new Label("Nome: "),
		 * txtNomePesquisa), new HBox(10, new Label("CPF.: "), txtPesquisa, new
		 * Label("UF.: "), txtUFPesquisa), new HBox(10, new Label("Cidade: "),
		 * txtCidadePesquisa, btnPesquisa) ) ); hb.setStyle("-fx-font-size: 15px;");
		 * painelMant = new BorderPane(hb);
		 */

		// FIM PAINEL
		// GERENCIAMENTO-----------------------------------------------------------------------------

		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelCad);
		pane.setCenter(painels);

		stage.setMaximized(true);

		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
		stage.setTitle("Manter Funcionarios");
		stage.show();

		btnCadCli.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		btnCadastrar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnAddProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnLimpaCampos.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		// btnPesquisa.addEventFilter(MouseEvent.MOUSE_CLICKED, this);

		addEventosFoco();
		startStyle();
		btnSelected(0);
		createTableColumnsFunc();
		funcio.attTableFuncionario();
		// funcio.attTableFuncionario();
		setFunctionFuncButtons();

	}

	private void addEventosFoco() {
		txtNome.focusedProperty().addListener(e -> {
			txtNome.setStyle(txtNome.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbDia.focusedProperty().addListener(e -> {
			cmbDia.setStyle(cmbDia.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbMes.focusedProperty().addListener(e -> {
			cmbMes.setStyle(cmbMes.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbAno.focusedProperty().addListener(e -> {
			cmbAno.setStyle(cmbAno.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
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
	public void createTableColumnsFunc() {

		tableFuncionario.setItems(funcio.getDataList());
		tableFuncionario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {
			@Override
			public void changed(ObservableValue<? extends Funcionario> funcionario, Funcionario funcionario1,
					Funcionario funcionario2) {
				if (funcionario2 != null) {
					try {
						// System.out.println("teste cpf - " + funcionario2.getCpf());
						btnCadastrar.setText("ALTERAR");
						btnLimpaCampos.setText("CANCELAR ALTERA��O");
						FuncionarioToBoundary(dao.pesquisarFuncionario(funcionario2.getCpf()));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		TableColumn<Funcionario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));

		TableColumn<Funcionario, String> sobrenome = new TableColumn<>("Sobrenome");
		sobrenome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getSobrenome()));

		TableColumn<Funcionario, Number> desc = new TableColumn<>("CPF");
		desc.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getCpf()));

		TableColumn<Funcionario, Button> excluir = new TableColumn<>("Excluir");
		excluir.setCellValueFactory(item -> new ReadOnlyObjectWrapper(item.getValue().getBtnExcluir()));
		tableFuncionario.getColumns().addAll(nome, sobrenome, desc, excluir);

	}

	private void setFunctionFuncButtons() {
		System.out.println("size: " + tableFuncionario.getItems().size());
		for (int i = 0; i < tableFuncionario.getItems().size(); i++) {
			final int l = i;

			tableFuncionario.getItems().get(i).getBtnExcluir().setOnAction(e -> {
				System.out.println("teste");
				try {
					dao.remover(tableFuncionario.getItems().get(l).getCpf());
					funcio.attTableFuncionario();
					limparCampos();
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setFunctionFuncButtons();
			});

		}
	}

	@SuppressWarnings("deprecation")
	public void FuncionarioToBoundary(Funcionario c) {
		System.out.println(c.getFarmacia().getUnidade());
		funcio.funcSel = c;
		this.txtNome.setText(c.getNome());
		this.cmbDia.getSelectionModel().select(Integer.toString(c.getDt_nasc().getDate()));
		this.cmbMes.getSelectionModel().select(Integer.toString(c.getDt_nasc().getMonth()));
		this.cmbAno.getSelectionModel().select(Integer.toString(c.getDt_nasc().getYear()));
		this.txtRG.setText(Long.toString(c.getRg()));
		this.txtCPF.setText(Long.toString(c.getCpf()));
		this.txtTelefone.setText(Long.toString(c.getTelefone()));
		this.txtEmail.setText(c.getEmail());
		this.txtSobrenome.setText(c.getSobrenome());
		this.txtSalario.setText(Float.toString(c.getSalario()));
		this.cmbFarmacia.getSelectionModel().select(c.getFarmacia());
		this.cmbCargo.getSelectionModel().select(c.getFuncao());

		Endereco ed = c.getEnd();
		this.txtCEP.setText(ed.getCep());
		this.txtRua.setText(ed.getRua());
		this.txtNum.setText(Integer.toString(ed.getNumero()));
		this.cmbCid.getSelectionModel().select(ed.getCidade());
		this.cmbUF.getSelectionModel().select(ed.getUf());
		this.cmbSexo.getSelectionModel().select(String.valueOf(c.getSexo()));
	}

	@SuppressWarnings("deprecation")
	public Funcionario boundaryToFuncionario() {

		Funcionario fun = new Funcionario();
		fun.setNome(txtNome.getText());
		fun.setSobrenome(txtSobrenome.getText());
		fun.setDt_nasc(new java.sql.Date(Integer.parseInt(this.cmbAno.getSelectionModel().getSelectedItem()),
				Integer.parseInt(this.cmbMes.getSelectionModel().getSelectedItem()),
				Integer.parseInt(this.cmbDia.getSelectionModel().getSelectedItem())));
		fun.setRg(Long.parseLong(txtRG.getText()));
		fun.setCpf(Long.parseLong(txtCPF.getText()));
		fun.setTelefone(Long.parseLong(txtTelefone.getText()));
		fun.setEmail(txtEmail.getText());
		// Farmacia c = new Farmacia(this.cmbAno.getSelectionModel().getSelectedItem());
		fun.setFarmacia(this.cmbFarmacia.getSelectionModel().getSelectedItem());
		fun.setSexo(cmbSexo.getSelectionModel().getSelectedItem().charAt(0));
		fun.setSalario(Float.parseFloat(txtSalario.getText()));
		Endereco ed = new Endereco();
		ed.setCep(this.txtCEP.getText());
		ed.setRua(this.txtRua.getText());
		ed.setNumero(Integer.parseInt(this.txtNum.getText()));
		ed.setCidade(this.cmbCid.getSelectionModel().getSelectedItem());
		ed.setUf(this.cmbUF.getSelectionModel().getSelectedItem());
		if (funcio.funcSel.getEnd() != null) {
			ed.setIdEndereco(funcio.funcSel.getEnd().getIdEndereco());
		}
		fun.setFuncao(cmbCargo.getSelectionModel().getSelectedItem());
		fun.setEnd(ed);
		return fun;
	}

	public void startStyle() {

		DropShadow dp = new DropShadow(4, 0, 0, Color.GRAY);

		String styleBtnPesquisa = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;"
				+ "-fx-background-radius: 7;" + "-fx-min-width: 100px;" + "-fx-min-height: 30px;" + "-fx-cursor: hand;";

		String styleBtns = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;" + "-fx-background-radius: 7;"
				+ "-fx-min-width: 275px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;";

		String styleBtnAddProb = "-fx-min-width: 70px;" + "-fx-background-color: #0095FE;" + "-fx-text-fill: white;"
				+ "-fx-font-size: 15px;" + "-fx-background-radius: 8;" + "-fx-cursor: hand;";

		String styleBtn = "-fx-background-radius: none;" + "-fx-min-width: 130px;" + "-fx-min-height: 40px;"
				+ "-fx-cursor: hand;" + "-fx-font-color: #545452;" + "-fx-font-weight: bold;";

		String stylePainel = "-fx-background-color: #FEFFFA;" + "-fx-padding: 50, 50, 50, 50;";

		String styleMenuTop = "-fx-background-color: #E0DACE";

		String styleEntradaDataNasc = "-fx-min-width: 130px;";

		String styleEntradas = "-fx-background-radius: 8;";

		String styleEntradaPesquisa = "-fx-background-radius: 8px 8px 8px 8px;" + "-fx-min-width: 625px;";

		String comboStyle = "-fx-background-radius: 8;" + "-fx-background-color: #FEFFFA;" + "-fx-cursor: hand;";

		btnCadCli.setStyle(styleBtn);

		painelCad.setStyle(stylePainel);
		/* painelMant.setStyle(stylePainel); */
		menuTop.setStyle(styleMenuTop);
		txtNome.setStyle("-fx-min-width: 100px;" + styleEntradas);
		cmbDia.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbDia.setEffect(dp);
		cmbMes.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbMes.setEffect(dp);
		cmbAno.setStyle(styleEntradaDataNasc + styleEntradas + comboStyle);
		cmbAno.setEffect(dp);
		txtRG.setStyle("-fx-min-width: 225px;" + styleEntradas);
		txtCPF.setStyle("-fx-min-width: 259px;" + styleEntradas);
		txtTelefone.setStyle("-fx-max-width: 250px;" + styleEntradas);
		txtEmail.setStyle("-fx-min-width: 523px;" + styleEntradas);
		txtSobrenome.setStyle("-fx-min-width: 300px;" + styleEntradas);
		txtCEP.setStyle("-fx-min-width: 228px; " + styleEntradas);
		txtRua.setStyle("-fx-min-width: 530px; " + styleEntradas);
		txtNum.setStyle("-fx-min-width: 227px; " + styleEntradas);
		cmbCid.setStyle("-fx-min-width: 207px; " + styleEntradas + comboStyle);
		cmbCid.setEffect(dp);
		cmbUF.setStyle("-fx-min-width: 259px; " + styleEntradas + comboStyle);
		cmbUF.setEffect(dp);
		txtPesquisa.setStyle(styleEntradaPesquisa + "-fx-font-size: 15px;");
		btnAddProb.setStyle(styleBtnAddProb);
		btnLimpaCampos.setStyle(styleBtns);
		btnCadastrar.setStyle(styleBtns);
		txtNomePesquisa.setStyle("-fx-min-width: 515px;" + styleEntradas);
		txtPesquisa.setStyle("-fx-min-width: 280px;" + styleEntradas);
		txtUFPesquisa.setStyle("-fx-min-width: 200px;" + styleEntradas);
		txtCidadePesquisa.setStyle("-fx-min-width: 262px;" + styleEntradas);
		btnPesquisa.setStyle(styleBtnPesquisa);
		cmbSexo.setStyle(comboStyle);
		cmbSexo.setEffect(dp);
		txtSalario.setStyle(styleEntradas);
		cmbTipoPesquisa.setStyle(styleEntradas);
		cmbFarmacia.setStyle(styleEntradas);
		cmbCargo.setStyle(styleEntradas);

	}

	public void btnSelected(int btn) {
		String CadSelected = "";
		if (btn == 0) {
			CadSelected = "#FEFFFA";

		} else {
			CadSelected = "#EAEAEA";

		}
		btnCadCli.setStyle("-fx-background-color: " + CadSelected + ";" + "-fx-background-radius: none;"
				+ "-fx-min-width: 130px;" + "-fx-min-height: 40px;" + "-fx-cursor: hand;");

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void handle(MouseEvent e) {

		FuncionarioDAO fdi = new FuncionarioDAOImpl();

		if (e.getSource() == btnCadastrar && camposValidos()) {

			if (btnCadastrar.getText().equals("ALTERAR") && camposValidos()) {
				alterarFuncionario();

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("SGDrugs");
				alert.setHeaderText("Voc� deseja alterar o login e senha desse usuario ?");
				alert.setContentText("Escolha sua op��o.");

				ButtonType buttonTypeSIM = new ButtonType("SIM");
				ButtonType buttonTypeNAO = new ButtonType("N�O");

				alert.getButtonTypes().setAll(buttonTypeSIM, buttonTypeNAO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeSIM) {
					attLogUsuario();
				}

				limparCampos();
			} else {

				cadastrarFuncionario();
				cadLogUsuario();
				limparCampos();
			}
			funcio.attTableFuncionario();
		} else

		if (e.getSource() == btnLimpaCampos) {
			if (btnLimpaCampos.getText().equals("CANCELAR ALTERA��O")) {
				btnCadastrar.setText("CADASTRAR");
				limparCampos();
			} else {
				limparCampos();
			}
		} else

		if (e.getSource() == btnAddProb) {

			if (cmbTipoPesquisa.getSelectionModel().getSelectedIndex() == -1) {
				Alert alert = new Alert(AlertType.WARNING, "� Necessario Selecionar um Tipo de Pesquisa");
				alert.show();
			} else if (cmbTipoPesquisa.getSelectionModel().getSelectedIndex() == 0) {
				try {
					funcio.pesquisarFuncionario(Long.parseLong(txtPesquisa.getText()));
				} catch (NumberFormatException ex) {
					txtPesquisa.clear();
					txtPesquisa.requestFocus();
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				
			} else if(cmbTipoPesquisa.getSelectionModel().getSelectedIndex() == 1) {
				try {
					if(!txtPesquisa.getText().equals("")) {
						funcio.pesquisarFuncionario(txtPesquisa.getText());
					} else {
						funcio.attTableFuncionario();
					}
				} catch (DAOException ex) {
					ex.printStackTrace();
				}
			}
			setFunctionFuncButtons();
		}

	}

	public void cadLogUsuario() {
		String nome, senha, nivel;

		TextInputDialog dialogNome = new TextInputDialog();
		dialogNome.setTitle("Cria��o de usuario");
		dialogNome.setHeaderText(
				"Para a finaliza��o do cadastro do funcionario, � necessario definir um usuario e senha");
		dialogNome.setContentText("Por favor, digite o usuario:");

		nome = dialogNome.showAndWait().get();

		TextInputDialog dialogSenha = new TextInputDialog();
		dialogSenha.setTitle("Cria��o de usuario");
		dialogSenha.setHeaderText(
				"Para a finaliza��o do cadastro do funcionario, � necessario definir um usuario e senha");
		dialogSenha.setContentText("Por favor, digite a senha:");

		senha = dialogSenha.showAndWait().get();

		List<String> choices = new ArrayList<>();
		choices.add("comum");
		choices.add("farmac�utico");
		choices.add("gerente");
		choices.add("propriet�rio");

		ChoiceDialog<String> dialogNivel = new ChoiceDialog<>("", choices);
		dialogNivel.setTitle("Cria��o de usuario");
		dialogNivel.setHeaderText("Defina o n�vel de usuario deste funcion�rio");
		dialogNivel.setContentText("Escolha um n�vel:");

		nivel = dialogNivel.showAndWait().get();

		funcio.addLogUsuario(nome, senha, nivel);

	}

	public void attLogUsuario() {
		String nome, senha, nivel;

		TextInputDialog dialogNome = new TextInputDialog();
		dialogNome.setTitle("Cria��o de usuario");
		dialogNome.setHeaderText(
				"Para a finaliza��o do cadastro do funcionario, � necessario definir um usuario e senha");
		dialogNome.setContentText("Por favor, digite o usuario:");

		nome = dialogNome.showAndWait().get();

		TextInputDialog dialogSenha = new TextInputDialog();
		dialogSenha.setTitle("Cria��o de usuario");
		dialogSenha.setHeaderText(
				"Para a finaliza��o do cadastro do funcionario, � necessario definir um usuario e senha");
		dialogSenha.setContentText("Por favor, digite a senha:");

		senha = dialogSenha.showAndWait().get();

		List<String> choices = new ArrayList<>();
		choices.add("comum");
		choices.add("farmac�utico");
		choices.add("gerente");
		choices.add("propriet�rio");

		ChoiceDialog<String> dialogNivel = new ChoiceDialog<>("", choices);
		dialogNivel.setTitle("Cria��o de usuario");
		dialogNivel.setHeaderText("Defina o n�vel de usuario deste funcion�rio");
		dialogNivel.setContentText("Escolha um n�vel:");

		nivel = dialogNivel.showAndWait().get();

		funcio.attLogUsuario(nome, senha, nivel);
	}

	private void alterarFuncionario() {
		// TODO Auto-generated method stub
		Funcionario fn = new Funcionario();
		try {
			fn = boundaryToFuncionario();
			funcio.alterar(fn);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void cadastrarFuncionario() {
		// Funcionario fn = new Funcionario();
		try {
			funcio.funcSel = boundaryToFuncionario();
			funcio.inserir();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * if(btnCadastrar.getText().equals("CADASTRAR") && camposValidos()) {
	 * JOptionPane.showMessageDialog(null, "cadastro realizado !!!", "Cadastro",
	 * JOptionPane.INFORMATION_MESSAGE);
	 * 
	 * 
	 * }else if(btnCadastrar.getText().equals("ALTERAR") && camposValidos()){
	 * cc.attCliente(boundaryToCliente()); JOptionPane.showMessageDialog(null,
	 * "Altera��es realizadas com sucesso", "Altera��o concluida",
	 * JOptionPane.INFORMATION_MESSAGE);
	 * 
	 * limparCampos();
	 * 
	 * } }else if(e.getSource() == btnAddProb) { ProblemaSaude ps =
	 * cc.pesquisarProb(this.txtPesquisa.getText()); if(ControlClientes.clientSel ==
	 * null) { ControlClientes.clientSel = new Cliente(); }
	 * if(!ControlClientes.clientSel.existProb(ps.getId_problema())) {
	 * ControlClientes.clientSel.getProblemasSaude().add(ps); } cc.attTableProb();
	 * }else if(e.getSource() == btnLimpaCampos) { limparCampos(); }
	 * if(e.getSource() == btnPesquisa) { if(txtPesquisa.getText().equals("")) {
	 * cc.attTableCliente(); }else { long cpf =
	 * Long.parseLong(txtPesquisa.getText()); Cliente cl = cc.pesquisarCliente(cpf);
	 * if(cl != null) { cc.attTableCliente(cl); }else {
	 * JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado", "Erro",
	 * JOptionPane.ERROR_MESSAGE); } }
	 * 
	 * } }
	 */

	public void limparCampos() {
		btnCadastrar.setText("CADASTRAR");
		btnCadCli.setText("CADASTRO");
		btnLimpaCampos.setText("LIMPAR CAMPOS");
		this.txtNome.setText("");
		this.txtSobrenome.setText("");
		this.cmbCargo.getSelectionModel().select(-1);
		this.cmbFarmacia.getSelectionModel().select(-1);
		this.cmbDia.getSelectionModel().select(-1);
		this.cmbMes.getSelectionModel().select(-1);
		this.cmbAno.getSelectionModel().select(-1);
		this.txtRG.setText("");
		this.txtCPF.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
		this.cmbSexo.getSelectionModel().select(-1);
		this.txtCEP.setText("");
		this.txtRua.setText("");
		this.txtNum.setText("");
		this.cmbCid.getSelectionModel().select(-1);
		this.cmbUF.getSelectionModel().select(-1);
		this.txtPesquisa.setText("");
		this.txtSalario.setText("");
		cc.attTableProb(new ArrayList<ProblemaSaude>());
		ControlClientes.clientSel = new Cliente();
		startStyle();
		btnSelected(0);
	}

	private boolean camposValidos() {
		boolean isValid = true;
		if (this.txtNome.getText().equals("")) {
			this.txtNome.setStyle(this.txtNome.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtSalario.getText().equals("")) {
			this.txtSalario.setStyle(this.txtSalario.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (txtTelefone.getText().equals("")) {
			this.txtTelefone.setStyle(this.txtTelefone.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (txtSobrenome.getText().equals("")) {
			this.txtSobrenome.setStyle(this.txtSobrenome.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;" + "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (this.cmbFarmacia.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbFarmacia.setStyle(this.cmbFarmacia.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (this.cmbCargo.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbCargo.setStyle(this.cmbCargo.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (txtEmail.getText().equals("")) {
			this.txtEmail.setStyle(this.txtEmail.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (this.cmbSexo.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbSexo.setStyle(this.cmbSexo.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}

		if (this.cmbDia.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbDia.setStyle(this.cmbDia.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.cmbMes.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbMes.setStyle(this.cmbMes.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.cmbAno.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbAno.setStyle(this.cmbAno.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
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
					"Alguns campos obrigat�rios nao foram preenchidos.\nPor favor preencha os campos com borda vermelha.",
					"Ops...", JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}

}