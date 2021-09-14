package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;
import com.projet.hungrybirds.actions.LoginAction;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    // Récupération des classes
    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    // Instanciation des variables
    EditText mEditMail, mEditPassword;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.activity_main);

        // Récupération du contexte
        mContext = this;

        // Récupération des boutons
        Button buttonConnexion = (Button)findViewById(R.id.buttonConnexion);

        buttonConnexion.setOnClickListener(mGetInputValues);
    }

    private View.OnClickListener mGetInputValues = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            mEditMail = (EditText)findViewById(R.id.editMailConnexion);
            mEditPassword = (EditText)findViewById(R.id.editPasswordConnexion);

            // Récupération du texte des inputs
            String zMail = cFunctionsClass.getTextFromInput(mEditMail);
            String zPasword = cFunctionsClass.getTextFromInput(mEditPassword);

            // Vérification du format du mail
            boolean bMailConfirm = cFunctionsClass.checkMail(zMail);

            // Si le format du mail correspond au format normal d'une adresse, alors on check dans le back si l'utilisateur existe
            if(bMailConfirm)
            {
                cLoginAction.sendLogin(mContext, zMail, zPasword, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        System.out.println("Result : " + result);
                    }
                });

                // Si l'utilisateur existe, alors il se connecte, sinon un message d'erreur s'affichera
            }
        }
    };

    public void goToGestionUser(View view)
    {
        Intent intent = new Intent(this, GestionUserActivity.class);
        startActivity(intent);
    }

    // Méthode pour aller sur le noRegister
}