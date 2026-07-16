package modelos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private String status;
    private ArrayList<Produto> listaProdutos;

    
    public Pedido() {
    }
    public Pedido(Cliente cliente, LocalDate data, String status, ArrayList<Produto> listaProdutos) {
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.listaProdutos = listaProdutos;
    }
    public Pedido(int id, Cliente cliente, LocalDate data, String status, ArrayList<Produto> listaProdutos) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.listaProdutos = listaProdutos;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }
    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    
}
