package de.java8tutorial.Annotation;

import java.lang.annotation.Inherited;

@Inherited
@interface ForEveryone {
}

@ForEveryone
class Superclass {
}

public class Subclass extends Superclass {
}
