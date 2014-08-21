package com.blinkbox.books.test

import org.mockito.Matchers._
import org.mockito.Mockito._
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent
import org.scalatest.{Matchers, FunSuite}
import org.scalatest.mock.MockitoSugar

class MatcherSugarTest extends FunSuite with Matchers with MockitoSugar with MatcherSugar {

  trait TestTrait {
    def hello(arg: String) = arg
  }

  test("Valid arg matcher should succeed") {
    val testMock = mock[TestTrait]
    testMock.hello("world")
    verify(testMock).hello(argThat{ x: String => x == "world" })
  }

  test("Invalid arg matcher should fail") {
    val testMock = mock[TestTrait]
    testMock.hello("world")
    val exception = intercept[ArgumentsAreDifferent] {
      verify(testMock).hello(argThat { x: String => x == "wibble" })
    }
    exception.getMessage should include regex "x.*==.*wibble"
  }

  test("Should be able to use `eql` as alisas for Matcher.eq") {
    val testMock = mock[TestTrait]
    testMock.hello("world")
    verify(testMock).hello(eql("world"))
    assert(testMock eq testMock, "Scala's `eq` method shouldn't be hidden")
  }
}
