package Objetos;

public class PessoaFisica extends Pessoa {
	
	private String nome, sobrenome, cpf, rg;

	public PessoaFisica(String id, String nome, String sobrenome, String cpf, String rg, String endereco) {
		super(id, endereco);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}
}
