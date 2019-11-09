package ar.project.web.tools.jarLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Load implements ServletContextListener {

	private static URLClassLoader cargadorJVM = (URLClassLoader) ClassLoader 
			.getSystemClassLoader();
		
	private static String execute;
	private static String show;

	private static String LOGFOLDER = "LOGFOLDER";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {

		execute = context.getServletContext().getInitParameter(
				"execute.jar.loading");
		show = context.getServletContext().getInitParameter("show.loaded.jars");
		
		// Load jar and folders
		if (execute.equals("true")) {

			// Prepare de clss loder and jar and folder list
			ClassLoader cl = Load.class.getClassLoader();
			List<URL> folders2load = new ArrayList<URL>();
			List<URL> jars2load = new ArrayList<URL>();

			// Loading configuration properties
			InputStream confs = Load.class.getClassLoader()
					.getResourceAsStream("config.properties");
			Properties config = new Properties();
			try {
				config.load(confs);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String folderClassPath = config.getProperty("folder.class.path");

			// Load folders
			InputStream inf = Load.class.getClassLoader().getResourceAsStream(
					"folders2load.properties");
			BufferedReader rdrf = new BufferedReader(new InputStreamReader(inf));

			// Load jars
			InputStream in = Load.class.getClassLoader().getResourceAsStream(
					"jars2load.properties");
			BufferedReader rdr = new BufferedReader(new InputStreamReader(in));

			try {

				// Folders
				String linef;
				while ((linef = rdrf.readLine()) != null) {
					if (linef.startsWith("#") || linef.trim().equals("")) {
						continue;
					}
					URL folder = new File(folderClassPath + linef).toURI()
							.toURL();
					folders2load.add(folder);
				}
				rdrf.close();

				Cargar(folders2load);

				// Jars
				String line;
				while ((line = rdr.readLine()) != null) {
					if (line.startsWith("#") || line.trim().equals("")) {
						continue;
					}
					jars2load.add(cl.getResource(line));
				}
				rdr.close();

				Cargar(jars2load);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Checking if one must show loaded jars and if we must load them
		InputStream props = Load.class.getClassLoader().getResourceAsStream(
				"general.properties");
		Properties properties = new Properties();
		try {
			properties.load(props);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// setting log path and level
		String unixHome = System.getenv("HOME");
		String winHome = System.getenv("USERPROFILE");
		String finalFolder = (unixHome == null) ? winHome : unixHome
				+ File.separator + LOGFOLDER;
		System.setProperty("env.home", finalFolder);
		String level = properties.getProperty("log.level");
		System.setProperty("log.level", level);

	}

	public static void Cargar(List<URL> jars) throws IOException {
		Class<URLClassLoader> claseJVM = URLClassLoader.class;
		Class<?>[] params = new Class[] { URL.class };
		if (show.equals("true")) {
			System.out.println("LOADING JARS/FOLDERS");
		}
		try {

			Method metodo = claseJVM.getDeclaredMethod("addURL", params);
			// esta linea es para volver accesible el metodo
			metodo.setAccessible(true);

			for (URL jar : jars) {
				if (show.equals("true")) {
					System.out.println("cargando jar/folders: " + jar);
				}
				
				metodo.invoke(cargadorJVM, new Object[] { jar });

			}

		} catch (Throwable th) {
			throw new IOException("Error al cargar librerias");
		}
	}

}
