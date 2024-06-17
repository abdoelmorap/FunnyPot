package store.funnypot.data.sharedPref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

import store.funnypot.data.models.auth.User;
import store.funnypot.util.helper.Constants;

public class SharedVariables {
    SharedPreferences mSharedPreference;
    SharedPreferences.Editor mSharedPreferenceEditor;

    Semaphore mSemaphore;
    public SharedVariables(Context mContext){
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        mSharedPreferenceEditor = mSharedPreference.edit();
        mSemaphore = new Semaphore(
                Constants.MAX_PROCESS_AVAILABLE,
                true
        );
    }
    void setBooleanSharedVariable(SharedKeys flag,Boolean value){
        try {
            mSemaphore.acquire();
            mSharedPreferenceEditor.putBoolean(java.lang.String.valueOf(flag), value);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    Boolean getBooleanSharedVariable(SharedKeys flag){
        Boolean returnValue = false;
        try {
            mSemaphore.acquire();
            returnValue = mSharedPreference.getBoolean(
                    java.lang.String.valueOf(flag),
                    false
            );
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


    void setUserInSharedVariable(SharedKeys flag, User value) {
        try {
            Gson gson =new Gson();
            String stringValue = gson.toJson(value);
            mSemaphore.acquire();
            mSharedPreferenceEditor.putString(java.lang.String.valueOf(flag), stringValue);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    User getUserFromSharedVariable(SharedKeys flag) {
        User returnValue = null;
        try {
            Gson gson =new Gson();
            mSemaphore.acquire();
            String stringValue = mSharedPreference.getString(
                    java.lang.String.valueOf(flag),
                    ""
            );
            returnValue = gson.fromJson(stringValue,User.class );
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }



    void setStringSharedVariable(SharedKeys flag,String value){
        try {
            mSemaphore.acquire();
            mSharedPreferenceEditor.putString(java.lang.String.valueOf(flag), value);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    String getStrSharedVariable(SharedKeys flag){
        String returnValue = "";
        try {
            mSemaphore.acquire();
            returnValue = mSharedPreference.getString(
                    java.lang.String.valueOf(flag),
                    ""
            );
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


    void setIntArrSharedVariable(SharedKeys flag, ArrayList<Integer> value){
        try {
            mSemaphore.acquire();
            Gson gson =new Gson();
            String stringValue = gson.toJson(value);
            mSharedPreferenceEditor.putString(java.lang.String.valueOf(flag), stringValue);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    ArrayList<Integer> getIntArrSharedVariable(SharedKeys flag){
        ArrayList<Integer> returnValue = new ArrayList<Integer>();
        try {
            Gson gson =new Gson();
            mSemaphore.acquire();
            String stringValue = mSharedPreference.getString(
                    java.lang.String.valueOf(flag),
                    ""
            );
            returnValue = gson.fromJson(stringValue,ArrayList.class);
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    void setIntSharedVariable(SharedKeys flag,int value){
        try {
            mSemaphore.acquire();
            mSharedPreferenceEditor.putInt(java.lang.String.valueOf(flag), value);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    int getintSharedVariable(SharedKeys flag){
        int returnValue = 0;
        try {
            mSemaphore.acquire();
            returnValue = mSharedPreference.getInt(
                    java.lang.String.valueOf(flag),
                    0
            );
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }



    void setFloatSharedVariable(SharedKeys flag,float value){
        try {
            mSemaphore.acquire();
            mSharedPreferenceEditor.putFloat(java.lang.String.valueOf(flag), value);
            mSharedPreferenceEditor.commit();
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    float getFloatSharedVariable(SharedKeys flag){
        float returnValue = 0.0f;
        try {
            mSemaphore.acquire();
            returnValue = mSharedPreference.getFloat(
                    java.lang.String.valueOf(flag),
                    0.0f
            );
            mSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }



}
