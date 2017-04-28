%%
%line
%column

%unicode
%standalone
%class AnalisadorMiniJava


/*%{

%}*/


Comentario = "//"[^\n]*|"/*"([^"*"]|"*"[^"/"])*"*/"
Whitespace = [\n|\f|\t|\r| ]
Reservada = "boolean"|"class"|"public"|"extends"|"static"|"void"|"main"|"String"|"int"|"while"|"if"|"else"|"return"|"length"|"true"|"false"|"this"|"new"|"System.out.println"
Operador = "&&"|"<"|"=="|"!="|"+"|"-"|"*"|"!"
Pontuacao = ";"|"."|","|"="|"("|")"|"{"|"}"|"["|"]"
Identificador = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
Inteiro = [1-9][0-9]*|0


%%

{Comentario}                      { System.out.println("Comentário: '" + yytext() + "'"); }
{Whitespace}                      { System.out.println("Whitespace: '" + yytext() + "'"); }
{Reservada}                       { System.out.println("Palavra reservada: '" + yytext() + "'"); }
{Operador}                        { System.out.println("Operador: '" + yytext() + "'"); }
{Pontuacao}                       { System.out.println("Delimitador e Pontuacao: '" + yytext() + "'"); }
{Identificador}                   { System.out.println("Identificador: '" + yytext() + "'"); }
{Inteiro}                         { System.out.println("Literal Inteiro: '" + yytext() + "'"); }


. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
