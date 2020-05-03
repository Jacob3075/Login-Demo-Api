# Login Demo API

#### Spring boot app to demonstrate simple user log in features

The LoginController class creates 2 dummy users that are stored in the embedded H2 database.
The controller has 4 endpoints :
* ***/login***:  which takes a User object. If it finds a user that matches the username/password pair in the H2 database it will fist delete it and then create a new user with the updated properties and add it to the database. 
* ***/dummy***: This endpoint takes a username and checks if that user is logged in. If it is then it will call the logout function after 1 min.
* ***/users***: This endpoint returns the list of all users.
* ***/add-user***: From this endpoint users can be added to the database. It will only accept if both the username and password are sent as parameters.

The user class is a basic Spring Bean with the getter and setters and a to string 