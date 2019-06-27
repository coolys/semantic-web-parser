# semantic web parser

Extract Schema.org structured data from HTML page

# Overview
semantic web parser parser is a Java Libray that parse HTML page in order to extract schema.org vocabulary.

**Note that this library is still in active development and no stable version has been released yet. The API can change at anytime.**

## Quick Start

Maven depedency 

```
 <depedency>
   <groupId>io.github.crawlerbot</groupId>
    <artifactId>crawler-parser</artifactId>
    <version>1.0.1-SNAPSHOT</version>
 </depedency>
 
```

You can check the content of the test to know how to use the library, basically you can parse an HTML page like this :
```java
Scraper scraper = new Scraper();
List<Map<String, Object>> thingList = scraper.extractTo(new URL("https://www.lazada.vn/products/combo-ao-thun-be-trai-chat-lieu-mem-min-vd-i225056154-s307710790.html?spm=a2o4n.home.flashSale.4.1905e182Os10xC&search=1&mp=1"), 20000);;
```

## Input / Output
Currently, the library accepts an HTML file or an URL in input. It will fetch it if needed, parse it (using jsoup) and convert the schema.org vocabulary found (in Microdata or in JSON-LD) into Java schema.org entities.

The output format use the [Google schemaorg-java](https://github.com/google/schemaorg-java) library. Note that I repackage this library to host it on Maven Central. The fork can be found [here](https://github.com/mautini/schemaorg-java).

## Known Limitations
- Support only the JSON-LD and Microdata format (RDFa support is coming)
- For limitations concerning the JSON-LD parsing, check the limitation of the [Google schemaorg-java](https://github.com/google/schemaorg-java) library
- In Microdata, all the value use the datatype `com.google.schemaorg.core.datatype.Text`
- The library is not available on Maven Central (it will be with the release of the first stable version)

## Links
- [schema.org](https://schema.org/)
- [JSON-LD](https://json-ld.org)
- [Google schemaorg-java](https://github.com/google/schemaorg-java)
- [Fork of Google schemaorg-java](https://github.com/mautini/schemaorg-java)
- [Jsoup](https://jsoup.org/)
