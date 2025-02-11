# OSRS_SpringBoot_Project
This is a Project I created to get myself more familiar with Spring Boot, REST APIs, and MongoDB Databases. The intention of this Project is to develop a REST API that retrieves Old School Runescape video game information and return that data to the front end to be displayed.

Currently, I am in Phase 1, which only consists of the Spring Boot backend code with MongoDB. Therefore, It is only possible to use this software alongside Postman.\
I am also using my own private MongoDB database, therefore the connection string is not included in the repository. You will need to create your own MongoDB database if you wish to use this software.\
I am getting the OSRS data from the "Application Programming Interface" available. You can find information about that here: https://runescape.wiki/w/Application_programming_interface 

Phase 1: (Develop a Backend REST API Endpoint for OSRS Data)\
Create "Player" endpoint that will be responsible for getting Player data. For now, this will primarily be for Skill data.\
Create ability to Get, Save, and Send player data to the Front End. Since I am starting with the Backend, I am testing this with Postman.\
Currently have the following Endpoints created:\
GET /player/getPlayerData\
GET /player/getPlayerSkillData\
GET /player/deletePlayerData\
TBD

Ideally, I would like to return more than one player's data at a time, while also allowing for the ability to compare skill statistics.

Phase 2: (Develop a Front End Web Application to display the OSRS Data that I have saved in my MongoDB Database.)\
Haven't thought too much about this yet. Will decide on whether to do this in Angular or React at a later time.\

I will add more to this once I start to develop more ideas and make more progress.