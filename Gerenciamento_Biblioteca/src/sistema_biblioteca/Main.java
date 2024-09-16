package sistema_biblioteca;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Biblioteca biblioteca = new Biblioteca();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("📚Bem-Vindo à Biblioteca Memphis📚");
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

			//Inserir o livro------------------------------------------------------------------>
			if (menu == 1) {
				System.out.print("Digite o título do livro: ");//pede o nome do livro ao usuario
				String titulo = scan.nextLine();//Scanner lê o titulo
				System.out.print("Digite o autor do livro: ");//pede o nome do autor ao usuario
				String autor = scan.nextLine();//Scanner lê o autor
				System.out.print("Digite o ISBN do livro: ");
				String isbn = scan.nextLine();//Scanner lê o isbn
				Livro livro = new Livro(titulo, autor, isbn, true);//cria um objeto chamada livro
				biblioteca.cadastrarLivro(livro);//cadastra o livro
				System.out.println("Livro inserido com sucesso!");
				
			//Remover o livro------------------------------------------------------------------>
			} else if (menu == 2) {
				System.out.print("Digite o ISBN do livro a ser removido: ");
				String isbn = scan.nextLine();//Scanner lê o isbn
				boolean removido = biblioteca.removerLivro(isbn);//variavel chamada removido
				if (removido) {//se removido for verdadeiro
					System.out.println("Livro removido com sucesso!");
				} else {
					System.out.println("Livro não encontrado!");
				}
			
			//Listar Livros Disponiveis---------------------------------------------------------->
			} else if (menu == 3) {
				System.out.println("Livros disponíveis:");
				System.out.println(" ");
				biblioteca.exibirLivrosDisponiveis();
				
			//Cadastrar Usuario------------------------------------------------------------------->
			} else if (menu == 4) {
				System.out.print("Digite o nome do usuário: ");//pede o nome do usuario
				String nome = scan.nextLine();//Scan lê o nome
				
				System.out.print("Digite o número de registro do usuário: ");//pede o registro do usuario
				String numeroRegistro = scan.nextLine();//Scan lê o numero de registro
				
				Usuario usuario = new Usuario(nome, numeroRegistro);//cria um objeto com o nome de usuario
				biblioteca.cadastrarUsuario(usuario);//chama o método para cadastrar usuario
				System.out.println("Usuário cadastrado com sucesso!");
			
			//Emprestar Livros--------------------------------------------------------------------->
			} else if (menu == 5) {
				System.out.print("Digite o ISBN do livro a ser emprestado: ");
				String isbn = scan.nextLine();//Scan lê o isbn
				
				System.out.print("Digite o número de registro do usuário: ");
				String numeroRegistro = scan.nextLine();//Scan lê o numero de registro
				biblioteca.emprestarLivro(isbn, numeroRegistro);//chama o método para emprestar livro
				
			//Devolver Livro------------------------------------------------------------------------>
			} else if (menu == 6) {
				System.out.print("Digite o ISBN do livro a ser devolvido: ");
				String isbn = scan.nextLine();//Scan lê o isbn
				
				System.out.print("Digite o número de registro do usuário: ");
				String numeroRegistro = scan.nextLine();//Scan lê o numero de registro
				biblioteca.devolverLivro(isbn, numeroRegistro);//chama o método para emprestar livro
			}

		} while (menu != 0);

		scan.close();
	}
}
