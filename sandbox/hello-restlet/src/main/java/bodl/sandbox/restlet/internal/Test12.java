package bodl.sandbox.restlet.internal;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Reaching target Resources
 * 
 * @author Jerome Louvel
 */
public class Test12 extends Application {

    @Override
    public Restlet createRoot() {
        // Create a router
        final Router router = new Router(getContext());

        // Attach the resources to the router
        router.attach("/users/{user}", UserResource.class);
        router.attach("/users/{user}/orders", OrdersResource.class);
        router.attach("/users/{user}/orders/{order}", OrderResource.class);

        // Return the root router
        return router;
    }

}
