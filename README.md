# Accounts test problem

## Problem description

Build a small account processing system where users can top up their accounts with money,
withdraw and transfer money to other accounts.

## Building and running

To build the project, run in project root directory:

```shell script
mvn clean package
```

After that, you can run the JAR with:

```shell script
java -jar target/account-1.0-SNAPSHOT-jar-with-dependencies.jar
```

and the server starts serving requests from http://localhost:5000

## REST routes description

All endpoints use JSON.

### Create account
POST /api/account/create

Request body:
```json
{ "email": "johndoe@mail.com" }
```
Response body:
```text
Empty body with status 200
```

### Account details
POST /api/account/details

Request body:
```json
{ "email": "johndoe@mail.com" }
```
Response body:
```json
{ "email": "johndoe@mail.com", "amount": 0 }
```

### Top up account
POST /api/account/topUp

Request body:
```json
{ "email": "johndoe@mail.com", "amount": 100 }
```
Response body:
```text
Empty body with status 200
```

### Withdraw from account
POST /api/account/withdraw

Request body:
```json
{ "email": "johndoe@mail.com", "amount": 100 }
```
Response body:
```text
Empty body with status 200 
```

### Transfer money
POST /api/account/transfer

Request body:
```json
{ "sourceEmail": "johndoe@mail.com", "targetEmail": "goodfriend@mail.com", "amount": 100 }
```
Response body:
```text
Empty body with status 200
```
