package Objetos;

public class Pessoa {

	private String id, endereco;
	
	public Pessoa(String id, String endereco) {
		this.id = id;
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}
}
