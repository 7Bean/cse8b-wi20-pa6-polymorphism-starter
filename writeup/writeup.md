# PA 6 - Polymorphism

## Assignment Overview
**Due date: Tuesday, February 18 @ 11:59PM**

This assignment is intended to further your understanding of inheritance of implementation and interfaces through the use of abstract classes and interfaces, respectively. More generally, it should give you a sense of how the object-oriented programming paradigm can be implemented in Java. It will also serve as a gentle introduction to boolean expressions if you are not yet familiar with them.

## Getting Started
There are a number of ways to get started on development. The following is the recommended way to ensure that your code will compile during grading.

1. If you are using your own machine or are on a lab computer to complete the assignment, go to step 2 directly. Otherwise, ssh into your cs8bwi20 account. 
    - `ssh cs8bwi20__@ieng6.ucsd.edu`
2. Acquire the starter files.
    - From ieng6: 
        - Log in to your cs8bwi20 account. 
        - From the command line, use the command `cp -r ~/../public/pa6 ~/` (this will copy the entire starter files directory to your home directory)
        - Type `ls ~` to verify that you have copied the `pa6` directory over. 
    - From GitHub: 
        - `git clone https://github.com/CaoAssignments/cse8b-wi20-pa6-polymorphism-starter.git`
        - Alternatively, you can download the repo as a zipped folder.
3. If you downloaded the repo as a zipped folder, navigate to it through your terminal or text editor (Atom, Eclipse, etc.). If you git cloned the repo, you can switch into that directory immediately.
    - `cd cse8b-wi20-pa6-polymorphism-starter`
    - Optional: You may choose to rename this repo. You can do this by using this command (before the `cd` command above):
        - `mv cse8b-wi20-pa6-polymorphism-starter pa6`
4. You can now start working on it through vim using the following command or open the directory in your preferred editor.
    - `vim Expression.java` or `gvim Expression.java`
5. To compile your code, use the `javac` command.
    - Syntax: `javac file1.java file2.java etc...`
    - Example: `javac BooleanExpressionTester.java` or simply `javac *.java`
6. To run your code, use the `java` command passing in the name of the class with the main method that you want to run.
    - Syntax: `java nameOfClass`
    - Example: `java BooleanExpressionTester`

## Provided Files
Only one file will be provided, namely the tester: `ExpressionTester.java`

You are responsible for testing all cases in order to ensure that your code works as intended. If you have any questions about the behavior of certain cases that are not addressed in the writeup, please make a Piazza post.

### Inheritance Hierarchy
In this assignment, you are implementing several boolean and arithmetic operations, such as conjunction (the `&&` operator) and addition.

Each operation will be broken down into their individual classes so we can practice polymorphism. For example, to perform addition, you will need to create a `Sum` object that will take in two `ArithmeticExpression` objects. From this, you can call on the `evaluate` method from the `Sum` object that will return the sum of the two `ArithmeticExpression` objects. Boolean expressions will also follow a similar workflow.


