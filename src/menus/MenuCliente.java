package menus;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ClienteDao;
import modelos.Cliente;

public class MenuCliente {

    private ClienteDao dao = new ClienteDao();
    private Scanner sc = new Scanner(System.in);

    public void inserirCliente() {

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Número: ");
        int numeroRua = sc.nextInt();
        sc.nextLine();

        System.out.print("Bairro: ");
        String bairro = sc.nextLine();

        System.out.print("CEP: ");
        int cep = sc.nextInt();
        sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        Cliente cliente = new Cliente(cpf, nome, email, rua, numeroRua, bairro, cep, cidade, estado);

        dao.salvar(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void alterarCliente() {

        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Número: ");
        int numeroRua = sc.nextInt();
        sc.nextLine();

        System.out.print("Bairro: ");
        String bairro = sc.nextLine();

        System.out.print("CEP: ");
        int cep = sc.nextInt();
        sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        Cliente cliente = new Cliente(id, cpf, nome, email, rua, numeroRua, bairro, cep, cidade, estado);

        dao.alterar(cliente);

        System.out.println("Cliente alterado com sucesso!");
    }

    public void consultarCliente() {

        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = dao.consultar(id);

        if (cliente != null) {

            System.out.println("ID: " + cliente.getId());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Rua: " + cliente.getRua());
            System.out.println("Número: " + cliente.getNumeroRua());
            System.out.println("Bairro: " + cliente.getBairro());
            System.out.println("CEP: " + cliente.getCep());
            System.out.println("Cidade: " + cliente.getCidade());
            System.out.println("Estado: " + cliente.getEstado());

        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void listarClientes() {

        ArrayList<Cliente> lista = dao.consultarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n------ Lista de Clientes ------");

        for (Cliente cliente : lista) {

            System.out.println(
                    "ID: " + cliente.getId()
                            + " | CPF: " + cliente.getCpf()
                            + " | Nome: " + cliente.getNome()
                            + " | Cidade: " + cliente.getCidade()
                            + " | Estado: " + cliente.getEstado());
        }
    }

    public void deletarCliente() {

        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);

        System.out.println("Cliente removido com sucesso!");
    }

    public void mostrarMenu() {

        System.out.println("\n------ MENU CLIENTES ------");
        System.out.println("1 - Inserir cliente");
        System.out.println("2 - Alterar cliente");
        System.out.println("3 - Consultar cliente");
        System.out.println("4 - Listar todos os clientes");
        System.out.println("5 - Deletar cliente");
        System.out.println("0 - Voltar");
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
                    inserirCliente();
                    break;

                case 2:
                    alterarCliente();
                    break;

                case 3:
                    consultarCliente();
                    break;

                case 4:
                    listarClientes();
                    break;

                case 5:
                    deletarCliente();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}