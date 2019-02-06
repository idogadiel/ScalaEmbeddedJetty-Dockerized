package com

import javax.ws.rs._
import javax.ws.rs.core.MediaType

@Path("home")
class RestLayer {

  @GET
  @Path("hello")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def helloWorld = "Hello, world!"

}
