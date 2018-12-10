package com.wep.iftube;
import static org.junit.Assert.*;

import org.junit.Test;

import com.wep.iftube.servicos.ServicoValidacao;


public class ServicoValidacaoTeste {
  
	
    @Test
    public void naoPodeValidarNomeEmBranco() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "";
    	String email = null;
    	String senha = null;
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
		
	}
    
    @Test
    public void naoPodeValidarNomeComMaisDe10Caracteres() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "Asdsasasssasasasasas";
    	String email = null;
    	String senha = null;
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
    }
    
    @Test
    public void naoPodeValidarNomeApenasComNumeros() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = null;
    	String senha = null;
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
    }
    
    @Test
	public void naoPodeValidarEmailSemOArroba() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "teste.com";
    	String senha = null;
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
    }
    
    @Test
    public void naoPodeValidarEmailComNumerosNoComeco() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = null;
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);		
	}
    
    @Test
    public void naoPodeValidarSenhaVasia() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = "";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
	}
    
    @Test
    public void naoPodeValidarSenhaComMenosDe8Carteres() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = "2018";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
	}
    
    @Test
    public void naoPodeValidarSenhaComMaisDe20Carecteres() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
	}
    
    @Test
    public void naoPodeValidarSenhaComNumerosEmOrdenCrecente() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = "12345678";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);
    }
    
    @Test
    public void naoPodeValidarSenhaComNumerosEmOrdenDecrecente() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = "123321";
    	String email = "123teste.com";
    	String senha = "87654321";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(false, ehCanalValido);

    }
    
    
    
    @Test
	public void permitirValidarComTudoCorreto() {
    	ServicoValidacao servico = new ServicoValidacao();
    	String nome = " arnold schwarzenegger";
    	String email = "terminatoT800@gmail.com";
    	String senha = "hastalavistababy";
		
    	boolean ehCanalValido = servico.validarCanal(nome, email, senha);    	
    	assertEquals(true, ehCanalValido);
    }
}
