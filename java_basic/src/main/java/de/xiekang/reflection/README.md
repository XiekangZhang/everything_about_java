# Java Reflection
Java reflection allows us to inspect and/or modify runtime attributes of classes,
interfaces, fields and methods. This particularly comes in handy when we don't 
know their names at compile time.
Additionally, we can instantiate new objects, invoke methods and get or set field
values using reflection.

## instantiate an object
- class.getConstructor(args.classes)
- constructor.newInstance(args.values)

## set fields/class attributes
- field.setAccessbile(true)
- field.set(class_Name, value)

## invoke methods
- method.invoke(class_Name, value)