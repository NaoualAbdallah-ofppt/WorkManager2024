package aa.bb.workmanageronetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView txt;
//Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.txt);


        //Mon travail peut avoir des contrainyte s= conditions d'excécution
        Constraints C = new Constraints.Builder()
                .setRequiresCharging(true) //en train de se charger
                .setRequiredNetworkType(NetworkType.CONNECTED) // réseau connecté
                .build();
        //Mon travail peut avoir besoin de données d'entrée
        //je suppose dans cet exemple que mon travail a besoin de deux informations
        //la première nommée num de type entier
        //et la deuxième nommée nom de type String
        Data D = new Data.Builder()
                .putInt("num", 45)
                .putString("nom","Alami")
                .build();
        //générer un identifiant unique (ce n'est pas obligatoire)
        UUID workId = UUID.randomUUID();

        WorkRequest wr1 = new OneTimeWorkRequest.Builder(myWork.class)
                .setId(workId) // l'identifiant va être utilisé pour annuler le travail par exemple
            //    .setConstraints(C)
                .setInputData(D)
               .setInitialDelay(1, TimeUnit.MINUTES)
               //si jamais je ne veux pas lancer le travail immédiatement
                //exécution différée
                .build();

        WorkManager.getInstance(this).enqueue(wr1);
//Si jamais notre travail retourne un résultat en sortie
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(wr1.getId())
                .observe(this, workInfo -> {
                 if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        // Récupération des données de sortie
                        Data outputData = workInfo.getOutputData();
                        // Extraction des données à partir de la clé
                        String resultData = outputData.getString("result_key");
                     //affichage dans un textview
                       txt.setText(resultData);
                        }

                });
        //pour annuler un travail démarré
//WorkManager.getInstance(this).cancelWorkById(workId);



/*
//durée minimale 15 mn
s'éxécutera toutes les 15 minutes mais ne sera lancé que dans une heure
        WorkRequest wr2= new PeriodicWorkRequest.Builder(
                myWork.class,15, TimeUnit.MINUTES)
                //.setInitialDelay(1,TimeUnit.HOURS)    //temps différée
                .build();
  WorkManager.getInstance(this).enqueue(wr2);

 */

        //Si aucune contrainte et si aucune donnée d'entrée ou de sortie
        //et s'il s'éxécute une seule fois et immédiatement

      //  WorkRequest wr = new OneTimeWorkRequest.Builder(myWork.class).build();
      //  WorkManager.getInstance(this).enqueue(wr1);
    }
}