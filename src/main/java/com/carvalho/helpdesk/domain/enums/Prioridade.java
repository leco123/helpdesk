package com.carvalho.helpdesk.domain.enums;

public enum Prioridade {
    BAIXA(0,"BAIXA"),
    MEDIA(1, "MÉDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer codigo){
        if(codigo == null) {
            return null;
        }
        for (Prioridade perfil: Prioridade.values()) {
            if(codigo.equals(perfil.getCodigo())){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida");
    }
}
