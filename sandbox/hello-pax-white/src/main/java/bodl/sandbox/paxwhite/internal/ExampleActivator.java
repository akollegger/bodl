package bodl.sandbox.paxwhite.internal;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.Servlet;

import org.ops4j.pax.web.extender.whiteboard.ResourceMapping;
import org.ops4j.pax.web.extender.whiteboard.runtime.DefaultResourceMapping;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Extension of the default OSGi bundle activator
 */
public final class ExampleActivator
    implements BundleActivator {
  /**
   * Called whenever the OSGi framework starts our bundle
   */
  public void start(BundleContext bc)
      throws Exception {
    System.out.println("STARTING bodl.sandbox.paxwhite");

    Dictionary props = new Hashtable();
    props.put("alias", "/sandbox/paxwhite/hello");
    props.put("servlet-name", "bodl-sandbox hello-pax-white");
    bc.registerService( Servlet.class.getName(), new HelloWorldServlet(), props );

    DefaultResourceMapping resourceMapping = new DefaultResourceMapping();
    resourceMapping.setAlias("/sandbox/paxwhite/hello/images");
    resourceMapping.setPath("/images");
    bc.registerService(ResourceMapping.class.getName(), resourceMapping, null);

  }

  /**
   * Called whenever the OSGi framework stops our bundle
   */
  public void stop(BundleContext bc)
      throws Exception {
    System.out.println("STOPPING bodl.sandbox.paxwhite");

    // no need to unregister our service - the OSGi framework handles it for us
  }
}

