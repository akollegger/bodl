package bodl.sandbox.jersey.helloscala.internal;

import java.util.{HashSet, Hashtable, Set}

import javax.servlet.Servlet
import javax.ws.rs.core.Application

import org.osgi.framework.{BundleActivator, BundleContext}

import com.sun.jersey.spi.container.servlet.ServletContainer

/**
 * Extension of the default OSGi bundle activator
 */
class ExampleActivator extends BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    def start(bc:BundleContext) =
    {
        println( "STARTING bodl.sandbox.jersey.helloscala" );

        val props = new Hashtable[String,String]();
        props.put( "alias", "/sandbox/jersey/helloscala" );
        props.put("servlet-name", "bodl-sandbox jersey helloscala");

        println( "REGISTER bodl.sandbox.jersey.helloscala.ServletContainer" );

        val sc: ServletContainer = new com.sun.jersey.spi.container.servlet.ServletContainer(new Application() {
          override def getClasses():Set[Class[_]] = {
            val s = new HashSet[Class[_]]()
            s.add(classOf[HelloWorldResource])
            return s
          }
        });
      
        // Register our example service implementation in the OSGi service registry
        bc.registerService( classOf[Servlet].getName(), sc, props );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    def stop(bc:BundleContext ) =
    {
        println( "STOPPING bodl.sandbox.paxwhite" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

