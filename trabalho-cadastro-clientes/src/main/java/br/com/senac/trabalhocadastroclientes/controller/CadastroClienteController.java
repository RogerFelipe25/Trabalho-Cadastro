package br.com.senac.trabalhocadastroclientes.controller;

import br.com.senac.trabalhocadastroclientes.model.ModelCliente;
import br.com.senac.trabalhocadastroclientes.model.ModelEndereco;
import br.com.senac.trabalhocadastroclientes.servece.CadastroCliente;
import br.com.senac.trabalhocadastroclientes.servece.CadastroEndereco;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
@Component
@FxmlView("/CadastroCliente.fxml")
public class CadastroClienteController {
    @FXML
    private TextField nomeCliente;
    @FXML
    private TextField rg;
    @FXML
    private TextField documento;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone;
    //aqui pertece ao layout cliente


    @FXML
    private TextField codigoCliente;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField bairro;
    @FXML
    private TextField cidade;
    @FXML
    private TextField estado;
    @FXML
    private TextField cep;
    //aqui pertece ao layout endereço


    @FXML
    private TableView<ModelCliente> tabelaCadastro;
    @FXML
    private TableColumn<ModelCliente, Integer> colunaCodigoCliente1;
    @FXML
    private TableColumn<ModelCliente, String> colunaNome;
    @FXML
    private TableColumn<ModelCliente, Double> colunaRg;
    @FXML
    private TableColumn<ModelCliente, Double> colunaDocumento;
    @FXML
    private TableColumn<ModelCliente, String> colunaEmail;
    @FXML
    private TableColumn<ModelCliente, Double> colunaTelefone;
    //aqui pertece ao layout cliente


    @FXML
    private TableView<ModelEndereco> tabelaEndereco;
    @FXML
    private TableColumn<ModelEndereco, Integer> colunaCodigoClienteEndereco;
    @FXML
    private TableColumn<ModelEndereco, String> colunaRua;
    @FXML
    private TableColumn<ModelEndereco, Double> colunaNumero;
    @FXML
    private TableColumn<ModelEndereco, String> colunaBairro;
    @FXML
    private TableColumn<ModelEndereco, String> colunaCidade;
    @FXML
    private TableColumn<ModelEndereco, String> colunaEstado;
    @FXML
    private TableColumn<ModelEndereco, Double> colunaCep;
    //aqui pertece ao layout endereço

    private int index = -1;

    @FXML
    public void initialize() {

        colunaCodigoCliente1.setCellValueFactory(new PropertyValueFactory<>("colunaCodigoCliente1"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        colunaCodigoClienteEndereco.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colunaCep.setCellValueFactory(new PropertyValueFactory<>("cep"));



this.carregarListaClientes();
this.cadastroendereco();

        tabelaCadastro.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    ModelCliente cacl = tabelaCadastro.getSelectionModel().getSelectedItem();

                    nomeCliente.setText(cacl.getNomeCliente());
                    rg.setText(String.valueOf(cacl.getRg()));
                    documento.setText(String.valueOf(cacl.getDocumento()));
                    email.setText(cacl.getEmail());
                    telefone.setText(String.valueOf(cacl.getTelefone()));

                    index = cacl.getId();


                }
            }
        });

        tabelaEndereco.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    ModelEndereco ce = tabelaEndereco.getSelectionModel().getSelectedItem();

                    codigoCliente.setText(String.valueOf(ce.getCodigo()));
                    rua.setText(ce.getRua());
                    numero.setText(String.valueOf(ce.getNumero()));
                    bairro.setText(ce.getBairro());
                    cidade.setText(ce.getCidade());
                    estado.setText(ce.getEstado());
                    cep.setText(String.valueOf(ce.getCep()));

                    index = ce.getCodigo();



                }
            }
        });

    }

    public void executarSalvarEndereco() {
        ModelEndereco moe = new ModelEndereco();
        moe.getCodigo(Integer.parseInt(codigoCliente.getText()));
        moe.setRua(rua.getText());
        moe.setNumero(Double.parseDouble(numero.getText()));
        moe.setBairro(bairro.getText());
        moe.setCidade(cidade.getText());
        moe.setEstado(estado.getText());
        moe.setCep(Double.parseDouble(cep.getText()));

        tabelaEndereco.getItems().addAll(moe);
        CadastroEndereco.inserirEndereco(moe);

        this.cadastroendereco();
        this.limparCampos();
    }

    public void cadastroendereco(){
        tabelaEndereco.getItems().remove(0, tabelaEndereco.getItems().size());

        List<ModelEndereco> moe = CadastroEndereco.cadastroendereco();

        tabelaEndereco.getItems().addAll(moe);

    }


    public void executarSalvar() {
        ModelCliente mod = new ModelCliente();
        mod.setNomeCliente(nomeCliente.getText());
        mod.setRg(Double.parseDouble(rg.getText()));
        mod.setDocumento(documento.getText());
        mod.setEmail(email.getText());
        mod.setTelefone(Double.parseDouble(telefone.getText()));


       /* if (CadastroCliente.buscarClienteByDocumento(String.valueOf(mod.getDocumento()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta");
            alert.setHeaderText("Documento: " + (documento.getText()) + " já existe na base!");
            alert.show();
        } else if (!String.valueOf(mod.getDocumento()).matches("[0-9]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Documento invalido");
            alert.show();
        }else{*/

        tabelaCadastro.getItems().addAll(mod);
        CadastroCliente.inserirCliente(mod);

        this.carregarListaClientes();
        this.limparCampos();}


    public void executarExcluirEndereco(){
        if (index > -1){
            CadastroEndereco.excluircadastroendereco(index);
            this.cadastroendereco();
            index = -1;

            this.limparCamposEndereco();
        }
    }
    public void executarExcluir() {
        if (index > -1) {
            CadastroCliente.excluirCadastroClientes(index);
            this.carregarListaClientes();
            index = -1;

            this.limparCampos();
        }
    }
        public void carregarListaClientes(){
            tabelaCadastro.getItems().remove(0, tabelaCadastro.getItems().size());

            List<ModelCliente> cliList = CadastroCliente.carregarClientes();

            tabelaCadastro.getItems().addAll(cliList);

    }


       public void limparCampos(){
        nomeCliente.setText("");
        rg.setText("");
        documento.setText("");
        email.setText("");
        telefone.setText("");
    }//pertece layout clientes



public void limparCamposEndereco(){
    codigoCliente.setText("");
    rua.setText("");
    numero.setText("");
    bairro.setText("");
    cidade.setText("");
    estado.setText("");
    cep.setText("");}
}












