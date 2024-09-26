package sistema_biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn());
            }
        }
    }

    // Cadastrar Usuários
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
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
        String pathUsuarios = "C:\\Users\\Aluno\\Documents\\SistemaBiblioteca\\Gerenciamento_Biblioteca\\Arquivos_CSV\\Usuarios.csv";

        if (livro != null && livro.isDisponibilidade()) {
            livro.setDisponibilidade(false);
            usuario.adicionarLivroEmprestado(livro);
            System.out.println("Livro emprestado com sucesso!");

            // Insere os dados em um CSV de empréstimos
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathEmprestimos, true))) {
                bw.write(numeroRegistro + "," + isbn);
                bw.newLine();
                System.out.println("Empréstimo registrado com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao realizar o empréstimo.");
            }

            // Adiciona o usuário ao arquivo de usuários, se não estiver presente
            adicionarUsuarioAoCSV(usuario, pathUsuarios);
        } else {
            System.out.println("O livro não está disponível.");
        }
    }

    // Adicionar Usuário ao CSV
    private void adicionarUsuarioAoCSV(Usuario usuario, String path) {
        boolean usuarioExistente = false;

        // Verifica se o usuário já está no arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[1].equals(usuario.getNumeroRegistro())) {
                    usuarioExistente = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se o usuário não existir, adiciona
        if (!usuarioExistente) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
                // Adiciona o nome e o número de registro na nova linha
                bw.write(usuario.getNome() + "," + usuario.getNumeroRegistro());
                bw.newLine(); // Garante que a próxima entrada vai para uma nova linha
                System.out.println("Usuário adicionado ao arquivo com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao adicionar usuário ao arquivo.");
            }
        } else {
            System.out.println("Usuário já está cadastrado no arquivo.");
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
