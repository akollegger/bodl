package bodl.sandbox.aiml.charliebot.internal;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import org.alicebot.server.net.servlet.Alice;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.servlet.Servlet;

/**
 * Extension of the default OSGi bundle activator
 */
public final class ExampleActivator
    implements BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING bodl.sandbox.aiml.charliebot" );

      Dictionary props = new Hashtable();
      props.put( "alias", "/sandbox/charliebot" );
      props.put("servlet-name", "Charliebot");
      bc.registerService( Servlet.class.getName(), new Alice(), props );

    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING bodl.sandbox.aiml.charliebot" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

