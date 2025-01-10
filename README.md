# OXTEST Back End Service

To run the project install java 17.
Enable annotation processing in your IDE.

## Run two docker containers:
### `Postgres`
Pull with command
#### `docker pull postgres`
and start container with
#### `docker run --name postgresox -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres`

### `Redis`
Pull with command
#### `docker pull redis`
and start container with
#### `docker run -d -p 6379:6379 --name redisox redis`

## Maven

Run `maven install` command.

## Run the project.
It will be opened on 8080 port.

## Note

To test the logic on web, firstly, it is needed to create Client (Which is ADMIN user by default) with Postman. All requests in the root. Contact is USER role by default and may be created from web.

Since the given task completion time is 2 days, the following things were not implemented and added:
- Desired microservice architecture.
- Fully configured Security and JWT token.
- Unit and Integration tests.
- No endpoint to search clients and contacts by different criteria (Can be achieved with Specification interface)
- Wasn't enough time to test N+1 problem, so it is possible (Can be fixed with @EntityGraph or FETCH Joins in case of custom queries)
- Documentation of API. But Postman collection is present in the root folder.
- Web view is raw. There is no ability to CRUD operations from web for Client. But it can be achived with Postman. Also, there is almost no functionality for USER role customers.



