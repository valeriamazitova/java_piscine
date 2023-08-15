# deleting the directory
rm -rf target

# creating the new dir since java -d doesn't create it
mkdir target

# set the destination directory for class files
javac -d ./target src/java/edu.school21.printer/*/*.java

cp -R src/resources target/.

jar cfmv ./target/images-to-chars-printed.jar src/manifest.txt -C target ex01 -C target resources
chmod u+x target/images-to-chars-printed.jar

java -jar target/images-to-chars-printed.jar --white=. --black=0
