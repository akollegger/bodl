import sbt._

class BodlProject(info: ProjectInfo) extends DefaultProject(info)
{

  //
  // Configuration
  //
  override def compileOptions = super.compileOptions ++
      Seq(Verbose)

  //
  // Repositories
  //
  val ScargRepo = "scarg-repo" at "http://xfire.github.com/scarg/maven-repo/"

  //
  // Versions
  //
  lazy val OSGI_VERSION = "4.2.0"
  lazy val FELIX_VERSION = "3.0.7"

  //
  // Dependencies
  //
  object Dependencies
  {
    lazy val osgi_core = "org.osgi" % "org.osgi.core" % OSGI_VERSION
    lazy val osgi_compendium = "org.osgi" % "org.osgi.compendium" % OSGI_VERSION
    lazy val felix_container = "org.apache.felix" % "org.apache.felix.main" % FELIX_VERSION
    lazy val scalaj_collection = "org.scalaj" % "scalaj-collection_2.8.0" % "1.0"
    lazy val scarg = "de.downgra" %% "scarg-core" % "1.0.0-SNAPSHOT"

  }

  //
  // Sub-projects
  //

  lazy val bodl_server = project("bodl-server", "Bodl Server", new BodlServer(_))

  class DefaultBodlProject(info: ProjectInfo) extends DefaultProject(info) with assembly.AssemblyBuilder
  {
    val scalaj_collection = Dependencies.scalaj_collection

    override val assemblyJarName = "bodl-" + projectVersion.value + ".jar"
  }

  class BodlServer(info: ProjectInfo) extends DefaultBodlProject(info)
  {
    //val osgi_core = Dependencies.osgi_core
    val felix_container = Dependencies.felix_container
    val scarg = Dependencies.scarg

  }

}
