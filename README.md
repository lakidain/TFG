# End of degree project
## Introduction 
Welcome to the repository for my end of degree project. Here you will find an app (Front-end based on Angular and back-end on Spring Boot) to audit companies. Auditors can audit companies with questionnaires, manage audited company employees. <br />
The webpage can be found on https://app-upaudit.firebaseapp.com/index <br />
And you can find me on [Linkedin](https://www.linkedin.com/in/anderlakidain/) or [Github](https://github.com/lakidain)
![Technologies](https://miro.medium.com/max/785/1*I9paH_iKTmSeYs1HzRzuag.png)

---

### Repository content
- [Front-end](https://github.com/lakidain/TFG/tree/master/auditorias-app): Front-end based on **Angular**. Used Boostrap
- [Back-end](https://github.com/lakidain/TFG/tree/master/spring-boot-backend): Back-end based on **Spring Boot**. Used AWS S3, OAuth 2.0 and Itext.
- [Database](https://github.com/lakidain/TFG/tree/master/Resources): Prepared **Mysql** database filled with all the data needed to test the deploy

---

### How to deploy
Here I describe the steps to deploy the solution like I did, it's not the only way, for example you can use an EC2 Amazon Web Service machine.

#### Deploying Back-End
##### 1. Heroku configuration
1. Create or use an active account on Heroku. 
2. Create New App and install Heroku CLI. 
3. On the app resources -> Addons add JawsDB as a resource.
##### 2. Create AWS account
1. Create or use an active account on Amazon Web Service. 
2. Create a new user with S3 privileges. 
3. Create a new bucket on Amazon S3, select the region and put public privileges. After that create two folders pdf and images.
##### 3. Preparing Deploy
1. Open Windows PowerShell and use in order this commands
```
cd Project Directory
heroku login
heroku git:remote -a nameProjectOnHeroku
heroku plugins:install java
heroku addons:create jawsdb
heroku config:get JAWSDB_URL
```
On the last step you will get your DB connection settings.
##### 3. Preparing application.properties
Theres is an application.properties on the project. You have to change the following parameters
- spring.datasource.url=Obtained with JAWSDB_URL
- spring.datasource.username=Obtained with JAWSDB_URL
- spring.datasource.password=Obtained with JAWSDB_URL
- amazon.accessKey=Obtained when creating Amazon S3 user
- amazon.secretKey=Obtained when creating Amazon S3 user
- amazon.bucketRegion=Look for the link of your region. Paris is eu-west-3
- amazon.bucketName=Name of the bucket create on Amazon S3
___
#### Deploying Front-End
