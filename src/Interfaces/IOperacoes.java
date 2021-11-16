package Interfaces;

import java.io.*;

import org.json.JSONObject;


public interface IOperacoes {
	public static void cadastrar(String documento, JSONObject cadastro) {
		String caminhoDiretorio = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Cadastros";
		String caminhoArquivo = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Cadastros\\" + documento;
		
		try {
			File diretorio = new File(caminhoDiretorio);
			File arquivo = new File(caminhoArquivo);
			
			if (!diretorio.exists()) {
				diretorio.mkdirs();
	        }
			
			if (diretorio.exists() && !arquivo.exists()) {
				arquivo.createNewFile();
			}

	        FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true);
	        
	        String cadastroTexto = cadastro.toString();

	        fw.write(cadastroTexto + ";");

	        fw.close();
	        
	        System.out.println("Cadastro realizado com sucesso!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static String[] listar(String documento) {
		String caminhoDiretorio = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Cadastros";
		String caminhoArquivo = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Cadastros\\" + documento;
		
		String[] listagem = null;
		
		try {	
			File diretorio = new File(caminhoDiretorio);
			File arquivo = new File(caminhoArquivo);
			
			if (!diretorio.exists() || !arquivo.exists()) {
				System.out.println("Não foi possivel encontrar o arquivo com os dados");
	        } else {
	        	FileReader leitura = new FileReader(arquivo);
	        	char[] chars = new char[(int) arquivo.length()];
	        	leitura.read(chars);
	            String conteudo = new String(chars);
	            listagem = conteudo.substring(0, conteudo.length() - 1).split(";");
	            leitura.close();
	        }
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return listagem;
	}
}
