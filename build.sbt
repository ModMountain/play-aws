name := "play-aws"
organization := "com.modmountain"
version := "0.1.0"

scalaVersion := "2.11.7"

lazy val root = (project in file(".")).enablePlugins(PlayLibrary, PlayReleaseBase)

val PlayVersion = playVersion("2.4.3")
playBuildRepoName in ThisBuild := "play-aws"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion % Provided,
  "com.amazonaws" % "aws-java-sdk" % "1.10.21"
)