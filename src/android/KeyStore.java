package com.markartishuk.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;

import android.content.Context;
import android.content.SharedPreferences;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KeyStore extends CordovaPlugin {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public KeyStore() {
        Context context = this.cordova.getActivity().getApplicationContext();
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        if (action.equals("setItem")) {
            String key = args.getString(0);
            String val = args.getString(1);
            this.setItem(key, val, callbackContext);
            return true;
        }
        if(action.equals("getItem")) {
            String key = args.getString(0);
            this.getItem(key, callbackContext);
            return true;
        }
        return false;
    }

    private void setItem(String key, String value, CallbackContext cb) {
        editor.putString(key, value);
        editor.commit();
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "Set -> " + key + ":" + value);
        cb.sendPluginResult(pluginResult);
    }

    private void getItem(String key, CallbackContext cb) {
        String val = sharedPreferences.getString(key, "");
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, val);
        cb.sendPluginResult(pluginResult);
    }
}