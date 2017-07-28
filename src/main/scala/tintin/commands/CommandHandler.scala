package tintin.commands

abstract class CommandHandler(matchString: String)(c: String) {
  def handle: CommandHandler
}

