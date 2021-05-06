# java_tutorial
## Java 8 Tutorial
### Java Enum
1. An enumerated type is a reference type with a finite set of possible values,
2. Enumerated types are classes
3. Enumerated types have no public constructor.
4. Enums are not Cloneable.
5. It is always safe to compare enum values using the _==_ operator instead of calling the _equals()_ method.
   Still the _equals()_ method allows enumerated values to be used as members of collections such as _Set_, _List_,
   and _Map_.

6. the _values()_ method always returns a newly created and initialized array.
7. EnumMap is much faster than HashMap

### Lambda Expressions
1. Lambda expression helps us to write our code in functional style. It provides a clear and concise way to
   implement Single Abstract Method interface by using an expression. It is very useful in collection library in
   which it helps to iterate, filter and extract data.

2. The Lambda expression is used to provide the implementation of an **interface** which has functional interface.
3. Java lambda expression is treated as a function, so compiler does not create .class file
4. An interface which has only **one abstract method** is called **functional interface**.
5. Java provides an anotation _@FunctionalInterface_, which is used to declare an interface as functional interface.
6. Java Lambda Expression Syntax: (argument_list) -> {body}

### functional interface
1. _Consumer_: void accept(T t), it accepts an input and returns no result.
2. _Runnable_: void run()
3. _Comparator_: comparing(T t)
4. _Predicate_: boolean test(T t)
5. _addActionListener_: 