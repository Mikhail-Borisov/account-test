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

After that server starts serving requests from http://localhost:5000

## REST routes description

All endpoints use JSON.

### Create account
POST /api/account/create

Request body:
{ "email": "johndoe@mail.com" }

Response body:
empty body with status 200 or
{ "error": "Account with email 'johndoe@mail.com' already exists" }

### Account details
POST /api/account/details

Request body:
{ "email": "johndoe@mail.com" }

Response body:
{ "email": "johndoe@mail.com", "amount": 0 } or
{ "error": "Account with email 'johndoe@mail.com' does not exist" }

### Top up account
POST /api/account/topUp

Request body:
{ "email": "johndoe@mail.com", "amount": 100 }

Response body:
empty body with status 200 or
{ "error": "Account with email 'johndoe@mail.com' does not exist" }

### Withdraw from account
POST /api/account/withdraw

Request body:
{ "email": "johndoe@mail.com", "amount": 100 }

Response body:
empty body with status 200 or 
{ "error": "Account with email 'johndoe@mail.com' does not exist" } or
{ "error": "Account with email 'johndoe@mail.com' does not have enough money to withdraw"

### Transfer money
POST /api/account/transfer

Request body:
{ "sourceEmail": "johndoe@mail.com", "targetEmail": "goodfriend@mail.com", "amount": 100 }

Response body:
empty body with status 200 or
{ "error": "Source account with email 'johndoe@mail.com' does not exist" } or
{ "error": "Target account with email 'goodfriend@mail.com' does not exist" } or
{ "error": "Account with email 'johndoe@mail.com' does not have enough money to transfer" }
