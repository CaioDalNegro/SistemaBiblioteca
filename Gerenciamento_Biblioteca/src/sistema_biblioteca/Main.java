package sistema_biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scan = new Scanner(System.in);

        System.out.println("📚 Bem-Vindo à Biblioteca Memphis 📚");
        System.out.println("Por Caio Dal Negro e Cayque Lima");

        int menu = 0;
        do {
            System.out.println("<-------------------------------->");
            System.out.println(" ");
            System.out.println("0. Sair");
            System.out.println("1. Inserir Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Listar Livros Disponíveis");
            System.out.println("4. Cadastrar Usuário");
            System.out.println("5. Emprestar Livro");
            System.out.println("6. Devolver Livro");
            System.out.print("Digite uma opção: ");
            menu = scan.nextInt();
            scan.nextLine();

            if (menu == 1) { // Inserir Livro
                System.out.print("Digite o título do livro: ");
                String titulo = scan.nextLine();
                System.out.print("Digite o autor do livro: ");
                String autor = scan.nextLine();
                System.out.print("Digite o ISBN do livro: ");
                String isbn = scan.nextLine();
                Livro livro = new Livro(titulo, autor, isbn, true);
                biblioteca.cadastrarLivro(livro);
                System.out.println("Livro inserido com sucesso!");
            } else if (menu == 2) { // Remover Livro
                System.out.print("Digite o ISBN do livro a ser removido: ");
                String isbn = scan.nextLine();
                boolean removido = biblioteca.removerLivro(isbn);
                System.out.println(removido ? "Livro removido com sucesso!" : "Livro não encontrado!");
            } else if (menu == 3) { // Listar Livros Disponíveis
                System.out.println("Livros disponíveis:");
                biblioteca.exibirLivrosDisponiveis();
            } else if (menu == 4) { // Cadastrar Usuário
                System.out.print("Digite o nome do usuário: ");
                String nome = scan.nextLine();
                System.out.print("Digite o número de registro do usuário: ");
                String numeroRegistro = scan.nextLine();
                Usuario usuario = new Usuario(nome, numeroRegistro);
                biblioteca.cadastrarUsuario(usuario);
                System.out.println("Usuário cadastrado com sucesso!");
            } else if (menu == 5) { // Emprestar Livro
                System.out.print("Digite o ISBN do livro a ser emprestado: ");
                String isbn = scan.nextLine();
                System.out.print("Digite o número de registro do usuário: ");
                String numeroRegistro = scan.nextLine();
                biblioteca.emprestarLivro(isbn, numeroRegistro);
            } else if (menu == 6) { // Devolver Livro
                System.out.print("Digite o ISBN do livro a ser devolvido: ");
                String isbn = scan.nextLine();
                System.out.print("Digite o número de registro do usuário: ");
                String numeroRegistro = scan.nextLine();
                biblioteca.devolverLivro(isbn, numeroRegistro);
            } else if (menu != 0) { // Opção inválida
                System.out.println("Opção inválida!");
            }

        } while (menu != 0);

        scan.close();
    }
}
