package me.sailor.libcommon.manager.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Administrator on2019/5/29 16:34
 * @desc
 */
public class ExecutorManager {
    private AppExecutors mExecutors;

    private ExecutorManager() {
        mExecutors = new AppExecutors(
                Executors.newSingleThreadExecutor(),
                Executors.newScheduledThreadPool(AppExecutors.THREAD_COUNT),
                new AppExecutors.MainThreadExecutor()
        );
    }

    static class ExecutorManagerHolder {
        static ExecutorManager instance = new ExecutorManager();
    }

    public static ExecutorManager getInstance() {
        return ExecutorManagerHolder.instance;
    }

    public Executor singleIO() {
        return mExecutors.singleIO();
    }

    public Executor netIO() {
        return mExecutors.networkIO();
    }

    public Executor mainThrea() {
        return mExecutors.mainThread();
    }
}
