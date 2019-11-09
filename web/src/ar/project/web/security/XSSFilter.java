package ar.project.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filtro para limpiar el request. Removiendo y/o encodeando
 * caracteres peligrosos o inyeccion de javascript (XSS)
 * @author ppaparin
 *
 */
public class XSSFilter implements Filter
{
    
    /**
     * @see ContainerRequestFilter#filter(ContainerRequest)
     */
    
    public void doFilter(ServletRequest request, ServletResponse response,
    		FilterChain chain) throws IOException, ServletException {
    	chain.doFilter(new FilteredRequest(request), response);
    }
      
    public void destroy() {
				
	}

	public void init(FilterConfig filterConfig) throws ServletException {
				
	}
}