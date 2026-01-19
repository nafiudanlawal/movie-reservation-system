# Movie Reservation Service

## Functionality
This service creates a restful API for movie reservation. Here are the key functions

- User Authentication & Authorization with JWT Auth 
- Movie CRUD functions
- Genre CRUD for admins
- Venue CRUD for admins
- Showtime for movies
- Reservation for users

## Requirements
- Postgresql 17.4+
- AWS *S3 (publicly accessible)*
- Java 21+
- Maven
- *Docker - optional*

## Run Locally
### Docker
This method requires that docker is installed. Change the environment variables for AWS credentials.

Build image
```bash
docker build -t movie-api .  
```
Run service
```bash
docker compose up
```

### Local OS
Running the application on your OS - Windows or Linux

Set Environment variables

Linux
```bash
 export AWS_ACCESS_KEY_ID="[key_id]"
 export AWS_SECRET_ACCESS_KEY="[secret_key]"
 export AWS_S3_BUCKET="[bucket_name]"
 export AWS_REGION="[region"
```
Windows Powershell
```bash
 $env:AWS_ACCESS_KEY_ID="[key_id]"
 $env:AWS_SECRET_ACCESS_KEY="[secret_key]"
 $env:AWS_S3_BUCKET="[bucket_name]"
 $env:AWS_REGION="[region]"
```
*replace key_id, secret, bucket_name and region to your AWS account.*

Run application
```bash
mvn spring-boot:run
```
## API Documentation
After you run the application the API spec is at 

```/swagger-ui/index.html```

```/v3/api-docs```

## References
- [Source Project](https://roadmap.sh/projects/movie-reservation-system)

## Database Design
![img.png](db-design.png)