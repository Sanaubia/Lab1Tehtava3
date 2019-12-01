package com.example.lab1tehtava3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);



        findViewById (R.id.button).setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);




    }


    private void lataa() {

        try {
            EditText osoite = findViewById (R.id.editText);
            TextView tuloste = findViewById (R.id.textView2);

            String oikeaOsoite = osoite.getText ().toString ();

            if(oikeaOsoite.contains ("https://") || oikeaOsoite.contains ("HTTPS://")){
               Log.d ("OSOITE", oikeaOsoite);
            }else{
                oikeaOsoite = "https://" + oikeaOsoite;

                Log.d ("OSOITE lisätty", oikeaOsoite);
            }


            URL url;
            url = new URL (oikeaOsoite);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ( );


            String result = fromStream (urlConnection.getInputStream ());

            tuloste.setText (result);

        } catch (MalformedURLException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
                                                                                    
    //from https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader (in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
        @Override
    public void onClick(View var1){

        if (var1.getId() == R.id.button){
            lataa ();
        }

    }



}
