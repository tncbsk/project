
# Reading is good

## Technologies

* JDK 11
* MongoDB  
* Spring boot
* JWT
* Lombok
* Mapstruct
* Swagger
* Mockito

#### Online book retail API

Server runs with Spring-Security using Bearer Token authentication. Email will be used as `user_name`

## Controllers(User, Book, Order, Statistic)

The description for each controller is defined by the swagger open api.


##Database Tables


"customer" : {
    "name": "String",
    "surname": "String",
    "email": "String",
    "password": "String"
}

"books" : {
    "id": "String",
    "bookName": "String",
    "isbn": "String",
    "writer": "String",
    "existYear": "String",
    "stock": "Long",
    "price": "Doublt"
}

"orders" : {

    "id": "String",
    "Customer": "customer",
    "amount": "BigDecimal",
    "orderDate": "DateTime",
    "status": "Status",
    "books": "OrderBookListModel",


## Rest Api

After running project you can try endpoints with http://localhost:8080/swagger-ui/index.html

