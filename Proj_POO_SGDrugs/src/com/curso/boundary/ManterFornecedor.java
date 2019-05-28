package com.curso.boundary;


import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.curso.control.ControlFornecedores;
import com.curso.entity.Fornecedor;
import com.curso.entity.Endereco;
import com.curso.entity.Farmacia;
import com.curso.entity.Fornecedor;
import com.curso.entity.ProblemaSaude;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ManterFornecedor extends Application implements EventHandler<MouseEvent> {
	

	private Button btnCadFornec;

	private Pane painelCad;
	private BorderPane painelMant;
	private HBox menuTop;
	private TextField txtNome;
	private ComboBox<String> cmbDia, cmbMes, cmbAno;
	private TextField txtRG, txtCNPJ;
	private TextField txtTelefone, txtEmail;
    private ComboBox<Farmacia> cmbFarmacia;
	private TextField txtCEP;
	private TextField txtRua, txtNum;
	private ComboBox<String> cmbCid, cmbUF;
	private TextField txtPesquisa;
	private TextField txtNomePesquisa;
	private TextField txtCNPJPesquisa;
	private TextField txtUFPesquisa;
	private TextField txtCidadePesquisa;
	private ComboBox<String> cmbSexo;
	private TableView<Fornecedor> tblProb;
	private Button btnAddProb, btnLimpaCampos, btnCadastrar, btnPesquisa;

	
	
	ControlFornecedores ff;
	
	@Override
	public void start(Stage stage) throws Exception{
		
		ff = new ControlFornecedores();
		painelCad = new Pane();

//INICIO PAINEL CADASTRO-------------------------------------------------------------------------------
		
		
		
		txtNome = new TextField();
		cmbFarmacia = new ComboBox<>(FXCollections.observableArrayList(
				new Farmacia ("Unidae Leste"),
				new Farmacia ("Unidae Sul"),
				new Farmacia ("Unidae Norte"),
				new Farmacia ("Unidae Oeste"),
				new Farmacia ("Unidae Sudeste")
				)) ;
		cmbDia = new ComboBox<String>(FXCollections.observableArrayList(ff.gerarArrayNum(1, 31)));
		cmbDia.setPromptText("Dia");
		cmbMes = new ComboBox<String>(FXCollections.observableArrayList(ff.gerarArrayNum(1, 12)));
		cmbMes.setPromptText("Mês");
		cmbAno = new ComboBox<String>(FXCollections.observableArrayList(ff.gerarArrayNum(1900, 2019)));
		cmbAno.setPromptText("Ano");
		txtRG = new TextField(); 
		txtCNPJ = new TextField();
		txtTelefone = new TextField();
		txtEmail = new TextField();
	
		
		txtCEP = new TextField();
		txtRua = new TextField();
		txtNum = new TextField();
		/*txtCid = new TextField();
		txtUF = new TextField();*/
		cmbCid = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"São Paulo", "Ribeirão", "Botucatu", "Piracicaba", "Santos", "Franca", "Araçatuba"}));
		cmbUF = new ComboBox<String>(FXCollections.observableArrayList(new String[] {"SP"}));
		cmbSexo = new ComboBox<String>(FXCollections.observableArrayList(new String[]{"M", "F"}));
		cmbSexo.getSelectionModel().select(0);
		
		txtPesquisa = new TextField();
		txtPesquisa.setPromptText("Insira o nome ou CNPJ do Funcionário..");
		
		btnLimpaCampos = new Button("LIMPAR CAMPOS");
		btnCadastrar = new Button("CADASTRAR");
		btnAddProb = new Button("PESQUISAR");
		tblProb = new TableView<Fornecedor>();
		tblProb.setMaxWidth(625);
		
		
		
		BorderPane pane = new BorderPane();
		
		btnCadFornec = new Button("CADASTRO");
		
		menuTop = new HBox(btnCadFornec);
		
		VBox entradaInfoCli = new VBox(
				new Label("INFORMAÇÕES RELACIONADAS AO FUNCIONÁRIO"),
				new Separator(),
				new HBox(10, new Label("Nome: "), txtNome),
				new HBox(10, new Label("Data Nascimento: "), cmbDia, new Label("/"), cmbMes, new Label("/"),cmbAno),
				new HBox(10, new Label("RG: "), txtRG, new Label("CNPJ: "), txtCNPJ),
				new HBox(10, new Label("Telefone: "), txtTelefone,new Label("Sexo: "), cmbSexo) ,
				new HBox(10, new Label("Email:"), txtEmail) 
		);
		entradaInfoCli.setSpacing(10);
		entradaInfoCli.setStyle("-fx-min-width: 50%; -fx-font-size: 15px;");
		entradaInfoCli.setPadding(new Insets(40));
		
		VBox entradaInfoEnd = new VBox(
				new Label("INFORMAÇÕES DE ENDEREÇO"),
				new Separator(),
				new HBox(10, new Label("Rua:"),  txtRua),
				new HBox(10, new Label("CEP:"),txtCEP , new Label("Número:"), txtNum),
				new HBox(10, new Label("Cidade:"), cmbCid, new Label("UF.:"), cmbUF)
				
		);
		entradaInfoEnd.setSpacing(10);
		entradaInfoEnd.setStyle("-fx-min-width: 50%; -fx-font-size: 15px"); 
		entradaInfoEnd.setPadding(new Insets(0, 40, 40, 40));
		
	
		Label tituloProb = new Label("PESQUISAR FUNCIONÁRIO");
		tituloProb.setStyle(" -fx-font-size: 15px");
		VBox entradaTab = new VBox(
				tituloProb,
				new Separator(),
				new HBox(txtPesquisa),
				btnAddProb,
				tblProb
		);
		entradaTab.setPadding(new Insets(39, 40, 40, 40));
		entradaTab.setSpacing(10);
		
		HBox hbBtns = new HBox(
				btnLimpaCampos,
				btnCadastrar
		);
		hbBtns.setPadding(new Insets(7, 0, 0, 0));
		hbBtns.setStyle("-fx-min-width: 50%; -fx-font-size: 15px");
		hbBtns.setAlignment(Pos.BASELINE_CENTER);
		hbBtns.setSpacing(20);
		
		HBox entradaInfoGeral = new HBox(
				new VBox(entradaInfoCli,entradaInfoEnd,hbBtns),
				entradaTab
		);
		entradaInfoGeral.setPadding(new Insets(20, 0, 0, 0));
		
		painelCad.getChildren().add(entradaInfoGeral);

