package br.com.projetoIndiv.materiasfaculdade.security.enums;

public enum RoleEnum {
	ROLE_USER("Role Usuário", 1),
	ROLE_MODERATOR("Role Estudante", 2),
	ROLE_ADMIN("Role Admin Facul", 3);
	
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