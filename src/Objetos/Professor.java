package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Professor extends PessoaFisica {
	
	private String turmas;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	public Professor(String id, String nome, String sobrenome, String cpf, String rg, String endereco,
			String turmas, List<Disciplina> disciplinas) {
		super(id, nome, sobrenome, cpf, rg, endereco);
		this.turmas = turmas;
		this.disciplinas = disciplinas;
	}

	public String getTurmas() {
		return turmas;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

}
