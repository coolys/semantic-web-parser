package io.github.crawlerbot;

import io.github.crawlerbot.extractor.Extractor;
import io.github.crawlerbot.extractor.JsonLdExtractor;
import io.github.crawlerbot.extractor.MetaExtractor;
import io.github.crawlerbot.extractor.MicrodataExtractor;
import io.github.crawlerbot.model.Entity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Scraper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);

    private List<Extractor> extractors;
    private MetaExtractor metaExtractor = new MetaExtractor();

    public Scraper() {
        extractors = Arrays.asList(
                new JsonLdExtractor(),
                new MicrodataExtractor()
        );
    }

    public Set<Map<String, List<String>>> extractMeta(URL url, int timeout) throws IOException {
        Document document = Jsoup.parse(url, timeout);
        return metaExtractor.extract(document);
    }
    public List<Entity> extract(File file) throws IOException {
        Document document = Jsoup.parse(file, "UTF-8");
        return scrap(document);
    }
    public Set<Map<String, List<String>>> extractMeta(File file) throws IOException {
        Document document = Jsoup.parse(file, "UTF-8");
        return metaExtractor.extract(document);
    }
    public List<Map<String, Object>>  extractTo(File file) throws IOException{
        Document document = Jsoup.parse(file, "UTF-8");
        return extractors.stream()
                .flatMap(extractor -> extractor.getThing(document).stream())
                .collect(Collectors.toList());
    }

    public List<Entity> extract(URL url, int timeout) throws IOException {
        Document document = Jsoup.parse(url, timeout);
        return scrap(document);
    }
    public List<Map<String, Object>>  extractTo(URL url, int timeout) throws IOException {
        Document document = Jsoup.parse(url, timeout);
        return extractors.stream()
                .flatMap(extractor -> extractor.getThing(document).stream())
                .collect(Collectors.toList());
    }
    public List<Map<String, Object>>  extractTo(String html) {
        Document document = Jsoup.parse(html);
        return extractors.stream()
                .flatMap(extractor -> extractor.getThing(document).stream())
                .collect(Collectors.toList());
    }

    public List<Entity> extract(String html) {
        Document document = Jsoup.parse(html);
        return scrap(document);
    }
    public Set<Map<String, List<String>>> extractMeta(String html) throws IOException {
        Document document = Jsoup.parse(html);
        return metaExtractor.extract(document);
    }

    private List<Map<String, Object>> scraps(Document document) {
        return new JsonLdExtractor().getThing(document);
    }
    private List<Entity> scrap(Document document) {
        return extractors.stream()
                .flatMap(extractor -> extractor.getThings(document).stream())
                .collect(Collectors.toList());
    }
}
