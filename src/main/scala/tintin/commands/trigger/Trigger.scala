package tintin.commands.trigger

import tintin.commands.CommandHandler

class Trigger(matchString: String)(c: String) extends CommandHandler(matchString)(c) {
  override def handle: CommandHandler = {
    println(s"Trigger ${matchString} ${c}")
    this
  }
}

