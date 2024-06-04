/* REQUIREMENT */

1. User has Google Chrome installed on their system.
2. User has JDK 1.7 installed on their system
3. User has Maven installed with proper environment variables

/* How to Run */

1. Right click on project and click on run as 'Maven test'

/* Run with Command Prompt */

1. Open Command Prompt
2. Go to project directory 
3. Type "mvn compile" and press Enter
4. After it's done type 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml' and press Enter


/* Reporting */

1. After running the above commands
2. Check '/absorbTest/test-output/index.html' to see the report of the latest run