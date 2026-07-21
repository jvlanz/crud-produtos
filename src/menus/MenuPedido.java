package menus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ItemPedidoDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelos.Cliente;
import modelos.ItemPedido;
import modelos.Pedido;
import modelos.Produto;

public class MenuPedido {

    private PedidoDao dao = new PedidoDao();
    private Scanner sc = new Scanner(System.in);
    private ProdutoDao produtoDao = new ProdutoDao();
    private ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

    public void inserirPedido() {

        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = new Cliente();
        cliente.setId(idCliente);

        LocalDate data = LocalDate.now();

        System.out.print("Status do pedido: ");
        String status = sc.nextLine();

        Pedido pedido = new Pedido(cliente, data, status);

        String resposta = null;

        do {

            System.out.print("ID do produto: ");
            int idProduto = sc.nextInt();

            Produto produto = produtoDao.consultar(idProduto);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(quantidade);

            pedido.getItens().add(item);

            System.out.print("Deseja adicionar outro produto? (S/N): ");
            resposta = sc.nextLine();

        } while (resposta.equalsIgnoreCase("S"));

        dao.salvar(pedido);

        System.out.println("Pedido cadastrado com sucesso!");
    }

    public void alterarPedido() {

        System.out.print("ID do pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = new Cliente();
        cliente.setId(idCliente);

        LocalDate data = LocalDate.now();

        System.out.print("Novo status: ");
        String status = sc.nextLine();

        Pedido pedido = new Pedido(id, cliente, data, status);

        dao.alterar(pedido);

        System.out.println("Pedido alterado com sucesso!");
    }

    public void consultarPedido() {

        System.out.print("ID do pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        Pedido pedido = dao.consultar(id);

        if (pedido != null) {

            System.out.println("\n------ Pedido ------");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getId());
            System.out.println("Data: " + pedido.getData());
            System.out.println("Status: " + pedido.getStatus());

        } else {

            System.out.println("Pedido não encontrado.");

        }
    }

    public void listarPedidos() {

        ArrayList<Pedido> lista = dao.consultarTodos();

        if (lista.isEmpty()) {

            System.out.println("Nenhum pedido cadastrado.");
            return;

        }

        System.out.println("\n------ Lista de Pedidos ------");

        for (Pedido pedido : lista) {

            System.out.println(
                    "ID: " + pedido.getId()
                            + " | Cliente: " + pedido.getCliente().getId()
                            + " | Data: " + pedido.getData()
                            + " | Status: " + pedido.getStatus());

        }
    }

    public void deletarPedido() {

        System.out.print("ID do pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);

        System.out.println("Pedido removido com sucesso!");

    }

    public void listarItensPedido() {

        System.out.print("ID do pedido: ");
        int idPedido = sc.nextInt();
        sc.nextLine();

        ArrayList<ItemPedido> itens = itemPedidoDao.consultarItensPedido(idPedido);

        if (itens.isEmpty()) {
            System.out.println("Esse pedido não possui itens.");
            return;
        }

        System.out.println("\n------ ITENS DO PEDIDO ------");

        for (ItemPedido item : itens) {

            Produto produto = produtoDao.consultar(item.getProduto().getId());

            System.out.printf(
                    "Produto: %s | Quantidade: %d | Preço: R$ %.2f%n",
                    produto.getDescricao(),
                    item.getQuantidade(),
                    produto.getPreco());

        }
    }

    public void mostrarMenu() {

        System.out.println("\n------ MENU PEDIDOS ------");
        System.out.println("1 - Inserir pedido");
        System.out.println("2 - Alterar pedido");
        System.out.println("3 - Consultar pedido");
        System.out.println("4 - Listar pedidos");
        System.out.println("5 - Deletar pedido");
        System.out.println("6 - Listar produtos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

    }

    public void iniciar() {

        int opcao;

        do {

            mostrarMenu();

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    inserirPedido();
                    break;

                case 2:
                    alterarPedido();
                    break;

                case 3:
                    consultarPedido();
                    break;

                case 4:
                    listarPedidos();
                    break;

                case 5:
                    deletarPedido();
                    break;
                case 6:
                    listarItensPedido();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");

            }

        } while (opcao != 0);

    }
}