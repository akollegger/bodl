package bodl.sandbox.hellojpa.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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
        System.out.println( "STARTING org.gatherdata.commons.db.jpa.example" );
        
        ExampleJpaAccess exampleAccess = new ExampleJpaAccess();
        
        ExampleDao dao = new ExampleDao();
        exampleAccess.demo(dao);
        
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING org.gatherdata.commons.db.jpa.example" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

