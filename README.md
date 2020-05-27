# Spring Data Redis with JWT

The point of this project was how to configure and use benefits of Redis with Microservices.
Secure the endpoint with JWT (Json Web Token). 
I have provided a Java Configuration class for the Redis Beans using Spring-data. 
Also in the example there is a simple CRUD api provided that is based on H2 as the DAO.

## How it works?
Basically, when you hit a request to retrieve the task list for the user the second microservice should get the user's data
from the database based on id or token. And it is expensive since it is `I/O` operation.
 The second option we can include User's data on the fly via an HTTP call.
 But this approach will increase the bandwidth. 
Here distributed caching coming to help.  When we successfully logged in Redis will store data for us with a key.
In other services which is called `task-service` with the helping of caching it will take the user's data from the cache.
So there is no need to lookup the user the second time.

## Redis
Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker. 
It supports data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs,
 geospatial indexes with radius queries and streams. Redis has built-in replication, Lua scripting, LRU eviction, 
 transactions and different levels of on-disk persistence, and provides high availability via Redis Sentinel and 
 automatic partitioning with Redis Cluster.


##### What is Distributed caching
In computing, a distributed cache is an extension of the traditional concept of cache used in a single locale. 
A distributed cache may span multiple servers so that it can grow in size and in transactional capacity. 
It is mainly used to store application data residing in database and web session data.

## JWT
Authentication is the most common scenario for using JWT. Once the user is logged in,
 each subsequent request will include the JWT, allowing the user to access routes, services, 
 and resources that are permitted with that token. Single Sign On is a feature that widely uses 
 JWT nowadays, because of its small overhead and its ability to be easily used across different 
 domains.


In this sample application, the custom JWT token based authentication flow can be designated as the following steps.

1. Get the JWT based token from the authentication endpoint, /login.
2. Extract token from the authentication result.
3. Set the HTTP header Authorization value as Bearer jwt_token.
4. Then send a request to access the protected resources. /tasks POST || GET
5. If the requested resource is protected, Spring Security will use our custom Filter to validate the JWT token, 
and build an Authentication object and set it in Spring Security specific SecurityContextHolder to complete the authentication progress.
6. If the JWT token is valid it will return the requested resource.

## Create a sample REST APIs

In this application, we will expose REST APIs for vehicle resources.

URI|Request|Response|Description
---|---|---|---
/user|GET|200, [{id: 1, name:'title'}, {id:'2', name:'title 2'}]| Get User
/user|POST {username:"username", password: "password"} | 201, no content in body, the value of HTTP response header **Location** is the uri of the new created vehicle| Create a new user
/login|POST {username:"username", password: "password"}| 204, No content | Login request, Header will include  Authorization → Bearer 
/tasks|GET|200, [ {"id": 1,"user": {"id": 1,"username": "user_name","password": "password"},"name": "task1","description": "taskDesc","deadline": null},]| Get all task list, Header must include Authorization → Bearer :token 
/tasks|POST| 201, no content in body, the value of HTTP response header **Location** is the uri of the new created vehicle| Create a new task, Header must include Authorization → Bearer :token




## To Run 
You can download docker version and run https://redis.io/download
`default port is 6379`

##### Terminal from the folder
`$ mvn clean spring-boot:run`

##### There are two microservices (you can clone other from `task-service` branch) and run.
`$ mvn clean spring-boot:run`



