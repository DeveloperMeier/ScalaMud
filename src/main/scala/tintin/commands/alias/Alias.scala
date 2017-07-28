package tintin.commands.alias

import java.io.{File, FileReader, FileWriter, PrintWriter}

import tintin.commands.CommandHandler

class Alias(matchString: String)(c: String) extends CommandHandler(matchString)(c) {
  import Aliases._
  override def handle: CommandHandler = {
    aliases = aliases ++ Map(matchString -> c)
    println(s"Alias ${matchString} ${c}")
    println(aliases)
    write
    this
  }
}


object Aliases {
  val file = new File("aliases")
  def load = new FileReader("aliases").read()
  def write = aliases.map(a => {
    val pw = new FileWriter(file, true)
    pw.write(s"${a._1} = ${a._2}\n")
    pw.close()
  })
  var aliases: Map[String,String] = Map.empty
  def sendOrFormat(aliasName: String) = aliases.find(_._1 == aliasName) match {
    case Some(x) => x._2
    case None => aliasName
  }
}

