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
