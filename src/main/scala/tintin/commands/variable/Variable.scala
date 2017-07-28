package tintin.commands.variable

import tintin.commands.CommandHandler

class Variable(matchString: String)(c: String) extends CommandHandler(matchString)(c) {
  override def handle: CommandHandler = {
    println(s"Variable ${matchString} ${c}")
    this
  }
}

