## Portfolio Monitor
### What is it?
#### It is a simple portfolio monitoring simulator
### How to use it?
#### Initialization
   1. Enter to the root directory of this project
   2. Execute the following command to copy the H2 database file
      1. Mac & Linux: "cp portfolio-monitor.mv.db ~/.h2"
      2. Windows: "copy portfolio-monitor.mv.db ~/.h2"
   3. The initial data has been imported into the database file, see "database-init.sql" for more details.
#### Run this project
   1. Mac & Linux: "./gradlew bootRun"
   2. Windows: ".\gradlew bootRun"
#### Change the initial data
   1. Change the security definition
      1. Open the "build.gradle", remove the "//" at the beginning of the "implementation 'org.springframework.boot:spring-boot-starter-web'"
      2. Run the project  
      3. Open "http://localhost:8080/h2-console", use the following parameters to connect
         1. "org.h2.Driver" as the Driver Class
         2. "jdbc:h2:file:~/.h2/portfolio-monitor" as the JDBC URL
         3. Empty username and password
      4. After connected, use SQL to query and update the current data
   2. The "position.csv" contains the position data. Here are some notes
      1. Make sure the position data can match the security definition data if any change be made
      2. The "PositionHolderTest" may be failed after modification
