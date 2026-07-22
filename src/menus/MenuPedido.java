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
    private ArrayList<ItemPedido> carrinho = new ArrayList<>();

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

    public void adicionaCarrinho() {

        System.out.println("ID do produto: ");
        int idProduto = sc.nextInt();
        Produto produto = produtoDao.consultar(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        ItemPedido item = new ItemPedido();
        item.setProduto(produto);
        carrinho.add(item);

        System.out.println("Produto adicionado ao carrinho!");
    }

    public void listarCarrinho() {

        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }
        System.out.println("\n------ CARRINHO ------");
        double total = 0;
        for (ItemPedido item : carrinho) {
            Produto produto = item.getProduto();
            double subtotal = produto.getPreco() * item.getQuantidade();
            total += subtotal;
            System.out.printf("%s | Quantidade: %d | Subtotal: R$ %.2f%n", produto.getDescricao(), item.getQuantidade(),
                    subtotal);
        }

        System.out.printf("Total: R$ %.2f%n", total);
    }

    public void removerCarrinho() {
        System.out.print("ID do produto para remover: ");
        int idProduto = sc.nextInt();
        sc.nextLine();
        for (ItemPedido item : carrinho) {
            if (item.getProduto().getId() == idProduto) {
                carrinho.remove(item);
                System.out.println("Produto removido do carrinho!");
                return;
            }
        }
        System.out.println("Produto não encontrado no carrinho.");
    }

    public void finalizarPedido() {

        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }
        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        LocalDate data = LocalDate.now();
        Pedido pedido = new Pedido(cliente, data, "Finalizado");
        // adiciona os produtos do carrinho no pedido
        for (ItemPedido item : carrinho) {
            pedido.getItens().add(item);
        }
        dao.salvar(pedido);
        carrinho.clear();
        System.out.println("Pedido finalizado com sucesso!");
    }

    public void mostrarMenu() {
        System.out.println("\n------ MENU PEDIDOS ------");
        System.out.println("1 - Adicionar produto ao carrinho");
        System.out.println("2 - Remover produto do carrinho");
        System.out.println("3 - Listar carrinho");
        System.out.println("4 - Finalizar pedido");
        System.out.println("5 - Consultar pedido");
        System.out.println("6 - Listar pedidos");
        System.out.println("7 - Listar itens do pedido");
        System.out.println("8 - Alterar pedido");
        System.out.println("9 - Deletar pedido");
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
                    adicionaCarrinho();
                    break;

                case 2:
                    removerCarrinho();
                    break;

                case 3:
                    listarCarrinho();
                    break;

                case 4:
                    finalizarPedido();
                    break;

                case 5:
                    consultarPedido();
                    break;

                case 6:
                    listarPedidos();
                    break;

                case 7:
                    listarItensPedido();
                    break;

                case 8:
                    alterarPedido();
                    break;

                case 9:
                    deletarPedido();
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