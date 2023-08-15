mkdir target

javac -d ./target src/java/edu.school21.printer/app/Program.java src/java/edu.school21.printer/logic/FilePrinter.java

java -cp ./target src/java/edu.school21.printer/*/*.java --white=. --black=0 --filePath=../it.bmp
