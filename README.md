# ResourceManager
A web-based application which allows consultants to enter their skill-set and have project managers and team leaders look up a skill set to identify availability of the skills to map onto a project time line.

Import the project in to eclipse and run Application.java as a Java Application.
It will then launch an embedded tomcat server which you can access in your web browser at https://localhost

If you have any errors you might need to right click on the project > Maven > update project to download required library files.
Using the default application.properties file it does require that MySQL server is installed and running using the default settings, and that a database has been created called testDB.
Otherwise another properties file has been created for integration tests and is not for use in a production environment. Replacing application.properties under src/main/resources with the contents of application-integrationtest.properties from src/test/resources will instead launch the application using an embedded H2 database and not require an installation of MySQL server.
