# Project: Parallel Web Crawler
This is initially built legacy web crawler, single-threaded application. It's a bit slow, and to improve its performance, it needs to upgrade the code to take advantage of multi-core architectures to increase crawler throughput and making it a multi-threaded application. Furthermore, it will measure the performance of your crawler to prove that, given the same amount of time, the multi-threaded implementation can visit more web pages than the legacy implementation. 

It performs the following steps to achieve the throughput:-

1) Crawler Configuration

Web crawler app reads in a JSON file to determine how it should run. Let's look an example of such a file:

#### JSON Configuration Example

```
{
  "startPages": ["http://example.com", "http://example.com/foo"],
  "ignoredUrls": ["http://example\\.com/.*"],
  "ignoredWords": ["^.{1,3}$"],
  "parallelism": 4,
  "implementationOverride": "com.udacity.webcrawler.SequentialWebCrawler",
  "maxDepth": 10,
  "timeoutSeconds": 2,
  "popularWordCount": 3,
  "profileOutputPath": "profileData.txt"
  "resultPath": "crawlResults.json"
}
```
2) Implementing Crawler Configuration
3) Crawler Output
   It needs to print the results to a JSON file using this format:

### Example JSON Output

```
{
  "wordCounts": {
    "foo": 54,
    "bar": 23,
    "baz": 14
  },
  "urlsVisited": 12 
}
```
3) Running the Legacy Crawler

4) Coding the Parallel Web Crawler


#### Running Tests

```
mvn test -Dtest=WebCrawlerTest,ParallelWebCrawlerTest
```

5) Fun with Functional Word Counting

```
mvn test -Dtest=WordCountsTest
```

6) Performance Profiler

#### Implementing the Profiler
```
mvn test -Dtest=ProfilerImplTest
```

7) Run the Parallel Crawler!
```
mvn package
java -classpath target/udacity-webcrawler-1.0.jar \
    com.udacity.webcrawler.main.WebCrawlerMain \
    src/main/config/sample_config.json
```
