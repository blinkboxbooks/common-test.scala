package com.blinkbox.books.test

import org.hamcrest.{Description, BaseMatcher, Matcher}
import scala.language.implicitConversions
import scala.language.experimental.macros
import scala.reflect.macros.Context

private object MatcherMacros {
  def toMatcher[T](c: Context)(f: c.Expr[T => Boolean])(implicit wtt: c.WeakTypeTag[T]): c.Expr[Matcher[T]] = {
    import c.universe._

    object MessageTransformer extends Transformer {
      override def transform(tree: c.universe.Tree): c.universe.Tree = tree match {
        case Literal(Constant("$PUT_REAL_MESSAGE_HERE$")) => Literal(Constant(show(f)))
        case x => super.transform(x)
      }
    }

    val ast = reify {
      new BaseMatcher[T] {
        override def matches(item: Any): Boolean = f.splice(item.asInstanceOf[T])
        override def describeTo(description: Description): Unit = description.appendText("$PUT_REAL_MESSAGE_HERE$")
      }
    }

    c.Expr[BaseMatcher[T]](MessageTransformer.transform(ast.tree))
  }
}

trait MatcherSugar {
  implicit def toMatcher[T](f: T => Boolean): Matcher[T] = macro MatcherMacros.toMatcher[T]
}

object MatcherSugar extends MatcherSugar
