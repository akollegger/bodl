package bodl.server

import de.downgra.scarg.{ArgumentParser, ConfigMap, ValueMap, DefaultHelpViewer}

/**
 * Launches the server from the command line.
 *
 */
object Main
{

  def main(args: Array[String])
  {

   val container:OSGiContainer = SimpleParser().parse(args) match
    {
      case Right(c) =>
        new OSGiContainer(c.directory)
      case Left(xs) => System.exit(1); null
    }

    println("Starting the Bodl Server.")

    container.start

    container.stop

    println("Stopped the Bodl Server.");
  }

  //
  // scarg for command-line arg processing
  // see: https://github.com/xfire/scarg
  //
  class Configuration(m: ValueMap) extends ConfigMap(m)
  {
    val directory = ("directory", "").as[String]
  }

  case class SimpleParser() extends ArgumentParser(new Configuration(_))
  with DefaultHelpViewer
  {
    override val programName = Some("bodl")
    override val UNKNOWN_ARGUMENT = "this means nothing to me: "

    // expected arguments
    ! "-d" | "--directory" |^ "<directory>" |* "bodl" |% "base directory, default: ./bodl" |> 'directory
  }

}

