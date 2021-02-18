/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

/**
 *
 * @author igorg
 */
public class Controller {
    
    Login L = new Login();
    Cadastro C  = new Cadastro();
    Cadastro_Idoso CI = new Cadastro_Idoso();
    Inicio I = new Inicio();
    Monitoramento M = new Monitoramento();
    menu ME = new menu();
    Lista_Idoso LI = new Lista_Idoso();
    Excluir_Cadastro_Usuario EU = new Excluir_Cadastro_Usuario();
    Pesquisar P = new Pesquisar();
    Registros R = new Registros();
    
    public void criarLogin(){
        
        L.setLocationRelativeTo(null);
        L.setVisible(true);
    }
    public void criarCadastro(){
        
        C.setLocationRelativeTo(null);
        C.setVisible(true);
    }
    public void criarInicio(){
        
        I.setLocationRelativeTo(null);
        I.setVisible(true);
    }
    public void criarMenu(){
        
        ME.setLocationRelativeTo(null);
        ME.setVisible(true);
    }
    public void criarCadastro_I(){
        
        CI.setLocationRelativeTo(null);
        CI.setVisible(true);
    }
    public void criarMonitoramento(){
        
        M.setLocationRelativeTo(null);
        M.setVisible(true);
    }
    public void criarLista(){
        
        
        LI.setLocationRelativeTo(null);
        LI.setVisible(true);
    }
    public void criarEU(){
        
        
        EU.setLocationRelativeTo(null);
        EU.setVisible(true);
    }
    public void criarRegistro(){
        
        
        R.setLocationRelativeTo(null);
        R.setVisible(true);
    }
    public void criarPesquisa(){
        
        
        P.setLocationRelativeTo(null);
        P.setVisible(true);
    }
    
}
