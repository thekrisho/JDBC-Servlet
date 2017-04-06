# JDBC-Servlet
A mini java server to access MS Access database through web gui

- Host with Tomcat 9.0
- Written to be used with my Android Sensor Monitor
- Simple GET and POST functions
- Java sdk1.8 to compile

Instructions:
- Copy "Database2.accdb" to C://temp/
- Copy "web.xml" to webapps/midp/WEB-INF/
- Copy "entries.java" to webapps/midp/WEB-INF/classes/
- Complile "entries.java" with /lib/servlet-api.jar
- Restart Tomcat


Compile Example (Your compile script should look something like this):

javac -classpath .;"C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar" "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\midp\WEB-INF\classes\entries1.java"


