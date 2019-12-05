package me.sailor.demolist.ui.system_about.callback;

import android.util.Log;

import java.util.ArrayList;

/**
 * -description:
 * -author: created by tang on 2019/10/15 9:21
 */
public enum CallbackManager {
    INSTANCE;

    CallbackManager() {
        Log.d("CallbackManager", "CallbackManager: ");
        mCallbacks = new ArrayList<>();
    }

    private ArrayList<ValueCallback> mCallbacks;

    public void setCallback(ValueCallback callback) {
        mCallbacks.add(callback);
        Log.d("CallbackManager", "size: " + mCallbacks.size() + "  hashCode---->" + mCallbacks.hashCode());
    }

    public void remove(ValueCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
        Log.d("CallbackManager", "size: " + mCallbacks.size() );
    }

    public ArrayList<ValueCallback> getCallbacks() {
        Log.d("CallbackManager", "size: " + mCallbacks.size() + "  hashCode--->" + mCallbacks.hashCode());
        return mCallbacks;
    }

}
