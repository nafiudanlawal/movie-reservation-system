# Movie Reservation Service

## Introduction
This service creates a restful API for movie reservation. Here are the key functions

- User Authentication & Authorization with JWT Auth 
- Movie CRUD functions
- Genre CRUD for admins
- Venue CRUD for admins
- Showtime for movies
- Reservation for users

## Requirements
- Postgresql 17.4+
- AWS S3
- Java 21+
- Maven

## Run locally
```bash
mvn spring-boot:run
```

## References
- [Source Project](https://roadmap.sh/projects/movie-reservation-system)

## Database Design
![img.png](db-design.png)