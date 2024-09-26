package sistema_biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

	private List<Livro> livros;//lista de livros
	private List<Usuario> usuarios;//lista de usuarios
	
	//método construtor------------------------------>
	public Biblioteca() {
		livros = new ArrayList<>();
		usuarios = new ArrayList<>();
	}
	
	//cadastrar livros-------------------------------->
	public void cadastrarLivro(Livro livro) {
		livros.add(livro);//adiciona livro
	}
	
	//remover livro------------------------------------>
	public boolean removerLivro(String isbn) {
		Livro livro = encontrarLivro(isbn);
		if (livro != null) {
			livros.remove(livro);
			return true;
		}
		return false;
	}
	
	//Encontrar livro----------------------------------->
	public Livro encontrarLivro(String isbn) {
		for (Livro livro : livros) {//itera livro
			if (livro.getIsbn().equals(isbn)) {//se livro igual ao isbn
				return livro;//retorna livro
			}
		}
		return null;
	}
	
	//Exibir Livros Disponiveis-------------------------->
	public void exibirLivrosDisponiveis() {
		for (Livro livro : livros) {//itera livro
			if (livro.isDisponibilidade()) {//se livro estiver disponivel exibe a lista
				System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn());
			}
		}
	}
	
	//Cadastrar Usuarios---------------------------------->
	public void cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);//adiciona o usuario
	}
	
	//Encontrar usuario----------------------------------->
	public Usuario encontrarUsuario(String numeroRegistro) {
		for (Usuario usuario : usuarios) {//itera usuario
			if (usuario.getNumeroRegistro().equals(numeroRegistro)) {//buscar de acordo com o numero de registro
				return usuario;//retorna o usuario
			}
		}
		return null;
	}
	
	//Emprestar Livro-------------------------------------->
	public void emprestarLivro(String isbn, String numeroRegistro) {
		Livro livro = encontrarLivro(isbn);//encontra livro pelo isbn
		Usuario usuario = encontrarUsuario(numeroRegistro);//encontra usuario pelo numero de registro
		String path = "C:\\Users\\Aluno\\Documents\\SistemaBiblioteca\\Gerenciamento_Biblioteca\\Arquivos_CSV\\Emprestimos.csv";
		
			if (livro != null && livro.isDisponibilidade()) {//se livro estiver disponivel ou diferente de null
				livro.setDisponibilidade(false);//muda disponivel para false(emprestado)
				usuario.adicionarLivroEmprestado(livro);//adiciona livro
				System.out.println("Livro emprestado com sucesso!");
			} else {
				System.out.println("O livro não está disponível.");
			}
			
			//insere os dados em um CSV
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
				bw.write(numeroRegistro + "," + isbn);
				bw.newLine();
				System.out.println("Não Houve nenhum erro ao realizar o emprestimo");
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erro para realizar o emprestimo");
			}

	}
	
	//devolver livro--------------------------------------->
	public void devolverLivro(String isbn, String numeroRegistro) {
		Livro livro = encontrarLivro(isbn);//livro busca pelo isbn
		Usuario usuario = encontrarUsuario(numeroRegistro);//usuario busca pelo numero de registro

			if (livro != null && livro.isDisponibilidade()) {//se livro for diferente de disponivel
				livro.setDisponibilidade(true);//define como true(disponivel)
				usuario.removerLivroEmprestado(livro);//remove livro do usuario
				System.out.println("Livro devolvido com sucesso!");
			} else {
				System.out.println("O livro já está disponível.");
			}	
	}
	
}
