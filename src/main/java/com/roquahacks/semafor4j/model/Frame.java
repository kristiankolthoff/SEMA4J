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
package com.roquahacks.semafor4j.model;

import java.util.List;
/**
 * Representing a <code>Frame</code> of the FrameNet semantic parsing and annotation project.
 */
public class Frame {

	private String sentence;
	private String name;
	private String target;
	private int rank;
	private List<FElement> fElements;
	
	public Frame(String sentence, String name, String target, List<FElement> fElements, int rank) {
		this.sentence = sentence;
		this.target = target;
		this.name = name;
		this.rank = rank;
		this.fElements = fElements;
	}
	
	public Frame(String sentence, String name, int rank) {
		this.sentence = sentence;
		this.name = name;
		this.rank = rank;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FElement> getfElements() {
		return fElements;
	}

	public void setfElements(List<FElement> fElements) {
		this.fElements = fElements;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
