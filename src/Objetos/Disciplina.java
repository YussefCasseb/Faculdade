package Objetos;

import java.util.ArrayList;
import java.util.List;

import ENUM.MateriasEnum;

public class Disciplina {
	
	private String nome;
	private List<MateriasEnum> materias = new ArrayList<MateriasEnum>();

	public Disciplina(String nome, List<MateriasEnum> materias) {
		this.nome = nome;
		this.materias = materias;
	}

	public String getNome() {
		return nome;
	}

	public List<MateriasEnum> getMaterias() {
		return materias;
	}

}
