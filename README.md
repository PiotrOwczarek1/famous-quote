# famous-quote
**Quotes of famous persons**

To start the application run following commands starting in the root directory 'famous-quote':

```
mvnw clean install
cd target
java -jar famous-quote-1.0.0.jar
```

A H2 database is by default stored in your home directory: "~/h2/famous-quote-db.mv" .
You can select another path for database by setting a property in java command. For same directory as .jar file it is:

```
java -jar -Dspring.datasource.url=jdbc:h2:file:./famous-quote-db famous-quote-1.0.0.jar
```

**For using REST API make following requests to given endpoints:**
- Show all quotes

```
GET   http://localhost:8080/quotes
```

- Save a new quote by sending Body in JSON

```
POST  http://localhost:8080/quotes
```

```
{
    "firstName": "Albert",
    "lastName": "Einstein",
    "quote": "Imagination is more important than knowledge"
}
```

- Update existing quote with id=2 by sending Body in JSON:

```
PUT  http://localhost:8080/quotes/2
```

```
{
    "firstName": "Albert",
    "lastName": "Einstein",
    "quote": "Imagination is more important than knowledge"
}
```

- Delete existing quote with id=2:

```
DELETE http://localhost:8080/quotes/2
```

