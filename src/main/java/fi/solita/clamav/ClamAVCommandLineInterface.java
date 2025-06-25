/*
 * Copyright datb ltd. 
 * LGPL-2.1 license as this gets compiled into ClamAVClient.jar
 */
package fi.solita.clamav;

import static fi.solita.clamav.ClamAVClient.isCleanReply;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Allow this .jar to be run from the command line. 
 * @author jase
 */
public class ClamAVCommandLineInterface {
    
    
  public static void main( String args[] )
    {
        if ( args.length == 0 )
        {
            System.out.println( "ClamAVClient hostname port filename " );         
            return;
        }
        
        String hostname = args[0];
        int port = Integer.parseInt( args[1] );
        
        ClamAVClient client = new ClamAVClient( hostname, port );
        for ( int i=2; i<args.length; i++ )
        {
            System.out.print( args[i] );            
            try {      
                File f = new File( args[i] );
                
                if ( f.isDirectory() )
                    continue;
                
                FileInputStream fis = new FileInputStream( f );
                byte[] reply = client.scan( fis );
                if ( isCleanReply( reply ) )
                    System.out.println ( " CLEAN" );
                else
                    System.out.println( new String( reply, StandardCharsets.US_ASCII ) );
            } catch ( Exception e ) {
                System.out.println( " ERROR" );
                e.printStackTrace();
                System.out.println( "When processing: " + args[i] );
            }
        }
    }    
    
}
