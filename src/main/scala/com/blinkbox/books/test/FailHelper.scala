package com.blinkbox.books.test

import java.util.concurrent.atomic.AtomicReference

import scala.util.{Try, Success, Failure}
import scala.concurrent.{Future, ExecutionContext}

import org.scalatest.Assertions
import org.scalatest.concurrent.{AsyncAssertions, PatienceConfiguration}

/**
 * Helper that can be mixed in when needing to test failing futures.
 */
trait FailHelper extends Assertions with AsyncAssertions with PatienceConfiguration {
  def failingWith[T <: Throwable : Manifest](f: Future[_])(implicit p: PatienceConfig, ec: ExecutionContext): T = {
    val at = new AtomicReference[Try[Any]]()

    val w = new Waiter
    f onComplete {
      case Success(i) =>
        at.set(Success(i))
        w.dismiss()
      case Failure(e) =>
        at.set(Failure(e))
        w.dismiss()
    }
    w.await()(p)

    at.get() match {
      case Success(i) => intercept[T](i)
      case Failure(e) => intercept[T](throw e)
    }
  }
}

