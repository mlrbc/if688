package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Teste {
	public static void main(String[] args) throws IOException {
		
		File folder = new File("testes");
		File[] files = folder.listFiles();
		for (int i=0; i< files.length; i++){
			System.out.println("Testando " + files[i]);
			InputStream stream = new FileInputStream(files[i]);
			ANTLRInputStream input = new ANTLRInputStream(stream);
			minijavaLexer lexer = new minijavaLexer(input);
			CommonTokenStream token = new CommonTokenStream(lexer);
			minijavaParser parser = new minijavaParser(token);
			parser.goal();
		}
	}
}
