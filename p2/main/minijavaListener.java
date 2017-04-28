// Generated from main/minijava.g4 by ANTLR 4.4

    package main;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link minijavaParser}.
 */
public interface minijavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link minijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull minijavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull minijavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(@NotNull minijavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(@NotNull minijavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull minijavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull minijavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(@NotNull minijavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(@NotNull minijavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull minijavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull minijavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull minijavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull minijavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(@NotNull minijavaParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(@NotNull minijavaParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull minijavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull minijavaParser.ClassDeclarationContext ctx);
}