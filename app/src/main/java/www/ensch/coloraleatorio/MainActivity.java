package www.ensch.coloraleatorio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String t1 = "Temporizador";
    Handler handler = new Handler();
    TextView tv;
    int time=0;
    int rate=100;
    Timer timer,timer2,timer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.textview);


        int factor1=1;

        timer=new Timer(t1);
        Tarea tarea=new Tarea(tv,factor1);
        timer.scheduleAtFixedRate(tarea, 0, rate);

    }

    @Override
    public void onPause(){
        super.onPause();
        timer.cancel();
        timer2.cancel();
        timer3.cancel();
    }

    class Tarea extends TimerTask {

        int factor;
        TextView textTarea;
        public Tarea(TextView textView,int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub

            Runnable cambiaTexto=new CambiaTexto(textTarea,factor);
            runOnUiThread(cambiaTexto);
        }
    }


    class Tarea2 extends TimerTask{

        int factor;
        TextView textTarea;
        public Tarea2(TextView textView,int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub

            Runnable cambiaTexto=new CambiaTexto(textTarea,factor);
            textTarea.post(cambiaTexto);
        }
    }


    class Tarea3 extends TimerTask{

        int factor;
        TextView textTarea;
        public Tarea3(TextView textView,int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public void run() {
            Runnable cambiaTexto=new CambiaTexto(textTarea,factor);
            handler.post(cambiaTexto);
        }
    }
    class CambiaTexto implements Runnable{

        int red,green,blue,factor;
        TextView textCambia;
        public CambiaTexto(TextView textView,int fact) {
            textCambia=textView;
            factor=fact;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            time=time+rate;

            red=(time/factor)%255;
            green=(int) ((0.75* time/factor)%255);
            blue=(int) ((0.60* time/factor)%255);

            String texto="TEMPORIZADOR\n rate= "+rate+"\n t= "+time;
            textCambia.setText(texto);
            textCambia.setTypeface(null, Typeface.BOLD);
            textCambia.setTextSize(30);
            textCambia.setTextColor(Color.rgb(red, green, blue));

        }
    }
}
