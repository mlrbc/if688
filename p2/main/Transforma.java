package main;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ast.*;
import main.minijavaParser.*;



public class Transforma {
    public Transforma(){}
    
    public Program visitGoal(GoalContext c){
        MainClass main = visitMainClass(c.mainClass());
        ClassDeclList classdl = visitClassDeclList(c.classDeclaration());
        return new Program (main, classdl);
    }


    public MainClass visitMainClass(MainClassContext c){
		return new MainClass(visitIdentifier(c.IDENTIFIER(0)), visitIdentifier(c.IDENTIFIER(1)), visitStatement(c.statement()));        
    }
    
    public ClassDeclList visitClassDeclList(List<ClassDeclarationContext> list){
    	ClassDeclList r = new ClassDeclList();
    	for (int i=0; i<list.size(); i++){
    		r.addElement(visitClassDecl(list.get(i)));
    	}
		return r;
    
    }
    
    public Identifier visitIdentifier(TerminalNode c){
    	return new Identifier(c.toString());   
    }

    public Statement visitStatement(StatementContext c){
    	Statement r = null;
    	String word = null;
    	String op = null;
    	if (c.getChild(0) != null) {
    		word = c.getChild(0).getText();
    	}
    	if (c.getChild(1) != null){
    		op = c.getChild(1).getText();
    	}
		if (word != null && word.equals("if")){
    		r = new If(visitExp(c.expression(0)), visitStatement(c.statement(0)), visitStatement(c.statement(1)));
    	} else if (word != null && word.equals("while")){
    		r = new While(visitExp(c.expression(0)), visitStatement(c.statement(0)));
    	} else if (word != null && word.equals("System.out.println")){
    		r = new Print(visitExp(c.expression(0)));
    	} else if (word != null && word.equals("{")){
    		r = new Block(visitStatementList(c.statement()));
    	} else if (op != null && op.equals("=")){
    		r = new Assign(visitIdentifier(c.IDENTIFIER()), visitExp(c.expression(0)));
    	} else if (op != null && op.equals("[")){
    		r = new ArrayAssign(visitIdentifier(c.IDENTIFIER()), visitExp(c.expression(0)), visitExp(c.expression(1)));
    	}    	
		return r;	
    }
    
	
	public ClassDecl visitClassDecl(ClassDeclarationContext c){
		return null;
	}
   
    private Exp visitExp(ExpressionContext c){
    	return null;
    }
    
    private StatementList visitStatementList(List<StatementContext> list) {
		return null;
	}
    
}
