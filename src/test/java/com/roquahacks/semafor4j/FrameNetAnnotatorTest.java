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
		final String javaHomePath = "/usr/lib/jvm/java-8-oracle/bin";
		this.fnAnno = new FrameNetAnnotator(javaHomePath);
	}
	
	@Test
	public void annotateStringTest() {
		final String studentTest = "The student sends the letter to the university";
		this.fnAnno.addToCache(studentTest);
		try {
			HashMap<String, List<Frame>> frameMap = fnAnno.fetchFNResultsFromCache();
			System.out.println();
			assertEquals(1, frameMap.size());
			List<Frame> frames = frameMap.get(studentTest);
			System.out.println(frames);
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

}
