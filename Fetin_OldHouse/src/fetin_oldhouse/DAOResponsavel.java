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

/**
 *
 * @author igorg
 */
public class DAOResponsavel {

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    String url = "jdbc:mysql://localhost:3306/oldhouse?useSSL=false&useTimezone=true&serverTimezone=Brazil/East";
    String user = "root";
    String password = "root123";

    Responsavel respaux = new Responsavel();

    public void conectaBanco() {
        try {
            // Objeto que estabelece uma conexao com o Banco de Dados, usando a URL, usuario e senha.
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :(");
        }
    }

    public int retorna_id() {
        int aux = 0;                
       try {
            conectaBanco();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Responsavel");
            Responsavel resp = new Responsavel();
            while (rs.next()) {
                
                aux = rs.getInt(1);
                resp.setId_resp(rs.getInt(1));
            }
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
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
       return aux;
    }

    public void insere() {
        // Conecto com o Banco
        conectaBanco();
        try {
            // Preparo a insercao
            pst = con.prepareStatement("INSERT INTO Responsavel (nome,cpf,rg,cidade,estado,telefone,data_nasc,rua,numero,bairro) VALUES(?,?,?,?,?,?,?,?,?,?)");
            // Cada numero indica a posicao que o valor sera inserido nas ? acima
            pst.setString(1, respaux.getNome());
            pst.setString(2, respaux.getCpf());
            pst.setString(3, respaux.getRg());
            pst.setString(4, respaux.getCidade());
            pst.setString(5, respaux.getEstado());
            pst.setString(6, respaux.getTelefone());
            pst.setString(7, respaux.getData_nasc());
            pst.setString(8, respaux.getRua());
            pst.setString(9, respaux.getNumero());
            pst.setString(10, respaux.getBairro());

            // Executo a pesquisa
            pst.executeUpdate();
            System.out.println("Sucesso! ;)");
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :( ex.getMessage()" + ex.getMessage());
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

    public ArrayList<Responsavel> selecionaTodos() {
// Lista que recebera todos os registros desta tabela
        ArrayList<Responsavel> listaResponsaveis = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// O metodo createStatement() cria um objeto Statement que permite enviar comandos SQL para o banco.

            st = con.createStatement();
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = st.executeQuery("SELECT * FROM Responsavel");
            // SELECT * FROM usuario where login = ? and senha = ?;
            //   usu.(rs.getInt(1));
            //   usu.setLogin(rs.getString(2));
            //
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                Responsavel resp = new Responsavel();
// Guardo o seu id (primeira coluna na tabela)

                resp.setId_resp(rs.getInt(1));
                resp.setNome(rs.getString(2));
                resp.setCpf(rs.getString(3));
                resp.setRg(rs.getString(4));
                resp.setCidade(rs.getString(5));
                resp.setEstado(rs.getString(6));
                resp.setTelefone(rs.getString(7));
                resp.setData_nasc(rs.getString(8));
                resp.setRua(rs.getString(9));
                resp.setNumero(rs.getString(10));
                resp.setBairro(rs.getString(11));

// Adiciono na lista de Livros
                listaResponsaveis.add(resp);
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
        return listaResponsaveis;
    }

    /*    public List<Responsavel> selecionaPorAutor() {
// Lista que recebera todos os registros desta tabela
        List<Responsavel> listaResponsaveis = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// Preparo a consulta
            pst = con.prepareStatement(
                    "SELECT * FROM Responsavel WHERE nome id_resp in (SELECT id_idoso from nome where Nome like ?)");
// Indico que o primeiro ? significa o nome digitado pelo usuario
            pst.setString(1, "%" + nome + "%");
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = pst.executeQuery();
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                Responsavel resp = new Responsavel();
// Guardo o seu codigo (primeira coluna na tabela)
                resp.setId_resp(rs.getInt(1));
                resp.setNome(rs.getString(2));
                resp.setIdade(rs.getInt(3));
                resp.setCpf(rs.getString(4));
                resp.setRg(rs.getString(5));
                resp.setCidade(rs.getString(6));
                resp.setEstado(rs.getString(7));
                resp.setTelefone(rs.getString(8));
                resp.setData_nasc(rs.getString(9));
// Adiciono na lista de Autores
                listaIdosos.add(idoso);
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
        return listaIdosos;
    }
     */
    public void update() {
// Conecto com o Banco
        conectaBanco();
        try {
// Preparo a atualizacao
            pst = con.prepareStatement("UPDATE Responsavel SET nome = ?,cpf =?,rg = ?,cidade = ?,estado = ?,telefone = ?,data_nasc = ?,rua = ?, numero = ?,bairro = ? WHERE  id_resp =  ?");
// Indico que o primeiro ? significa o nome digitado pelo usuario
            pst.setString(1, respaux.getNome());
            pst.setString(2, respaux.getCpf());
            pst.setString(3, respaux.getRg());
            pst.setString(4, respaux.getCidade());
            pst.setString(5, respaux.getEstado());
            pst.setString(6, respaux.getTelefone());
            pst.setString(7, respaux.getData_nasc());
            pst.setString(8, respaux.getRua());
            pst.setString(9, respaux.getNumero());
            pst.setString(10, respaux.getBairro());

// Executo a atualizacao
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

    public void delete() {
// Conecto com o Banco
        conectaBanco();
        try {
// Preparo a exclusao
            pst = con.prepareStatement("DELETE FROM Responsavel WHERE id_resp = ?");
// Indico que a ? significa o Codigo do Autor
            pst.setInt(1, respaux.getId_resp());
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
