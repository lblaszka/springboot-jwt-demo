# springboot-jwt-demo
JWT + Spring security.

## Endpoints:
* <b>/getToken POST</b>  - generete JWT token. On requset body insert {"username":"user","password":"user"} for get user access, or {"username":"user","password":"user"} to get admin access.
* <b>/demo/forAll GET</b>  - this endpoint should be enable for all.
* <b>/demo/forUser GET</b> - this endpoint shoud be enable only for user with access "ROLE_USER".
* <b>/demo/forAdmin GET</b>  - this endpoint shoud be enable only for user with access "ROLE_ADMIN".
* <b>/demo/me GET</b>  - show datas from JWT. If jwt is incorrect or not included show "you are nothing".

ps. Admin accout has "ROLE_USER" and "ROLE_ADMIN".

When you got token from /getToken, add to header "Authorization" with "Bearer " on begin. 