#### Diagram
![](https://i.imgur.com/mAN7BYB.png)



Looking at the diagram, we can see that all classes will inherit from the `Expression` class. All `Expression`
 objects will have an `evaluate` method that will return a `Value` upon evaluation.
 
For this assignment, there will be two subclasses (types) of `Expression` - `ArithmeticExpression` and `BooleanExpression`. Similarly, there will be two subclasses of `Value` - `IntegerValue` and `BooleanValue`.

Upon calling on the `evaluate` method which returns a `Value`, `ArithmeticExpression` will return an `IntegerValue`, while `BooleanExpression` will return a `BooleanValue`. (Think about why you can do this.)

Something you will notice is that `Value` implements both `BoolEvaluable` and `IntEvaluable`. These interfaces "decorate" our `Value` class and, in theory, allows us to evaluate any `Value` as a `boolean` or `int`. Both interfaces have a single method that you can call on to return either a `boolean` or `int` with respect to the value that the `Value` object represents (such as `false` or `10`).

Because `IntegerValue` and `BooleanValue` both inherit from `Value`, they also inherit the "trait" of being evaluable as a `boolean` and an `int`. You might ask, how is this possible? To put it simply, we will treat the conversion in the following manner.

##### Conversion Guide
- From `boolean` to `int`:
    - `false` => `0`
    - `true` => `1`
- From `int` to `boolean`:
    - `0` => `false`
    - any other int => `true`

## Your Task: 
You will need to create 3 interfaces, 3 abstract classes, and 13 concrete classes (yes, you read that correctly) for a total of 19 files.

##### IMPORTANT NOTES
- If **any** of the operands are `null` upon calling `evaluate`, return `null`. For example, `true || null` should return `null`. This applies to all operators.
- For each operator that you will implement (the concrete classes), keep in mind that you will be passing in a **`BooleanExpression`** or **`ArithmeticExpression`**. This means that you will have to evaluate this expression **before** performing the operation.
    - For example, if we created a `Negative` object and passed in an `Sum` object with the operands being 5 and 6, then you need to evaluate the `Sum` object first before performing `Negative`.
    - This would look like `-(5 + 6)` where you would evaluate `(5 + 6)` first.
- You should not have to create additional instance variables other than the ones mentioned. If you find yourself creating some, look in the parent classes to see if there are any that you can use.
- You do not have to worry about Integer overflow/underflow for this assignment. We will let Java  handle it for us.

### Interfaces

#### `BoolEvaluable.java`: 1 abstract method
- Declare a `public` method named `boolEvaluate` that takes in no arguments and returns a `boolean`

#### `IntEvaluable.java`: 1 abstract method
- Declare a `public` method named `intEvaluate` that takes in no arguments and returns an `int` 

#### `Value.java`
- This interface contains **nothing**
- We are defining it here so that the return value of the method `evaluate` can accept both `IntegerValue` and `BooleanValue` types
- In general, you would be able to add more function declarations to this file if you believe any subclass of `Value` requires some functionality

### Abstract Classes

#### `Expression.java`: 1 abstract method, 2 concrete methods
- Declare a `public` `abstract` method named `evaluate` that takes in no arguments and returns a `Value`
- Override the `toString` method by calling on the `evaluate` method and returning the `String` representation of the `Value` object that is returned from `evaluate`
    - If the `evaluate` returns null, then `toString` should return the String `"undefined"`
- Override the `equals` method by comparing the `toString` value of the caller and the `toString` value of argument
    - Remember that this method should **never** return `true` if one of the compared objects is not actually an `Expression` object


#### `ArithmeticExpression.java`: 2 instance variables
- This abstract class will extend `Expression`
- Declare two `public` `ArithmeticExpression` instance variables
- These will represent the (up to) 2 operands to an arithmetic operation (e.g., a and b in the expression **a** + **b**)
    - Subclasses will use these objects for their evaluations

#### `BooleanExpression.java`: 2 instance variables
- This abstract class will extend `Expression`
- Declare two `public` `BooleanExpression` instance variables
- These will represent the (up to) 2 operands to an boolean operation (e.g., a and b in the expression **a** AND **b**)
    - Subclasses will use these objects for their evaluations

### Concrete Classes
These concrete classes will extend some of the abstract classes that were defined above. They will also implement the methods that were declared in the interfaces and abstract classes.

### Classes that extend `BooleanExpression`
These classes will allow you to perform the negation (`!`), conjunction (`&&`), disjunction (`||`), equivalence (`==`), and exclusive disjunction operations and implication (`=>`) expression. **Make sure they extend `BooleanExpression`.**

#### `BooleanValue.java`: 1 instance variable, 1 constructor, 4 other concrete methods
- This class will represent a `boolean` with extra functionality
    - Implement `Value`,`IntEvaluable` and `BoolEvaluable` 
    - Declare a `public` `boolean` instance variable. This will store the boolean value of this `BooleanValue` object
    - Create a `public` constructor that takes in a single `boolean` and initializes the instance variable according to the argument
- You also need to implement the methods that were defined in the superclasses and their interfaces
    - Implement (and override) the `evaluate` method by returning a reference to `this` instance
    - Implement (and override) the `intEvaluate` method by returning the respective `int` mentioned in the conversion guide [above](#####Conversion-Guide)
    - Implement (and override) the `boolEvaluate` method by returning the primitive `boolean` this `BooleanValue` represents
    - Implement (and override) the `toString` method by returning the String value `"true"` if this `BooleanValue` represents `true` and the String value `"false"` if it represents `false`

#### `Negation.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in a single `BooleanExpression` and stores it (note that this will only use one of your two instance variables, so make sure that you only use the one that you set when you evaluate)
- Implement the `evaluate` method by returning the negation of the result of the `BooleanExpression` that was passed into the constructor
    - If the `BooleanExpression` is invalid (in other words, `null` or it evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null`. 

**Truth table for negation (you may know this as NOT)**
- A is the result of the boolean expression

|A|Output (NOT A)|
|-|--------------|
|`false`|`true`|
|`true`|`false`|

#### `Conjunction.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `BooleanExpression` and stores them
- Implement the `evaluate` method by returning the conjunction of the result of the first `BooleanExpression` and the result of the second `BooleanExpression`
    - If either of the `BooleanExpression`s is invalid (`null` or evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null`

**Truth table for conjunction (you may know this as AND)**
- A is the result of the first boolean expression
- B is the result of the second boolean expression

|A|B|Output (A AND B)|
|-|-|----------------|
|`false`|`false`|`false`|
|`false`|`true`|`false`|
|`true`|`false`|`false`|
|`true`|`true`|`true`|


#### `Disjunction.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `BooleanExpression` and stores them
- Implement the `evaluate` method by returning the disjunction of the result of the first `BooleanExpression` and the result of the second `BooleanExpression`
    - If either of the `BooleanExpression`s is invalid (`null` or evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null`. 

**Truth table for disjunction (you may know this as OR)**
- A is the result of the first boolean expression
- B is the result of the second boolean expression

|A|B|Output (A OR B)|
|-|-|---------------|
|`false`|`false`|`false`|
|`false`|`true`|`true`|
|`true`|`false`|`true`|
|`true`|`true`|`true`|

#### `Equivalence.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `BooleanExpression` and stores them
- Implement the `evaluate` method by returning the equivalence of the result of the first `BooleanExpression` and the result of the second `BooleanExpression`
    - If either of the `BooleanExpression`s is invalid (`null` or evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null`
    - Note: We **are not** handling the case of both being `null` as returning a true value. It should still return `null` in this case.

**Truth table for equivalence (you may know this as ==)**
- A is the result of the first boolean expression
- B is the result of the second boolean expression

|A|B|Output (A == B)|
|-|-|----------------|
|`false`|`false`|`true`|
|`false`|`true`|`false`|
|`true`|`false`|`false`|
|`true`|`true`|`true`|

#### `ExclusiveDisjunction.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `BooleanExpression` and stores them
- Implement the `evaluate` method by returning the exclusive disjunction of the result of the first `BooleanExpression` and the result of the second `BooleanExpression`
    - If either of the `BooleanExpression`s is invalid (`null` or evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null` 

**Truth table for exclusive disjunction (you may know this as XOR)**
- A is the result of the first boolean expression
- B is the result of the second boolean expression

|A|B|Output (A XOR B)|
|-|-|----------------|
|`false`|`false`|`false`|
|`false`|`true`|`true`|
|`true`|`false`|`true`|
|`true`|`true`|`false`|

#### `Implication.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `BooleanExpression` and stores them
- Implement the `evaluate` method by returning the implication of the result of the first `BooleanExpression` and the result of the second `BooleanExpression` (the first argument to the constructor implies the second argument to the constructor).
    - If either of the `BooleanExpression`s is invalid (`null` or evaluates to something that is not evaluable as a boolean) then `evaluate` should return `null` 

**Truth table for implication (you may know this as =>)**
- A is the result of the first boolean expression
- B is the result of the second boolean expression

|A|B|Output (A => B)|
|-|-|---------------|
|`false`|`false`|`true`|
|`false`|`true`|`true`|
|`true`|`false`|`false`|
|`true`|`true`|`true`|

### Classes that extend `ArithmeticExpression`
These classes will allow you to perform the negative (`-`), sum (`+`), difference (`-`), product (`*`), and quotient (`/`) operations. **Make sure they extend `ArithmeticExpression`.**

#### `IntegerValue.java`: 1 instance variable, 1 constructor, 4 other concrete methods
- Similar to `BooleanValue`, this class will represent an `int` with extra functionality
    - Implement `Value`,`IntEvaluable` and `BoolEvaluable`
    - Declare a `public` `int` instance variable. This will store the int value of this `IntegerValue` object
    - Create a `public` constructor that takes in a single `int` and initializes the instance variable according to the argument
- Again, you need to implement the methods that were defined in the superclasses and their interfaces
    - Implement (and override) the `evaluate` method by returning a reference to `this` instance
    - Implement (and override) the `intEvaluate` method by returning the `int` that this `IntegerValue` represents
    - Implement (and override) the `boolEvaluate` method by returning the respective `boolean` mentioned in the conversion guide [above](#####Conversion-Guide)
    - Implement (and override) the `value` method by returning the `String` representation of the `int` that this `IntegerValue` represents
        - For example, if this `IntegerValue` represents `10`, this method should return the String value `"10"`.

#### `Negative.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in a single `ArithmeticExpression` and stores it (note that this will only use one of your two instance variables, so make sure that you only use the one that you set when you evaluate)
- Implement the `evaluate` method by returning the result of the `ArithmeticExpression` that was passed into the constructor multiplied by -1
    - If the `ArithmeticExpression` is invalid (`null` or evaluates to something that is not evaluable as an int) then `evaluate` should return `null`

#### `Sum.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `ArithmeticExpression` and stores them
- Implement the `evaluate` method by returning the sum of the result of the first `ArithmeticExpression` and the result of the second `ArithmeticExpression`
    - If either of the `ArithmeticExpression`s is invalid (`null` or evaluates to something that is not evaluable as an int) then `evaluate` should return `null` 

#### `Difference.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `ArithmeticExpression` and stores them
- Implement the `evaluate` method by returning the difference of the result of the first `ArithmeticExpression` and the result of the second `ArithmeticExpression` (the first argument to the constructor minus the second argument to the constructor)
    - If either of the `ArithmeticExpression`s is invalid (`null` or evaluates to something that is not evaluable as an int) then `evaluate` should return `null` 

#### `Product.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `ArithmeticExpression` and stores them
- Implement the `evaluate` method by returning the product of the result of the first `ArithmeticExpression` and the result of the second `ArithmeticExpression`
    - If either of the `ArithmeticExpression`s is invalid (`null` or evaluates to something that is not evaluable as an int) then `evaluate` should return `null`
    - Note: We **are not** handling the case where one `ArithmeticExpression` is `null` and the other is `0` by returning a `0` value. It should still return `null` in this case.

#### `Quotient.java`: 1 constructor, 1 other concrete method
- Create a `public` constructor that takes in two `ArithmeticExpression` and stores them
- Implement the `evaluate` method by returning the quotient of the result of the first `ArithmeticExpression` divided by the result of the second `ArithmeticExpression` (the first argument to the constructor divided by the second argument to the constructor).
    - If either of the `ArithmeticExpression`s is invalid (`null` or evaluates to something that is not evaluable as an int) then `evaluate` should return `null`. 
    - Note: If the result of the `ArithmeticExpression` divisor is 0, then it is invalid (divide by zero exception will occur). Return `null` in this case.

## Testing Your Code: 
We have provided the tester file `ExpressionTester.java` for the purposes of testing your code. In it, you will find examples of how to test your `evaluate` methods.

In each sample test, we are creating a new `ArithmeticExpression` or `BooleanExpression` and calling `System.out.println` after performing an operation on the `Value`s that we pass in. Recall that `System.out.println()` implicitly calls an object's `toString` method so in order for your output to be displayed, you must have implemented the `toString` methods in both `IntegerValue` and `BooleanValue`.

Although our other expression concrete classes do not implement `toString`, they have inherited the functionality that we desire from the `Expression` class. Recall that in the `Expression` class, we have implemented the `toString` method to return the String representation of the `Value` object that is returned after calling `evaluate`.

You should also try testing your code on more complex expressions, such as the nested expression present in the test case `arithComplex`.

```java
IntegerValue one = new IntegerValue(1);
IntegerValue two = new IntegerValue(2);
IntegerValue three = new IntegerValue(3);

Expression arithComplex = new Sum(one, new Difference(three, two));
System.out.println("1 + (3 - 2) evaluated to: " + arithComplex);
```

The constructor of `Sum` expects two `ArithmeticExpression`s, and since both `IntegerValue` and `Difference` extend `ArithmeticExpression`, we can use them as arguments to `Sum`'s constructor. Notice that the order in which we pass in our expressions to the constructors matter. Had we done `Expression arithComplex = new Sum(one, new Difference(two, three));` we would have ended up with the expression `1 + (2 - 3)` which would have evaluated to 0 instead of 2. Following these examples, create more tests on your own in order to thoroughly test the functionality of `evaluate` in both `ArithmeticExpression` and `BooleanExpression`. You may also want to print the actual (what your code evaluates) and the expected (what you manually calculate) side-by-side for easy comparison (or maybe print out a boolean saying if they are equal or not). 


## README
The following questions about provided files and general policies are graded for fair effort and completeness. Your file should be named `README.md`.

1. Your classmate missed class (oh no!) and doesn't understand polymorphism. In your own words, explain the concept of polymorphism so your classmate can understand.
2. As mentioned earlier, we can return `IntegerValue` or `BooleanValue` when we call on `evaluate` despite the return value of `evaluate` being `Value`. Why can we do this?
3. In the `BooleanExpression` and `ArithmeticExpression` classes, we inherit from `Expression`. Yet, we don't implement the `evaluate` method. Why is this allowed?
4. What is the difference between inheritance of interfaces and inheritance of implementation?
5. Why does Java support multiple inheritance of interfaces but not multiple inheritance of implementation?

### Student Satisfaction Survey
Please fill out our [student satisfaction survey](https://docs.google.com/forms/d/e/1FAIpQLScU3pdntLUPS2tY9n9GFDbTeTUWPKfwJLVV6eyPxpG8jL80rA/viewform). We are changing how we approach giving assignments and would like to hear about your experiences. 


## Style
We will grade your code style thoroughly. Namely, there are a few things you must have in each file / class / method (this includes the `README.md`):

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/eng.ucsd.edu/cse8b-winter2020/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.


## Submission
Required Submission Files (20 files)
- `ArithmeticExpression.java`
- `BoolEvaluable.java`
- `BooleanExpression.java`
- `BooleanValue.java`
- `Conjunction.java`
- `Difference.java`
- `Disjunction.java`
- `Equivalence.java`
- `ExclusiveDisjunction.java`
- `Expression.java`
- `Implication.java`
- `IntEvaluable.java`
- `IntegerValue.java`
- `Negation.java`
- `Negative.java`
- `Product.java`
- `Quotient.java`
- `Sum.java`
- `Value.java`
- `README.md`

*Start early and start often!*