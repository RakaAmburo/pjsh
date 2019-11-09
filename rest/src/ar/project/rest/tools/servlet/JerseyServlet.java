package ar.project.rest.tools.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class JerseyServlet extends ServletContainer {

	private static final long serialVersionUID = 1L;

	public static String PACKAGES;
	public static String RECURSIVE;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		PACKAGES = config
				.getInitParameter("jersey.config.server.provider.packages");
		RECURSIVE = config
				.getInitParameter("jersey.config.server.provider.scanning.recursive");

		
		try {
			ClassPath classPath = ClassPath.from(JerseyServlet.class
					.getClassLoader());
			Set<ClassInfo> clases = classPath
					.getTopLevelClassesRecursive(PACKAGES);
			for (ClassInfo classInfo : clases) {
				Class<?> clazz = classInfo.load();
				//System.out.println(clazz.getCanonicalName());
				SpringDepReInjector.parse2(clazz);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
