package fi.solita.clamav;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * These tests assume clamd is running and responding in the virtual machine. 
 */
public class PingTest {

  private static String CLAMAV_HOST = "clamav.datb.net";  
    
  @Test
  public void testPingPong() throws UnknownHostException, IOException  {
    ClamAVClient cl = new ClamAVClient(CLAMAV_HOST, 3310);
    assertTrue(cl.ping());
  }
}
