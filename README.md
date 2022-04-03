# Melbn
===========================
###########configuration file
jdk 1.8.0+
eclipse

###########configuration configuration
1. Download JDK from Oracle website
    https://www.oracle.com/java/technologies/downloads/

2. Add system environment variables
    Right click "My Computer" -> "Properties" -> "Advanced System Settings" -> "Advanced" -> "Environment Variables" -> "System Variables":
    1)Variable name: JAVA_HOME
      Variable value: C:\Program Files (x86)\Java\jdk1.8.0_91 // To configure according to your actual path
    2)Variable name: CLASSPATH
      Variable value: . ;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar; // remember there is a "." in front of it
    3)Variable name: Path
      Variable value: %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

3. Test if the JDK is installed successfully
  "Start" -> "Run", type "cmd"
  Type in the command: java -version, java, javac
  The following message appears, indicating that the environment variables were successfully configured
  
  C:\Users\bagoo>java -version
  java version "17.0.2" 2022-01-18 LTS
  Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
  Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

4. Download the project and access the src folder in the the command line
    cd src's directory in your computer
5. Compile the Main.java
    javac Main.java
6. Run the program
    java main
