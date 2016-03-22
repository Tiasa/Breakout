default: ; @echo "Compiling..." ; javac -cp Breakout.jar *.java

%: ; # Arg 1
        @: ; # Arg 2

run: default ; java -cp "Breakout.jar:." -splash:splash.png Breakout $(filter-out $@,$(MAKECMDGOALS))

clean: ; rm -f *.class