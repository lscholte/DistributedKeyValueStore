package server;

import java.util.HashMap;
import java.util.Map;

import utilities.Logger;

/**
 * A program driver that starts either an RPC server.
 * @author lscholte
 *
 */
public final class Driver {
  
  private static final String USAGE = "Usage: server <port> [Simulated RPC Processing time (ms)]";
  
  /**
   * Entry point for the server program.
   * @param args the arguments to determine how to create the server
   */
  public static void main(String[] args) throws Throwable {
    if (args.length != 1 && args.length != 2) {
      Logger.logError(USAGE);
      return;
    }
    
    int port;
    try {
      port = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException e) {
      Logger.logError(USAGE);
      return;
    }
    
    long simulatedRpcProcessingTimeMs = 0;
    if (args.length == 2) {
      try {
        simulatedRpcProcessingTimeMs = Long.parseLong(args[1]);
      }
      catch (NumberFormatException e) {
        Logger.logError(USAGE);
        return;
      } 
    }
    

    Map<String, String> map = new HashMap<String, String>();
    RpcServer server = new RpcServer(port, map, simulatedRpcProcessingTimeMs);
    server.start();
  }

}
