package ar.project.webServ.area.interceptors;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.log4j.Logger;
import org.springframework.xml.transform.StringSource;

public class LoggingSoapResponse implements CachedOutputStreamCallback {

    private static final Logger LOG = Logger
            .getLogger(LoggingSoapResponse.class.getName());
    
    private static final String UTF8 = "UTF-8";
    private static final String YESFLAG = "yes";
    private static final String INDENTAMOUNTPROPERTY = "{http://xml.apache.org/xslt}indent-amount";
    private static final String INDENTAMOUNT = "4";
    
    @Override
	public void onClose(CachedOutputStream cos) {
		try {
			if (cos != null) {

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, UTF8);
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, YESFLAG);
				transformer.setOutputProperty(OutputKeys.INDENT, YESFLAG);
				transformer.setOutputProperty(INDENTAMOUNTPROPERTY, INDENTAMOUNT);
				StringWriter stringWriter = new StringWriter();
				StreamResult streamResult = new StreamResult(stringWriter);
				transformer.transform(new StringSource(IOUtils.toString(cos.getInputStream())), streamResult);

				LOG.info("Message Response:\n\n"
		                + stringWriter.toString() + "\n");
			}

		} catch (Exception e) {
			LOG.error("Error read the stream", e);
		}
	}

    @Override
    public void onFlush(CachedOutputStream arg0) {

        LOG.debug("Flushing CachedOutputStreamCallback");

    }

}
