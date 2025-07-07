import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MapaCidades mapa = new MapaCidades();
        Scanner scanner = new Scanner(System.in);

        cadastrarCidadesIniciais(mapa);

        while (true) {
            System.out.println("\nSISTEMA DE CIDADES E ROTAS");
            System.out.println("1. Cadastrar nova cidade");
            System.out.println("2. Criar nova rota entre cidades");
            System.out.println("3. Listar todas as cidades");
            System.out.println("4. Ver conexões de uma cidade");
            System.out.println("5. Verificar conexão entre cidades");
            System.out.println("6. Listar cidades sem conexão");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarNovaCidade(scanner, mapa);
                        break;
                    case 2:
                        criarNovaRota(scanner, mapa);
                        break;
                    case 3:
                        listarTodasCidades(mapa);
                        break;
                    case 4:
                        listarConexoesCidade(scanner, mapa);
                        break;
                    case 5:
                        verificarConexaoCidades(scanner, mapa);
                        break;
                    case 6:
                        mapa.listarCidadesSemConexao();
                        break;
                    case 0:
                        System.out.println("Saindo");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarCidadesIniciais(MapaCidades mapa) {
        Cidade[] cidades = {
            new Cidade("São Sebastião do Paraíso", "MG", 70000),
            new Cidade("Itamogi", "MG", 10700),
            new Cidade("Monte Santo de Minas", "MG", 20800),
            new Cidade("São Tomás de Aquino", "MG", 6740),
            new Cidade("Carrancas", "MG", 5000)
        };

        for (Cidade cidade : cidades) {
            mapa.adicionarCidade(cidade);
        }
    }

    private static void cadastrarNovaCidade(Scanner scanner, MapaCidades mapa) {
        System.out.println("\n CADASTRAR NOVA CIDADE ");
        
        System.out.print("Nome da cidade: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine().trim();
        
        System.out.print("População: ");
        int populacao = Integer.parseInt(scanner.nextLine());
        
        try {
            Cidade novaCidade = new Cidade(nome, estado, populacao);
            mapa.adicionarCidade(novaCidade);
            System.out.println("Cidade cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cidade: " + e.getMessage());
        }
    }

    private static void criarNovaRota(Scanner scanner, MapaCidades mapa) {
        System.out.println("\n=== CRIAR NOVA ROTA ===");
        
        if (mapa.getCidades().size() < 2) {
            System.out.println("É necessário ter pelo menos 2 cidades cadastradas");
            return;
        }
        
        System.out.println("Cidades disponíveis:");
        mapa.getCidades().forEach(c -> System.out.println("- " + c.getNome()));
        
        System.out.print("\nDigite o nome da cidade origem: ");
        String nomeOrigem = scanner.nextLine().trim();
        
        System.out.print("Digite o nome da cidade destino: ");
        String nomeDestino = scanner.nextLine().trim();
        
        System.out.print("Distância em km: ");
        int distancia = Integer.parseInt(scanner.nextLine());
        
        try {
            Cidade origem = encontrarCidadePorNome(nomeOrigem, mapa);
            Cidade destino = encontrarCidadePorNome(nomeDestino, mapa);
            
            if (origem != null && destino != null) {
                mapa.conectarCidades(origem, destino, distancia);
                System.out.println("Rota criada com sucesso entre " + origem.getNome() + " e " + destino.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar rota: " + e.getMessage());
        }
    }

    private static void listarTodasCidades(MapaCidades mapa) {
        System.out.println("\n LISTA DE CIDADES ");
        mapa.getCidades().forEach(c -> {
            System.out.println("\n" + c);
            System.out.println("Total de conexões: " + 
                mapa.getConexoes(c).size());
        });
    }

    private static void listarConexoesCidade(Scanner scanner, MapaCidades mapa) {
        System.out.println("\n CONEXÕES DA CIDADE ");
        System.out.print("Digite o nome da cidade: ");
        String nomeCidade = scanner.nextLine().trim();
        
        Cidade cidade = encontrarCidadePorNome(nomeCidade, mapa);
        if (cidade != null) {
            mapa.listarConexoes(cidade);
        }
    }

    private static void verificarConexaoCidades(Scanner scanner, MapaCidades mapa) {
        System.out.println("\n VERIFICAR CONEXÃO ");
        System.out.print("Digite o nome da cidade origem: ");
        String nomeOrigem = scanner.nextLine().trim();
        
        System.out.print("Digite o nome da cidade destino: ");
        String nomeDestino = scanner.nextLine().trim();
        
        Cidade origem = encontrarCidadePorNome(nomeOrigem, mapa);
        Cidade destino = encontrarCidadePorNome(nomeDestino, mapa);
        
        if (origem != null && destino != null) {
            boolean conectadas = mapa.existeCaminho(origem, destino);
            System.out.println("\nResultado: " + 
                (conectadas ? "EXISTE caminho" : "NÃO existe caminho") + 
                " entre " + origem.getNome() + " e " + destino.getNome());
        }
    }

    private static Cidade encontrarCidadePorNome(String nome, MapaCidades mapa) {
        return mapa.getCidades().stream() //Converte para Stream
                  .filter(c -> c.getNome().equalsIgnoreCase(nome)) //filtrar cidades 
                  .findFirst() //pega a primeira 
                  .orElseGet(() -> { //trata caso n encontre a cidade
                      System.out.println("Cidade não encontrada: " + nome);
                      return null;
                  });
    }
}