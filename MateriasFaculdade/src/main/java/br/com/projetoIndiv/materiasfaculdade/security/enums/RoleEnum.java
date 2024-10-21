package br.com.projetoIndiv.materiasfaculdade.security.enums;

public enum RoleEnum {
	ROLE_USUARIO("usuário", 1),
	ROLE_ESTUDANTE("estudante", 2),
	ROLE_ADMIN("admin", 3);
	
	private String tipo;
    private int codigo;
    
    private RoleEnum(String tipo, int codigo) {
        this.tipo = tipo;
        this.codigo = codigo;
    }
    
    private RoleEnum(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo () {
        return codigo;
    }
    
    public String getTipo () {
        return tipo;
    } 
}