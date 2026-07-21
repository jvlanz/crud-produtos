package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.ItemPedido;
import modelos.Produto;
import utils.ConectaDB;

public class ItemPedidoDao {

    public void salvar(ItemPedido item, int idPedido) {

        String sql = "INSERT INTO tb_itens_pedido(id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";

        Connection con = ConectaDB.conectar();

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ItemPedido> consultarItensPedido(int idPedido) {
        String sql = "SELECT * FROM tb_itens_pedido WHERE id_pedido = ?";
        Connection con = ConectaDB.conectar();
        ArrayList<ItemPedido> itens = new ArrayList<>();

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setId(rs.getInt("id"));
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));

                item.setProduto(produto);
                item.setQuantidade(rs.getInt("quantidade"));

                itens.add(item);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itens;
    }

    public void deletarItensPedido(int idPedido) {

        String sql = "DELETE FROM tb_itens_pedido WHERE id_pedido = ?";

        Connection con = ConectaDB.conectar();

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}