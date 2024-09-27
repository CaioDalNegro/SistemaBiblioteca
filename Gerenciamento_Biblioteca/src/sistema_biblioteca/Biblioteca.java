package sistema_biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private List<Livro> livros; // lista de livros
	private List<Usuario> usuarios; // lista de usuários

	// Método construtor
	public Biblioteca() {
		livros = new ArrayList<>();
		usuarios = new ArrayList<>();
	}

	// Cadastrar livros
	public void cadastrarLivro(Livro livro) {
		livros.add(livro);
	}

	// Remover livro
	public boolean removerLivro(String isbn) {
		Livro livro = encontrarLivro(isbn);
		if (livro != null) {
			livros.remove(livro);
			return true;
		}
		return false;
	}

	// Encontrar livro
	public Livro encontrarLivro(String isbn) {
		for (Livro livro : livros) {
			if (livro.getIsbn().equals(isbn)) {
				return livro;
			}
		}
		return null;
	}

	// Exibir Livros Disponíveis
	public void exibirLivrosDisponiveis() {
		for (Livro livro : livros) {
			if (livro.isDisponibilidade()) {
				System.out.println(
						"Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn());
			}
		}
	}

	// Cadastrar Usuários no csv
	public void cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		String pathUsuarios = "C:\\Users\\Aluno\\Documents\\SistemaBiblioteca\\Gerenciamento_Biblioteca\\Arquivos_CSV\\Usuarios.csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathUsuarios, true))) {
	            bw.write(usuario.getNome() + "," + usuario.getNumeroRegistro());//
	            bw.newLine();
	        System.out.println("Usuários salvos com sucesso no CSV.");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao salvar usuários.");
	    }

	}
	
	 // Método para registrar livro no CSV
    public void registrarLivroNoCSV(Livro livro) {
    	
    	String pathLivros = ("C:\\Users\\Aluno\\Documents\\SistemaBiblioteca\\Gerenciamento_Biblioteca\\Arquivos_CSV\\Livro.csv");
    	
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLivros, true))) {
            bw.write(livro.getTitulo() + " ," + livro.getAutor() + " ," + livro.getIsbn() + " ," + livro.isDisponibilidade());
            bw.newLine();
        System.out.println("Livro Adicionado com sucesso");
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erro ao adcionar os livros.");
    }
    }

	// Encontrar usuário
	public Usuario encontrarUsuario(String numeroRegistro) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNumeroRegistro().equals(numeroRegistro)) {
				return usuario;
			}
		}
		return null;
	}

	// Emprestar Livro
	public void emprestarLivro(String isbn, String numeroRegistro) {
		Livro livro = encontrarLivro(isbn);
		Usuario usuario = encontrarUsuario(numeroRegistro);
		String pathEmprestimos = "C:\\Users\\Aluno\\Documents\\SistemaBiblioteca\\Gerenciamento_Biblioteca\\Arquivos_CSV\\Emprestimos.csv";

		// se livro for diferente ou igual a null e livro
		if (livro != null && livro.isDisponibilidade()) {
			livro.setDisponibilidade(false);// muda a disponibilidade para false
			usuario.adicionarLivroEmprestado(livro);
			System.out.println("Livro emprestado com sucesso!");

			// Insere os dados em um CSV de empréstimos
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathEmprestimos, true))) {
				bw.write(usuario.getNumeroRegistro() + "," + livro.getIsbn());
				bw.newLine();// pula de linha
				System.out.println("Empréstimo registrado com sucesso.");// exibe msg
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erro ao realizar o empréstimo.");
			}
		} else {
			System.out.println("O livro não está disponível.");
		}
	}
	
	// Devolver livro
	public void devolverLivro(String isbn, String numeroRegistro) {
		Livro livro = encontrarLivro(isbn);
		Usuario usuario = encontrarUsuario(numeroRegistro);

		if (livro != null && !livro.isDisponibilidade()) {
			livro.setDisponibilidade(true);
			usuario.removerLivroEmprestado(livro);
			System.out.println("Livro devolvido com sucesso!");
		} else {
			System.out.println("O livro já está disponível ou não foi encontrado.");
		}
	}
}
