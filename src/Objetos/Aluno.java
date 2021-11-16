package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends PessoaFisica {
	
	private String ra, turma;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	public Aluno(String id, String nome, String sobrenome, String cpf, String rg, String endereco,
			String ra, String turma, List<Disciplina> disciplinas) {
		super(id, nome, sobrenome, cpf, rg, endereco);
		this.ra = ra;
		this.turma = turma;
		this.disciplinas = disciplinas;
	}

	public String getRa() {
		return ra;
	}

	public String getTurma() {
		return turma;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

}
