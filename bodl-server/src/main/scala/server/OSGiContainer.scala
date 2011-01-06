package bodl.server

import scalaj.collection.Imports._

import org.osgi.framework.launch.Framework
import org.apache.felix.framework.Felix
import java.io.File
import org.osgi.framework.Constants

/**
 */
class OSGiContainer(val baseDirectory: String = "bodl")
{

  val systemDirectory = baseDirectory + File.separator + "system"
  val cacheDirectory = systemDirectory + File.separator + "cache"

  val config = Map(
    Constants.FRAMEWORK_STORAGE -> cacheDirectory
  )

  val osgiFramework: Framework = new Felix(config asJava)

  def start =
  {
    osgiFramework init

    osgiFramework start

    listBundles

    println("OSGi Container started.")
  }

  def stop =
  {
    osgiFramework.stop
    osgiFramework.waitForStop(100)
    println("OSGi Container stopped.")
  }

  def listBundles =
  {
    osgiFramework.getBundleContext.getBundles.foreach(bundle =>
      {
        println("[" + bundle.getBundleId + "] " + bundle.getSymbolicName)
      })
  }

}