//FIM PAINEL CADASTRO----------------------------------------------------------------------------------
		
//INICIO PAINEL GERENCIAMENTO--------------------------------------------------------------------------
		
		txtNomePesquisa = new TextField();
		txtCNPJPesquisa = new TextField();
		txtUFPesquisa = new TextField();
		txtCidadePesquisa = new TextField();
		btnPesquisa = new Button("PESQUISAR");
	
		
		Label lblTitulo = new Label("PESQUISA Fornecedor");
		HBox hb = new HBox(80,
				new VBox(10,
							lblTitulo,
							new Separator(),
							new HBox(10, new Label("Nome: "), txtNomePesquisa),
							new HBox(10, new Label("CNPJ.: "), txtCNPJPesquisa, new Label("UF.: "), txtUFPesquisa),
							new HBox(10, new Label("Cidade: "), txtCidadePesquisa, btnPesquisa)
						)
				);
		hb.setStyle("-fx-font-size: 15px;");
		painelMant = new BorderPane(hb);
		
//FIM PAINEL GERENCIAMENTO-----------------------------------------------------------------------------
		
		pane.setTop(menuTop);
		StackPane painels = new StackPane(painelMant, painelCad);
		pane.setCenter(painels);
		
		Scene scene = new Scene(pane, 1100,600);
		stage.setScene(scene);
		stage.setTitle("Manter Fornecedores");
		stage.show();
		
		btnCadFornec.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		
		btnCadastrar.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnAddProb.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnLimpaCampos.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		btnPesquisa.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
		
		addEventosFoco();
		startStyle();
		btnSelected(0);
		createTableColumnsFornec();
		
		
	}
	
	private void addEventosFoco() {
		txtNome.focusedProperty().addListener(e -> {
			txtNome.setStyle(txtNome.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbDia.focusedProperty().addListener(e -> {
			cmbDia.setStyle(cmbDia.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbMes.focusedProperty().addListener(e -> {
			cmbMes.setStyle(cmbMes.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbAno.focusedProperty().addListener(e -> {
			cmbAno.setStyle(cmbAno.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtRG.focusedProperty().addListener(e -> {
			txtRG.setStyle(txtRG.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		}); 
		txtCNPJ.focusedProperty().addListener(e -> {
			txtCNPJ.setStyle(txtCNPJ.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtCEP.focusedProperty().addListener(e -> {
			txtCEP.setStyle(txtCEP.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtRua.focusedProperty().addListener(e -> {
			txtRua.setStyle(txtRua.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		txtNum.focusedProperty().addListener(e -> {
			txtNum.setStyle(txtNum.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbCid.focusedProperty().addListener(e -> {
			cmbCid.setStyle(cmbCid.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
		cmbUF.focusedProperty().addListener(e -> {
			cmbUF.setStyle(cmbUF.getStyle() + "-fx-border-color: none;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
		});
	}
	

	@SuppressWarnings("unchecked")
	public void createTableColumnsFornec() {
		
	
		
		TableColumn<Fornecedor, Number> id_fornec = new TableColumn<>("ID");
		id_fornec.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getID()));
		
		TableColumn<Fornecedor, String> tipo = new TableColumn<>("Nome");
		tipo.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getPrimeiroNome()));
		
		TableColumn<Fornecedor, Number> desc = new TableColumn<>("CNPJ");
		desc.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getCnpj()));
		
		
		tblProb.getColumns().addAll(id_fornec,tipo,desc);
		
	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	public void FornecedorToBoundary(Fornecedor f) {
		this.txtNome.setText(f.getPrimeiroNome());
		this.cmbDia.getSelectionModel().select(Integer.toString(f.getDt_nasc().getDate()));
		this.cmbMes.getSelectionModel().select(Integer.toString(f.getDt_nasc().getMonth()));
		this.cmbAno.getSelectionModel().select(Integer.toString(f.getDt_nasc().getYear()));
		this.txtRG.setText(Long.toString(f.getRg()));
		this.txtCNPJ.setText(Long.toString(f.getCnpj()));
		this.txtTelefone.setText(Long.toString(f.getTelefone()));
		this.txtEmail.setText(f.getEmail());
	
		Endereco ed = f.getEnd();
		this.txtCEP.setText(ed.getCep());
		this.txtRua.setText(ed.getRua());
		this.txtNum.setText(Integer.toString(ed.getNumero()));
		this.cmbCid.getSelectionModel().select(ed.getCidade());
		this.cmbUF.getSelectionModel().select(ed.getUf());
		this.cmbSexo.getSelectionModel().select(String.valueOf(f.getSexo()));
	}
	
	
	@SuppressWarnings("deprecation")
	public Fornecedor boundaryToFornecedor() {
		
		Fornecedor f = new Fornecedor();
		f.setPrimeiroNome(this.txtNome.getText());
		f.setDt_nasc(new Date(Integer.parseInt(this.cmbAno.getSelectionModel().getSelectedItem()), Integer.parseInt(this.cmbMes.getSelectionModel().getSelectedItem()), Integer.parseInt(this.cmbDia.getSelectionModel().getSelectedItem())));
		f.setRg(Long.parseLong(this.txtRG.getText()));
		f.setCnpj(Long.parseLong(this.txtCNPJ.getText()));
		if(!this.txtTelefone.getText().equals("")) {
			f.setTelefone(Long.parseLong(this.txtTelefone.getText()));
		}
		f.setEmail(this.txtEmail.getText());
		
		f.setSexo(cmbSexo.getSelectionModel().getSelectedItem().charAt(0));
		
		Endereco ed = new Endereco();
		ed.setCep(this.txtCEP.getText());
		ed.setRua(this.txtRua.getText());
		ed.setNumero(Integer.parseInt(this.txtNum.getText()));
		ed.setCidade(this.cmbCid.getSelectionModel().getSelectedItem());
		ed.setUf(this.cmbUF.getSelectionModel().getSelectedItem());
		f.setEnd(ed);
		
		return f;
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
		
		String styleEntradaPesquisa = "-fx-background-radius: 8px 8px 8px 8px;"
				+ "-fx-min-width: 625px;";
		
		String stylePesquisaProb = "-fx-min-height: 30px;"
				+ "-fx-min-width: 30px;"
				+ "-fx-background-radius: 0px 8px 8px 0px;"
				+ "-fx-background-color: #0095FE;"
				+ "-fx-cursor: hand;";
		
		String comboStyle = "-fx-background-radius: 8;"
				+ "-fx-background-color: #FEFFFA;"
				+ "-fx-cursor: hand;";
		
		btnCadFornec.setStyle(styleBtn);
		
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
		txtCNPJ.setStyle("-fx-min-width: 259px;" + styleEntradas);
		txtTelefone.setStyle("-fx-max-width: 250px;" + styleEntradas);
		txtEmail.setStyle("-fx-min-width: 523px;" + styleEntradas);
	
		txtCEP.setStyle("-fx-min-width: 228px; " + styleEntradas);
		txtRua.setStyle("-fx-min-width: 530px; " + styleEntradas);
		txtNum.setStyle("-fx-min-width: 227px; " + styleEntradas);
		cmbCid.setStyle("-fx-min-width: 207px; " + styleEntradas + comboStyle);
		cmbCid.setEffect(dp);
		cmbUF.setStyle("-fx-min-width: 259px; " + styleEntradas + comboStyle);
		cmbUF.setEffect(dp);
		txtPesquisa.setStyle(styleEntradaPesquisa + "-fx-font-size: 15px");
		btnAddProb.setStyle(styleBtnAddProb);
		btnLimpaCampos.setStyle(styleBtns);
		btnCadastrar.setStyle(styleBtns);
		txtNomePesquisa.setStyle("-fx-min-width: 515px;" + styleEntradas);
		txtCNPJPesquisa.setStyle("-fx-min-width: 280px;" + styleEntradas);
		txtUFPesquisa.setStyle("-fx-min-width: 200px;" + styleEntradas);
		txtCidadePesquisa.setStyle("-fx-min-width: 262px;" + styleEntradas);
		btnPesquisa.setStyle(styleBtnPesquisa);
		cmbSexo.setStyle(comboStyle);
		cmbSexo.setEffect(dp);
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
		btnCadFornec.setStyle(
				"-fx-background-color: " + CadSelected + ";"
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
		if(e.getSource() == btnCadFornec) {
			painelCad.toFront();
			btnSelected(0);	
		}else
		
		if(e.getSource() == btnCadastrar) {
			if(btnCadastrar.getText().equals("CADASTRAR") && camposValidos()) {
				JOptionPane.showMessageDialog(null, "cadastro realizado !!!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				if(ff.cadFornecedor(boundaryToFornecedor())) {
					limparCampos();
				}
				
			}else 
			if(btnCadastrar.getText().equals("ALTERAR") && camposValidos()){
				ff.attFornecedor(boundaryToFornecedor());
				JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso", "Alteração concluida", JOptionPane.INFORMATION_MESSAGE);
				
				limparCampos();
				
			}
		}else
		if(e.getSource() == btnLimpaCampos) {
			limparCampos();
		}
		if(e.getSource() == btnPesquisa) {
			if(txtCNPJPesquisa.getText().equals("")) {
				ff.attTableFornecedor();
			}else {
				long CNPJ = Long.parseLong(txtCNPJPesquisa.getText());
				Fornecedor cl = ff.pesquisarFornecedor(CNPJ);
				if(cl != null) {
					ff.attTableFornecedor(cl);
				}else {
					JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		}
	}
	
	public void limparCampos() {
		btnCadastrar.setText("CADASTRAR");
		btnCadFornec.setText("CADASTRO");
		btnLimpaCampos.setText("LIMPAR CAMPOS");
		this.txtNome.setText("");
		this.cmbDia.getSelectionModel().select(-1);
		this.cmbMes.getSelectionModel().select(-1);
		this.cmbAno.getSelectionModel().select(-1);
		this.txtRG.setText("");
		this.txtCNPJ.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
		
		this.txtCEP.setText("");
		this.txtRua.setText("");
		this.txtNum.setText("");
		this.cmbCid.getSelectionModel().select(-1);
		this.cmbUF.getSelectionModel().select(-1);
		this.txtPesquisa.setText("");
		ControlFornecedores.fornecSel = new Fornecedor();
		startStyle();
		btnSelected(0);
	}
	
	private boolean camposValidos() {
		boolean isValid = true;
		if(this.txtNome.getText().equals("")) {
			this.txtNome.setStyle(this.txtNome.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbDia.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbDia.setStyle(this.cmbDia.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbMes.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbMes.setStyle(this.cmbMes.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbAno.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbAno.setStyle(this.cmbAno.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.txtRG.getText().equals("")) {
			this.txtRG.setStyle(this.txtRG.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.txtCNPJ.getText().equals("")) {
			this.txtCNPJ.setStyle(this.txtCNPJ.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.txtCEP.getText().equals("")) {
			this.txtCEP.setStyle(this.txtCEP.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.txtRua.getText().equals("")) {
			this.txtRua.setStyle(this.txtRua.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.txtNum.getText().equals("")) {
			this.txtNum.setStyle(this.txtNum.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbCid.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbCid.setStyle(this.cmbCid.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(this.cmbUF.getSelectionModel().getSelectedIndex() == -1) {
			this.cmbUF.setStyle(this.cmbUF.getStyle() + "-fx-border-color: red;"
					+ "-fx-border-radius: 8px;"
					+ "-fx-background-radius: 8px;");
			isValid = false;
		}
		if(!isValid) {
			JOptionPane.showMessageDialog(null, "Alguns campos obrigatórios nao foram preenchidos.\nPor favor preencha os campos com borda vermelha.", "Ops...", JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}



}
