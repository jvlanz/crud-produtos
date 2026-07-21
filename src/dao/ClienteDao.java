package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.ICRUD;
import modelos.Cliente;
import utils.ConectaDB;

public class ClienteDao implements ICRUD<Cliente, Integer> {

    @Override
    public Cliente salvar(Cliente obj) {
        String sql = "INSERT INTO tb_clientes(cpf, nome, email, rua, numeroRua, bairro, cep, cidade, estado)VALUES(?,?,?,?,?,?,?,?,?)";
        Connection con = ConectaDB.conectar();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getRua());
            stmt.setInt(5, obj.getNumeroRua());
            stmt.setString(6, obj.getBairro());
            stmt.setInt(7, obj.getCep());
            stmt.setString(8, obj.getCidade());
            stmt.setString(9, obj.getEstado());

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;

    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM tb_clientes WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            con.close();

            System.out.println("Cliente removido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Cliente obj) {
        String sql = "UPDATE tb_clientes SET cpf = ?, nome = ?, rua = ?, numeroRua = ?, bairro = ?, cep = ?, cidade = ?, estado = ? WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getRua());
            stmt.setInt(4, obj.getNumeroRua());
            stmt.setString(5, obj.getBairro());
            stmt.setInt(6, obj.getCep()); // se CEP for String
            stmt.setString(7, obj.getCidade());
            stmt.setString(8, obj.getEstado());
            stmt.setInt(9, obj.getId());

            stmt.executeUpdate();

            stmt.close();
            con.close();

            System.out.println("Cliente alterado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente consultar(Integer id) {
        String sql = "SELECT * FROM tb_clientes WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNome(rs.getString("email"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumeroRua(rs.getInt("numeroRua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getInt("cep"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                rs.close();
                stmt.close();
                con.close();

                return cliente;
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
        String sql = "SELECT * FROM tb_clientes";

        Connection con = ConectaDB.conectar();
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumeroRua(rs.getInt("numeroRua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getInt("cep"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                clientes.add(cliente);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }

}
