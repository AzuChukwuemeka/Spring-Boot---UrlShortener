# URL Shortener with Spring Boot, PostgreSQL, and Redis
## Note ENV FILE IS NEEDED AT THE ROOT FOLDER WITH THE VARIABLES

## DB_URL="jdbc:postgresql://localhost:5432/urlShortenerDb"
##DB_USERNAME="code"
## DB_PASSWORD="password"
Redis doesn't need a username for the application just a password which is same as DB password

This is a URL shortener service built with Spring Boot, PostgreSQL, and Redis, postgres and redis running in Docker containers. The service allows users to shorten URLs and redirects them when accessing shortened URLs.

## Features

- **Shorten URLs**: Accepts a long URL and returns a shortened version.
- **Redirect to Original URL**: Accepts a shortened code and redirects to the original long URL.
- **PostgreSQL**: Stores the long URLs and their corresponding shortened codes.
- **Redis**: Caches the shortened URL data for faster access.

## Tech Stack
- **Spring Boot**: Backend framework
- **PostgreSQL**: Relational database for storing URL mappings
- **Redis**: In-memory data store for caching shortened URL information
- **Docker**: Containerized environment for easy deployment

## API Endpoints

### 1. Shorten a URL

- **Endpoint**: `POST /api/v1/shorten`
- **Request Body**:
  ```json
  {
    "longUrl": "https://www.example.com"
  }
## RESPONSE
{
"shortUrl": "http://short.ly/abcd123"
}
### 2. Redirect to Original URL
   Endpoint: GET /api/v1/short/{code}

- Path Variable: code (the shortened URL code)

- Response: Redirects to the original URL

- Example: A GET request to /api/v1/short/abcd123 redirects to https://www.example.com.

Getting Started
Prerequisites
Docker: Install Docker to run PostgreSQL, Redis, and the Spring Boot application in containers.

Setup
Clone this repository:

bash
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener
Build and start the Docker containers for PostgreSQL, Redis, and the Spring Boot application:

bash
docker-compose up --build

The application will be available at http://localhost:8080.

Database Initialization
On startup, the PostgreSQL database will be initialized with necessary tables for storing the long URL and its corresponding shortened code.

Running the Application
Once the Docker containers are up and running, you can use the following API endpoints:

1. Shorten a URL:

Send a POST request to http://localhost:8080/api/v1/shorten with a JSON body containing the long URL.

Example request:

curl -X POST http://localhost:8080/api/v1/shorten -H "Content-Type: application/json" -d '{"longUrl": "https://www.example.com"}'
Redirect to Original URL:
2. Redirecting to Original URL

Send a GET request to http://localhost:8080/api/v1/short/{code} where {code} is the shortened code.
Example request:
curl -L http://localhost:8080/api/v1/short/abcd123

Feel free to fork this project Contributions are welcome!

