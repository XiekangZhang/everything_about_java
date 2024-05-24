# Best Practises

## Serious Polymorphism: Interfaces And Abstract Classes

- define what kind of classes are concrete and which is abstract. --> _abstract_ class can not be instantiated.
- An _abstract_ method has no-body! --> You can not have an abstract method in a non-abstract class. But you can mix 
abstract and non-abstract methods.
- _subclass instanceof parent-class_ a way to avoid _ClassCastException_ 