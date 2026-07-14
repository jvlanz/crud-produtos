package modelos;

import java.util.Scanner;

import dao.ProdutoDao;

public class Menu {
    private ProdutoDao dao = new ProdutoDao();
    private Scanner sc = new Scanner(System.in);

    public void inserirProduto() {
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        Produto p = new Produto(descricao, preco);
        dao.salvar(p);

        System.out.println("Produto cadastrado!");
    }

    public void alterarProduto() {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Novo preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        Produto p = new Produto(id, descricao, preco);
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
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void listarProdutos() {
        // código para listar
    }

    public void deletarProduto() {
        // código para deletar
    }

    public void mostrarMenu() {
        System.out.println("Bem vindo ao meu CRUD");
        System.out.println("1 - inserir um produto");
        System.out.println("2 - alterar um produto");
        System.out.println("3 - consultar um produto");
        System.out.println("4 - listar todos os produtos");
        System.out.println("5 - deletar um produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}
