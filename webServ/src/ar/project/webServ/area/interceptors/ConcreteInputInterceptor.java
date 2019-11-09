package ar.project.webServ.area.interceptors;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;

public class ConcreteInputInterceptor extends AbstractPhaseInterceptor<Message>{
	
	private static final Logger LOG = Logger
            .getLogger(LoggingSoapResponse.class.getName());
	
	/**
	 * Default constructor.
	 */
	public ConcreteInputInterceptor() {
		super(Phase.RECEIVE);
	}

	 public void handleMessage ( Message message ) throws Fault
	    {
	        //get the remote address
	        HttpServletRequest httpRequest = (HttpServletRequest) message.get ( AbstractHTTPDestination.HTTP_REQUEST );
	        LOG.info ("Request From the address : " + httpRequest.getRemoteAddr ( ) );

	        try
	        {
	            // now get the request xml
	            InputStream is = message.getContent ( InputStream.class );
	            CachedOutputStream os = new CachedOutputStream ( );
	            IOUtils.copy ( is, os );
	            os.flush ( );
	            message.setContent (  InputStream.class, os.getInputStream ( ) );
	            is.close ( );

	            LOG.info ("The request is:\n\n" + IOUtils.toString ( os.getInputStream ( ) ) + "\n");
	            os.close ( );
	        }

	        catch ( Exception ex )
	        {
	            ex.printStackTrace ( );
	        }

	    }

}
