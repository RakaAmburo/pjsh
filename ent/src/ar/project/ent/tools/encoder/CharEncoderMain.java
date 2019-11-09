package ar.project.ent.tools.encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Operations on char primitives and Character objects.
 * 
 * This class tries to handle <code>null</code> input gracefully. An exception
 * will not be thrown for a <code>null</code> input. Each method documents its
 * behaviour in more detail.
 * 
 * @author Stephen Colebourne
 * @since 2.1
 * @version $Id: CharUtils.java 437554 2006-08-28 06:21:41Z bayard $
 */
public class CharEncoderMain {

	static String pattern = "[a-zA-z0-9=./]";
	static Pattern pat = Pattern.compile(pattern);
	public static String propName = "ApplicationResources.properties";
	static boolean advise = false;

	// --------------------------------------------------------------------------
	/**
	 * Converts the string to the unicode format '\u0020'.
	 * 
	 * This format is the Java source code format.
	 * 
	 * <pre>
	 *   CharUtils.unicodeEscaped(' ') = "\u0020"
	 *   CharUtils.unicodeEscaped('A') = "\u0041"
	 * </pre>
	 * 
	 * @param ch
	 *            the character to convert
	 * @return the escaped unicode string
	 */
	public static String unicodeEscaped(char ch) {
		if (ch < 0x10) {
			return "\\u000" + Integer.toHexString(ch);
		} else if (ch < 0x100) {
			return "\\u00" + Integer.toHexString(ch);
		} else if (ch < 0x1000) {
			return "\\u0" + Integer.toHexString(ch);
		}
		return "\\u" + Integer.toHexString(ch);
	}

	public static void main(String[] args) throws IOException {

		// String encoded = unicodeEscaped(new String("�").charAt(0));
		// System.out.println(encoded);

		InputStream in = CharEncoderMain.class.getClassLoader().getResourceAsStream(propName);
		BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = rdr.readLine()) != null) {
			if (line.startsWith("#") || line.trim().equals("")) {
				System.out.println(line);
				continue;
			}		
			StringBuffer finalLine = new StringBuffer();
			boolean writeAdvise = false;
			for(Character c : line.toCharArray()) {
				Matcher matcher = pat.matcher(c.toString());
				if (!matcher.find()){
					String encoded = unicodeEscaped(new String(c.toString()).charAt(0));
					finalLine.append(encoded);
					writeAdvise = true;
				} else{
					finalLine.append(c);
				}
				
			}
			
			if (writeAdvise && advise){
				finalLine.append(" # CARACTER MODIFICADO");
			}
			
			System.out.println(finalLine.toString());

		}

	}

	/**
	 * Converts the string to the unicode format '\u0020'.
	 * 
	 * This format is the Java source code format.
	 * 
	 * If <code>null</code> is passed in, <code>null</code> will be returned.
	 * 
	 * <pre>
	 *   CharUtils.unicodeEscaped(null) = null
	 *   CharUtils.unicodeEscaped(' ')  = "\u0020"
	 *   CharUtils.unicodeEscaped('A')  = "\u0041"
	 * </pre>
	 * 
	 * @param ch
	 *            the character to convert, may be null
	 * @return the escaped unicode string, null if null input
	 */
	public static String unicodeEscaped(Character ch) {
		if (ch == null) {
			return null;
		}
		return unicodeEscaped(ch.charValue());
	}

}