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
        ClassDecl r = null;
        String two = null;
        if (c.getChild(2) != null){
            two = c.getChild(2).getText();
        }
        if (two != null && two.equals("{")){
            r = new ClassDeclSimple(visitIdentifier(c.IDENTIFIER(0)), visitVarDeclList(c.varDeclaration()), visitMethodDeclList(c.methodDeclaration()));
        } else if (two != null && two.equals("extends")){
            r = new ClassDeclExtends(visitIdentifier(c.IDENTIFIER(0)), visitIdentifier(c.IDENTIFIER(1)), visitVarDeclList(c.varDeclaration()), visitMethodDeclList(c.methodDeclaration()));
        }
        
        return r;
    }
   
    private Exp visitExp(ExpressionContext c){
        Exp r = null;
        String one = null;
        if (c.getChild(0) != null) {
            one = c.getChild(0).getText();
        }
        String two= null;
        if (c.getChild(1) != null) {
            two = c.getChild(1).getText();
        }
        String three= null;
        if (c.getChild(2) != null) {
            three = c.getChild(2).getText();
        }
        
        
        if (c.OP() != null){
            Exp ex1 = visitExp(c.expression(0));
            Exp ex2 = visitExp(c.expression(1));
            if (two.equals("&&")){
                r = new And(ex1, ex2);
            } else if (two.equals("+")){
                r = new Plus(ex1, ex2);
            } else if (two.equals("-")){
                r = new Minus(ex1, ex2);
            } else if (two.equals("*")){
                r = new Times(ex1, ex2);
            } else if(two.equals("<")){
                r = new LessThan(ex1, ex2);
            }
            
        } else if (c.INTEGER() != null){
            r = new IntegerLiteral(Integer.parseInt(c.INTEGER().getText()));
        
        } else if (one != null && one.equals("!")){
            r = new Not(visitExp(c.expression(0)));
        } else if (one != null && one.equals("(")){
            r = visitExp(c.expression(0));
            
        } else if (one != null && one.equals("new")){
            if (two != null && two.equals("int")){ //new para array
                r = new NewArray(visitExp(c.expression(0)));
            } else if (three != null && three.equals("(")){ //new para objeto
                r = new NewObject(new Identifier(c.IDENTIFIER().getText()));
            }
        } else if ( two!= null && two.equals(".") && three != null && three.equals("length")){ //chamando funcao especifica
            r = new ArrayLength(visitExp(c.expression(0)));
        } else if ( two!= null && two.equals(".") && three != null){ 
            ExpList l = new ExpList();
            for (int i = 1; i < c.expression().size(); i++) {
                l.addElement(visitExp(c.expression(i)));
            }
            r = new Call(visitExp(c.expression(0)), visitIdentifier(c.IDENTIFIER()), l);
            
        } else if (two != null && two.equals("[")){
            r = new ArrayLookup(visitExp(c.expression(0)), visitExp(c.expression(1)));          
        } else if(c.getText().equals("this")) {
            r = new This();         
        } else if(c.getText().equals("true")) {
            r = new True();
        } else if(c.getText().equals("false")) {
            r = new False();
        } else if (c.IDENTIFIER() != null) {
            r = new IdentifierExp(c.IDENTIFIER().getText());
        }
            
        
        return r;
    }
    
    private StatementList visitStatementList(List<StatementContext> list) {
        StatementList r = new StatementList();
        for (int i=0; i<list.size();i++){
            r.addElement(visitStatement(list.get(i)));
        }
        return r;
    }
    
    private VarDeclList visitVarDeclList(List<VarDeclarationContext> list) {
        VarDeclList r = new VarDeclList();
        for (int i=0; i<list.size(); i++){
            r.addElement(visitVarDecl(list.get(i)));
        }
        return r;
    }
    
    private MethodDeclList visitMethodDeclList(List<MethodDeclarationContext> list) {
        MethodDeclList r = new MethodDeclList();
        for (int i=0; i<list.size();i++){
            r.addElement(visitMethodDecl(list.get(i)));
        }
        
        return r;
    }
    
    private VarDecl visitVarDecl(VarDeclarationContext c) {
        return new VarDecl(visitType(c.type()), visitIdentifier(c.IDENTIFIER()));
    }
    
  
    private MethodDecl visitMethodDecl(MethodDeclarationContext c) {
        FormalList l = new FormalList();
        for(int i=1; i < c.type().size(); i++){
            l.addElement(new Formal(visitType(c.type(i)), visitIdentifier(c.IDENTIFIER(i))));
        }       
        return new MethodDecl(visitType(c.type(0)), visitIdentifier(c.IDENTIFIER(0)), l, visitVarDeclList(c.varDeclaration()), visitStatementList(c.statement()), visitExp(c.expression()));
    }

    private Type visitType(TypeContext c) {
        Type r = null;
        String s = c.getText();
        if (s.equals("boolean")){
            r = new BooleanType();
        } else if (s.equals("int[]") || c.getChild(0) != null && c.getChild(0).equals("int") && c.getChild(1) != null && c.getChild(1).equals("[")){
            r = new IntArrayType();
        } else if(s.equals("int")){
            r = new IntegerType();
        } else if (c.IDENTIFIER() != null){
            r = new IdentifierType(c.getText());
        }       
        
        return r;
    }

}
