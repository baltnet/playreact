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
					Process(npx + "webpack --display=errors-only", base).run()
				)
			}

			override def afterStarted(): Unit = {
				process = Option(
					Process(npx + "webpack --watch --display=errors-only", base).run()
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
		if (Process(npx + "webpack", base).! != 0) throw new Exception("Something goes wrong when running webpack.")
	}

	def npx(): String = {
		System.getProperty("os.name").toLowerCase match {
			// Windows npx.cmd is buggy detecting node_modules folder inside NodeJS when invoked via SBT Process() function.
			// Use npx.cmd from .bin folder inside build directory, cross the fingers and hope for the best.
			case win if win.contains("win") => "node_modules/.bin/npx.cmd "
			case _ => "npx "
		}
	}
}
