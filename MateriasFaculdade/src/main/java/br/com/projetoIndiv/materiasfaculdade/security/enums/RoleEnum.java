package br.com.projetoIndiv.materiasfaculdade.security.enums;

public enum RoleEnum {
    ROLE_ESTUDANTE("estudante", 1),
    ROLE_FACULDADE("faculdade", 2);

    private String tipo;
    private int codigo;

    private RoleEnum(String tipo, int codigo) {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    private RoleEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }
}