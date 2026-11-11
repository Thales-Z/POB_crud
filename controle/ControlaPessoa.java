package controle;

import dominio.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlaPessoa {

    private ArrayList<Pessoa> pessoas;
    private final String arquivoPessoas = "pessoa.csv";

    public ControlaPessoa() {
        this.pessoas = new ArrayList<>();
        carregarPessoas();
    }

    public void adicionarPessoa(int id, String nome, int idade, double altura) {
        Pessoa pessoa = new Pessoa(id, nome, idade, altura);
        pessoas.add(pessoa);
        salvarPessoas();
        System.out.println("Pessoa adicionada com sucesso!");
    }

    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("\nLista de Pessoas:");
            for (Pessoa pessoa : pessoas) {
                pessoa.listarPessoa();
            }
        }
    }

    public void alterarPessoa(int id, Scanner scanner) {
        Pessoa pessoa = buscarPessoaPorId(id);

        if (pessoa != null) {
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();

            System.out.print("Nova idade: ");
            int novaIdade = scanner.nextInt();

            System.out.print("Nova altura: ");
            double novaAltura = scanner.nextDouble();
            scanner.nextLine();

            pessoa.setNome(novoNome);
            pessoa.setIdade(novaIdade);
            pessoa.setAltura(novaAltura);

            salvarPessoas();
            System.out.println("Pessoa alterada com sucesso!");
        } else {
            System.out.println("Pessoa com ID " + id + " não encontrada.");
        }
    }

    public void excluirPessoa(int id) {
        Pessoa pessoa = buscarPessoaPorId(id);

        if (pessoa != null) {
            pessoas.remove(pessoa);
            salvarPessoas();
            System.out.println("Pessoa excluída com sucesso!");
        } else {
            System.out.println("Pessoa com ID " + id + " não encontrada.");
        }
    }

    private Pessoa buscarPessoaPorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    private void salvarPessoas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPessoas))) {
            for (Pessoa pessoa : pessoas) {
                writer.write(pessoa.getId() + ";" + pessoa.getNome() + ";" + pessoa.getIdade() + ";" + pessoa.getAltura());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pessoas: " + e.getMessage());
        }
    }

    private void carregarPessoas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPessoas))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                double altura = Double.parseDouble(dados[3]);

                pessoas.add(new Pessoa(id, nome, idade, altura));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo pessoa.csv não encontrado. Será criado ao salvar novos registros.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar pessoas: " + e.getMessage());
        }
    }
}