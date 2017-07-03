## Gaussian Solver, Spring, REST, MySQL
Implementation of a ParallelGaussianSolver with REST service endpoints and database<br/>

### Technologies
Application: Spring, SpringBoot  <br />
Frontend: Spring Rest, Apache Tomcat <br />
Calculation: Parallel Gaussian Solver <br />
Database: MySQL <br />



### REST Service
2 Endpoints:  <br />
Send Calculation Request:  <br />
*curl -X POST -d '{"a":[2.0,4.0,5.0,-6.0],"b":[8.0,4.0]}' -H 'Content-Type: application/json' localhost:8080/*

Retrieve all stored requests:  <br />
*curl -X GET localhost:8080 <br />*


### Database Table Implementations
Clients implemented with JDBC driver <br />

MySQLClient_2 (standard): <br />
2 columns: id, JSON  <br />





### Steps
##### MYSQL
Start MySQL <br />
create database:  <br />
mysql –uroot –p –e “source src/main/resources_db/create_database.sql” <br />

Create table with sql scripts: <br />
mysql –uroot –p –e “source src/main/resources_db/create_table_client_2.sql” <br />

Create tables with Java Test: <br />
run 'createTable()' method via AppTest <br />


##### Run with Maven 
*mvn spring-boot:run* <br />








