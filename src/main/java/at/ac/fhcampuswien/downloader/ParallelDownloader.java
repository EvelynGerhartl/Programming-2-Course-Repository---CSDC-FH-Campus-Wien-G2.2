package at.ac.fhcampuswien.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader{

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables

        //Returns the number of processors (!) available to the JVM
        int numWorkers = Runtime.getRuntime().availableProcessors();
        //creates threadPool; reuses a fixed number of threads
        ExecutorService threadPool = Executors.newFixedThreadPool(numWorkers);


      //  <>Future<>submit(Callable<> task);

        List<Callable<String>> callables = new ArrayList<>();  //all the to be executed tasks
        for(String url : urls){ // create tasks dynamically
           // int idx = i;
            // pass the async function as a lambda
            Callable<String> task = () -> saveUrl2File(url);
            // returns callable (future???) objects -> adds all objects to array --- correct???
            callables.add(task);          //alt:  futures.add(pool.submit(task)); // pool.submit returns Future objects -> add all Future objects to array
        }
        //Pass 10 tasks to the pool
      //  for(int i = 0; i < 10; i++){
            //create task to task
        //    Runnable worker = new WorkerThread(i);
            //pass task to threadPool
      //      threadPool.execute(worker);
        //}
        //stop accepting new tasks & finish all ongoing executions
     //   threadPool.shutdown();
        //
        //  int count = 0;

        return 0;
    }

    private Integer doWork(int idx) {
        return null;
    }
}
