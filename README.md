# Hotel Rooms Reservations

A Spring Boot-based hotel room reservation system. The project is designed to manage users, room bookings, prices, discounts, and additional services for reserved rooms.

## Description

The application allows:

- registering and managing users;
- adding, updating, and deleting rooms;
- reserving rooms for a user with balance and availability checks;
- applying discounts to rooms for a specific period;
- automatically releasing booked rooms after the reservation time expires;
- purchasing additional services for a reserved room;
- using basic authorization with Spring Security and roles such as USER and ADMIN.

## Technologies

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- Hibernate
- Gradle
- Lombok

## Project Structure

```text
src/
├── main/
│   ├── java/com/duikt/hotelroomsreservations/
│   │   ├── controller/      # REST controllers
│   │   ├── dto/             # DTO requests and responses
│   │   ├── entity/          # JPA entities
│   │   ├── exceptions/      # custom exceptions
│   │   ├── repository/      # JPA repositories
│   │   ├── security/        # Spring Security configuration
│   │   ├── service/         # service interfaces
│   │   └── service/impl/    # service implementations
│   └── resources/
│       └── application.properties
└── test/
    └── java/...             # tests
```

## Main Entities

### User

- id
- name
- email
- password
- role
- balance
- rooms
- additionalServices

### Room

- id
- roomNumber
- type
- price
- discountedPrice
- isAvailable
- buyDate
- busyTo
- discountTo
- user

### AdditionalServices

- id
- serviceName
- price
- room
- user

## API

Base prefix: `/api`

### Users

- `POST /api/users` — create a user
- `GET /api/users` — get all users (ADMIN)
- `GET /api/users/{id}` — get user by ID
- `PUT /api/users/{id}` — update a user (ADMIN)
- `DELETE /api/users/{id}` — delete a user (ADMIN)

### Rooms

- `POST /api/rooms` — create a room (ADMIN)
- `GET /api/rooms` — get all rooms
- `GET /api/rooms/{id}` — get a room by ID
- `GET /api/rooms/users/{id}` — get rooms by user ID
- `GET /api/rooms/number/{roomNumber}` — get rooms by room number
- `PUT /api/rooms/{id}` — update a room (ADMIN)
- `PUT /api/rooms/{roomId}/users/{userId}/buy` — reserve a room for a user
- `PUT /api/rooms/{roomId}/discount` — set a discount (ADMIN)
- `DELETE /api/rooms/{id}` — delete a room (ADMIN)

### Additional Services

- `POST /api/additional-services` — create a service
- `GET /api/additional-services` — get all services
- `GET /api/additional-services/{id}` — get a service by ID
- `PUT /api/additional-services/{id}` — update a service
- `DELETE /api/additional-services/{id}` — delete a service
- `GET /api/additional-services/buy/{serviceId}/room/{roomId}/user/{userId}` — purchase a service for a user’s room

## Security

The project uses Spring Security with HTTP Basic authentication.

Roles:

- `USER` — reserve rooms and purchase additional services;
- `ADMIN` — manage rooms and users.

New users can also register through `POST /api/users` without prior authentication.

## Database

PostgreSQL is required to run the app.

Default configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/course_work
spring.datasource.username=postgres
spring.datasource.password=1111
spring.jpa.hibernate.ddl-auto=update
```

Before running the application, create the `course_work` database or change the settings to match your PostgreSQL instance.

## Running the Project

### Requirements

- JDK 17+
- PostgreSQL
- Gradle Wrapper (included in the project)

### Commands

1. Clone the repository:

```bash
git clone https://github.com/Dexter2343/Hotel-Rooms-Reservations.git
cd Hotel-Rooms-Reservations
```

2. Create a PostgreSQL database and configure the connection in `application.properties`.

3. Run the project:

```bash
./gradlew bootRun
```

4. Run tests:

```bash
./gradlew test
```

## Notes

- Rooms are automatically released after `busyTo` expires through a scheduled task (`@Scheduled`).
- Discounts are also automatically cleared after `discountTo` expires.
- The user balance is checked during room booking and service purchase.

## License

This project was developed as an educational/coursework hotel reservation management service. If needed, a specific license can be added or the description can be adjusted for production use.
