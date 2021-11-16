package faculdade;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import ENUM.MateriasEnum;
import Interfaces.IOperacoes;
import Objetos.Aluno;
import Objetos.Disciplina;
import Objetos.Fornecedor;
import Objetos.Pessoa;
import Objetos.PessoaFisica;
import Objetos.PessoaJuridica;
import Objetos.Produto;
import Objetos.Professor;

public class Faculdade {
	
	public static Disciplina obterDisciplinas(boolean isProfessor, Scanner scanner) {
		String nome;
		
		List<String> opcoesMaterias = new ArrayList<String>();
		
		for (MateriasEnum materia : MateriasEnum.values()) {
			opcoesMaterias.add(materia.getDescricao());
        }
		
		if (isProfessor) {
			System.out.println("Nome da disciplina que está lecionando:");
		} else {
			System.out.println("Nome da disciplina que está cursando:");
		}
		
		nome = scanner.nextLine();
		System.out.println("");
		System.out.println("Matérias da disciplina: " + opcoesMaterias + " (Se for mais de 1 separe-as com virgula)");
		Disciplina disciplina = new Disciplina(nome, MateriasEnum.obterEnumPelaString(scanner.nextLine()));
		System.out.println("");
		
		return disciplina;
	}
	
	public static void formularioCadastro() {
		UUID uuid = UUID.randomUUID();
		Scanner scanner = new Scanner(System.in);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		List<String> tiposCadastro = Arrays.asList(new String[]{"Pessoa", "Pessoa Fisica", "Pessoa Juridica",
				"Professor", "Fornecedor", "Aluno"});
		
		String id = uuid.toString();
		String tipoCadastro = null, tipoPessoaFisica = "Sim";
		String nome = null, sobrenome = null, cpf = null, rg = null, endereco = null, ra = null,
				turma = null, razaoSocial = null, nomeFantasia = null, cnpj = null, ie = null, tipoFornecedor = null;
		String documento = null;
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		List<Produto> produtos = new ArrayList<Produto>();
		
		do {
			System.out.println("Qual o tipo de cadastro que deseja realizar ? " + tiposCadastro);
			tipoCadastro = scanner.nextLine();
			System.out.println("");
		} while (!tiposCadastro.contains(tipoCadastro));
		
		if (tipoCadastro.equals("Professor")) {			
			do {
				System.out.println("É um cadastro de pessoa fisica ? (Sim/Não)");
				tipoPessoaFisica = scanner.nextLine();
				System.out.println("");
			} while (tipoPessoaFisica.equalsIgnoreCase("Sim") || tipoPessoaFisica.equalsIgnoreCase("Não") ? false : true);
		}
		
		if (tipoCadastro.equals("Pessoa")) {
			System.out.println("Endereço:");
			endereco = scanner.nextLine();
			System.out.println("");
			
			Pessoa pessoa = new Pessoa(id, endereco);
			
			json.put("id", pessoa.getId());
			json.put("endereço", pessoa.getEndereco());
			
			documento = "Pessoa.txt";
		} else if (!tipoCadastro.equals("Pessoa Juridica") && !tipoCadastro.equals("Fornecedor") && tipoPessoaFisica.equals("Sim")) {
			System.out.println("Nome:");
			nome = scanner.nextLine();
			System.out.println("");
			System.out.println("Sobrenome:");
			sobrenome = scanner.nextLine();
			System.out.println("");
			System.out.println("CPF:");
			cpf = scanner.nextLine();
			System.out.println("");
			System.out.println("RG:");
			rg = scanner.nextLine();
			System.out.println("");
			System.out.println("Endereço:");
			endereco = scanner.nextLine();
			System.out.println("");
			
			PessoaFisica pessoaFisica = new PessoaFisica(id, nome, sobrenome, cpf, rg, endereco);
			
			json.put("id", pessoaFisica.getId());
			json.put("nome", pessoaFisica.getNome());
			json.put("sobrenome", pessoaFisica.getSobrenome());
			json.put("cpf", pessoaFisica.getCpf());
			json.put("rg", pessoaFisica.getRg());
			json.put("endereço", pessoaFisica.getEndereco());
			
			documento = "PessoaFisica.txt";
			
			if (tipoCadastro.equals("Professor")) {
				System.out.println("Turmas: (Se for mais de 1 separe-as com virgula)");
				turma = scanner.nextLine();
				System.out.println("");
				
				String cadastrarOutraDisciplina;
				
				System.out.println("Abaixo informe a(s) disciplina(s) deste professor");
				System.out.println("");
				
				do {
					disciplinas.add(obterDisciplinas(true, scanner));
					
					System.out.println("Deseja informar outra disciplina ? (Sim/Não)");
					cadastrarOutraDisciplina = scanner.nextLine();
					System.out.println("");
				} while (cadastrarOutraDisciplina.equalsIgnoreCase("Sim") ? true : false);
				
				for (Disciplina disciplina : disciplinas) {
					jsonArray.put(new JSONObject().put("nome", disciplina.getNome()).put("materias", disciplina.getMaterias()));
				}
				
				Professor professor = new Professor(id, nome, sobrenome, cpf, rg, endereco, turma, disciplinas);
				
				json.put("turmas", professor.getTurmas());
				json.put("disciplinas", jsonArray);
				
				documento = "Professor.txt";
			}
			
			if (tipoCadastro.equals("Aluno")) {
				System.out.println("RA:");
				ra = scanner.nextLine();
				System.out.println("");
				System.out.println("Turma:");
				turma = scanner.nextLine();
				System.out.println("");
				
				String cadastrarOutraDisciplina;
				
				System.out.println("Abaixo informe a(s) disciplina(s) deste aluno");
				System.out.println("");
				
				do {
					disciplinas.add(obterDisciplinas(false, scanner));
					
					System.out.println("Deseja informar outra disciplina ? (Sim/Não)");
					cadastrarOutraDisciplina = scanner.nextLine();
					System.out.println("");
				} while (cadastrarOutraDisciplina.equalsIgnoreCase("Sim") ? true : false);
				
				for (Disciplina disciplina : disciplinas) {
					jsonArray.put(new JSONObject().put("nome", disciplina.getNome()).put("materias", disciplina.getMaterias()));
				}
				
				Aluno aluno = new Aluno(id, nome, sobrenome, cpf, rg, endereco, ra, turma, disciplinas);
				
				json.put("ra", aluno.getRa());
				json.put("turma", aluno.getTurma());
				json.put("disciplinas", jsonArray);
				
				documento = "Aluno.txt";
			}
		} else if (tipoCadastro.equals("Pessoa Juridica") || tipoCadastro.equals("Fornecedor") || tipoPessoaFisica.equals("Não")) {
			System.out.println("Razão Social:");
			razaoSocial = scanner.nextLine();
			System.out.println("");
			System.out.println("Nome Fantasia:");
			nomeFantasia = scanner.nextLine();
			System.out.println("");
			System.out.println("CNPJ:");
			cnpj = scanner.nextLine();
			System.out.println("");
			System.out.println("IE:");
			ie = scanner.nextLine();
			System.out.println("");
			System.out.println("Endereço:");
			endereco = scanner.nextLine();
			System.out.println("");
			
			PessoaJuridica pessoaJuridica = new PessoaJuridica(id, razaoSocial, nomeFantasia, cnpj, ie, endereco);
			
			json.put("id", pessoaJuridica.getId());
			json.put("razão social", pessoaJuridica.getRazaoSocial());
			json.put("nome fantasia", pessoaJuridica.getNomeFantasia());
			json.put("cnpj", pessoaJuridica.getCnpj());
			json.put("ie", pessoaJuridica.getIe());
			json.put("endereço", pessoaJuridica.getEndereco());
			
			documento = "PessoaJuridica.txt";
			
			if (tipoCadastro.equals("Professor")) {
				System.out.println("Turmas: (Se for mais de 1 separe-as com virgula)");
				turma = scanner.nextLine();
				System.out.println("");
				
				String cadastrarOutraDisciplina;
				
				System.out.println("Abaixo informe a(s) disciplina(s) deste professor");
				System.out.println("");
				
				do {
					disciplinas.add(obterDisciplinas(true, scanner));
					
					System.out.println("Deseja informar outra disciplina ? (Sim/Não)");
					cadastrarOutraDisciplina = scanner.nextLine();
					System.out.println("");
				} while (cadastrarOutraDisciplina.equalsIgnoreCase("Sim") ? true : false);
				
				for (Disciplina disciplina : disciplinas) {
					jsonArray.put(new JSONObject().put("nome", disciplina.getNome()).put("materias", disciplina.getMaterias()));
				}
				
				Professor professor = new Professor(id, nome, sobrenome, cpf, rg, endereco, turma, disciplinas);
				
				json.put("turmas", professor.getTurmas());
				json.put("disciplinas", jsonArray);
				
				documento = "Professor.txt";
			}
			
			if (tipoCadastro.equals("Fornecedor")) {
				System.out.println("Área de atuação: (papelaria, loja, etc)");
				tipoFornecedor = scanner.nextLine();
				System.out.println("");
				
				String nomeProduto, tipo, cadastrarOutroProduto;
				float valor;
				
				System.out.println("Abaixo informe o(s) produto(s) vendido(s) por este fornecedor");
				System.out.println("");
				
				do {
					System.out.println("Nome do Produto:");
					nomeProduto = scanner.nextLine();
					System.out.println("");
					System.out.println("Tipo do Produto:");
					tipo = scanner.nextLine();
					System.out.println("");
					System.out.println("Valor do Produto:");
					valor = Float.valueOf(scanner.nextLine());
					System.out.println("");
					
					produtos.add(new Produto(nomeProduto, tipo, valor));
					
					System.out.println("Deseja informar outro produto ? (Sim/Não)");
					cadastrarOutroProduto = scanner.nextLine();
					System.out.println("");
				} while (cadastrarOutroProduto.equalsIgnoreCase("Sim") ? true : false);
				
				for (Produto produto : produtos) {
					jsonArray.put(new JSONObject().put("nome", produto.getNome())
							.put("tipo", produto.getTipo()).put("valor", produto.getValor()));
				}
				
				Fornecedor fornecedor = new Fornecedor(id, razaoSocial, nomeFantasia, cnpj, ie, endereco, tipoFornecedor, produtos);
				
				json.put("tipo", fornecedor.getTipo());
				json.put("produtos", jsonArray);
				
				documento = "Fornecedor.txt";
			}
		}
		
		scanner.close();
		
		IOperacoes.cadastrar(documento, json);
	}
	
