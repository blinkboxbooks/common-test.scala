# Change log

## 0.2.3 ([#6](https://git.mobcastdev.com/Platform/common-scala-test/pull/6) 2014-08-29 15:07:19)

Cross compile to Scala 2.11

### Improvements

- Now cross-compiles to Scala 2.11.
- Updated dependencies to latest versions.

### Notes

- There’s a deprecation warning in one of the macros, but I can’t fix
it while compiling for 2.10 as the new type only exists in 2.11. It
seems to work either way though.

## 0.2.2 ([#5](https://git.mobcastdev.com/Platform/common-scala-test/pull/5) 2014-08-21 11:37:22)

Added alias for Matchers.eq

### Improvement:

- Added `MatcherSugar.eql`, as an alias for `Matchers.eq` which clashes with Scala's standard `eq` method.


## 0.2.1 ([#4](https://git.mobcastdev.com/Platform/common-scala-test/pull/4) 2014-08-14 17:43:51)

Updated scalatest dependency to 2.2.1

### Improvement

- Updated scalatest dependency to 2.2.1


## 0.2.0 ([#3](https://git.mobcastdev.com/Platform/common-scala-test/pull/3) 2014-07-28 09:48:10)

Added sugar for mockito matchers

### New features

- Added a `MatcherSugar` trait to allow matchers to be implemented as anonymous functions
- Added a `MockitoSyrup` trait to collect the various additional mockito sugar traits.

## 0.1.0 ([#1](https://git.mobcastdev.com/Platform/common-scala-test/pull/1) 2014-07-25 16:19:02)

Initial check-in

### New features:

- Initial check-in for common-test library.
- Added AnswerSugar trait for making it easier to work with Mockito's Answers in Scala.


