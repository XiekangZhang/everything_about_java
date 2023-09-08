# Learn JPA & Hibernate
This tutorial is based on [Bealdung JPA](https://www.baeldung.com/learn-jpa-hibernate).

## necessary annotations
- @Entity, and ensure that the entity has a no-arg constructor and a primary key with @Id
- @Table, @Column
- @Transient: make a field non-persistent
- @Temporal: transform temporal data between the database and Java
- @Enumerated: transform enum data between the database and Java

## Hibernate Entity Lifecycle
- A managed entity is always a persistent entity - it must have a database identifier, even though
the database row representation is not yet created i.e. the INSERT statement is pending the end
of the unit of work.
- A detached entity is just an ordinary entity POJO whose identity value corresponds to a database
row. The difference from a managed entity is that it's not tracked anymore by any persistence
context. 
- A transient entity is simply an entity object that has no representation in the persistent store
and is not managed by any Session.
- An entity is in a deleted state if Session.delete(entity) has been called, and the Session
has marked the entity for deletion. 

## JPA Entity Lifecycle Events & annotations to handle the callbacks
- callback methods are required to have a void return type
- if any of our callbacks for persisting or removing an entity throw an exception, the transaction
will be rolled back

## JPA @Basic vs @Column
- Attributes of the @Basic annotation are applied to JPA entities, whereas the attributes of 
@Column are applied to the database columns
- @Basic annotation's _optional_ attribute defines whether the entity field can be _null_ or not;
on the other hand, @Column annotation's _nullable_ attribute specifies whether the corresponding
database column can be _null_
- we can use @Basic to indicate that a field should be lazily loaded
- the @Column annotation allows us to specify the _name_ of the mapped database column

## @Size, @Length, @Column(length = 10)
- @Size: define the length of the field from an entity
- @Length: use for hibernate
- @Column(length = 10): use for JPA --> varchar(10)
- @Column + @Size define the validation 

## @Embedded and @Embeddable