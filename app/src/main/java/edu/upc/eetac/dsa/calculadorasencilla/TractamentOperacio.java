package edu.upc.eetac.dsa.calculadorasencilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ignacio on 05/05/2017.
 */

public class TractamentOperacio extends AppCompatActivity {
   String histo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atctivity_tractament_operacio);
        histo=getIntent().getExtras().getString("operacio");
        EditText text1 =(EditText) findViewById(R.id.editText2);
        text1.setText(histo);
    }
    public void borrar (View view){
        Intent intent=getIntent();
        intent.putExtra("delete",histo);
        setResult(1,intent);
        finish();
    }
    public void modificar(View view){
        Intent intent=getIntent();
        intent.putExtra("modify",histo);
        setResult(2,intent);
        finish();
    }
}
