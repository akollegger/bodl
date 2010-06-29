package bodl.sandbox.jersey.jacksonjsonprovider;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.Servlet;
import javax.ws.rs.core.Application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.sun.jersey.spi.container.servlet.ServletContainer;

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
        System.out.println( "STARTING bodl.sandbox.jersey.jacksonjsonprovider" );

        Dictionary props = new Hashtable();
        props.put( "alias", "/sandbox/jersey/jacksonjsonprovider" );
        props.put("servlet-name", "bodl-sandbox jersey jackson");

        System.out.println( "REGISTER bodl.sandbox.jersey.jacksonjsonprovider.ServletContainer" );

		ServletContainer sc = new com.sun.jersey.spi.container.servlet.ServletContainer(new Application() {
			public Set<Class<?>> getClasses() {
				Set<Class<?>> s = new HashSet<Class<?>>();
				s.add(EmptyArrayResource.class);
				s.add(NonJAXBBeanResource.class);
				s.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
				return s;
			}
		});
        // Register our example service implementation in the OSGi service registry
        bc.registerService( Servlet.class.getName(), sc, props );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING bodl.sandbox.paxwhite" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

