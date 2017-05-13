package edu.upc.eetac.dsa.calculadorasencilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Ignacio on 05/05/2017.
 */

public class LlistaOperacions extends AppCompatActivity {
    List<String> historial= new ArrayList<>();
    String histo=new String();
    String[] list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_operacions);
        Listar();
        ArrayAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,historial);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LlistaOperacions.this,TractamentOperacio.class);
                intent.putExtra("operacio",((TextView)view).getText());
                startActivityForResult(intent,100);

            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        Intent intent1=getIntent();
        if(resultCode==1){
            String result=intent.getExtras().getString("delete");
            for (int i=0;i<historial.size();i++){
                if(historial.get(i).equals(result))
                    historial.remove(i);
            }
            ArrayAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,historial);
            ListView listView=(ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
        else if(resultCode==2)
        {
            String result=intent.getExtras().getString("modify");
            intent1.putExtra("modify",result);
            setResult(1,intent1);
            finish();
        }
    }
    private void Listar(){
        histo=getIntent().getExtras().getString("histo");
        list=histo.split(",");
        for (int i=0;i<list.length;i++){
            if(!list[i].equals(getIntent().getExtras().getString("borrar")))
                historial.add(list[i]);
        }
    }
    public void borrarhisto(View view){
        Intent intent=new Intent(LlistaOperacions.this,ConfirmarEsborrar.class);
        startActivity(intent);
    }
    public void atras(View view){
        Intent intent1=getIntent();
        StringBuffer list=new StringBuffer();
        for (int i=0;i<historial.size();i++){
            list.append(historial.get(i));
            if(i<historial.size()-1)
            list.append(",");
        }
        intent1.putExtra("lista",list.toString());
        setResult(RESULT_OK,intent1);
        finish();
    }
}
