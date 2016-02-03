# SEMA4J
SEMA4J is a Java wrapper for easily accessing FrameNet semantic parsing by the [SEMAFOR](https://github.com/Noahs-ARK/semafor-semantic-parser) parser. Since this parser is primarly
designed as a command line tool, this wrapper gives you easy programmatic access to FrameNet semantic parsing. It is also
very easy to configure and get started with, let's check it out.

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
Often you want to collect multiple sentences to be annotated at once. Simply add them to
the cache. From the reults map, you can easily access to Frame list for each annotated string.
```java
String sentence = "The students sends the letter to the university.";
List<String> sentences = new ArrayList<>();
sentences.add("Wait for the results");
sentences.add("Receive acknowledgement from the university")
//Add to cache
fnAnno.addToCache(sentence);
fnAnno.addToCache(sentences);
//Get annotation results
HashMap<String, List<Frame>> frameAnnos = fnAnno.fetchFNResults(sentence);
List<Frame> frames = frameAnnos.get(sentence);
```
