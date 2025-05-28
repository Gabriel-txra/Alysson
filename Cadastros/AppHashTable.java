
import java.util.Hashtable;
import java.util.Scanner;

public class AppHashTable {
    private static Scanner input = new Scanner(System.in);
    private static Hashtable <String, Aluno> listaAlunos = new Hashtable <>(); // aqui voce define a chave e o valor ()
    public static int menu(){
        System.out.println("1 -Cadastrar");
        System.out.println("2- Listar todos");
        System.out.println("3- Buscar pela chave");
        System.out.println("0 - Sair");
        System.out.println("Escolha: ");
        int op = input.nextInt(); //limpar buffer
        input.nextLine();
        return op;
    }
    public static void cadastrar() {
        System.out.println("---Cadastro---");
        System.out.println("Matricula: ");
        String matricula = input.nextLine();
        if (listaAlunos.contains(matricula)){
            System.out.println("Matricula ja cadastrada!");
            return; // paro a execução do metodo
        }
        System.out.println("Nome: ");
        String Nome = input.nextLine();
        System.out.println("Curso: ");
        String Curso = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Matricula");

        Aluno aluno = new Aluno(Nome, Curso, matricula, email);
        
        listaAlunos.put(matricula, aluno); // quando eu altero o objeto para aluno ao inves de String, colocar aluno ele ja me tras todas as informações
        

    }
    public static String buscar(){
        System.out.println("---Busca---");
        System.out.println("Matricula: ");
        String matricula = input.nextLine();  
        if (listaAlunos.containsKey(matricula)){
            Aluno aluno = listaAlunos.get(matricula);
            System.out.println("Aluno encontrado: "+ aluno);
        }else{
        System.out.println("Matricula" + matricula + " nao encontrada!");
        }

        return null;

    }

    public static void main(String[] args) {
        int opcao = 0;
        do{
            opcao = menu();
            switch(opcao){
                case 1:
                    cadastrar();
                    break;
                case 2:
                    System.out.println(listaAlunos);
                    break;
                case 3:
                    buscar();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;//
                default:
                System.out.println("Opção inválida!");
            }
        }while(opcao!=0);
        
    }
}
