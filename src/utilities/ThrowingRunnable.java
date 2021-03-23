package utilities;

/**
 * An interface that that mimics the {@code Runnable} interface but allows
 * checked exceptions to be thrown. This interface can be used in lambda expressions.
 * @author lscholte
 *
 */
public interface ThrowingRunnable {
  
  /**
   * Runs an arbitrary set of code.
   */
  public void run() throws Exception;
  
  /**
   * Creates a {@code Runnable} from a {@code ThrowingRunnable} that converts any checked
   * exceptions raised when {@code run} is called to an unchecked {@code RuntimeException}.
   * Use this to create {@code Runnable} objects that throw checked exceptions.
   * @param runnable the {@code Runnable} to wrap
   * @return a {@code Runnable} that converts any checked exceptions to an unchecked exception
   */
  public static Runnable wrapThrowingRunnable(ThrowingRunnable runnable) {
    return () -> {
      try {
        runnable.run();
      }
      catch (Throwable e) {
        throw new RuntimeException(e);
      }
    };
  }
}