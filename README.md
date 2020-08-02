# Getting Started

### How to run
local: gradle appengineRun
appengine: gradle appengineDeploy

### Application flow
1. Process creator authorization and download its channels memberships
2. Authorize supporter by his google account, check if has subscription to creator youtube channel, 
save his email and subscribed channel data

### Creator flow
To get creator channel memberships application has to authorize creator using his google account:
* run application: ./gradlew bootRun
* go to login page: http://localhost:8080/login
* click button: "login with google"
* authorize with Tyrion Frey (test creator account)
* after correct authorization and redirect to page http://localhost:8080/loginSuccessCreator where is than you message
* creator auth token is saved in repository
* backend process triggered by cron refresh creator token and update creator memberships list

### Supporter flow
Supporter youtube membership is verified using google authorization, and his email data is saved:
* go to login page: http://localhost:8080/login
* click button: "login with google"
* authorize with supporter google auccount that join to some of creator youtube channel 
* after correct authorization and redirect to page http://localhost:8080/loginSuccess will be visible 
form for putting email address
* email address included as default in form is address parsed from supporter google profile
* it could be changed and submitted
* after submitting it is saved in repository and send to fourthwall api

### Technical information:
- google authorization is done using spring-security-web and spring-security-oauth2 libraries
- google profile data, youtube user channels data and youtube memberships data are fetched using google apis,
there are retrofit interfaces (for memberships api genereted from tapir definitions) and okhttp client is used.
- data is stored in memory repositories implementation, so application restart forces memberships fetching rerun


