
# REST API services with Spring Boot

Developed by [![Linkedin: ricardoalexandreribeiro](https://img.shields.io/badge/-Ricardo%20Ribeiro-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ricardoalexandreribeiro/)](https://www.linkedin.com/in/ricardoalexandreribeiro/)

If you want, read the reference documentation about [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## About the application

This repository contains an example REST API Services with Spring Boot.

The purpose of the application is:
* Access and manipulate information in a PostgreSQL Database from a set of methods to create (POST), read (GET), change (PUT) and delete (DELETE), by HTTP messages.
* The idea is to manage the voting agenda. Then, the application allows to register members who can vote and agendas to be voted.


## Developed With

* **Spring Boot** Java framework used to create a micro Service.
* Database **PostgreSQL** to store the data, and **DB2** in memory for tests.
* **Maven** as Dependency Management System, and **Lombok Java library** to improve productivity.
* **Eclipse** as IDE.
* **Swagger** using a UI module that allows to interact with the our API. 
* **Heroku** for delploy an API.


## How to test the API using 

- You can test the API using the Swagger:

		https://assembleia-api-sicred.herokuapp.com/swagger-ui.html

- Or you can access through the address:

		https://assembleia-api-sicred.herokuapp.com/
		
### Using the end points: "/member/"


* GET (/member) - List all Members


* DELETE (/member/{id}) - Delete an existing Member

		
* POST (/member) - Insert a new Member
		
		{
		  "name": "string",
		  "cpf": 0
		}
		
* PUT (/member/{id}) - Update an existing Member
		
		{
		  "name": "string",
		  "cpf": 0
		}

### Using the end points: "/agenda/"


* GET (/agenda) - List all Agendas


* POST (/agenda) - Insert a new agenda with description
		
		{
		  "description": "string",
		}
		
* PUT (/member/{id}) - Open the agenda session, if the expiration date was informed empty(""), it will be added 01 minute.
		
		{
		  "expiration": "2021-03-01 15:00:00",
		}

### Using the end points: "/vote/"


* POST (/vote) - Register the member vote
		
		{
		  "description": "string",
		}
		
* PUT (/member/{id}) - Open the agenda session, if the expiration date was informed empty(""), it will be added 01 minute.
		
		{
		  "expiration": "2021-03-01 15:00:00",
		}
		
		
## How to develop
* You will need a Windows or Linux with Java/OpenJDK.
* Application is using Maven, so all required libraries should be downloaded automatically.
* Clone the git repository using the URL on the Github home page:

		`https://github.com/ralexandre11/assembleia-api`

		`$ cd assembleia-api`

* To buid the image Docker, use the command:

		`$ mvn package`

