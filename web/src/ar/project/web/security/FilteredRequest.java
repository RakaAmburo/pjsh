package ar.project.web.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;


/**
 * Clase encargada del filtrado XSS
 * @author ppaparin
 *
 */
public class FilteredRequest extends HttpServletRequestWrapper {

	public FilteredRequest(ServletRequest request) {
		super((HttpServletRequest)request);
	}
	
	public String getParameter(String paramName) {
		String value = super.getParameter(paramName);
		value = sanitize(value);
		return value;
	}
	
	public Cookie[] getCookies(){
		Cookie[] cookies = super.getCookies();
		for (int index = 0; index < cookies.length; index++) {
			cookies[index].setComment(sanitize(cookies[index].getComment()));
			cookies[index].setDomain(sanitize(cookies[index].getDomain()));
			cookies[index].setPath(sanitize(cookies[index].getPath()));
			cookies[index].setValue(sanitize(cookies[index].getValue()));
		}
		return cookies;
		
	}
	
	public String getHeader(String name){
		return sanitize(super.getHeader(name));
	}
	
	public Enumeration<String> getHeaders(String name){
		Enumeration<?> headers = super.getHeaders(name);
		ArrayList<String> cleanHeaders = new ArrayList<String>();
		while(headers.hasMoreElements()){
			String value = (String) headers.nextElement();
			cleanHeaders.add(value);
		}
		Enumeration<String> newHeaders = Collections.enumeration(cleanHeaders);
		return newHeaders;
	}
	
	public Map<String, String[]> getParameterMap() {
		Map<?, ?> map = super.getParameterMap();
		Hashtable<String, String[]> newMap = new Hashtable<String, String[]>();
		Set<?> set = map.entrySet();
		Iterator<?> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<?, ?> entry =  (Entry<?, ?>) it.next();
            String[] paramValues = (String[]) entry.getValue();
            for (int i = 0; i < paramValues.length; i++) {
            	paramValues[i] = sanitize(paramValues[i]);
            }
            newMap.put((String)entry.getKey(), (String[])entry.getValue());
        }    
		
		return newMap;
	}
	
	public String[] getParameterValues(String paramName) {
		String values[] = super.getParameterValues(paramName);
		
		for (int index = 0; index < values.length; index++) {
			values[index] = sanitize(values[index]);
		}
		
		return values;
	}
	
	/**
     * Strips any potential XSS threats out of the value
     * @param value
     * @return
     */
    public static String sanitize( String value )
    {
        if( value != null )//Agregar
        {
        	// Use the ESAPI library to avoid encoded attacks.
            value = ESAPI.encoder().canonicalize( value );
              
            // Avoid null characters
            value = value.replaceAll("\0", "");
             
            // Clean out HTML
            value = Jsoup.clean( value, Whitelist.none() );
        }
        return value;
    }

}
