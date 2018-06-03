package cr.ac.tec.ec.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;

import java.util.concurrent.TimeUnit;

/**
 * Created by CASA on 6/2/2018.
 */

public class Database {
    private static MobileServiceClient mClient;

    public static void connect(Activity context){
        try{
            mClient = new MobileServiceClient(
                    "https://requerimientos-cines35mm.azurewebsites.net",
                    context
            );
        }catch(Exception e){}
    }

    public static MobileServiceList<Género> getGens(){
        try {
            final MobileServiceTable<Género> mGens = mClient.getTable(Género.class);
            //return mGens.execute().get();
            MobileServiceList<Género> results;

            new AsyncTask<MobileServiceList<Género>, MobileServiceList<Género>, MobileServiceList<Género>>() {

                @Override
                protected MobileServiceList<Género> doInBackground(MobileServiceList<Género>... params) {
                    try {
                        return mGens.execute().get();
                        //Log.d("debug","data success");
                        //String str="";
                    }
                    catch(Exception e)
                    {

                    }
                    return null;
                }
            }.execute();

            return null;
        }catch(Exception e){
            return null;
        }
    }
}
