package bodl.sandbox.restlet.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class ExampleActivator implements BundleActivator {

    private Component component;

    public void start(BundleContext context) throws Exception {
        // Create a component
        component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);

        // Create an application
        final Application application = new Part12();

        // Attach the application to the component and start it
        component.getDefaultHost().attachDefault(application);
        component.start();
    }

    public void stop(BundleContext context) throws Exception {
        component.stop();
    }

}
