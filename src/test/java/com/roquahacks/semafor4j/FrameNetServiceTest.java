package com.roquahacks.semafor4j;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FrameNetServiceTest {

	private FrameNetService fnService;
	private List<String> sentences;
	
	@Before
	public void init() {
		try {
			this.fnService = new FrameNetService();
			this.sentences = new ArrayList<String>();
			this.sentences.add("Send invitation to the student");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createAnnotationFileTest() {
		fnService.createAnnotationFile(sentences);
		File f = new File(FrameNetOptions.ABS_PATH_FNDATA + FrameNetOptions.FN_FILE_NAME);
		assertEquals(true, f.exists());
	}
	
	@Test
	public void runFNSemanticParsingTest() {
		fnService.runFNSemanticParsing();
		File f = new File(FrameNetOptions.ABS_PATH_FNDATA + FrameNetOptions.FN_FILE_OUT_NAME);
		assertEquals(true, f.exists());
	}
	
	@Test
	public void runDirCleanerTest() {
		fnService.runFNSemanticParsing();
		File fIn = new File(FrameNetOptions.ABS_PATH_FNDATA + FrameNetOptions.FN_FILE_NAME);
		File fOut = new File(FrameNetOptions.ABS_PATH_FNDATA + FrameNetOptions.FN_FILE_OUT_NAME);
		fnService.cleanAll();
		assertEquals(false, fIn.exists());
		assertEquals(false, fOut.exists());
	}
	
	@After
	public void clean() {
		fnService.cleanAll();
	}
}
