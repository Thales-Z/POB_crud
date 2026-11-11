package View;

import controle.ControlaPessoa;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        ControlaPessoa controle = new ControlaPessoa();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Alterar Pessoa");
            System.out.println("4. Excluir Pessoa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();

                    System.out.print("Altura: ");
                    double altura = scanner.nextDouble();
                    scanner.nextLine();

                    controle.adicionarPessoa(id, nome, idade, altura);
                    break;

                case 2:
                    controle.listarPessoas();
                    break;

                case 3:
                    System.out.print("ID da Pessoa a alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();

                    controle.alterarPessoa(idAlterar, scanner);
                    break;

                case 4:
                    System.out.print("ID da Pessoa a excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    controle.excluirPessoa(idExcluir);
                    break;

                case 0:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}