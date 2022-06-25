package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.controllers.NewsAPIException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsAPIException {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables

        //Returns the number of processors (!) available to the JVM
        int numWorkers = Runtime.getRuntime().availableProcessors();
        //creates threadPool; reuses a fixed number of threads
        ExecutorService threadPool = Executors.newFixedThreadPool(numWorkers);
        //  <>Future<>submit(Callable<> task);

        List<Callable<String>> callables = new ArrayList<>();  //all the to be executed tasks
        //* Callable: represents a task that can be executed in different threads and returns a generic value
        for (String url : urls) { // create tasks dynamically *(for each url from list of urls)
            // pass the async (????) function as a lambda
            Callable<String> task = () -> saveUrl2File(url);
            // returns callable (future???) objects -> adds all objects to array --- correct???
            callables.add(task);          //alt:  futures.add(pool.submit(task)); // pool.submit returns Future objects -> add all Future objects to array
        }

        int count = 0;

        try {
            List<Future<String>> futures = threadPool.invokeAll(callables);
            for (Future<String> result : futures) {
                if (result.get() != null) {
                    count++;    //* when downloaded successfully for print & comparison in menu
                }
            }
        } catch (InterruptedException | ExecutionException e) { //add exception to method signature
            throw new NewsAPIException(e.getMessage());
        }
        threadPool.shutdown();    //needed termination of all threads
        return count;             //*returns amount of articles downloaded successfully
    }
}
