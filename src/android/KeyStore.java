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
         Context context = this.cordova.getActivity().getApplicationContext();
        if(context == null) {
              PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Context is null");
                cb.sendPluginResult(pluginResult);
            return;
        }
        
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE );
        
        if(sharedPreferences == null) {
              PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Sharedpreferences is null");
                cb.sendPluginResult(pluginResult);
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(editor == null) {
              PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "editor is null");
                cb.sendPluginResult(pluginResult);
            return;
        }

        editor.putString(key, value);
        editor.commit();
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "Set -> " + key + ":" + value);
        cb.sendPluginResult(pluginResult);
    }

    private void getItem(String key, CallbackContext cb) {
        Context context = this.cordova.getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE );
         if(sharedPreferences == null) {
              PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Sharedpreferences is null");
                cb.sendPluginResult(pluginResult);
            return;
        }
        String val = sharedPreferences.getString(key, "");
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, val);
        cb.sendPluginResult(pluginResult);
    }
}