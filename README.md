# Login Demo API

#### Spring boot app to demonstrate simple user log in features

The LoginController class creates 2 dummy users that are stored in the embedded H2 database.
The controller has 5 endpoints :
* ***/login***:  which takes a User object. If it finds a user that matches the username/password pair in the H2 database it will fist delete it and then create a new user with the updated properties and add it to the database. To access the endpoint you have to send the data as a JSON body.

* ***/dummy-{username}***: This endpoint takes a username and checks if that user is logged in. If it is then it will call the logout function after 1 min.  To access the endpoint you have to replace "username" with the username of the account you want to test.

* ***/users***: This endpoint returns the list of all users.

* ***/add-user***: From this endpoint users can be added to the database. It will only accept if both the username and password are sent as parameters. To access this end point and add users you have to send the user data as a JSON body.

* ***/logout-{username}***: This endpoint is used to log out a particular user. To access the endpoint you have to replace "username" with the name of the user you want to log out of.

All the endpoints mentioned above send a String response indicating the status of the request.

The user class is a basic Spring Bean with the getter and setters and a to string 