/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igorg
 */
public class DAOIdoso {

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    String url = "jdbc:mysql://localhost:3306/oldhouse?useSSL=false&useTimezone=true&serverTimezone=Brazil/East";
    String user = "root";
    String password = "root123";

    Idoso idoaux = new Idoso();
    
    public void conectaBanco() {
        try {
            // Objeto que estabelece uma conexao com o Banco de Dados, usando a URL, usuario e senha.
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :("+ex.getMessage());
        }
    }

    public void insere() {
        // Conecto com o Banco
        conectaBanco();
        try {
            // Preparo a insercao
            pst = con.prepareStatement("INSERT INTO Idoso (nome, observacoes, cidade, rg, cpf, data_nasc, estado, foto,responsavel_id, casa_id, tag_idoso) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            // Cada numero indica a posicao que o valor sera inserido nas ? acima
            pst.setString(1, idoaux.getNome());
            pst.setString(2, idoaux.getObservacoes());
            pst.setString(3, idoaux.getCidade());
            pst.setString(4, idoaux.getRg());
            pst.setString(5, idoaux.getCpf());
            pst.setString(6, idoaux.getData_nasc());
            pst.setString(7, idoaux.getEstado());
            pst.setString(8, idoaux.getFoto());
            pst.setInt(9, idoaux.getResponsavel_id());
            pst.setInt(10, idoaux.getCasa());
            pst.setString(11, idoaux.getTag_idoso());
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

    public ArrayList<Idoso> selecionaTodos() {
// Lista que recebera todos os registros desta tabela
        ArrayList<Idoso> listaIdosos = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// O metodo createStatement() cria um objeto Statement que permite enviar comandos SQL para o banco.

            st = con.createStatement();
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = st.executeQuery("SELECT * FROM Idoso");
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                Idoso idoso = new Idoso();
// Guardo o seu id (primeira coluna na tabela)
                idoso.setId_idoso(rs.getInt(1));
                idoso.setNome(rs.getString(2));
                idoso.setObservacoes(rs.getString(3));
                idoso.setCidade(rs.getString(4));
                idoso.setRg(rs.getString(5));
                idoso.setCpf(rs.getString(6));
                idoso.setData_nasc(rs.getString(7));
                idoso.setEstado(rs.getString(8));
                idoso.setFoto(rs.getString(9));
                idoso.setResponsavel_id(rs.getInt(10));
                idoso.setCasa(rs.getInt(11));
                idoso.setTag_idoso(rs.getString(12));
// Adiciono na lista de Livros
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
    
    public boolean checaTag(String tag){
        boolean found = false;
        try {
            conectaBanco();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Idoso");
            while (rs.next()) {
                Idoso ido = new Idoso();
                
                ido.setTag_idoso(rs.getString(12));
                if(rs.getString(12).equals(tag)) found = true;
                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :("+ex.getMessage());
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
        
        return found;
    }

 /*   public List<Idoso> selecionaPorNome() {
// Lista que recebera todos os registros desta tabela
        List<Idoso> listaIdosos = new ArrayList<>();
        try {
// Conecto com o Banco
            conectaBanco();
// Preparo a consulta
            pst = con.prepareStatement(
                    "SELECT * FROM Idoso WHERE nome id_idoso in (SELECT id_idoso from nome where Nome like ?)");
// Indico que o primeiro ? significa o nome digitado pelo usuario
            pst.setString(1, "%" + nome + "%");
// O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            rs = pst.executeQuery();
// O metodo next() caminha entre as linhas da tabela de resultados retornada.
            while (rs.next()) {
// A cada nova interacao, cria um novo objeto Livro
                Idoso idoso = new Idoso();
// Guardo o seu codigo (primeira coluna na tabela)
                idoso.setId_idoso(rs.getInt(1));
                idoso.setNome(rs.getString(2));
                idoso.setIdade(rs.getInt(3));
                idoso.setObservacoes(rs.getString(4));
                idoso.setCidade(rs.getString(5));
                idoso.setRg(rs.getString(6));
                idoso.setCpf(rs.getString(7));
                idoso.setData_nasc(rs.getString(8));
                idoso.setEstado(rs.getString(9));
                idoso.setResponsavel_id(rs.getInt(10));
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
            pst = con.prepareStatement("UPDATE Idoso SET nome = ?,observacoes =?,cidade =  ?,rg = ?,cpf = ?,data_nasc = ?,estado = ?,foto = ?, responsavel_id = ?, casa_id = ?, tag_idoso = ? WHERE  id_idoso =  ?");
// Indico que o primeiro ? significa o nome digitado pelo usuario
            pst.setString(1, idoaux.getNome());
            pst.setString(2, idoaux.getObservacoes());
            pst.setString(3, idoaux.getCidade());
            pst.setString(4, idoaux.getRg());
            pst.setString(5, idoaux.getCpf());
            pst.setString(6, idoaux.getData_nasc());
            pst.setString(7, idoaux.getEstado());
            pst.setString(8, idoaux.getFoto());
            pst.setInt(9, idoaux.getResponsavel_id());
            pst.setInt(10, idoaux.getCasa());
            pst.setString(11, idoaux.getTag_idoso());

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

    public void delete(String nome) {
// Conecto com o Banco
        conectaBanco();
        try {
// Preparo a exclusao
            pst = con.prepareStatement("DELETE FROM Idoso WHERE nome = ?");
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
