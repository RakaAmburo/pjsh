package ar.project.persist.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import ar.project.ent.entities.area.Country;

public class CountryList {

	public static final String ispas = "";

	public static void geocoding(String addr) throws Exception {
		// build a URL
		String s = "http://maps.google.com/maps/api/geocode/json?"
				+ "sensor=false&address=";
		s += URLEncoder.encode(addr, "UTF-8");
		URL url = new URL(s);

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// build a JSON object
		JSONObject obj = new JSONObject(str);
		if (!obj.getString("status").equals("OK"))
			return;

		// get the first result
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		System.out.println(res.getString("formatted_address"));
		JSONObject loc = res.getJSONObject("geometry")
				.getJSONObject("location");
		System.out.println("lat: " + loc.getDouble("lat") + ", lng: "
				+ loc.getDouble("lng"));
	}

	public static List<Country> getPaises() {
		List<Country> countries = new ArrayList<Country>();
		try {
			String jsonStr = readFile("/home/pedro/countyList");

			JSONArray lista = new JSONArray(jsonStr);
			for (int i = 0; i < lista.length(); i++) {
				String name = lista.getJSONObject(i).get("name").toString();
				String code = lista.getJSONObject(i).get("code").toString();
				// System.out.println(name+ " " + code);
				Country c = new Country();
				c.setCode(code);
				c.setName(name);
				countries.add(c);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return countries;
	}

	public static void main(String[] args) throws IOException {

	}

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line.trim());
				// sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

}
