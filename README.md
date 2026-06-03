# Project Setup Guide

## Prerequisites

* PostgreSQL installed and running
* Java 21 installed
* Maven installed
* Postman

---

## Step 1: Configure Database and Environment Variables

### Create PostgreSQL Database

Create a new PostgreSQL database.

Example:

```sql
CREATE DATABASE jwt_auth_db;
```

### Configure Environment Variables

Add the following environment variables to your project:

```properties
SERVER_PORT=8080

PG_DB_URL=jdbc:postgresql://localhost:5432/<your_database_name>
PG_DB_USERNAME=<your_database_username>
PG_DB_PASSWORD=<your_database_password>

JWT_SECRET_KEY=<your_secret_key>
JWT_EXPIRATION_TIME=86400000
```

---

## Step 2: Initialize Database

Open:

```text
src/main/java/com/viheakode/api/config/SecurityConfig.java
```

### Disable Method Security

Comment out:

```java
@EnableMethodSecurity
```

### Allow Public Access to Startup Endpoint

Uncomment:

```java
.requestMatchers("/start").permitAll()
```

### Run the Project

The application will automatically create the required database tables.

After the application starts, open Postman and send:

**Request**

```http
GET http://localhost:8080/start
```

**Response**

```text
Project started.
```

Execute the provided sample SQL script to insert initial data into the database.

---

## Step 3: Enable Authentication

Open:

```text
src/main/java/com/viheakode/api/config/SecurityConfig.java
```

### Enable Method Security

Uncomment:

```java
@EnableMethodSecurity
```

### Secure the Startup Endpoint

Comment out:

```java
.requestMatchers("/start").permitAll()
```

### Restart the Application

---

## Step 4: Authenticate

Open Postman and send:

**Request**

```http
POST http://localhost:8080/api/v1/auth/authenticate
```

**Body**

```json
{
  "username": "viheakode",
  "password": "viheakode"
}
```

**Response**

```json
{
  "token": "<jwt_token>"
}
```

Authentication completed successfully. You can now use the returned JWT token to access protected APIs.

---

## Notes

* Replace all placeholder values with your actual database configuration.
* Keep your JWT secret key secure.
* Include the JWT token in the Authorization header for protected endpoints:

```http
Authorization: viheakode <jwt_token>
```

  

  
