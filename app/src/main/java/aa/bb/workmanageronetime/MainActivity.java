package aa.bb.workmanageronetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constraints C = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data D = new Data.Builder()
                .putInt("num", 45)
                .putString("nom","Alami")
                .build();
//générer un identifiant
        UUID workId = UUID.randomUUID();
        WorkRequest wr1 = new OneTimeWorkRequest.Builder(myWork.class)
                .setId(workId) // l'identifiant va être utilisé pour annuler le travail par exemple
                .setConstraints(C)
                .setInputData(D)
              // .setInitialDelay(1, TimeUnit.MINUTES)
               //si jamais je ne veux pas lancer le travail immédiatement
                //exécution différée
                .build();

        WorkManager.getInstance(this).enqueue(wr1);

        //pour annuler un travail démarré
//WorkManager.getInstance(this).cancelWorkById(workId);


/*
        WorkRequest wr2= new PeriodicWorkRequest.Builder(
                myWork.class,15, TimeUnit.MINUTES)
                //.setInitialDelay(1,TimeUnit.HOURS)
               // .setConstraints(C)
             //   .setInputData(D)
                //durée minimale 15 mn
                .build();
  WorkManager.getInstance(this).enqueue(wr2);

 */

        //Si aucune contarainte et si aucune donnée
      //  WorkRequest wr = new OneTimeWorkRequest.Builder(myWork.class).build();

    }
}