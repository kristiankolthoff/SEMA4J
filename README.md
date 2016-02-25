# SEMA4J
SEMA4J is a Java wrapper for easily accessing FrameNet semantic parsing by the [SEMAFOR](https://github.com/Noahs-ARK/semafor-semantic-parser) parser. Since this parser is primarly
designed as a command line tool, this wrapper gives you easy programmatic access to FrameNet semantic parsing. It is also
very easy to configure and get started with, let's check it out.

#Installation
In order to be able to execute SEMA4J, you additionally need to add the original files of SEMAFOR to SEMA4J to the resources folder. The files you need to add can be found on the corresponding GitHub repository ([SEMAFOR Semantic Parser](https://github.com/Noahs-ARK/semafor-semantic-parser)). Just download the repository there, and include to the orignal repository file structure, the stackedParserServer and models folders to the root. A more detailed description of that can be found on their GitHub repository. Finally, add these files to this program under main/resources/semafor.

#Example
First of all, create a FrameNetAnnotator instance by specifying your JAVA_HOME installation path.
```java
FrameNetAnnotator fnAnno = new FrameNetAnnotator(javaHomePath);
```
Instantiating the FrameNetAnnotator that way sets up standard options for the parser. If you
want to set specific options, create a FrameNetOptions object and then create the annotator.
```java
FrameNetOptions fnOpts = new FrameNetOptions(true, true, false, FrameNetOptions.DECODING_TYPE_BEAM,
                                              FrameNetOptions.NO_GOLD_FILES, javaHomePath);
FrameNetAnnotator fnAnno = new FrameNetAnnotator(fnOpts);
```
Once you have created the annotator, you can start with the semantic parsing of strings.
This can be achieved, by simply calling the corresponding fetchFNResult methods as
shown below. This method returns a list with all frames invoked by the assigned string.
Afterwards you can access all properties of a frame and also iterate through the frame elements.

```java
List<Frame> frames = fnAnno.fetchFNResult("Send you application to the university");
for(Frame frame : frames) {
//Do something with the frame
  for(FElement felement : frame.iterator()) {
    //Do something with frame element
  }
}
```
Often you want to collect multiple sentences to be annotated at once. Simply add them to
the cache. From the results map, you can easily access to Frame list for each annotated string.

```java
List<String> sentences = new ArrayList<>();
sentences.add("Wait for the results");
sentences.add("Receive acknowledgement from the university")
//Add to cache
fnAnno.addToCache(sentences);
//Get annotation results
HashMap<String, List<Frame>> frameAnnos = fnAnno.fetchFNResultsFromCache(sentence);
//Access frames for "Wait for the results"
List<Frame> frames = frameAnnos.get(sentences.get(0));
```