	public static void listarCadastro() {
		Scanner scanner = new Scanner(System.in);
		
		List<String> documentos = Arrays.asList(new String[]{"Pessoa", "Pessoa Fisica", "Pessoa Juridica",
				"Professor", "Fornecedor", "Aluno"});
		
		String documento = null;
		
		do {
			System.out.println("Em qual tabela deseja realizar a consulta ? " + documentos);
			documento = scanner.nextLine();
			System.out.println("");
		} while (!documentos.contains(documento));
		
		String[] listagem = IOperacoes.listar(documento + ".txt");
		
		if (listagem == null || listagem.length == 0) {
			System.out.println("Nenhuma informação para ser listada");
		} else {
			for (String linha : listagem) {
				JSONObject conteudo = new JSONObject(linha);
				for (int i = 0; i < conteudo.names().length(); i++) {
					if (conteudo.names().get(i).equals("disciplinas")) {
						JSONArray disciplinasLista = conteudo.getJSONArray("disciplinas");
						System.out.println("disciplinas:");
						for (int j = 0; j < disciplinasLista.length(); j++) {
							JSONObject disciplinas = new JSONObject(disciplinasLista.get(j).toString());
							for (int k = 0; k < disciplinas.names().length(); k++) {
								System.out.println(disciplinas.names().get(k) + ": " + disciplinas.get((String) disciplinas.names().get(k)));
							}
						}
					} else if (conteudo.names().get(i).equals("produtos")) {
						JSONArray produtosLista = conteudo.getJSONArray("produtos");
						System.out.println("produtos:");
						for (int l = 0; l < produtosLista.length(); l++) {
							JSONObject produtos = new JSONObject(produtosLista.get(l).toString());
							for (int m = 0; m < produtos.names().length(); m++) {
								System.out.println(produtos.names().get(m) + ": " + produtos.get((String) produtos.names().get(m)));
							}
						}
					} else {
						System.out.println(conteudo.names().get(i) + ": " + conteudo.get((String) conteudo.names().get(i)));
					}
				}
				
				System.out.println("--------------------------------------------------------------------------------------------------------------");
			}
		}
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String operacao = null;
		
		System.out.println("Bem Vindo ao Sistema da Faculdade");
		System.out.println("");
		
		do {
			System.out.println("O que deseja fazer ? (Cadastrar, Listar)");
			operacao = scanner.nextLine();
			System.out.println("");
		} while (operacao.equalsIgnoreCase("Cadastrar") || operacao.equalsIgnoreCase("Listar") ? false : true);
		
		
		if (operacao.equalsIgnoreCase("Cadastrar")) {
			formularioCadastro();
		} else {
			listarCadastro();
		}
		
		scanner.close();
	}

}
