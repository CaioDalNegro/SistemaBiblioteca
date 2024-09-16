package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	//atributos da classe usuario
	private String nome;
	private String numeroRegistro;
	private List<Livro> livrosEmprestados;//lista de livros emprestados
	
	//método construtor
	public Usuario(String nome, String numeroRegistro) {
		this.nome = nome;
		this.numeroRegistro = numeroRegistro;
		this.livrosEmprestados = new ArrayList<>();
	}
	
	//métodos get e set-------------------------------------->
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	//adicionar Livro Emprestado---------------------------->
	public void adicionarLivroEmprestado(Livro livro) {
		livrosEmprestados.add(livro);
	}
	
	//remover Livros emprestados---------------------------->
	public void removerLivroEmprestado(Livro livro) {
		livrosEmprestados.remove(livro);
	}
	
	//retorna lista de livros emprestados------------------->
	public List<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}
}
