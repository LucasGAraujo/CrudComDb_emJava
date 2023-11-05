package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import repository.Jogadoresrepository;
import dominio.Jogadores;

public class Main {
    private static int proximoId = 1;
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                Jogadoresrepository jogadorRepo = new Jogadoresrepository();
                System.out.println(jogadorRepo.listarJogadores());
                while (true) {
                    System.out.println("Escolha uma opção:");
                    System.out.println("1- Adicionar Jogador");
                    System.out.println("2- Excluir Jogador");
                    System.out.println("3- Atualizar Jogador");
                    System.out.println("4- Pesquisar Jogador");
                    System.out.println("0- Sair");
                    System.out.println("------------------------------------");

                    int Opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (Opcao) {
                        case 1:
                            adicionarJogador(scanner, jogadorRepo);
                            break;
                        case 2:
                            excluirJogador(scanner, jogadorRepo);
                            break;
                        case 3:
                            atualizarJogador(scanner, jogadorRepo);
                            break;
                        case 4:
                            pesquisarJogador(scanner, jogadorRepo);
                            break;
                        case 0:
                            System.out.println("Encerrando o programa.");
                            jogadorRepo.fecharConexao();
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }

            private static void adicionarJogador(Scanner scanner, Jogadoresrepository jogadorRepo) {
                System.out.println("Digite o nome do jogador: ");
                String nome = scanner.nextLine();
                Date dataNascimento = obterDataNascimento(scanner);
                System.out.println("Digite a posição: ");
                String posicao = scanner.nextLine();
                System.out.println("Digite o clube: ");
                String clube = scanner.nextLine();
                System.out.println("Digite a nacionalidade: ");
                String nacionalidade = scanner.nextLine();
                Jogadores jogador = new Jogadores(proximoId, nome, dataNascimento, posicao, clube, nacionalidade);
                jogadorRepo.createJogador(jogador);
                proximoId++;
            }

            private static void excluirJogador(Scanner scanner, Jogadoresrepository jogadorRepo) {
                System.out.println("Escolha o jogador para excluir (ID):");
                List<Jogadores> jogadoresList = jogadorRepo.listarJogadores();
                for (int i = 0; i < jogadoresList.size(); i++) {
                    String nomeDoJogador = jogadoresList.get(i).getNome();
                    Integer id = jogadoresList.get(i).getId();
                    System.out.println(id+" - " +nomeDoJogador);
                }
                System.out.println("Digite o ID do jogador que deseja excluir: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                jogadorRepo.deleteJogador(id);
            }
            private static void atualizarJogador(Scanner scanner, Jogadoresrepository jogadorRepo) {
                List<Jogadores> jogadoresList = jogadorRepo.listarJogadores();
                for (int i = 0; i < jogadoresList.size(); i++) {
                    String nomeDoJogador = jogadoresList.get(i).getNome();
                    Integer id = jogadoresList.get(i).getId();
                    System.out.println(id+" - " +nomeDoJogador);
                }
                System.out.println("Escolha o jogador para atualizar (ID - Nome):");
                System.out.println("Digite o ID do jogador que deseja atualizar: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Digite o novo nome do jogador: ");
                String novoNome = scanner.nextLine();
                Date novaDataNascimento = obterDataNascimento(scanner);
                System.out.println("Digite a nova posição: ");
                String novaPosicao = scanner.nextLine();
                System.out.println("Digite o novo clube: ");
                String novoClube = scanner.nextLine();
                System.out.println("Digite a nova nacionalidade: ");
                String novaNacionalidade = scanner.nextLine();

                Jogadores jogador = new Jogadores(id, novoNome, novaDataNascimento, novaPosicao, novoClube, novaNacionalidade);
                jogadorRepo.updateJogador(jogador);
            }

            private static void pesquisarJogador(Scanner scanner, Jogadoresrepository jogadorRepo) {
                System.out.println("Digite o nome do jogador que deseja pesquisar: ");
                String nome = scanner.nextLine();
                List<Jogadores> jogadores = jogadorRepo.pesquisarPorNome(nome);
                if (jogadores.isEmpty()) {
                    System.out.println("Nenhum jogador encontrado com o nome fornecido.");
                } else {
                    for (Jogadores jogador : jogadores) {
                        System.out.println("ID: " + jogador.getId());
                        System.out.println("Nome: " + jogador.getNome());
                        System.out.println("Data de Nascimento: " + jogador.getDataNascimento());
                        System.out.println("Posição: " + jogador.getPosicao());
                        System.out.println("Clube: " + jogador.getClube());
                        System.out.println("Nacionalidade: " + jogador.getNacionalidade());
                    }
                }
            }
            private static Date obterDataNascimento(Scanner scanner) {
                System.out.println("Digite a data de nascimento (yyyy-MM-dd): ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dataNascimento = null;
                try {
                    dataNascimento = dateFormat.parse(scanner.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return dataNascimento;
            }
        }
