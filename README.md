# Squeryl-Demo
Basic Squeryl Example

STEPS to SETUP PROJECT

1. git clone https://github.com/rahulsharmaindia/Squeryl-Demo-ISSUE-259.git
2. cd Squeryl-Demo-ISSUE-259
3. sbt update
4. sbt run

Now it will display the default object values instead of values from database that we inserted on the console.
This is the problem due to the condition !clazzName.startsWith("net.sf.cglib.") in FieldReferenceLinker.scala in Squeryl.

Output from Scala Sourcecode tests is -

```
inside if: 
Looking at scala.Tuple2
inside if: 
Looking at org.squeryl.test.musicdb.Person$$EnhancerByCGLIB$$f679116d
inside if: 
Looking at scala.Some
inside if: 
Looking at java.lang.Long
Looking at org.squeryl.test.musicdb.Cd$$EnhancerByCGLIB$$76c122d9
inside if: 
Looking at org.squeryl.test.musicdb.Person$$EnhancerByCGLIB$$f679116d
```


Output from my sample code is -

```
inside if: 
Looking at net.sf.cglib.empty.Object$$EnhancerByCGLIB$$844be325
```
