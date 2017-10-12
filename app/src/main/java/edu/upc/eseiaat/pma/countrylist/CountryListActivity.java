package edu.upc.eseiaat.pma.countrylist;

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

        //Adaptadot

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, country_list);
        list.setAdapter(adapter);

        //Toque rápido en un país
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int pos, long id) {
                String short_click = "Has escogigo " + country_list.get(pos) + ".";
                Toast.makeText(CountryListActivity.this, short_click, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
