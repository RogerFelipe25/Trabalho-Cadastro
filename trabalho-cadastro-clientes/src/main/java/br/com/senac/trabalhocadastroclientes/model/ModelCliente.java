package br.com.senac.trabalhocadastroclientes.model;

public class ModelCliente {

    private String nomeCliente;
    private Double rg;
    private String documento;
    private String email;
    private Double telefone;
    private Integer id;
    public ModelCliente(){

    }

    public ModelCliente(int id, String nome, double rg, String documento, String email, double telefone) {
        this.id = id;
        this.nomeCliente = nome;
        this.documento = documento;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double getRg() {
        return rg;
    }

    public void setRg(Double rg) {
        this.rg = rg;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }
}
