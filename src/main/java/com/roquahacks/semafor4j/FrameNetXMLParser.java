package com.roquahacks.semafor4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.roquahacks.semafor4j.model.FElement;
import com.roquahacks.semafor4j.model.Frame;

/**
 * The <code>FrameNetXMLParser</code> parses the XML document produced by SEMAFOR
 * and constructs <code>Frame</code> objects with <code>FrameElements</code>.
 */
public class FrameNetXMLParser {

	private DocumentBuilder db;
	private Document doc;
	
	public static final String TAG_SENTENCE = "sentence";
	public static final String TAG_TEXT = "text";
	public static final String TAG_ANNOTATION_SETS = "annotationSets";
	public static final String TAG_ANNOTATION_SET = "annotationSet";
	public static final String TAG_FRAME_NAME = "frameName";
	public static final String TAG_LAYERS = "layers";
	public static final String TAG_LAYER = "layer";
	public static final String TAG_LABELS = "labels";
	public static final String TAG_LABEL = "label";
	public static final String TAG_START = "start";
	public static final String TAG_END = "end";
	public static final String TAG_ID = "ID";
	public static final String TAG_NAME = "name";
	
	
	public FrameNetXMLParser() throws ParserConfigurationException {
		this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}
	
	public HashMap<String,List<Frame>> fetchFNData(String path) throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, List<Frame>> frameMap = new HashMap<String, List<Frame>>();
		doc = db.parse(path);
		/**Get list of sentences which were annotated**/
		NodeList listSentence = doc.getElementsByTagName(TAG_SENTENCE);
		/**Iterate over each sentence**/
		for (int i = 0; i < listSentence.getLength(); i++) {
			List<Frame> frames = new ArrayList<Frame>();
			Node nSentence =  listSentence.item(i);
			if(nSentence.getNodeType() == Node.ELEMENT_NODE) {
				Element eSentence = (Element) nSentence;
				String currSentenceText = eSentence.getElementsByTagName(TAG_TEXT).item(0).getTextContent();
				Element eAnnotationSet = (Element) eSentence.getElementsByTagName(TAG_ANNOTATION_SETS).item(0);
				NodeList nFrameSets = eAnnotationSet.getElementsByTagName(TAG_ANNOTATION_SET);
				/**Iterate over each invoked frame for the current sentence**/
				for (int j = 0; j < nFrameSets.getLength(); j++) {
					Element currAnnoSet = (Element) nFrameSets.item(j);
					String frameName = currAnnoSet.getAttribute(TAG_FRAME_NAME);
					Frame f = new Frame(currSentenceText, frameName, j);
					Element eLayers = (Element) currAnnoSet.getElementsByTagName(TAG_LAYERS).item(0);
					NodeList nLayers = eLayers.getElementsByTagName(TAG_LAYER);
					/**Extract Target indices from layer id=1**/
					Element eTarget = (Element) nLayers.item(0);
					Element eTargetLabels = (Element) eTarget.getElementsByTagName(TAG_LABELS).item(0);
					Element eTargetLabel = (Element) eTargetLabels.getElementsByTagName(TAG_LABEL).item(0);
					int targetStart = Integer.valueOf(eTargetLabel.getAttribute(TAG_START));
					int targetEnd = Integer.valueOf(eTargetLabel.getAttribute(TAG_END));
					f.setTarget(extractTarget(targetStart, targetEnd, currSentenceText));
					/**Extract frame elements from layer id=2**/
					Element eFrameElements = (Element) nLayers.item(1);
					Element eFrameLabels = (Element) eFrameElements.getElementsByTagName(TAG_LABELS).item(0);
					NodeList nFrameLabels = eFrameLabels.getElementsByTagName(TAG_LABEL);
					/**Iterate over each frame element of the current frame**/
					List<FElement> frameElements = new ArrayList<FElement>();
					for (int k = 0; k < nFrameLabels.getLength(); k++) {
						Element eFrameElement = (Element) nFrameLabels.item(k);
						int fid = Integer.valueOf(eFrameElement.getAttribute(TAG_ID));
						int startFe = Integer.valueOf(eFrameElement.getAttribute(TAG_START));
						int endFe = Integer.valueOf(eFrameElement.getAttribute(TAG_END));
						FElement fe = new FElement(eFrameElement.getAttribute(TAG_NAME), 
								extractFEContent(startFe, endFe, currSentenceText), fid);
						frameElements.add(fe);
					}
					f.setfElements(frameElements);
					frames.add(f);
				}
				frameMap.put(currSentenceText, frames);
			}
		}
		return frameMap;
	}
	
	private String extractTarget(int start, int end, String sentence) {
		return sentence.substring(start, end+1);
	}
	
	private String extractFEContent(int start, int end, String sentence) {
		return sentence.substring(start, end+1);
	}
}
