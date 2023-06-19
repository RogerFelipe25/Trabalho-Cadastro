package br.com.senac.trabalhocadastroclientes.servece;

import br.com.senac.trabalhocadastroclientes.db.ConexaoDataBase;
import br.com.senac.trabalhocadastroclientes.model.ModelEndereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroEndereco {

        private static ConexaoDataBase conexao = new ConexaoDataBase() ;

        public static List<ModelEndereco> cadastroendereco() {
            List<ModelEndereco> out = new ArrayList<>();

            try {
                Connection conn = conexao.getConexao();

                Statement sta = conn.createStatement();
                ResultSet rs = sta.executeQuery("select * from public.endereco;");

                while (rs.next()) {
                    ModelEndereco mde = new ModelEndereco(rs.getInt("codigo"),
                                    rs.getString("rua"),
                                    rs.getDouble("numero"),
                                    rs.getString("bairro"),
                                    rs.getString("cidade"),
                                    rs.getString("estado"),
                                    rs.getDouble("cep"));

                    out.add(mde);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return out;
        }


        public static void inserirEndereco(ModelEndereco endereco) {
            try {
                Connection conn = conexao.getConexao();

                String sqlInsert = "insert into endereco  (codigo, rua , numero , bairro, cidade, estado, cep) values (?, ? , ?, ?, ?, ?, ?)";

                PreparedStatement pre = conn.prepareStatement(sqlInsert);

                pre.setInt(1, endereco.getCodigo());
                pre.setString(2, endereco.getRua());
                pre.setDouble(3, endereco.getNumero());
                pre.setString(4, endereco.getBairro());
                pre.setString(5, endereco.getCidade());
                pre.setString(6, endereco.getEstado());
                pre.setDouble(7, endereco.getCep());

                pre.execute();

                pre.close();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }




        }


    public static boolean excluircadastroendereco(int codigo) {
        try {
            Connection conn = conexao.getConexao();
            String deleteSql = "delete from endereco where codigo =?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, codigo);

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }}


