package client;

/**
 * An interface for a client that can send GET, PUT, and DELETE
 * commands for key-value pairs to a server.
 * @author lscholte
 *
 */
public interface Client {
  
  /**
   * Starts the client. User input is read and validated at which point
   * commands will be sent to the server.
   */
  public void start() throws Throwable;
  
  /**
   * Sends a PUT command to the server.
   * @param key the key to store
   * @param value the value to store
   */
  public void sendPut(String key, String value);
  
  /**
   * Sends a GET command to the server.
   * @param the key to use to retrieve a value
   */
  public void sendGet(String key);
  
  /**
   * Sends a DELETE command to the server.
   * @param key the key to delete
   */
  public void sendDelete(String key);

}
