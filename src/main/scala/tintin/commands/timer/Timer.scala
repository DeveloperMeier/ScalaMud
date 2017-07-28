package tintin.commands.timer

import tintin.commands.CommandHandler

class Timer(matchString: String)(c: String) extends CommandHandler(matchString)(c) {
  override def handle: CommandHandler = {
    println(s"Timer ${matchString} ${c}")
    this
  }
}

