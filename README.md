# java_tutorial
## Java 8 Tutorial

---
### Java Enum
1. An enumerated type is a reference type with a finite set of possible values,
2. Enumerated types are classes
3. Enumerated types have no public constructor.
4. Enums are not Cloneable.
5. It is always safe to compare enum values using the _==_ operator instead of calling the _equals()_ method.
   Still the _equals()_ method allows enumerated values to be used as members of collections such as _Set_, _List_,
   and _Map_.

6. the _values()_ method always returns a newly created and initialized array.
7. EnumMap is much faster than HashMap, and EnumMap saves the inserted order.
---
### Lambda Expressions
1. Lambda expression helps us to write our code in functional style. It provides a clear and concise way to
   implement Single Abstract Method interface by using an expression. It is very useful in collection library in
   which it helps to iterate, filter and extract data.

2. The Lambda expression is used to provide the implementation of an **interface** which has functional interface.
3. Java lambda expression is treated as a function, so compiler does not create .class file
4. An interface which has only **one abstract method** is called **functional interface**. (no signature in front of method)
5. Java provides an annotation _@FunctionalInterface_, which is used to declare an interface as functional interface.
6. Java Lambda Expression Syntax: (argument_list) -> {body}
---
### Java functional interface
An Interface contains exactly one abstract method. It can have any number of default, static methods
, and methods of object class, but can contain only one abstract method. 
A functional interface can extends another interface only when it does not have any abstract method.
#### Java Predefined-Functional Interfaces
1. _BiConsumer<T, U>_: void accept(T t, U u), accepts two inputs and returns no result
2. _Consumer<T>_: void accept(T t), it accepts an input and returns no result
3. _Function<T, R>_: R apply(T t), it applies this function to the given argument
4. _Predicate<T>_: boolean test(T t), it evaluates this predicate on the given argument
5. _BiFunction<T, U, R>_: R apply(T t, U u)
6. _BinaryOperator<T>_: inherits from BiFunction, maxBy(T t, U u), it returns the greater of the two elements <-> minBy()
7. _BiPredicate<T, U>_: boolean test(T t, U u) \
[more functional interfaces](https://www.javatpoint.com/java-8-functional-interfaces)
---
### Java Method References
Method reference is used to refer method of functional interface. Each time when you are using lambda
expression to just referring a method, you can replace your lambda expression with method reference.
#### Types of Method References
* Reference to a static method: ContainingClass::staticMethodName
* Reference to an Instance Method: ContainingClass::staticMethodName
* Reference to a Constructor: ClassName::new
---
### Java Stream
allow functional-style operations on the elements and operations performed on a stream does not modify
its source. \
allMatch(Predicate), anyMatch(Predicate), builder(), collect(Collectors), 
concat(Stream a, Stream b), (long)count(), distinct(), (Stream)empty(),
filter(Predicate), findAny(), findFirst(), flatMap(Function<T, Stream>),
forEach(Consumer), forEachOrdered(Consumer), generate(Supplier), iterate(T, UnaryOperator),
limit(long), map(Function), max(Comparator), min(Comparator), peek(Consumer),
reduce(Function), skip(long), sorted(opt(Comparator)), toArray(opt(IntFunction)) 
[more information](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
In IntStream, DoubleStream, LongStream there are more specified methods like sum(), 
summaryStatistics().

**_Java 8 streams can't be reused. If you want to refer a stream, you have to 
cast the stream to other collection type._**
#### Stream Pipeline
source, intermediate operation(s) and a terminal operation. The intermediate operation
is lazy. That means, if the terminal operation is missing, the intermediate operation
will not be executed. The right order is one of the most important aspects of 
chaining operations in the stream pipeline. Map() operations are usually expensive.
#### Stream Reduction --> terminal operation
count, max, min --> predefined methods to reduce stream. Still, you can use reduce()
and the collect() to create your own reduction mechanism.

---
### Java Base64 Encode and Decode
create encode then use .encode()
create decode then use .decode()
---
### Java Default Methods
you can create default and static method in an interface
#### Java 8 interfaces vs abstract class
possible the same, however, you can create a constructor in abstract class but
not in interfaces
---
### Java Collectors
Collectors is a final class that extends Object class. It provides reduction
operations, such as accumulating elements into collections, summarizing elements
according to various criteria etc. 
* groupingBy(Function, opt(Supplier), opt(Collector))
* partitioningBy(Predicate, opt(Collector))
* toMap, toList, toSet ...
* summarizingInt(ToIntFunction)
* summarizingLong(ToLongFunction)
* summarizingDouble(ToDoubleFunction)

[more functions](https://www.javatpoint.com/java-8-collectors)

---
### Java StringJoiner
StringJoiner(delimiter, opt(prefix), opt(suffix))
add(CharSequence), merge(StringJoiner), length()
---
### Java Optional Class
It is a public final class and used to deal with NullPointerException in Java application.

---
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
---
## Java General

---
### Java Generics
1. Type safety: we can hold only a single type of objects in generics.
Without Generics, we can store any type of objects
   
2. Type casting is not required
3. Compile-Time checking
### Generic class
A class that can refer to any type is known as a generic class. 
* T - Type
* E - Element
* K - Key
* N - Number
* V - Value

#### Wildcard
wildcard: ? --> any type

Upper Bound Wildcard: List<Number> vs List<? extends Number>: restrict the unknown type to be 
a specific type, or a subtype of that type

Unbounded Wildcards can be useful in the following scenarios:
* When the given method is implemented by using the functionality provided in the Object class
* When the generic class contains the methods that don't depend on the type parameter

Lower Bounded Wildcards: restrict the unknown type to be a specific type, or a supertype of that type

---
