package tintin

import tintin.commands._
import tintin.commands.alias.{Alias, Aliases}
import tintin.commands.timer.Timer
import tintin.commands.trigger.Trigger
import tintin.commands.variable.Variable

import scala.io.StdIn.readLine

class InputLoop {
  def run(args: Array[String]) = {
    Stream.continually(readLine("Input> ")).takeWhile(_ != "exit").foreach(parseInput)
  }


  def parseInput(s: String): Unit = {
   s.charAt(0) match {
     case '#' => {
       val r = """#(.*?)\s\{(.*?)\}\s?\{(.*?)\}""".r
       val r2 = """#(.*?)\s(.*?)\s(.*?)""".r
       val parts = r.unapplySeq(s) match {
         case Some(x) => x
         case None => r2.unapplySeq(s).getOrElse(throw new Exception("Cannot Coerce into CommandHandler"))
       }
       println(handleCommand(parts(0), parts(1), parts(2)))
     }
     case _ => println(Aliases.sendOrFormat(s))
   }
  }

  def handleCommand(commandType: String, matchString: String, command: String): CommandHandler = {
    val commandHandler = commandType match {
      case "trigger" => new Trigger(matchString)(command)
      case "alias" => new Alias(matchString)(command)
      case "timer" => new Timer(matchString)(command)
      case "variable" => new Variable(matchString)(command)
      case _ => throw new Exception("Command not recognized")
    }
    commandHandler.handle
  }

}

