MAINDIR=main
MAINCLASS=Teste

all: antlr-generate run 


antlr-generate:
	java -jar lib/antlr-4.4-complete.jar $(MAINDIR)/*.g4

run:
	javac -cp lib/antlr-4.4-complete.jar $(MAINDIR)/*.java
	java -cp lib/antlr-4.4-complete.jar:. $(MAINDIR).$(MAINCLASS)
clean:
	rm $(MAINDIR)/*.class
	rm $(MAINDIR)/*.tokens
