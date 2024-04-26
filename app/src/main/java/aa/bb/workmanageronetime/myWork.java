package aa.bb.workmanageronetime;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class myWork  extends Worker {
    public myWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //Si des données ont été envoyée par le flux principal
        //elle peuvent être récupérées ici
        //Cet exemple pour une donnée envoyée avec la clé num
        int data1 =getInputData().getInt("num",0);
        String data2 =getInputData().getString("nom");

        //un travail long


        Log.i("aa","travail ");
        return Result.success();
    }
}
