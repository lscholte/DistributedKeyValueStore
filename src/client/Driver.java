package client;

import utilities.Logger;

/**
 * A program driver that starts an RPC client.
 * @author lscholte
 *
 */
public class Driver {
  
  private static final String USAGE = "Usage: client <ip> <port>";
  
  /**
   * Entry point for the client program.
   * @param args the arguments to determine how to create the client
   */
  public static void main(String[] args) throws Throwable {
    if (args.length != 2) {
      Logger.logError(USAGE);
      return;
    }
    
    String ip = args[0];
    int port;
    try {
      port = Integer.parseInt(args[1]);
    }
    catch (NumberFormatException e) {
      Logger.logError(USAGE);
      return;
    }
    
    Client client = new RpcClient(ip, port);
    client.start();
  }

}
