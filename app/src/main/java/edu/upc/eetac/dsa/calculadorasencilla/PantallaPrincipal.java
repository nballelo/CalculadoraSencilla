package edu.upc.eetac.dsa.calculadorasencilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class PantallaPrincipal extends AppCompatActivity {
    List<String> historial=new ArrayList<String>();
    String tag="Events";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Log.d(tag,"Event onCreate");
    }
    public void mostrarResultat(View view){
        try {
            EditText text1 =(EditText) findViewById(R.id.Num1);
            EditText text2 =(EditText) findViewById(R.id.Num2);
            EditText text3 =(EditText) findViewById(R.id.resultat);
            Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
            String s = mySpinner.getSelectedItem().toString();
            int num1=Integer.parseInt(text1.getText().toString());
            int num2=Integer.parseInt(text2.getText().toString());
            int resultat=0;
            switch (s){
                case "+":
                    resultat=num1+num2;
                    break;
                case "-":
                    resultat=num1-num2;
                    break;
                case "*":
                    resultat=num1*num2;
                    break;
                case "/":
                    if(num2!=0){
                        resultat=num1/num2;
                        if((num1%num2)!=0)
                            Toast.makeText(this,"esta calculadora no muestra los decimales",LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(this,"no se puede dividir entre 0",LENGTH_SHORT).show();
                    break;
            }
            String operacio=((historial.size()+1)+": "+num1+" "+s+" "+num2+" = "+resultat);
            historial.add(operacio);
            text3.setText(String.valueOf(resultat));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Algo salió mal",Toast.LENGTH_LONG).show();
        }
    }
    public void c(View view){
        EditText text1 =(EditText) findViewById(R.id.Num1);
        EditText text2 =(EditText) findViewById(R.id.Num2);
        EditText text3 =(EditText) findViewById(R.id.resultat);
        text1.setText("0");
        text2.setText("0");
        text3.setText("0");
    }
    public void mostrarHistorial(View view){
        try {
            StringBuffer list=new StringBuffer();
            for (int i=0;i<historial.size();i++){
                list.append(historial.get(i));
                if(i<historial.size()-1)
                    list.append(",");
            }
            Intent intent=new Intent(PantallaPrincipal.this,LlistaOperacions.class);
            intent.putExtra("histo",list.toString());
            startActivityForResult(intent,100);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Algo salió mal",Toast.LENGTH_LONG).show();

        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        EditText text1 =(EditText) findViewById(R.id.Num1);
        EditText text2 =(EditText) findViewById(R.id.Num2);
        EditText text3 =(EditText) findViewById(R.id.resultat);
        if(resultCode==1){
            String result=intent.getExtras().getString("modify");
            String[] as=result.split(" ");
            text1.setText(as[1]);
            text2.setText(as[3]);
            text3.setText(as[5]);
        }
        else if(resultCode==RESULT_OK){
            historial=new ArrayList<String>();
            String histo=intent.getExtras().getString("lista");
            String[] list=histo.split(",");
            String s,j;
            for (int i=1;i<=list.length;i++){
                    s=list[i-1];
                    j=(i)+s.substring(1,s.length());
                    historial.add(j);
            }
            text1.setText("0");
            text2.setText("0");
            text3.setText("0");
        }
    }
}
