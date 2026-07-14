package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Produto;
import utils.ConectaDB;

public class ProdutoDao implements ICRUD {

    @Override
    public Produto salvar(Produto prod) {
        String sql = "INSERT INTO tb_produtos(descricao, preco)values(?,?)";

        Connection con = ConectaDB.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, prod.getDescricao());
            stm.setDouble(2, prod.getPreco());
            stm.execute();

            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                prod.setId(rs.getInt(1));
            }

            stm.close();
            con.close();
            rs.close();

            return prod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM tb_produtos WHERE id=?";
        try {
            Connection con = ConectaDB.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.execute();

            stmt.close();
            con.close();

            System.out.println("Produto removido!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void alterar(Produto prod) {
        String sql = "UPDATE tb_produtos SET descricao=?, preco=? WHERE id=?";
        Connection con = ConectaDB.conectar();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, prod.getDescricao());
            stmt.setDouble(2, prod.getPreco());
            stmt.setInt(3, prod.getId());

            stmt.execute();

            stmt.close();
            con.close();

            System.out.println("Atualizado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Produto consultar(int id) {
        Produto produto = null;
        Connection con = ConectaDB.conectar();
        String sql = "SELECT * FROM tb_produtos WHERE id = ?";
        
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                rs.getInt(1);
                rs.getString(2);
                rs.getDouble(3);
                produto = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public ArrayList<Produto> consultarTodos() {
        List<Produto> produtos = new ArrayList<>();
        Connection con = ConectaDB.conectar();
        String sql = "SELECT * FROM tb_produtos";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                rs.getInt(1);
                rs.getString(2);
                rs.getDouble(3);
                Produto p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                produtos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(produtos);
    }

}
