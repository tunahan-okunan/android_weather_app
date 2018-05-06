package com.okunan.tunahan.havadurumuuygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView getirilecekTextView;
    Button click;
    EditText mEditText;
    HavaDurumuGetir mHavaDurumuGetir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHavaDurumuGetir=new HavaDurumuGetir(getirilecekTextView,mEditText.getText().toString());
                mHavaDurumuGetir.execute();
            }
        });

    }
    private void init(){
        getirilecekTextView=(TextView)findViewById(R.id.fetched_data_text_view);
        click=(Button)findViewById(R.id.click_me_button);
        mEditText=(EditText)findViewById(R.id.sehir_edit_text);
    }
}
