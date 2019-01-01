# Spring Boot with GraphQL Query, using apigen plugin Example

## Requirements
You'll need 
 - [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-3848520.html).
 - [Meven 3.X](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-3848520.html).
 - [lombok plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin).

## Frameworks used:
- [Graphql para java](https://github.com/graphql-java/graphql-java)
- [Graphql-apigen](https://github.com/Distelli/graphql-apigen) 
- [Graphql-Spring-Boot](https://github.com/graphql-java/graphql-spring-boot)

## Book Store
- `/book` is the REST resource which can fetch Books information
- DataFetchers are Interfaces for RuntimeWiring of GraphQL with JpaRepository

# Model configuration
- To create a rest service on graphQl using the plugin - [apigen](https://github.com/Distelli/graphql-apigen) to facilitate the 
  implementation of the model in graphql and the other functionalities for queries or
  mutations of the objects.
  
   ```
   type Book @java(package:"com.graphql.schema.library.book") {
       id: String! #Identifier of Book
       isn: String! #Isn of Book
       title: String! #Tittle of Book
       publishedDate: String! #published Date of Book
   }
   ```   
   - Definition of the book object with its corresponding attributes.

    ```
    # Root query
    type QueryRoot @java(package:"com.graphql.schema.library.root.query") {
        books(id: String!): [Book]
        book(id: String!): Book
    }
    ```   
   - Definition of the queries that can be made about the object.

    ```
    # Entry object to group the properties of a book and create it
    input InputCreateBook @java(package:"com.graphql.schema.library.mutation") {
       isn: String!
       title: String!
       publishedDate: String!
    }   
    ```
   - Object definition with which new book type objects can be created.

    ```
    # Entry object to group the properties of a book and update it
    input InputUpdateBook @java(package:"com.graphql.schema.library.mutation") {
        id: String!
        isn: String!
        title: String
        publishedDate: String
    }
    ```
   - Object definition with which you can update existing objects of type book

    ```
    # Operations on books
    type Mutation @java(package:"com.graphql.schema.library.mutation") {
        createBook(book: InputCreateBook!): Book
        deleteBook(id: String!): Boolean
        updateBook(book: InputUpdateBook!): Book
    }
    ```
   - Definition of operations with which the state of a book type object can be modified
   
   ## Note: With the annotation @java(package: "") we indicate to the apigen plugin where we want it to generate the sources.

## Use mode 
-  Accessing the url `http://localhost:8090/book` you will see a view like the following

   ![CustomerDtoDefintion](images/index.png?raw=true "Index")
   
# Query

- Usage for `books`
```
{
   books {
     id
     isn
     title
     publishedDate
   }
 }
 ```
- Result
```
{
   "data": {
     "books": [
       {
         "id": "4028808d680b062c01680b06380c0000",
         "isn": "4bbce612-3f4c-4d44-a8e3-151b35f23071",
         "title": "Java",
         "publishedDate": "2019-01-01"
       },
       {
         "id": "4028808d680b062c01680b06383f0001",
         "isn": "453be0f2-789f-49cd-b15e-5f2ed5f74754",
         "title": "Python",
         "publishedDate": "2019-01-01"
       },
       {
         "id": "4028808d680b062c01680b0638400002",
         "isn": "7b241943-7823-4b89-a304-cec9d79680dd",
         "title": "PHP",
         "publishedDate": "2019-01-01"
       },
       {
         "id": "4028808d680b062c01680b0638410003",
         "isn": "13f57848-6afb-4886-a90c-c7f70804021a",
         "title": "C#",
         "publishedDate": "2019-01-01"
       },
       {
         "id": "4028808d680b062c01680b0638420004",
         "isn": "ef2ef914-30e1-46f8-b4c4-cd4e709fa5f9",
         "title": "C++",
         "publishedDate": "2019-01-01"
       }
     ]
   }
}
 ```
- Usage for `book`
```
{
   book(id: "4028808d680ae16901680ae174c90000") {
     id,
     title,
     publishedDate
   }
}
```
- Result 
```
{
   "data": {
     "book": {
       "id": "4028808d680b062c01680b06380c0000",
       "title": "Java",
       "publishedDate": "2019-01-01"
     }
   }
}
 ```
- Combination of both `book` and `books`
```
{
   book(id: "4028808d680ae16901680ae174c90000"){
       id,
       title,
       publishedDate
     },
     books{
       id,
       isn
     }
}
```
 
- Result
```
{
   "data": {
     "book": {
       "id": "4028808d680b062c01680b06380c0000",
       "title": "Java",
       "publishedDate": "2019-01-01"
     },
     "books": [
       {
         "id": "4028808d680b062c01680b06380c0000",
         "isn": "4bbce612-3f4c-4d44-a8e3-151b35f23071"
       },
       {
         "id": "4028808d680b062c01680b06383f0001",
         "isn": "453be0f2-789f-49cd-b15e-5f2ed5f74754"
       },
       {
         "id": "4028808d680b062c01680b0638400002",
         "isn": "7b241943-7823-4b89-a304-cec9d79680dd"
       },
       {
         "id": "4028808d680b062c01680b0638410003",
         "isn": "13f57848-6afb-4886-a90c-c7f70804021a"
       },
       {
         "id": "4028808d680b062c01680b0638420004",
         "isn": "ef2ef914-30e1-46f8-b4c4-cd4e709fa5f9"
       }
     ]
   }
}
```
 
 #Mutations
 
 - Usage for `createBook`
 
 ```
 mutation($book: InputCreateBook) {
    createBook(book: $book) {
      id
      isn
      title
      publishedDate
    }
  }
 ```
  
  - Variables
  ```
  {
    "book": {
      "isn": "4bbce612-3f4c-4d44-a8e3-151b35f236754",
      "title": "R",
      "publishedDate": "2019-01-02"
    }
  }
  ```
  
   ![CustomerDtoDefintion](images/createBook.png?raw=true "CreateBook definition")

  - Usage for `updateBook`
    
 ```
 mutation($book: InputUpdateBook) {
    createBook(book: $book) { 
  		title
    }
  }
  ```
  - Variables
  ```
  {
    "book": {
      "title": "Node Developer"
    }
  }
  ```
  ![CustomerDtoDefintion](images/beforeUpdate.png?raw=true "BeforeUpdate definition")

  ![CustomerDtoDefintion](images/updateResult.png?raw=true "UpdateResult definition")
  
  - Note: As shown in the example, graphql allows you to create and query the object
    created in the same request.
     

 - Usage for `deleteBook`
 
 ```
 mutation ($id: String) {
    deleteBook(id: $id)
  }
 ```

  - Variables
  
  ```
  {
    "id": "4028808d680b568e01680b5698b60000"
  }
  ```




 