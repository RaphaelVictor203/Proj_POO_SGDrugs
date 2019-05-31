package com.curso.boundary;

import javafx.scene.control.TextField;
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

public class ManterCliente extends Application implements EventHandler<MouseEvent> {

	private Button btnCadCli;
	private Button btnMantCli;
	private Pane painelCad;
	private BorderPane painelMant;
	private HBox menuTop;
	private TextField txtNome;
	private TextField txtDia, txtMes, txtAno;
	private TextField txtRG, txtCPF;
	private TextField txtTelefone, txtEmail;
	private TextField txtCartaoSus;
	private TextField txtCEP;
	private TextField txtRua, txtNum;
	private TextField txtCid, txtUF;
	private TextField txtPesquisa;
	private TextField txtNomePesquisa;
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
		txtDia = new TextField();
		txtMes = new TextField();
		txtAno = new TextField();
		txtRG = new TextField();
		txtCPF = new TextField();
		txtTelefone = new TextField();
		txtEmail = new TextField();
		txtCartaoSus = new TextField();

		txtCEP = new TextField();
		txtRua = new TextField();
		txtNum = new TextField();
		txtCid = new TextField();
		txtUF = new TextField();
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
				new HBox(10, new Label("Data Nascimento: "), txtDia, new Label("/"), txtMes, new Label("/"), txtAno),
				new HBox(10, new Label("RG: "), txtRG, new Label("CPF: "), txtCPF),
				new HBox(10, new Label("Telefone: "), txtTelefone, new Label("Email:"), txtEmail),
				new HBox(10, new Label("Cartão SUS: "), txtCartaoSus, new Label("Sexo: "), cmbSexo));
		entradaInfoCli.setSpacing(10);
		entradaInfoCli.setStyle("-fx-min-width: 50%; -fx-font-size: 15px;");
		entradaInfoCli.setPadding(new Insets(40));

		VBox entradaInfoEnd = new VBox(new Label("INFORMAÇÕES DE ENDEREÇO"), new Separator(),
				new HBox(10, new Label("CEP.:"), txtCEP),
				new HBox(10, new Label("Rua:"), txtRua, new Label("Número:"), txtNum),
				new HBox(10, new Label("Cidade:"), txtCid, new Label("UF.:"), txtUF));
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

		txtNomePesquisa = new TextField();
		txtCPFPesquisa = new TextField();
		txtUFPesquisa = new TextField();
		txtCidadePesquisa = new TextField();
		btnPesquisa = new Button("PESQUISAR");
		tblCli = new TableView<Cliente>();
		tblCli.setMinWidth(600);

		Label lblTitulo = new Label("PESQUISA CLIENTE");
		HBox hb = new HBox(80,
				new VBox(10, lblTitulo, new Separator(), new HBox(10, new Label("Nome: "), txtNomePesquisa),
						new HBox(10, new Label("CPF.: "), txtCPFPesquisa, new Label("UF.: "), txtUFPesquisa),
						new HBox(10, new Label("Cidade: "), txtCidadePesquisa, btnPesquisa)),
				tblCli);
		hb.setStyle("-fx-font-size: 15px;");
		painelMant = new BorderPane(hb);

		// FIM PAINEL
		// GERENCIAMENTO-----------------------------------------------------------------------------

		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelMant, painelCad);
		pane.setCenter(painels);

		Scene scene = new Scene(pane, 1100, 600);
		stage.setScene(scene);
		stage.setTitle("Manter Clientes");
		stage.show();

		btnCadCli.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnMantCli.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCadastrar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnAddProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnLimpaCampos.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisaProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		// txtNome.addEventFilter(MouseEvent.MOUSE_CLICKED, this);

		addEventosFoco();
		startStyle();
		btnSelected(0);
		createTableColumnsProb();
		createTableColumnsClientes();

	}

	private void addEventosFoco() {
		txtNome.focusedProperty().addListener(e -> {
			txtNome.setStyle(txtNome.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtDia.focusedProperty().addListener(e -> {
			txtDia.setStyle(txtDia.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtMes.focusedProperty().addListener(e -> {
			txtMes.setStyle(txtMes.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtAno.focusedProperty().addListener(e -> {
			txtAno.setStyle(txtAno.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
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
		txtCid.focusedProperty().addListener(e -> {
			txtCid.setStyle(txtCid.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtUF.focusedProperty().addListener(e -> {
			txtUF.setStyle(txtUF.getStyle() + "-fx-border-color: none;" + "-fx-border-radius: 8px;"
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
				Cliente c = cc.pesquisarCliente((long) tblCli.getItems().get(l).getCpf());
				ControlClientes.clientSel = c;
				clienteToBoundary(c);
				System.out.println(c.getSexo());
				painelCad.toFront();
				btnSelected(0);
				cc.attTableProb(c.getProblemasSaude());
				tblProb.refresh();
				setFunctionProbButtons();
				btnCadCli.setText("ALTERAÇÃO");
				btnCadastrar.setText("ALTERAR");
				btnLimpaCampos.setText("CANCELAR ALTERAÇÃO");
			});

			tblCli.getItems().get(i).getBtnExcluir().setOnAction(e -> {
				Cliente c = cc.pesquisarCliente((long) tblCli.getItems().get(l).getCpf());
				ControlClientes.clientSel = c;
				cc.removerCliente();
				setFunctionCliButtons();
				limparCampos();
			});
		}
	}

	@SuppressWarnings("deprecation")
	public void clienteToBoundary(Cliente c) {

		this.txtNome.setText(c.getPrimeiroNome());
		this.txtDia.setText(Integer.toString(c.getDt_nasc().getDate()));
		this.txtMes.setText(Integer.toString(c.getDt_nasc().getMonth()));
		this.txtAno.setText(Integer.toString(c.getDt_nasc().getYear()));
		this.txtRG.setText(Long.toString(c.getRg()));
		this.txtCPF.setText(Long.toString(c.getCpf()));
		this.txtTelefone.setText(Long.toString(c.getTelefone()));
		this.txtEmail.setText(c.getEmail());
		this.txtCartaoSus.setText(Long.toString(c.getCartaoSUS()));
		Endereco ed = c.getEnd();
		this.txtCEP.setText(ed.getCep());
		this.txtRua.setText(ed.getRua());
		this.txtNum.setText(Integer.toString(ed.getNumero()));
		this.txtCid.setText(ed.getCidade());
		this.txtUF.setText(ed.getUf());
		this.cmbSexo.getSelectionModel().select(String.valueOf(c.getSexo()));
	}

	@SuppressWarnings("deprecation")
	public Cliente boundaryToCliente() {

		Cliente c = new Cliente();
		c.setPrimeiroNome(this.txtNome.getText());
		c.setDt_nasc(new Date(Integer.parseInt(this.txtAno.getText()), Integer.parseInt(this.txtMes.getText()),
				Integer.parseInt(this.txtDia.getText())));
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
		ed.setCidade(this.txtCid.getText());
		ed.setUf(this.txtUF.getText());
		c.setEnd(ed);

		c.setProblemasSaude(ControlClientes.clientSel.getProblemasSaude());
		return c;
	}

	public void startStyle() {

		String styleBtnPesquisa = "-fx-background-color: #0095FE;" + "-fx-text-fill: white;"
				+ "-fx-background-radius: 7;" + "-fx-min-width: 240px;" + "-fx-min-height: 30px;" + "-fx-cursor: hand;";

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

		String comboStyle = "-fx-background-radius: 8;" + "-fx-background-color: #FEFFFA;" + "-fx-cursor: hand";

		btnCadCli.setStyle(styleBtn);
		btnMantCli.setStyle(styleBtn);
		painelCad.setStyle(stylePainel);
		painelMant.setStyle(stylePainel);
		menuTop.setStyle(styleMenuTop);
		txtNome.setStyle("-fx-min-width: 515px;" + styleEntradas);
		txtDia.setStyle(styleEntradaDataNasc + styleEntradas);
		txtMes.setStyle(styleEntradaDataNasc + styleEntradas);
		txtAno.setStyle(styleEntradaDataNasc + styleEntradas);
		txtRG.setStyle("-fx-min-width: 225px;" + styleEntradas);
		txtCPF.setStyle("-fx-min-width: 259px;" + styleEntradas);
		txtTelefone.setStyle("-fx-max-width: 186px;" + styleEntradas);
		txtEmail.setStyle("-fx-min-width: 253px;" + styleEntradas);
		txtCartaoSus.setStyle("-fx-min-width: 355px;" + styleEntradas);
		txtCEP.setStyle("-fx-min-width: 530px; " + styleEntradas);
		txtRua.setStyle("-fx-min-width: 228px; " + styleEntradas);
		txtNum.setStyle("-fx-min-width: 227px; " + styleEntradas);
		txtCid.setStyle("-fx-min-width: 207px; " + styleEntradas);
		txtUF.setStyle("-fx-min-width: 259px; " + styleEntradas);
		txtPesquisa.setStyle(styleEntradaPesquisa + "-fx-font-size: 15px");
		btnAddProb.setStyle(styleBtnAddProb);
		btnLimpaCampos.setStyle(styleBtns);
		btnCadastrar.setStyle(styleBtns);
		btnPesquisaProb.setStyle(stylePesquisaProb);
		txtNomePesquisa.setStyle("-fx-min-width: 515px;" + styleEntradas);
		txtCPFPesquisa.setStyle("-fx-min-width: 280px;" + styleEntradas);
		txtUFPesquisa.setStyle("-fx-min-width: 200px;" + styleEntradas);
		txtCidadePesquisa.setStyle("-fx-min-width: 262px;" + styleEntradas);
		btnPesquisa.setStyle(styleBtnPesquisa);
		cmbSexo.setStyle(comboStyle);
		cmbSexo.setEffect(new DropShadow(4, 0, 0, Color.GRAY));
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
				JOptionPane.showMessageDialog(null, "cadastro realizado !!!", "Cadastro",
						JOptionPane.INFORMATION_MESSAGE);
				if (cc.cadCliente(boundaryToCliente())) {
					limparCampos();
				}
				tblCli.refresh();
				setFunctionCliButtons();

			} else if (btnCadastrar.getText().equals("ALTERAR") && camposValidos()) {
				cc.attCliente(boundaryToCliente());
				JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso", "Alteração concluida",
						JOptionPane.INFORMATION_MESSAGE);
				tblCli.refresh();
				limparCampos();
				setFunctionCliButtons();
			}
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
		} else if (e.getSource() == btnPesquisaProb) {
			ProblemaSaude ps = cc.pesquisarProb(this.txtPesquisa.getText());
			if (ps != null) {
				if (ControlClientes.clientSel == null) {
					ControlClientes.clientSel = new Cliente();
				}
				if (!ControlClientes.clientSel.existProb(ps.getId_problema())) {
					ControlClientes.clientSel.getProblemasSaude().add(ps);
				}
				cc.attTableProb();
				setFunctionProbButtons();
			} else {
				JOptionPane.showMessageDialog(null, "Problema não encontrado");
			}
		}
	}

	public void limparCampos() {
		btnCadastrar.setText("CADASTRAR");
		btnCadCli.setText("CADASTRO");
		btnLimpaCampos.setText("LIMPAR CAMPOS");
		this.txtNome.setText("");
		this.txtDia.setText("");
		this.txtMes.setText("");
		this.txtAno.setText("");
		this.txtRG.setText("");
		this.txtCPF.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
		this.txtCartaoSus.setText("");
		this.txtCEP.setText("");
		this.txtRua.setText("");
		this.txtNum.setText("");
		this.txtCid.setText("");
		this.txtUF.setText("");
		this.txtPesquisa.setText("");
		cc.attTableProb(new ArrayList<ProblemaSaude>());
		ControlClientes.clientSel = new Cliente();
	}

	private boolean camposValidos() {
		boolean isValid = true;
		if (this.txtNome.getText().equals("")) {
			this.txtNome.setStyle(this.txtNome.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtDia.getText().equals("")) {
			this.txtDia.setStyle(this.txtDia.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtMes.getText().equals("")) {
			this.txtMes.setStyle(this.txtMes.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtAno.getText().equals("")) {
			this.txtAno.setStyle(this.txtAno.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
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
		if (this.txtCid.getText().equals("")) {
			this.txtCid.setStyle(this.txtCid.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if (this.txtUF.getText().equals("")) {
			this.txtUF.setStyle(this.txtUF.getStyle() + "-fx-border-color: red;" + "-fx-border-radius: 8px;"
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
