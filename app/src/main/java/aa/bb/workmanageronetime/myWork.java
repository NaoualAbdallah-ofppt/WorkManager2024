package aa.bb.workmanageronetime;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class myWork  extends Worker {
    public myWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        /*
        tacheLongue();
        return Result.success();
         */




        //Si des données ont été envoyée par le flux principal
        //elle peuvent être récupérées ici
        //Cet exemple pour une donnée envoyée avec la clé num
        int data1 =getInputData().getInt("num",0);
        String data2 =getInputData().getString("nom");

        String retour=tacheLongue(data1,data2);

        //si jamais la fonction retourne qq chose et que je veux envoyer ce
        //résultat au flux principal pour exploitation

        Data outputData = new Data.Builder()
                .putString("result_key", retour)

                .build();

      // Retour du résultat avec les données de sortie
        return Result.success(outputData);
        //si pas de données retournée
        // return Result.success();




    }
    String tacheLongue(int num, String param1){
        //ici une tâche longue
        //lecture d'un fichier
        //accès à une api et récupération des données
        //accès à une BD distante
        //Copie
        //Téléchargemengt

        //supposons que elle va retourner qq
        //dans ce exemple un String
        return  "Bonjour, je viens de finir";

    }
}

