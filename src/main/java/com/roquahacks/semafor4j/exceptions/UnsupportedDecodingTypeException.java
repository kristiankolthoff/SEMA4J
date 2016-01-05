/**
 * 
 * SEMAFOR4J
 * 
 * Copyright (C) 2015 Kristian Kolthoff
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roquahacks.semafor4j.exceptions;
/**
 * Represents an exception for an unsupported decoding type
 * that is tried to be set to SEMAFOR.
 * @author Krisitan Kolthoff
 */
public class UnsupportedDecodingTypeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475425176599200961L;
	
	private static final String MESSAGE = "Unsupported decoding type specified for SEMAFOR : ";

	public UnsupportedDecodingTypeException(String description) {
		super(MESSAGE + description);
	}
}
