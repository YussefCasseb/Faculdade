package Objetos;

public class PessoaJuridica extends Pessoa {
	
	private String razaoSocial, nomeFantasia, cnpj, ie;

	public PessoaJuridica(String id, String razaoSocial, String nomeFantasia, String cnpj, String ie, String endereco) {
		super(id, endereco);
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.ie = ie;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getIe() {
		return ie;
	}

}
