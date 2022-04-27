package com.udacity.webcrawler;

import com.udacity.webcrawler.parser.PageParser;
import com.udacity.webcrawler.parser.PageParserFactory;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

public class Crawler extends RecursiveAction {

    private final Clock clock;
    private final Duration time;
    private final int WordCount;
    private final int maxDepth;
    private final PageParserFactory factory;
    private final List<Pattern> ignoredUrls;
    private final Set<String> visited;
    private final String url;
    private final Instant finish;
    private final Map<String,Integer> counts;

    public Crawler (
            Clock clock,
            int maxDepth,
            PageParserFactory factory,
            Duration time,
            int wordCount,
            List<Pattern> ignoredUrls,
            Set<String> visited,
            String url,
            Instant finish,
            Map<String,Integer> counts) {
        this.clock=clock;
        this.maxDepth = maxDepth;
        this.factory=factory;
        this.time=time;
        this.WordCount=wordCount;
        this.ignoredUrls = ignoredUrls;
        this.visited = visited;
        this.url = url;
        this.finish=finish;
        this.counts = counts;

    }
    @Override
    protected void compute() {

        //Instant deadline=clock.instant().plus(time);
        if(maxDepth==0 || clock.instant().isAfter(finish))
        {
            return;
        }
        for(Pattern pattern:ignoredUrls)
        {
            if(pattern.matcher(url).matches())
            {
                return;
            }
        }
        if(visited.contains(url))
        {
            return;
        }
        visited.add(url);
        PageParser.Result result = factory.get(url).parse();
        for (ConcurrentMap.Entry<String,Integer> entryset : result.getWordCounts().entrySet()) {
            counts.compute(entryset.getKey(),(k,v)->(v==null)?entryset.getValue():entryset.getValue()+v);
        }
        List<Crawler> tasks = new ArrayList<>();
        for (String url : result.getLinks()) {
            tasks.add(new Crawler(
                    clock,maxDepth-1, factory,time,WordCount, ignoredUrls,visited, url, finish, counts));
        }
        invokeAll(tasks);
    }
}
