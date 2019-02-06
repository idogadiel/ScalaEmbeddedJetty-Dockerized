package com

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.{ContextHandler, DefaultHandler, HandlerList, ResourceHandler}
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer

object Main  extends App {



  // Creating the new Server// Creating the new Server

  val server = new Server(9999)

  //1.Creating resource for ui
  val resourceHandler = new ResourceHandler
  resourceHandler.setResourceBase("ui")
  resourceHandler.setDirectoriesListed(true)
  val contextHandler = new ContextHandler("/ui")
  contextHandler.setWelcomeFiles(Array[String]("index.html"))
  contextHandler.setHandler(resourceHandler)


  //2.Creating resource for Rest
  val config = new ResourceConfig
  config.packages("com")
  val servlet = new ServletHolder(new ServletContainer(config))
  val servletContextHandler = new ServletContextHandler(server, "/*")
  servletContextHandler.addServlet(servlet, "/*")


  // adding both handles
  val handlers = new HandlerList
  handlers.setHandlers(Array(contextHandler, servletContextHandler, new DefaultHandler))
  server.setHandler(handlers)


  // Starting the MinimalServer
  server.start
  System.out.println("Started!")
  server.join
}
