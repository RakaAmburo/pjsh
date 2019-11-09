package ar.project.ent.tools.jarLoader4Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Load {

	public static URLClassLoader cargadorJVM = (URLClassLoader) ClassLoader
			.getSystemClassLoader();

	public static void execute() {
		
		//picking properties
		InputStream props = Load.class.getClassLoader().getResourceAsStream("general.properties");
		Properties properties = new Properties();
		try {
			properties.load(props);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//setting log path and level
		String unixHome = System.getenv("HOME");
		String winHome = System.getenv("USERPROFILE");
		System.setProperty("env.home", (unixHome == null)?winHome:unixHome);
		String level = properties.getProperty("log.level");
		System.setProperty("log.level", level);
		
		InputStream in = Load.class.getResourceAsStream("classPath.properties");
		BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
		ClassLoader cl = Load.class.getClassLoader();
		List<URL> jars = new ArrayList<URL>();
		try {
			String line;
			while ((line = rdr.readLine()) != null) {
				if (line.startsWith("#") || line.trim().equals("")) {
					continue;
				}
				jars.add(cl.getResource(line));
			}
			rdr.close();

			Cargar(jars);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void Cargar(List<URL> jars) throws IOException {
		Class<URLClassLoader> claseJVM = URLClassLoader.class;
		Class<?>[] params = new Class[] { URL.class };
		//System.out.println("CARGANDO LIBRERIAS");
		try {

			Method metodo = claseJVM.getDeclaredMethod("addURL", params);
			// esta linea es para volver accesible el metodo
			metodo.setAccessible(true);

			for (URL jar : jars) {
				//System.out.println("cargando jar: " + jar);
				if (jar == null){
					System.err.println("LIBRERIA NULA");
					System.exit(1);
				}
				metodo.invoke(cargadorJVM, new Object[] { jar });

			}

		} catch (Throwable th) {
			throw new IOException("Error al cargar librerias");
		}
	}

}
