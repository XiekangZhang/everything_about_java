# Best Practises

## Serious Polymorphism: Interfaces And Abstract Classes

- define what kind of classes are concrete and which is abstract. --> _abstract_ class can not be instantiated.
- An _abstract_ method has no-body! --> You can not have an abstract method in a non-abstract class. But you can mix 
abstract and non-abstract methods.
- _parent-class instanceof subclass_ a way to avoid _ClassCastException_ 
- With polymorphism, the reference type can be a superclass of the actual object type. _Animal a = new Dog()_.

## Life and Death of an Object: Constructors and Garbage Collection
- In Java, objects live in the _heap_ and method & local variables live in the _stack_. Heap and stack are part of memory.
- instance vs local (stack) variables
  - Instance variables are declared inside a class but not inside a method.
  - Local variables are declared inside a method, including method parameters. 