/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igorg
 */
public class DAOUsuario {
    
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    String url = "jdbc:mysql://localhost:3306/oldhouse?useSSL=false&useTimezone=true&serverTimezone=Brazil/East";
    String user = "root";
    String password = "root123";

    Usuario usaux = new Usuario();
    
    public void conectaBanco() {
        try {
            // Objeto que estabelece uma conexao com o Banco de Dados, usando a URL, usuario e senha.
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :("+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insere() {
        // Conecto com o Banco
        conectaBanco();
        try {
            // Preparo a insercao
            pst = con.prepareStatement("INSERT INTO usuario (login, nome, senha) VALUES(?,?,?)");
            // Cada numero indica a posicao que o valor sera inserido nas ? acima
            pst.setString(1, usaux.getLogin());
            pst.setString(2, usaux.getNome());
            pst.setString(3, usaux.getSenha());
            // Executo a pesquisa
            pst.executeUpdate();
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :("+ex.getMessage());
        } finally {
            // Independente se a conexao deu certo ou errado, fecha as conexoes pendentes
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro: Conexão não pode ser fechada! :(");
            }
        }
    }
    
    public ArrayList<Usuario> selecionaTodos() {
// Lista que recebera todos os registros desta tabela
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// O metodo createStatement() cria um objeto Statement que permite enviar comandos SQL para o banco.

            st = con.createStatement();
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = st.executeQuery("SELECT * FROM Usuario");
            // SELECT * FROM usuario where login = ? and senha = ?;
            //   usu.(rs.getInt(1));
            //   usu.setLogin(rs.getString(2));
            //
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                 Usuario usu = new Usuario();
// Guardo o seu id (primeira coluna na tabela)
                usu.setIdusuario(rs.getInt(1));
                usu.setLogin(rs.getString(2));
                usu.setNome(rs.getString(3));
                usu.setSenha(rs.getString(4));
                
// Adiciono na lista de Livros
                listaUsuarios.add(usu);
            }
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
// Independente se a conexao deu certo ou errado, fecha as conexoes pendentes
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro: Conexão não pode ser fechada! :(");
            }
        }
        return listaUsuarios;
    }
    
    public void delete(String nome) {
// Conecto com o Banco
        conectaBanco();
        try {
// Preparo a exclusao
            pst = con.prepareStatement("DELETE FROM Usuario WHERE nome = ?");
// Indico que a ? significa o Codigo do Autor
            pst.setString(1, nome);
// Executo a exclusao
            pst.executeUpdate();
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
// Independente se a conexao deu certo ou errado, fecha as conexoes pendentes
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro: Conexão não pode ser fechada! :(");
            }
        }
    }
}
