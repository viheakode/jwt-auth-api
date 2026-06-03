# jwt-auth-api
Step 1
1. Create database in PostgreSQL
2. Past env viriable in project
  SERVER_PORT=8080
  PG_DB_URL=jdbc:postgresql://localhost:5432/[your database name]
  PG_DB_USERNAME=[your database username]
  PG_DB_PASSWORD=[your database password]
  JWT_SECRET_KEY=[your scecret key]
  JWT_EXPIRATION_TIME=86400000
Step 2
  1. Go to src\main\java\com\viheakode\api\config\SecurityConfig.java
     disable line 21 //@EnableMethodSecurity
     enable line 35 // .requestMatchers("/start").permitAll()
  2. Run project 
  3. in postman Method: GET URL: localhost:8080/start (esponse: Project started.nsert sample data into database)
  4. Go to src\main\java\com\viheakode\api\config\SecurityConfig.java
     enable line 21 //@EnableMethodSecurity
     disable line 35 // .requestMatchers("/start").permitAll()
Step 3
  1. Restart proejct
  2. in postman Method: Post URL: localhost:8080/api/v1/auth/autheticate
  {
      "username": "s.admin",
      "password": "viheakode"
  }
  3. done
  

  
