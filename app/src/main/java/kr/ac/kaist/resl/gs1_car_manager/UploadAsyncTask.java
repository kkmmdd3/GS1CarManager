package kr.ac.kaist.resl.gs1_car_manager;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Uploading asynchronous task
 */
public class UploadAsyncTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {

        Log.d(TAG, "Uploading " + params.length + " readings..");

        final String endpoint = "http://14.63.168.75:8080";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RESTService service = retrofit.create(RESTService.class);

        try {
            Call<Response> call2 = service.uploadTest(params[0].toString());
            Response response2 = call2.execute().body();
            Log.d(TAG, "read1:" + params[0].toString());
            assert response2.getStatus() == 200;
        } catch (IOException re) {
            Log.d(TAG, "read2:" + params[0]);
            Log.e(TAG, re.toString());
        }

        Log.d(TAG, "Done");
        return null;
    }
}