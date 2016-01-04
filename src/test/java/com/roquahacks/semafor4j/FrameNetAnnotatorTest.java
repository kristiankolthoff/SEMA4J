package com.roquahacks.semafor4j;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.roquahacks.semafor4j.model.Frame;


public class FrameNetAnnotatorTest {

	private FrameNetAnnotator fnAnno;
	
	@Before
	public void init() throws FileNotFoundException, ParserConfigurationException {
		this.fnAnno = FrameNetAnnotator.getInstance();
	}
	
	@Test
	public void annotateActivityTest() {
		this.fnAnno.annotate("The student sends the letter to the university.");
		try {
			HashMap<String, List<Frame>> frameMap = fnAnno.fetchFNResults();
			assertEquals(1, frameMap.size());
			List<Frame> frames = frameMap.get("The student sends the letter to the university.");
			assertEquals(4, frames.size());
			assertEquals("Education_teaching", frames.get(0).getName());
			assertEquals("Sending", frames.get(1).getName());
			assertEquals("Text", frames.get(2).getName());
			assertEquals("Locale_by_use", frames.get(3).getName());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void annotateModelTest() {
		fnAnno.annotate("");
		try {
			HashMap<String, List<Frame>> frameMap = fnAnno.fetchFNResults();
			List<Frame> frames = frameMap.get("Send letter of acceptance");
			assertEquals("Sending", frames.get(2).getName());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
