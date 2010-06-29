package bodl.sandbox.jersey.helloscala.internal

import javax.ws.rs.{GET, Path, Produces}

// The Scala class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
class HelloWorldResource {
    
    // The Scala method will process HTTP GET requests
    @GET 
    // The Scala method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces(Array("text/plain"))
    def getClichedMessage():String = {
        // Return some cliched textual content
        return "Hello World";
    }
}