# DTU_CDIO_Final
This was a project, where a database, backend with REST service and frontend, along with a physical Mettler Toledo weight. This project includes 4 people's work over 15 days in total.

## Project description
The purpose of this project was to interact with a Mettler weight and implement a procedure, and according to the weighed amounts, creating, reading and updating values in the database. We had to implement CRUD functionality and communicate, with JSON through a REST web service, for managing users, products, recipes, resources and resource batches.

## Technologies required for running project
- Tomcat
- MySQL database

## Installation
(note: to run the weight procedure, you need a physical weight)

1. Create a database schema using the DDL script [found here](https://github.com/casperbh96/DTU_CDIO_Final/blob/master/SCRIPT_CDIO_FINAL_DDL.sql).

2. Insert dummy data by the DML script [found here](https://github.com/casperbh96/DTU_CDIO_Final/blob/master/SCRIPT_DIO_FINAL_DML.sql).

3. Pull the project and update the database connection in the [Connector class](https://github.com/casperbh96/DTU_CDIO_Final/blob/master/src/main/java/DataAccess/dao/Connector.java)
