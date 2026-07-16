package menus;

import java.util.ArrayList;

import java.util.Scanner;

import dao.ProdutoDao;
import modelos.Produto;

public class MenuProduto {
    private ProdutoDao dao = new ProdutoDao();
    private Scanner sc = new Scanner(System.in);

    public void inserirProduto() {
        System.out.print("Descrição: ");
        String descricao = sc.next();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        System.out.print("Estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Produto p = new Produto(descricao, preco, estoque);
        dao.salvar(p);

        System.out.println("Produto cadastrado!");
    }

    public void alterarProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = sc.next();
        System.out.print("Novo preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();
        System.out.print("Novo estoque: ");
        int estoque = sc.nextInt();

        Produto p = new Produto(id, descricao, preco, estoque);
        dao.alterar(p);
        System.out.println("Produto alterado");
    }

    public void consultarProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Produto produto = dao.consultar(id);

        if (produto != null) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Estoque: " + produto.getEstoque());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void listarProdutos() {
        ArrayList<Produto> lista = dao.consultarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("---- Lista de Produtos ----");
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId()
                    + " | Descrição: " + p.getDescricao()
                    + " | Preço: R$ " + p.getPreco()
                    + " | Estoque: " + p.getEstoque());
        }
    }

    public void deletarProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);
    }

    public void mostrarMenu() {
        System.out.println("\n---- Bem vindo ao meu CRUD ----");
        System.out.println("1 - inserir um produto");
        System.out.println("2 - alterar um produto");
        System.out.println("3 - consultar um produto");
        System.out.println("4 - listar todos os produtos");
        System.out.println("5 - deletar um produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }


    public void iniciar() {
        int opcao;

        do {
            mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    inserirProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    consultarProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    deletarProduto();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}