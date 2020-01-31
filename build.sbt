import play.sbt.PlayImport.PlayKeys.playRunHooks

name := "playreact"
organization := "ee.baltnet.test"

version := "1.0-SNAPSHOT"
scalaVersion := "2.13.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
	guice,
	"org.webjars" %% "webjars-play" % "2.8.0",
	"org.webjars.npm" % "react" % "16.12.0",
	"org.webjars.npm" % "react-dom" % "16.12.0",
	"org.webjars.npm" % "prop-types" % "15.7.2"
)

playRunHooks += baseDirectory.map(Webpack.apply).value

lazy val webpack = TaskKey[Unit]("webpack", "Run webpack when packaging the application")
webpack := Webpack.dist(baseDirectory.value)
dist := dist.dependsOn(webpack).value
stage := stage.dependsOn(webpack).value
test := (test in Test).dependsOn(webpack).value
