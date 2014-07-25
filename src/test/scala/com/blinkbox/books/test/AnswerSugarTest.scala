package com.blinkbox.books.test

import org.mockito.Mockito._
import org.mockito.Matchers._
import org.scalatest.mock.MockitoSugar
import org.scalatest.FunSuite
import org.mockito.invocation.InvocationOnMock

class AnswerSugarTest extends FunSuite with MockitoSugar with AnswerSugar {

  trait TestTrait {
    def noArgs: String
    def withArgs(num: Int): String
  }

  test("Check Answer with no arguments, thenAnswer style") {
    val testMock = mock[TestTrait]
    when(testMock.noArgs).thenAnswer(() => { "No-args result" })

    val result = testMock.noArgs
    assert(result == "No-args result")
  }

  test("Check Answer with no arguments, doAnswer style") {
    val testMock = mock[TestTrait]
    doAnswer(() => { "No-args result" }).when(testMock).noArgs

    assert(testMock.noArgs == "No-args result")
  }

  test("Check Answer with arguments, thenAnswer style") {
    val testMock = mock[TestTrait]
    when(testMock.withArgs(anyInt)).thenAnswer((inv: InvocationOnMock) => { "Argument: " + inv.getArguments()(0).asInstanceOf[Int] })

    assert(testMock.withArgs(42) == "Argument: 42")
  }

  test("Check Answer with arguments, doAnswer style") {
    val testMock = mock[TestTrait]
    doAnswer((inv: InvocationOnMock) => { "Argument: " + inv.getArguments()(0).asInstanceOf[Int] }).when(testMock).withArgs(anyInt)

    assert(testMock.withArgs(42) == "Argument: 42")
  }

}
