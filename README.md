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
There are two services, main and notification.
Main is on 8080 port, the notification is on 8082.

## Note

In this version of app, The client is an ADMIN, and the customer is USER role.

The following things were not implemented and added:
- Fully configured Security and JWT token. You can find integration with Keycloak in git history.
- Unit and Integration tests.
- No endpoint to search clients and contacts by different criteria (Can be achieved with Specification interface)
- Wasn't enough time to test N+1 problem, so it is possible (Can be fixed with @EntityGraph or FETCH Joins in case of custom queries)
- Documentation of API. But Postman collection is present in the root folder.
- Web view is raw.
- Credentials were not extracted to env file.

In the version of the app with keycloak there will be ADMIN, CLIENT and CONTACT roles and all desired functionality for each of them.\

I spent much more time than I wanted to implement web with keycloak auth, so, I decided to revert changes and continue developing it beyond the test task. 


