package bodl.services.storage.core.internal

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

class OSGiActivator
    extends BundleActivator
{
  def start( bc:BundleContext  ) = {
    System.out.println( "STARTING bodl.services.storage.core" );
  }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
  def stop( bc:BundleContext  ) = {
    System.out.println( "STOPPING bodl.services.storage.core" );

        // no need to unregister our service - the OSGi framework handles it for us
  }
}

