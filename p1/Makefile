JAVA=java
JAVAC=javac
FILE=vrm.jflex
FILEG=AnalisadorMiniJava
INPUT=input.in

all: run

run: generate_java compile
	$(JAVA) $(FILEG) $(INPUT)
compile:
	$(JAVAC) $(FILEG).java

generate_java:
	$(JAVA) -jar lib/jflex.jar $(FILE)

clean:
	rm $(FILEG).class*
	rm $(FILEG).java*
