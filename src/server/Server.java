package server;

/**
 * A server that can receive GET, PUT, and DELETE
 * commands for key-value pairs to store in a map. This class
 * is thread-safe with respect to reads/writes to the map.
 * @author lscholte
 *
 */
public interface Server {
  
  /**
   * Starts the server and awaits requests from a client.
   */
  public void start() throws Throwable;
}
