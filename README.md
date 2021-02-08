# redisImpl
test task for avito. implementation of redis on spring boot + spring web

## Launch:

- docker-compose up
- open in browser -> localhost:8080

## Commands:

### Strings:
- GET /strings - print all strings
- GET /strings/{key} - redis get string
- GET /strings/keys - print all keys
- POST /strings/{key}/{value} - redis set string
- POST /strings/{key}/{value}/{ex} - redis set string with ttl
- DELETE /strings/{key} - redis del string

### Lists:
- GET /lists - print all lists
- GET /lists/keys - print all keys
- GET /lists/{listName} - redis get list
- GET /lists/{listName}/{index} - redis get string with index from list
- POST /lists/{listName}/{index}/{value} - redis put value with index in list
- POST /lists/{listName}/{index}/{value}/{ex} - redis put value with index in list with ttl
- DELETE /lists/{listName} - redis del list
- DELETE /lists/{listName}/{index} - redis del value from list

### Maps








