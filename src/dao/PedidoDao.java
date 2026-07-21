package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.ICRUD;
import modelos.Cliente;
import modelos.Pedido;
import utils.ConectaDB;

public class PedidoDao implements ICRUD<Pedido, Integer> {

    @Override
    public Pedido salvar(Pedido obj) {
        String sql = "INSERT INTO tb_pedidos(id_cliente, data_pedido, status) VALUES (?, ?, ?)";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setDate(2, java.sql.Date.valueOf(obj.getData()));
            stmt.setString(3, obj.getStatus());
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
        String sql = "DELETE FROM tb_pedidos WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            con.close();

            System.out.println("Pedido removido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Pedido obj) {
        String sql = "UPDATE tb_pedidos SET id_cliente = ?, data_pedido = ?, status = ? WHERE id = ?";
        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setDate(2, java.sql.Date.valueOf(obj.getData()));
            stmt.setString(3, obj.getStatus());
            stmt.setInt(4, obj.getId());

            stmt.executeUpdate();

            stmt.close();
            con.close();

            System.out.println("Pedido alterado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido consultar(Integer id) {
        String sql = "SELECT * FROM tb_pedidos WHERE id = ?";
        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                pedido.setCliente(cliente);
                pedido.setData(rs.getDate("data_pedido").toLocalDate());
                pedido.setStatus(rs.getString("status"));

                rs.close();
                stmt.close();
                con.close();

                return pedido;
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
    public ArrayList<Pedido> consultarTodos() {
        String sql = "SELECT * FROM tb_pedidos";
        Connection con = ConectaDB.conectar();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                pedido.setCliente(cliente);
                pedido.setData(rs.getDate("data_pedido").toLocalDate());
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }

}
