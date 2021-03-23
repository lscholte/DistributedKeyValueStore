package utilities;

import com.google.protobuf.Message;

/**
 * A utility class for doing repetitive operations on Protobuf objects
 * @author lscholte
 *
 */
public final class ProtobufUtils {
  
  /**
   * Formats a Protobuf message in a friendly manner that includes the
   * name of the message and its contents.
   * @param message the message to format into a String
   * @return a formatted string of the Protobuf message
   */
  public static String getPrintableMessage(Message message) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
      .append(message.getDescriptorForType().getName())
      .append(" {")
      .append(System.lineSeparator())
      .append(message.toString())
      .append("}");
    return stringBuilder.toString();
  }

}
