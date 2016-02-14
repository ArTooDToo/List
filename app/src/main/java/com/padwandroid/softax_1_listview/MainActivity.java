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
    private Handler han = new Handler();
    private boolean stop = false;

    Runnable runnab = new Runnable() {
        @Override
        public void run() {
            try{
                Random liczba = new Random();

                        // lista mnieszja niż 10
                        if(m_parts.size()<=9){
                            int liczbalos = liczba.nextInt(2);  //od 0 do 1
                            if(liczbalos == 0){
                                m_parts.add(new Item("Jabłko ", getResources().getColor(R.color.green), ostatni));
                            }else if(liczbalos == 1){
                                m_parts.add(new Item("Pomarańcza ", getResources().getColor(R.color.orange), ostatni * 11));
                            }
                            //lista równa 10
                        }else if(m_parts.size() > 9){
                            int liczbalos_2 = liczba.nextInt(10); // od 0 do 9
                            if(liczbalos_2 > 8){
                                //usun losowy element
                                liczbalos_2 = liczba.nextInt(10);
                                Item e = m_parts.get(liczbalos_2);
                                m_parts.remove(e);
                            }else if(liczbalos_2 <= 8){
                                int wybierz = liczba.nextInt(10);
                                //jesli jablko
                                Item e = m_parts.get(wybierz);
                                int licznik = e.getLiczba();
                                String imie = e.getName();
                                if(imie == "Pomarańcza "){
                                    e.setLiczba(((licznik / 11) + 1) * 11);
                                }else{
                                    e.setLiczba(licznik + 1);
                                }
                            }

                    }
            }
            catch (Exception e){

            }
            finally {
                upDate();
                han.postDelayed(runnab, 1000);
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartB = (Button)findViewById(R.id.start_button);
        StopB = (Button)findViewById(R.id.stop_button);
        StartB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                han.post(runnab);
                //zabezpiecznie
                stop = false;
            }
        });

        StopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stop == false){
                    han.removeCallbacks(runnab);
                    stop = true;
                }else {
                    m_parts.removeAll(m_parts);
                    upDate();
                    ostatni = 1;
                    stop = false;
                }
            }
        });

    }

    public void upDate(){
        m_adapter = new ItemAdapter(MainActivity.this, R.layout.list_item, m_parts);
        setListAdapter(m_adapter);
    }

}

