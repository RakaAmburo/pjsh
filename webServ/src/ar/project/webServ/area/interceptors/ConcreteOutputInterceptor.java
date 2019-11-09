package ar.project.webServ.area.interceptors;

import java.io.OutputStream;

import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class ConcreteOutputInterceptor extends AbstractPhaseInterceptor<Message> {
	
	
	/**
	 * Default constructor.
	 */
	public ConcreteOutputInterceptor() {
		super(Phase.PRE_STREAM);
	}

	@Override
	public void handleMessage(Message message) {

		OutputStream os = message.getContent(OutputStream.class);
		CacheAndWriteOutputStream cwos = new CacheAndWriteOutputStream(os);
		message.setContent(OutputStream.class, cwos);
        cwos.registerCallback(new LoggingSoapResponse());

	}

}
