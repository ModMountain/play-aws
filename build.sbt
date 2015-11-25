name := "play-aws"
organization := "com.modmountain.play.aws"
version := "0.2.0-SNAPSHOT"

scalaVersion := "2.11.7"

lazy val root = (project in file(".")).enablePlugins(PlayLibrary, PlayReleaseBase)

val PlayVersion = playVersion("2.4.3")
playBuildRepoName in ThisBuild := "play-aws"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion % Provided,
  "com.amazonaws" % "aws-java-sdk" % "1.10.21"
)


publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/ModMountain/play-aws"))

pomExtra := (
  <scm>
    <url>git@github.com:ModMountain/play-aws.git</url>
    <connection>scm:git:git@github.com:ModMountain/play-aws.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sirsavary</id>
      <name>Jesse Savary</name>
      <url>http://www.jessesavary.com</url>
    </developer>
  </developers>
)