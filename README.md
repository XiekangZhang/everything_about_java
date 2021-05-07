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

### Java Annotations
Java Annotation is a tag that represents the metadata attached with class, interface, methods or fields to indicate
some additional information which can be used by java compiler and JVM.
#### Build-In Java Annotations used in Java Code
@Override
@SuppressWarnings
@Deprecated
#### Built-In Java Annotations used in other annotations
@Target: where the annotation is used. 
__TYPE__, __FIELD__, __METHOD__, __CONSTRUCTOR__,
__LOCAL_VARIABLE__, __ANNOTATION_TYPE__, __PARAMETER__\
@Retention: the level of annotation. __SOURCE__ (not be available in the compiled class), 
__CLASS__ (.class file, in java compiler not in JVM),
__RUNTIME__ (java compiler and JVM)\
@Inherited: mark the annotation to be inherited to subclasses \
@Documented
#### Java Custom Annotations
by using @interface before the annotation name, points should be remembered
1. Method should not have any throws clauses
2. Method should return one of the following: primitive data types, String, Class, enum or array
of these data types
   
3. Method should not have any parameter
4. We should attach @ just before interface keyword to define annotation
5. It may assign a default value to the method
