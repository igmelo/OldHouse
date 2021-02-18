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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author igorg
 */
public class DAOCheck {
    
    
    public static DAOCheck getInstance() {
        return DAOCheckHolder.INSTANCE;
    }
    
    private static class DAOCheckHolder {

        private static final DAOCheck INSTANCE = new DAOCheck();
    }
    
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    String url = "jdbc:mysql://localhost:3306/oldhouse?useSSL=false&useTimezone=true&serverTimezone=Brazil/East";
    String user = "root";
    String password = "root123";

    
    public void conectaBanco() {
        try {
            // Objeto que estabelece uma conexao com o Banco de Dados, usando a URL, usuario e senha.
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :("+ex.getMessage());
        }
    }

    public void insere(Check check) {
        // Conecto com o Banco
        
        String aux = "naosei";
        conectaBanco();
        try {
            // Preparo a insercao
            pst = con.prepareStatement("INSERT INTO Checar (tag, tempo) VALUES (?,?)");
            // Cada numero indica a posicao que o valor sera inserido nas ? acima
            pst.setString(1, check.getTag());
            pst.setTimestamp(2, Timestamp.valueOf(check.getTime()));
            // Executo a pesquisa
            pst.executeUpdate();
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("ERRO INSERIR");
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
    
    public ArrayList<Check> selecionaTodos() {
// Lista que recebera todos os registros desta tabela
        ArrayList<Check> listaCheck = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// O metodo createStatement() cria um objeto Statement que permite enviar comandos SQL para o banco.

            st = con.createStatement();
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = st.executeQuery("SELECT * FROM Checar");
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                Check check = new Check(rs.getInt(1),rs.getString(2), rs.getTimestamp(3).toLocalDateTime());
// Guardo o seu id (primeira coluna na tabela)
                listaCheck.add(check);
// Adiciono na lista de Livros
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
        return listaCheck;
    }
}
