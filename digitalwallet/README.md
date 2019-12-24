Digital wallet

Spring boot Digital wallet Application
Digital wallet application provide various REST APIs to manage digital wallet

REST API end points
# Add account
 URL : http://localhost:8080/accounts
 Header : Content-Type:application/json
 Request Body :
 {
   "name":"digital-wallet-3",
   "balance": 0,
   "recharged": false,
   "promotionalBalance":{
      "balance":100
   }
 }
Response: Return saved account object
Error handling : balance should not be less than 0
Error response :
{
    "timestamp": "2019-12-23T23:52:50.831+0000",
    "status": 400,
    "errors": [
        "must be greater than or equal to 0"
    ]
}

# Add Balance
 URL : http://localhost:8080/accounts/<id>/balance
 Header : Content-Type:application/json
 Request Body :
 {
  "rechargeAmount":300,
  "promocodes":["WELCOME","SALTSIDE"]
 }
Response: Return saved account object
Error handling:
 # Requesting account id not in the database
   {
    "timestamp": "2019-12-24T01:49:58.400+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Record not found",
    "path": "/accounts/5e0152ceed84905c69d41bda1"
    }

#Use Balance
 URL : http://localhost:8080/accounts/<id>
 Header : Content-Type:application/json
 Request Body :
 {
   "balance":50
 }
Response : Return account with updated balance
Error handling : 
 # use balance should not more than current balance in the account
   Error response:
   {
    "timestamp": "2019-12-24T01:49:08.104+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Account does not have enought balance",
    "path": "/accounts/5e0152ceed84905c69d41bda"
   }
 # Requesting account id not in the database
   {
    "timestamp": "2019-12-24T01:49:58.400+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Record not found",
    "path": "/accounts/5e0152ceed84905c69d41bda1"
    }

# check Balance
 URL : http://localhost:8080/accounts/<id>
 Response : Return balance for given account id
 Error handling
  {
    "timestamp": "2019-12-24T01:49:58.400+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Record not found",
    "path": "/accounts/5e0152ceed84905c69d41bda1"
    }


 
mvn clean install
mvn spring-boot:run


