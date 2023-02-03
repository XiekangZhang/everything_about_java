# An Introduction to Atomic Variables in Java
## This tutorial is based on [Atomic Baeldung](https://www.baeldung.com/java-atomic-variables). 
TODO: [Read!!!](https://www.baeldung.com/java-synchronized) [READ!!!](https://www.baeldung.com/java-concurrent-map)

Simply put, a shared mutable state very easily leads to problems when concurrency is involved. 
Locks and Atomic Variables are two ways to handle that problem. 

Combination of _synchronized_ and _volatile_ description words to make locks in usage. However, the performance
takes a hit. When multiple threads attempt to acquire a lock, one of them wins, while the rest of the threads
are either blocked or suspended. __The process of suspending and then resuming a thread is very expensive and 
effects the overall efficiency of the system__.

Non-blocking algorithms for concurrent - compare-and-swap (CAS). The CAS operation updates atomically the value
in M to B, but only if the existing value in M matches A, otherwise no action is taken. When multiple threads attempt
to update the same value through CAS, one of them wins and updates the value. __However, unlike in the case of locks, 
no other thread gets suspended.__ However, the core program logic becomes more complex. This is because we have to 
handle the scenario when the CAS operation didn't succeed. 