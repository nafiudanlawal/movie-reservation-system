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

## Run locally
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

## References
- [Source Project](https://roadmap.sh/projects/movie-reservation-system)

## Database Design
![img.png](db-design.png)