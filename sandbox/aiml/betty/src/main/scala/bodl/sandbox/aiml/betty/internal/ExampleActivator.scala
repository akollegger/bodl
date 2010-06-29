package bodl.sandbox.aiml.betty.internal;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import bodl.sandbox.aiml.betty.ExampleService;

class ExampleActivator
    extends BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    override def start(bc:BundleContext) = {
        println( "STARTING bodl.sandbox.helloscala" )

        val props = new Properties()
        // add specific service properties here...

        println( "REGISTER bodl.sandbox.helloscala.ExampleService" )

        // Register our example service implementation in the OSGi service registry
        val impl = new ExampleServiceImpl()
        bc.registerService( classOf[ExampleService].getName(), impl, props )

        val hello = impl.scramble("Hello World")
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    override def stop(bc:BundleContext) =
    {
        println( "STOPPING bodl.sandbox.helloscala" )

        // no need to unregister our service - the OSGi framework handles it for us
    }
}


