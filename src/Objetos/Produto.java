package Objetos;

public class Produto {
	
	private String nome, tipo;
	private float valor;

	public Produto(String nome, String tipo, float valor) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public float getValor() {
		return valor;
	}

}
