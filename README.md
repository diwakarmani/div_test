Poker intro :- A command line program that takes input, via STDIN, a "stream" of hands for a two player poker game.
               At the completion of the stream, program should print to STDOUT the number of hands won by Player 1, and the number of hands won by Player 2.

Command to build project using maven from root directory of source code :- mvn clean install -DskipTests
From eclipse IDE run as maven package or install to build the project

After build it will create target folder with the jar file - poker-0.0.1-SNAPSHOT.jar

Execute project :-   

linux: cat poker-hands.txt | java -jar poker-0.0.1-SNAPSHOT.jar

Windows: type poker-hands.txt | java -jar poker-0.0.1-SNAPSHOT.jar

Eclipse IDE : run as java application and after appliction starts enter the hands value for each player similar to row in poker-hands.txt after entering clik enter twice to exit.

Execute test case :
command to execute test case having maven in the environment: mvn test

IDE : run as junit 


Output format :

Player 1: x hands
Player 2: y hands
