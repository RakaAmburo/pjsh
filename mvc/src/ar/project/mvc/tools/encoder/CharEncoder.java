package ar.project.mvc.tools.encoder;

import java.io.IOException;

import ar.project.ent.tools.encoder.CharEncoderMain;

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
public class CharEncoder {

	public static void main(String[] args) throws IOException {
		CharEncoderMain.propName = "ApplicationResources_fr_FR.properties";
		CharEncoderMain.main(args);
	}

}