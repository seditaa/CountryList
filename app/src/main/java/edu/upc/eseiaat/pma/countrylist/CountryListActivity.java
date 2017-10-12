package edu.upc.eseiaat.pma.countrylist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CountryListActivity extends AppCompatActivity {

    private ArrayList<String> country_list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        String[] countries = getResources().getStringArray(R.array.countries);
        country_list = new ArrayList<>(Arrays.asList(countries));

        ListView list = (ListView) findViewById(R.id.country_list);

        //Adaptador

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, country_list);
        list.setAdapter(adapter);

        //Toque rápido en un país
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int pos, long id) {
                String short_click = " '" + country_list.get(pos) + "'.";
                Toast.makeText(CountryListActivity.this, getResources().getString(R.string.chosen) + short_click, Toast.LENGTH_SHORT).show();
            }
        });

        //Toque largo en un país
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View item, final int pos, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CountryListActivity.this);
                builder.setTitle(R.string.confirmation);
                String question = getResources().getString(R.string.question);
                builder.setMessage(question + " " + country_list.get(pos) + "?");
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country_list.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.create().show();
                return true;
            }
        });
    }
}
