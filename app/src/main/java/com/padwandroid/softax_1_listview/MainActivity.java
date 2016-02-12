package com.padwandroid.softax_1_listview;


import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ListActivity {

    private ArrayList<Item> m_parts = new ArrayList<Item>();
    private ItemAdapter m_adapter;
    private Button StartB, StopB;
    private int ostatni = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartB = (Button)findViewById(R.id.start_button);
        StopB = (Button)findViewById(R.id.stop_button);

        m_adapter = new ItemAdapter(this, R.layout.list_item, m_parts);
        setListAdapter(m_adapter);

        final Runnable rr = new Runnable() {
            @Override
            public void run() {
                Random liczba = new Random();

                // lista mnieszja niż 10
                    if(m_parts.size()<=9){
                        int liczbalos = liczba.nextInt(2);  //od 0 do 1
                        if(liczbalos == 0){
                            m_parts.add(new Item("Jabłko ", getResources().getColor(R.color.green), ostatni));
                            m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                            setListAdapter(m_adapter);
                        }else if(liczbalos == 1){
                            m_parts.add(new Item("Pomarańcza ", getResources().getColor(R.color.orange), ostatni*11));
                            m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                            setListAdapter(m_adapter);
                        }
                        //lista równa 10
                    }else if(m_parts.size() > 9){
                        int liczbalos_2 = liczba.nextInt(10); // od 0 do 9
                        if(liczbalos_2 > 8){
                            Item e = m_parts.get(liczbalos_2);
                            m_parts.remove(e);
                            m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                            setListAdapter(m_adapter);
                        }else if(liczbalos_2 <= 8){
                            int wybierz = liczba.nextInt(10);
                            //jesli jablko
                            Item e = m_parts.get(wybierz);
                            int licznik = e.getLiczba();
                            String imie = e.getName();
                            if(imie == "Pomarańcza "){
                                e.setLiczba((licznik+1)*11);
                                m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                                setListAdapter(m_adapter);
                            }else{
                                e.setLiczba(licznik+1);
                                m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                                setListAdapter(m_adapter);
                            }

//                            Item e = m_parts.get(wybierz);
//                            int aktualna_liczba = e.getLiczba();
//                            e.setLiczba(aktualna_liczba +1 );
//                            m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
//                            setListAdapter(m_adapter);
                        }
                    }

                }
        };

        final Handler han = new Handler();

        StartB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i =0;
                while (i<50){
                    han.postDelayed(rr, 1000*i);
                    i++;
                }

            }
        });

        StopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                han.removeCallbacks(rr);
                for(int i=0; i<m_parts.size();i++){
                    m_parts.removeAll(m_parts);
                    m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
                    setListAdapter(m_adapter);

                }
                ostatni = 1;
            }
        });

    }
}

