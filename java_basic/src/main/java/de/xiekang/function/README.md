# Function
- It's recommended that all functional interfaces have an informative
_@FunctionalInterface_ annotation.
- Any interface with a SAM(Single Abstract Method) is a functional interface,
and its implementation may be treated as lambda expressions. 
- Since a primitive type can't be a generic type argument
  - IntFunction, LongFunction, DoubleFunction: 
    arguments are of specified type, return type is parameterized
  - ToIntFunction, ToLongFunction, ToDoubleFunction:
    return type is of specified type, arguments are parameterized
  - DoubleToIntFunction, DoubleToLongFunction, IntToDoubleFunction, 
    and so on, both argument and return type defined as primitive types