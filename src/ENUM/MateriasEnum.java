package ENUM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MateriasEnum {
	PORTUGUES("Portugu�s"),
    MATEMATICA("Matem�tica"),
    QUIMICA("Quimica"),
	GEOGRAFIA("Geografia"),
	FISICA("F�sica"),
	ADM("ADM"),
	BANCO_DE_DADOS("Banco de Dados"),
	CONCEITOS_DE_COMPUTACAO("Conceitos de Computa��o");

    private String descricao;

    MateriasEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static List<MateriasEnum> obterEnumPelaString(String texto) {
    	List<String> materias = new ArrayList<String>(Arrays.asList(texto.split(",")));
    	List<MateriasEnum> materiasEnum = new ArrayList<MateriasEnum>();
    	
    	for (String materiaLista : materias) {
    		for (MateriasEnum materia : MateriasEnum.values()) {
                if (materia.descricao.equalsIgnoreCase(materiaLista.trim())) {
                    materiasEnum.add(materia);
                }
            }
    	}
    	
        return materiasEnum;
    }
}
