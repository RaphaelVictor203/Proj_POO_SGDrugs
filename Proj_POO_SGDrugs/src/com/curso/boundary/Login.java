package com.curso.boundary;

import java.io.FileInputStream;

import javax.swing.text.PasswordView;

import com.curso.dao.DAOException;
import com.curso.dao.LoginDAO;
import com.curso.dao.LoginDAOImpl;
import com.curso.entity.Funcionario;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

	StackPane panePrincipal = new StackPane();
	BorderPane painelSubPrincipal = new BorderPane();
	ImageView imgView;
	TextField txtUsuario = new TextField();
	PasswordField txtSenha = new PasswordField();
	VBox vbLogin;
	Label lblNomeProg = new Label("Login");
	Label lblMsgLogin = new Label("conta n�o encontrada !!!");
	Button btnLogar = new Button("Entrar");
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		txtUsuario.setPromptText("Usu�rio");
		txtSenha.setPromptText("Senha");
		
		Scene scene = new Scene(panePrincipal, 500, 400);
		stage.setScene(scene);
		stage.show();
		
		imgView = new ImageView(new Image(new FileInputStream("imgs\\back_tela_principal.jpg")));
        imgView.setFitWidth(scene.getWidth());
        imgView.setFitHeight(scene.getHeight());
		panePrincipal.getChildren().add(imgView);
		panePrincipal.getChildren().add(painelSubPrincipal);
		
		lblMsgLogin.setVisible(false);
		vbLogin = new VBox(10, txtUsuario, txtSenha, btnLogar, lblMsgLogin);
		//vbLogin.setStyle("-fx-background-color: yellow");
		vbLogin.setMaxWidth(stage.getWidth());
		vbLogin.setMaxHeight(stage.getHeight()/2);
		txtSenha.setMaxWidth(stage.getWidth()/1.5);
		txtUsuario.setMaxWidth(stage.getWidth()/1.5);
		btnLogar.setMaxWidth(stage.getWidth()/1.5);
		
		painelSubPrincipal.setCenter(vbLogin);
		
		VBox vbTitulo = new VBox(lblNomeProg);
		vbTitulo.setAlignment(Pos.CENTER);
		painelSubPrincipal.setTop(vbTitulo);
		Label lblprg = new Label(" � SGDrugs");
		lblprg.setStyle("-fx-text-fill: white");
		painelSubPrincipal.setBottom(lblprg);
		
		
		startStyle();
		
		btnLogar.setOnAction(e -> {
			LoginDAO ldi = new LoginDAOImpl();
			try {
				lblMsgLogin.setVisible(true);
				Funcionario func = ldi.pesquisarPorConta(txtUsuario.getText(), txtSenha.getText());
				if(func != null) {
					lblMsgLogin.setText("bem vindo " + func.getNome());
					lblMsgLogin.setStyle("-fx-font-size: 15px; -fx-text-fill: white");
					TelaPrincipal tp = new TelaPrincipal();
					try {
						tp.func = func;
						tp.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					lblMsgLogin.setText("conta n�o encontrada !!!");
					lblMsgLogin.setStyle("-fx-font-size: 15px; -fx-text-fill: red");
				}
			} catch (DAOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void startStyle() {
		panePrincipal.setStyle(""
				+ " -fx-min-width: 100%; -fx-min-height: 100%;");

		painelSubPrincipal.setStyle("-fx-background-color: rgba(0, 0, 0, .7); -fx-min-width: 100%; -fx-min-height: 100%;");
		vbLogin.setAlignment(Pos.CENTER);
		vbLogin.setStyle("-fx-background-color: rgba(0, 0, 0, .7)");
		txtUsuario.setStyle("-fx-font-size: 18px");
		txtSenha.setStyle("-fx-font-size: 18px");
		lblNomeProg.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");
		btnLogar.setStyle("-fx-background-color: #0095FE;"
				+ "-fx-text-fill: white;"
				+ "-fx-cursor: hand;"
				+ "-fx-font-size: 18px");
		lblMsgLogin.setStyle("-fx-font-size: 15px; -fx-text-fill: red"); 
				
	}

}
