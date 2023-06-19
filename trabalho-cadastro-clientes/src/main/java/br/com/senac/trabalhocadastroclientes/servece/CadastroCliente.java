package br.com.senac.trabalhocadastroclientes.servece;

import br.com.senac.trabalhocadastroclientes.JavaFxApplication;
import br.com.senac.trabalhocadastroclientes.TrabalhoCadastroClientesApplication;
import br.com.senac.trabalhocadastroclientes.controller.CadastroClienteController;
import br.com.senac.trabalhocadastroclientes.db.ConexaoDataBase;
import br.com.senac.trabalhocadastroclientes.model.ModelCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CadastroCliente {
    private static ConexaoDataBase conexao = new ConexaoDataBase() ;

        public static List<ModelCliente> carregarClientes() {
            List<ModelCliente> out = new ArrayList<>();

            try {
                Connection conn = conexao.getConexao();

                Statement sta = conn.createStatement();
                ResultSet rs = sta.executeQuery("select * from public.clientes;");

                while (rs.next()) {
                    ModelCliente cacl = new ModelCliente
                            (rs.getInt("codigo_cli"),
                                    rs.getString("nome"),
                                    rs.getDouble("rg"),
                                    rs.getString("documento"),
                                    rs.getString("email"),
                                    rs.getDouble("telefone"));

                    out.add(cacl);


                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return out;
        }


        public static void inserirCliente(ModelCliente cliente) {
            try {
                Connection conn = conexao.getConexao();

                String sqlInsert = "insert into clientes  (nome, rg , documento, email, telefone) values (?, ? , ?,?,?)";

                PreparedStatement pre = conn.prepareStatement(sqlInsert);

                pre.setString(1, cliente.getNomeCliente());
                pre.setDouble(2, cliente.getRg());
                pre.setString(3, cliente.getDocumento());
                pre.setString(4, cliente.getEmail());
                pre.setDouble(5, cliente.getTelefone());

                pre.execute();

                pre.close();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }




        }

    public static boolean excluirCadastroClientes(int codigo) {
        try {
            Connection conn = conexao.getConexao();
            String deleteSql = "delete from clientes where codigo_cli =?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, codigo);

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    public static boolean buscarClienteByDocumento(String documento){
        try{
            Connection conn = conexao.getConexao();

            String selectSql = "select codigo_cli from clientes where documento = '" + documento + "'";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}








