package com.wep.iftube.servicos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicoValidacao {

	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String NOME_PATTERN = "^[A-Za-z_0-9-\\+]* [ [A-Z][a-z]]*";
	
	private static  Pattern pattern;	
	
	private static Matcher matcher;
	
	public boolean validarCanal( String nome, String email, String senha ) {
		if(validarNome(nome) && validarEmail(email) && !validarSenha(senha))
		return true;
		
		return false;
	}
	
	public boolean validarNome(String nome) {
		pattern = Pattern.compile(NOME_PATTERN, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(nome);
		return matcher.matches();
	}
	
    public  boolean validarEmail(String email){
		
	    pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);		
		matcher= pattern.matcher(email);
	    return matcher.matches();	 
	}
    
    public boolean validarSenha(String senha) {
    	if(senha.isEmpty() 
    			&& (senha.length() <= 8) 
    			&& (senha.length() >= 20) 
    			&& (senha.contains("12345678"))
    			&& (senha.contains("87654321")))
    	return true;
    	
    	return false;
    }
}
