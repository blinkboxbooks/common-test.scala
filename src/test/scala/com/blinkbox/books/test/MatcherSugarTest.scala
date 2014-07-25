package com.blinkbox.books.test

import org.mockito.Matchers._
import org.mockito.Mockito._
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class MatcherSugarTest extends FunSuite with MockitoSugar with MatcherSugar {

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
      verify(testMock).hello(argThat { x: String => x == "wibble"})
    }
    println(exception.getMessage)
    assert("a" == "a")
  }

}
