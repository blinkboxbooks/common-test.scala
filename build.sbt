name := "common-test"

organization := "com.blinkbox.books"

version := scala.util.Try(scala.io.Source.fromFile("VERSION").mkString.trim).getOrElse("0.0.0")

scalaVersion  := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.7")

libraryDependencies ++= {
  Seq(
    "org.scalatest"      %% "scalatest"            % "2.2.0",
    "junit"               % "junit"                % "4.11",
    "com.novocode"        % "junit-interface"      % "0.10",
    "org.mockito"         % "mockito-core"         % "1.9.5"
  )
}
