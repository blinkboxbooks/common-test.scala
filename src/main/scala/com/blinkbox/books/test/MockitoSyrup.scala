package com.blinkbox.books.test

import org.scalatest.mock.MockitoSugar

trait MockitoSyrup extends MockitoSugar
  with AnswerSugar
  with MatcherSugar

object MockitoSyrup extends MockitoSyrup