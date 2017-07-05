package com.example.stus.test_;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.stus.test_.activitys.MainActivity;
import com.example.stus.test_.interfaces.IOnTaskSuccess;
import com.example.stus.test_.models.CountryModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class GetСountriesTask extends AsyncTask<Void, Void, Void> {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private IOnTaskSuccess onTaskSuccess;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(App.getCurrentActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        String url = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
        String jsonStr = sh.makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                Iterator<String> contacts = jsonObj.keys();
                while (contacts.hasNext()) {
                    CountryModel countryModel = new CountryModel();
                    String key = contacts.next();
                    countryModel.setCountryName(key);
                    countryModel.setCountryArray(String.valueOf(jsonObj.getJSONArray(key)));
                    App.rm().beginTransaction();
                    App.rm().copyToRealmOrUpdate(countryModel);
                    App.rm().commitTransaction();
                }


            } catch (final JSONException e) {
                e.printStackTrace();

            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (pDialog.isShowing())
            pDialog.dismiss();
        onTaskSuccess.onTaskSuccess();

    }

    public void setOnTaskSuccess(IOnTaskSuccess onTaskSuccess) {
        this.onTaskSuccess = onTaskSuccess;
    }
}
