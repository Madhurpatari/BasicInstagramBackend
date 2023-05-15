# Basic Instagram Backend Design
This project is the backend implementation of a simplified version of Instagram. 
It provides RESTful APIs for user authentication, post creation, and post retrieval.

## Technologies Used
* Java
* Spring Boot
* Spring Data JPA
* MySQL (or any other database of your choice)

## Getting Started
To get started with the project, follow these steps:

1.Clone the repository to your local machine.

2.Open the project in your preferred IDE.

3.Configure the database connection in the application.properties file.

4.Run the application.

## API Endpoints
The following are the available API endpoints:

### User API
* POST /user/signup: Sign up a new user.
* POST /user/signin: Sign in with an existing user.
* PUT /user/{userEmail}: Update user details.
### Post API
* POST /posts: Save a new post.
* GET /posts/{postId}: Retrieve a post by its ID.
Please refer to the source code for detailed request and response formats for each API.


## Database Schema
The project uses the following database schema:
### User Table
| Column   | Type           |
|----------|----------------|
| id       | BIGINT         |
| firstName| VARCHAR        |
| lastName | VARCHAR        |
| age      | INTEGER        |
| email    | VARCHAR        |
| phoneNumber | VARCHAR     |
| password | VARCHAR        |

### Post Table
| Column   | Type           |
|----------|----------------|
| id       | BIGINT         |
| created_date| LOCALDATETIME        |
| updated_date | LOCALDATETIME        |
| post_data      | VARCHAR        |
| user_id    | BIGINT        |


## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please create an issue or submit a pull request.







