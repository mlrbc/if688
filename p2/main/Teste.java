package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ast.*;
import visitor.*;

import main.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Teste {
	public static void main(String[] args) {
		try {
			File folder = new File("testes");
			File[] files = folder.listFiles();
			int num = 0;
			if (files != null) {
				num = files.length;
			
				for (int i=0; i < num; i++){
					System.out.println("Testando " + files[i]);
					InputStream stream = new FileInputStream(files[i]);
					ANTLRInputStream input = new ANTLRInputStream(stream);
					minijavaLexer lexer = new minijavaLexer(input);
					CommonTokenStream token = new CommonTokenStream(lexer);
					minijavaParser parser = new minijavaParser(token);
					
					Transforma t = new Transforma();
					Program prog = t.visitGoal(parser.goal());
					prog.accept(new PrettyPrintVisitor());
				}
			} else {
				System.out.println("Não foi possível ler nada, verifique o local do diretório testes. Se estiver no eclipse, ele deve estar no diretório do projeto que fica dentro do workspace.");
			}
		} catch (FileNotFoundException e){
			System.out.println("Erro com a entrada de teste. " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
