cls
javac -d Framework/build/ Framework/src/*.java
cd Framework/build
jar -cf ../../WebGeneralizer.jar etu1832 utilitaires
cd ../../
IF NOT EXIST WebApplication/build/WEB-INF/lib. mkdir WebApplication\build\WEB-INF\lib
copy WebGeneralizer.jar WebApplication\build\WEB-INF\lib
IF NOT EXIST WebApplication/build/WEB-INF/classes. mkdir WebApplication\build\WEB-INF\classes
SET CLASSPATH=.\WebApplication\build\WEB-INF\lib\WebGeneralizer.jar
javac -d WebApplication/build/WEB-INF/classes WebApplication/src/*.java
cd WebApplication\build
jar -cf ../../WebApplication.war WEB-INF
cd ../../
copy WebApplication.war C:\"Program Files"\"Apache Software Foundation"\"Tomcat 8.5"\webapps