package com.sun.jersey.samples.bookmark.internal;

import com.sun.jersey.samples.bookmark.resources.BookmarkResource;
import com.sun.jersey.samples.bookmark.resources.BookmarksResource;
import com.sun.jersey.samples.bookmark.resources.UserResource;
import com.sun.jersey.samples.bookmark.resources.UsersResource;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.servlet.Servlet;
import javax.ws.rs.core.Application;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

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
        System.out.println( "STARTING bodl.sandbox.bookmark" );

        Dictionary props = new Hashtable();
        props.put( "alias", "/sandbox/jersey/bookmark" );
        props.put("servlet-name", "bodl-sandbox-jersey bookmark ");

        System.out.println( "REGISTER bodl.sandbox.jersey.sandbox.ExampleService" );

    		ServletContainer sc = new ServletContainer(new Application() {
          public Set<Class<?>> getClasses() {
            Set<Class<?>> s = new HashSet<Class<?>>();
            s.add(BookmarkResource.class);
            s.add(BookmarksResource.class);
            s.add(UserResource.class);
            s.add(UsersResource.class);
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
        System.out.println( "STOPPING bodl.sandbox.jersey.bookmark" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}