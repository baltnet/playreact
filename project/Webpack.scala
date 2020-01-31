import play.sbt.PlayRunHook
import sbt.io.syntax.File

import scala.sys.process.Process

// Original: https://github.com/nouhoum/play-react-webpack/blob/master/project/Webpack.scala

object Webpack {
	def apply(base: File): PlayRunHook = {
		object WebpackHook extends PlayRunHook {
			var process: Option[Process] = None

			override def beforeStarted(): Unit = {
				process = Option(
					Process("npx webpack --display=errors-only", base).run()
				)
			}

			override def afterStarted(): Unit = {
				process = Option(
					Process("npx webpack --watch --display=errors-only", base).run()
				)
			}

			override def afterStopped(): Unit = {
				process.foreach(_.destroy())
				process = None
			}
		}

		WebpackHook
	}

	def dist(base: File): Unit = {
		if (Process("npx webpack", base).! != 0) throw new Exception("Something goes wrong when running webpack.")
	}
}
