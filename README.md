# UNISTUDY Backend
- Front repository <a href="https://github.com/uni-study/unistudy-frontend" target="_blank"><img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white"/></a>


  

## Environment
- Server
  - OS: Ubuntu 18.04
  - DB: MySQL 8.0.34 on Amazon RDS
  - Language: Java 11
  - Framework & Main Libraries: Springboot 2
  - Communication protocol: HTTP
 
- Deploy
  - EC2 Server: The application is deployed on an EC2 server.
  - RDS Database: MySQL 8 is used as the database on Amazon RDS.
  - Frontend: The frontend is hosted on Amazon S3, and CloudFront is used as a content delivery network.

## Main features
HTTP request APIs are implemented.
### User
- signup, login, logout with session
- check session
- find user and filtering according to the condition
- find my studygroup, my posts, my information

### Post
- write
- delete
- edit
- find posts and filtering
- write and delete comment


### study group controller
- form a group
- delete group
- apply group(for normal user)
- accept application(for group leader)
- withdraw group
- edit group information
- find all users in the studygroup
  


## Learn and Difficulties
It was the first time to build server using springboot. Also, It was first time to construct Database schema and make a plane for API requests. 
As a result, it was challenging to remove data anomaly from tasks involving fk construction or multiple DB, and it spent a lot of time. While developing the server, I was able to learn how to plan and design the overall api and database structure needed for the service.
Also, I experienced a lot of CORS errors in my connection to the front end. In the process of handling this error, I learned a lot about http or network.
It was very difficult to handling the error that occurs after deploying, because homepage works well in local environment. I've struggled in this area, and I'd like to learn more so I can solve it.
