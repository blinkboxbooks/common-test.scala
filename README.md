# common-config

Contains code for testing your Scala code, building on ScalaTest and Mockito.
 
## Using Mockito Syrup

The `AnswerSugar` trait makes it easier to work with Mockito `Answer`s in Scala. Instead of creating an `Answer[T]` object and overriding `answer()`, you can pass in a closure to the method. In other words, instead of writing:

```scala
when(myMock.method()).thenAnswer(new Answer[ReturnType] {
  override def answer(invocation: InvocationOnMock): ReturnType = "My result"
}
```

you can write:

```scala
when(myMock.method()).thenAnswer(() => { "My result" })
```

The `MatcherSugar` trait allows you to write `Matcher`s as anonymous functions in Scala. Instead of creating a `Matcher[T]` object and overriding `matches`/`describeTo`, you can pass in a closure to the method. In other words, instead of writing:

```scala
verify(myMock).method(argThat(new Matcher[String] {
  override def matches(item: Any): Boolean = item == "test string"
  override def describeTo(description: Description): Unit = description.appendText("test string")
}))
 ```

you can write:

```scala
verify(myMock).method(argThat{ arg: String => arg == "test string" })
```
