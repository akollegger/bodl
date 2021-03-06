package bodl.sandbox.termlib.core.internal;

import java.util.Dictionary;
import java.util.Properties;

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
    System.out.println("STARTING bodl.sandbox.termlib.core");

    DefaultResourceMapping resourceMapping = new DefaultResourceMapping();
    resourceMapping.setAlias("/sandbox/termlib/lib");
    resourceMapping.setPath("/termlib");
    bc.registerService(ResourceMapping.class.getName(), resourceMapping, null);
  }

  /**
   * Called whenever the OSGi framework stops our bundle
   */
  public void stop(BundleContext bc)
      throws Exception {
    System.out.println("STOPPING bodl.sandbox.termlib.core");

    // no need to unregister our service - the OSGi framework handles it for us
  }
}

