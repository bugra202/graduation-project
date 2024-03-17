# Project Title and Description
## Restaurant Recommendation Application To The User
This application records, lists, updates and deletes users, comments and restaurant information. It also offers three restaurant recommendations to the user, taking into account the user's location, the restaurant's location and the restaurant's average score.
### Main Features
* **Modular structure**: Since the project has a modular structure, developers can add new features or remove existing features according to their needs.
* **Scalability**: The application is optimized for large-scale projects and can scale to meet high performance requirements.
* **Flexible structure**: Thanks to the flexible structure of the project, developers can integrate with different technologies and customize the features of the application.

**This application** is designed to enable users to reach restaurants comfortably and to prevent users from losing possible time and fuel.

# Installation
## Installation
Follow the steps below to install and run the application:

**1.Copy Project**

First, clone the project from GitHub to copy it to your local computer:

[Click the link to clone](https://github.com/bugra202/graduation-project.git)

**2.Open Intelijj Idea**

Paste the cloning link you copied from the link above into the URL section by clicking file -> new -> Project from version control and click clone.

![image](https://github.com/bugra202/graduation-project/assets/117045544/3ec7db44-a468-4907-845f-60aaab5970cc)

**3.Install Docker**

Install and run Docker on your computer

[click on the link and install docker](https://www.docker.com/products/docker-desktop/)

**4.Application Running Stages.**

* First, we go to the lifecyle section in the graduation-project section from Maven and perform the clean and then install process.

![image](https://github.com/bugra202/graduation-project/assets/117045544/f86e90a7-474c-4d1f-a966-41f1ffcaa228)

* Then in the services section we have three services. It is recommended to run the GraduationProjectCoreApplication service first, as docker-compose will create the containers by pulling the required images from docker hub. You can then run other services regardless of their order.

  ![image](https://github.com/bugra202/graduation-project-n11/assets/117045544/6d97b179-868a-4d27-96f8-ef6ed89d46ff)

* How to change port number? if you wanna change port number, write (server: port: 8081) command on application.yml in src/main/resources/application.yml. you can change port number in applicaton.yml.

  ![image](https://github.com/bugra202/graduation-project-n11/assets/117045544/7740b75b-34a4-49cb-9a79-a759a8f4a729)


* You had bettet to look at this applicaton.yml pls. Database's table can be generated automatically, İf you write spring:jpa:hibernate:ddl-auto:create-drop on application.yml.
your database's table can create regularly when you wrote spring:jpa:hibernate:ddl-auto:create on application.yml

![image](https://github.com/bugra202/graduation-project-n11/assets/117045544/ab47837e-7bee-4127-b1f9-8ee2fe417549)

# Access Links To Applications Running in the Docker Environment

* When docker-compose runs, the necessary features for Postgresql, Solr and Kafka will be created automatically, you do not need to take any extra action. You can use the application directly.

* **pg4Admin** -> localhost:5050
  
* **solr** -> http://localhost:8983/solr/
  
* **kafka** -> http://localhost:9090/

# Swagger Documantation

* The application is compatible with Swagger UI and every service is documented. You can connect to the Swagger interface via the port where the services are running and perform the necessary operations.

* Sample Swagger UI Link -> http://localhost:8080/swagger-ui/index.html#

# License

* This project is licensed under the MIT License, which allows users and developers to use, modify, and distribute the project freely. This license describes the terms for use and distribution of the project, as well as a limited warranty granted by the project's copyright owner.

# Contact

* **Email** : tsdmr.bugra@gmail.com
* **Linkedin** : [Account](https://www.linkedin.com/in/bugratasdemir/)



