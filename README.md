# jinq - Java Integrated Query - LINQ for Java

This library is inspired in [LINQ - Method Syntax (C#)](https://docs.microsoft.com/en-us/dotnet/csharp/linq/write-linq-queries) and helps Java programmers to manipulate list and its elements. 


Examples of use:

```java
QueryableList<User> users = new QueryableList<>();

users.add(new User(10, "Tom", "tom@example.com", 40.2F));
users.add(new User(26, "Clark", "clark@example.com", 62.7F));
users.add(new User(52, "Marie", "marie@example.com", 54.7F));
users.add(new User(37, "Peter", "peter@example.com", 87.7F));
users.add(new User(10, "Cleber", "cleber@example.com", 47.7F));
users.add(new User(10, "John", "john@example.com", 50.7F));

//Do the Map operation over user list
QueryableList<Integer> map = users.map(x -> x.getAge());

//Find the first element that matches with predicate
User ageTen = users.find(x -> x.getAge() == 10);

//Find all elements that matches with predicate
QueryableList<User> listAgeTen = users.findAll(x -> x.getAge() == 10);

//Remove all elements that matches with predicate
users.removeAll(x -> x.getAge() == 10);

//Remove first elements that matches with predicate
users.remove(x -> x.getEmail().equals("clark@example.com"));

//Group the list by age
QueryableList<Group<Integer, User>> ageGroup = Group.of(users, it -> it.getAge());

//Get the max value from List
User older = users.max(x -> x.getAge());

//Get the max value from List
User newer = users.min(x -> x.getAge());


```
