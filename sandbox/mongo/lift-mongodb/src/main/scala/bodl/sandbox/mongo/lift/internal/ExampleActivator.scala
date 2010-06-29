package bodl.sandbox.mongo.lift.internal

import org.osgi.framework.{BundleContext, BundleActivator}
import java.util.Properties
import bodl.sandbox.mongo.lift.ExampleService

/**
 * Extension of the default OSGi bundle activator
 */
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

        impl.tryIt()
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