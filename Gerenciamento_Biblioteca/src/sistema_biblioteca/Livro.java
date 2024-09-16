package sistema_biblioteca;

public class Livro {
	
	//atributos da classe livro
	private String titulo;
	private String autor;
	private String isbn;
	private boolean disponibilidade;
	
	//método construtor de livro
	public Livro(String titulo, String autor, String isbn, boolean disponibilidade) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.disponibilidade = disponibilidade;
	}
	
	//métodos get e set--------------------------------------->
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
}
	