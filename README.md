<p>Digital wallet</p>
<p>Spring boot Digital wallet Application Digital wallet application provide various REST APIs to manage digital wallet REST API end points</p>
<p>mvn clean install</p>
<p>mvn spring-boot:run</p>
<p># Add account URL : <a href="http://localhost:8080/accounts ">http://localhost:8080/accounts </a></p>
<p>Header : Content-Type:application/json</p>
<p>Request Body : { "name":"digital-wallet-3", "balance": 0, "recharged": false, "promotionalBalance":{ "balance":100 } }</p>
<p>Response: Return saved account object</p>
<p>Error handling : balance should not be less than 0</p>
<p>Error response : { "timestamp": "2019-12-23T23:52:50.831+0000", "status": 400, "errors": [ "must be greater than or equal to 0" ] }</p>
<p># Add Balance</p>
<p>URL : <a href="http://localhost:8080/accounts/id/balance ">http://localhost:8080/accounts/id/balance </a></p>
<p>Header : Content-Type:application/json</p>
<p>Request Body : { "rechargeAmount":300, "promocodes":["WELCOME","SALTSIDE"] }</p>
<p>Response: Return saved account object</p>
<p>Error handling:</p>
<p># Requesting account id not in the database</p>
<p>{ "timestamp": "2019-12-24T01:49:58.400+0000", "status": 404, "error": "Not Found", "message": "Record not found", "path": "/accounts/5e0152ceed84905c69d41bda1" }</p>
<p>#Use Balance</p>
<p>URL : <a href="http://localhost:8080/accounts/id">http://localhost:8080/accounts/ id</a></p>
<p>Header : Content-Type:application/json</p>
<p>Request Body : { "balance":50 }</p>
<p>Response : Return account with updated balance</p>
<p>Error handling :</p>
<p># use balance should not more than current balance in the account</p>
<p>Error response:</p>
<p>{ "timestamp": "2019-12-24T01:49:08.104+0000", "status": 400, "error": "Bad Request", "message": "Account does not have enought balance", "path": "/accounts/5e0152ceed84905c69d41bda" }</p>
<p># Requesting account id not in the database</p>
<p>{ "timestamp": "2019-12-24T01:49:58.400+0000", "status": 404, "error": "Not Found", "message": "Record not found", "path": "/accounts/5e0152ceed84905c69d41bda1" }</p>
<p># check Balance</p>
<p>URL : <a href="http://localhost:8080/accounts/ id">http://localhost:8080/accounts/ id</a></p>
<p>Response : Return balance for given account id</p>
<p>Error handling</p>
<p>{ "timestamp": "2019-12-24T01:49:58.400+0000", "status": 404, "error": "Not Found", "message": "Record not found", "path": "/accounts/5e0152ceed84905c69d41bda1" }</p>
<p>&nbsp;</p>
