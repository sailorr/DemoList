package me.sailor.libcommon.executor;/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import io.reactivex.annotations.NonNull;


/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
class AppExecutors {

    public static final int THREAD_COUNT = 3;

    private final Executor singleIO;

    private final Executor networkIO;

    private final Executor mainThread;

    public AppExecutors(Executor singleIO, Executor networkIO, Executor mainThread) {
        this.singleIO = singleIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public Executor singleIO() {
        return singleIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
