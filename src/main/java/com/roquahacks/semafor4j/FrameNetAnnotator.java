package com.roquahacks.semafor4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.roquahacks.semafor4j.model.Frame;

/**
 * 
 * <br>The <code>FrameNetAnnotator</code> is a wrapper class for extracting frame net semantic annotations from 
 * the SEMAFOR semantic parser. 
 */
public class FrameNetAnnotator {

	private FrameNetXMLParser fnParser;
	private FrameNetService fnService;
	private static FrameNetAnnotator fnAnno;
	private List<String> activitiesToAnnotate;
	
	private FrameNetAnnotator() throws ParserConfigurationException, FileNotFoundException {
		this.fnService = new FrameNetService();
		this.fnParser = new FrameNetXMLParser();
		this.activitiesToAnnotate = new ArrayList<String>();
	}
	
	
	public FrameNetAnnotator annotateSentences(List<String> sentences) {
		activitiesToAnnotate.addAll(sentences);
		return this;
	}
	
	
	public FrameNetAnnotator annotate(String s) {
		activitiesToAnnotate.add(s);
		return this;
	}
	
	public HashMap<String, List<Frame>> fetchFNResults() throws ParserConfigurationException, SAXException, IOException {
		fnService.createAnnotationFile(activitiesToAnnotate);
		fnService.runFNSemanticParsing();
		HashMap<String, List<Frame>> frameMap = fnParser.fetchFNData(FrameNetOptions.ABS_PATH_FNDATA + 
				FrameNetOptions.FN_FILE_OUT_NAME);
		fnService.cleanAll();
		activitiesToAnnotate.clear();
		return frameMap;
	}
	
	public static FrameNetAnnotator getInstance() throws ParserConfigurationException, FileNotFoundException {
		if(fnAnno == null) {
			fnAnno = new FrameNetAnnotator();
		}
		return fnAnno;
	}
}
