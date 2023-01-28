package com.example.maya10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Main activity 2.
 */
public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener{
    /**
     * The TextView
     */
    TextView tv;
    /**
     * The ListView
     */
    ListView lv;
    /**
     * The Type.
     */
    int type =0;
    /**
     * The First element.
     */
    double firstElement, /**
     * The Progressor of the series
     */
    progressor, /**
     * The Sum of the series
     */
    sum;
    /**
     * The Place of the series
     */
    int place;
    /**
     * The Arr
     */
    String[] arr= new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView)findViewById(R.id.tv);
        lv = (ListView)findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Intent gi= getIntent();
        type=gi.getIntExtra("type", -1);
        progressor=gi.getDoubleExtra("progressor", 0);
        firstElement=gi.getDoubleExtra("firstElement", -1);
        arr[0] = firstElement + "";

        if(type==0) {
            for (int i = 1; i < arr.length; i++)
                arr[i] = (firstElement + progressor * (i)) + "";
        }
        else{
            for (int i = 1; i < arr.length; i++)
                arr[i] = String.format("%s", (firstElement * Math.pow(progressor,i)));
        }
        ArrayAdapter<String> adp= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        lv.setAdapter(adp);
        lv.setOnCreateContextMenuListener(this);
    }

    /**
     * Calculates the sum of a series
     *
     * @param i the place of the element
     */
    public void calcSum(int i)
    {
        if (type==0){
            sum = ((2*firstElement +i*progressor)*(i+1))/2;
        }
        else{
            sum= firstElement* (((Math.pow(progressor,i+1))-1)/(progressor-1));
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add("place");
        menu.add("sum");
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        place = info.position +1;
        calcSum(info.position);
        String st =item.getTitle().toString();
        if (st.equals("sum")) {
            tv.setText("the sum is: " + sum);
        }
        if(st.equals("place"))
        {
            tv.setText("the place is: " + place);
        }
        return true;
    }


    /**
     * goes to the previous view
     *
     * @param view the view
     */
    public void previous(View view) {
        finish();
    }


}