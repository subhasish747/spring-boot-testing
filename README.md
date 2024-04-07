### Guides
The illustrate how to perfom Junit and Integration Test in testcontainer:


curl -X POST \
http://localhost:8080/spring-app/api/employees/save \
-H 'Content-Type: application/json' \
-d '{
"firstName": "John",
"lastName": "Doe",
"email": "johndoe@example.com"
}'


curl -X GET http://localhost:8080/api/employees/test