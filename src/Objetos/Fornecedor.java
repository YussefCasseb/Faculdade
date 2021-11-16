package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends PessoaJuridica {
	
	private String tipo;
	private List<Produto> produtos = new ArrayList<Produto>();

	public Fornecedor(String id, String razaoSocial, String nomeFantasia, String cnpj, String ie, String endereco,
			String tipo, List<Produto> produtos) {
		super(id, razaoSocial, nomeFantasia, cnpj, ie, endereco);
		this.tipo = tipo;
		this.produtos = produtos;
	}
	
	public String getTipo() {
		return tipo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
