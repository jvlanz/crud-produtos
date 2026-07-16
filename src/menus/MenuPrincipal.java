package menus;

import java.util.Scanner;

public class MenuPrincipal {

    private Scanner sc = new Scanner(System.in);

    private MenuProduto menuProduto = new MenuProduto();
    private MenuCliente menuCliente = new MenuCliente();

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1 - Produtos");
            System.out.println("2 - Clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    menuProduto.iniciar();
                    break;

                case 2:
                    menuCliente.iniciar();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");

            }

        } while (opcao != 0);

    }

}