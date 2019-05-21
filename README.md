# springboot-jwt-demo
JWT + Spring security.

## Endpoints:
* /getToken POST - generete JWT token. On requset body insert {"username":"user","password":"user"} for get user access, or {"username":"user","password":"user"} to get admin access.
* /demo/forAll GET - this endpoint should be enable for all.
* /demo/forUser GET - this endpoint shoud be enable only for user with access "ROLE_USER".
* /demo/forAdmin GET - this endpoint shoud be enable only for user whith access "ROLE_ADMIN".
* /demo/me - show datas from JWT. If jwt is incorrect or not included show "you are nothing".

ps. Admin accout has "ROLE_USER" and "ROLE_ADMIN".
