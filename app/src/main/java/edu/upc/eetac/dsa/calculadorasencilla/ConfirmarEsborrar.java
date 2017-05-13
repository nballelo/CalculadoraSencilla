package edu.upc.eetac.dsa.calculadorasencilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ignacio on 05/05/2017.
 */

public class ConfirmarEsborrar extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_esborrar);
    }
    public void no(View view){
        onBackPressed();
    }
    public void borrar(View view){
        Intent intent=new Intent(ConfirmarEsborrar.this,PantallaPrincipal.class);
        startActivity(intent);
        finish();
    }
}
