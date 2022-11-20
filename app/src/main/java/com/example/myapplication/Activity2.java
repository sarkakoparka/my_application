package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Activity3;
import com.example.myapplication.R;


public class Activity2 extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionTextView;
    TextView pytaniaTextView;
    Button odpA, odpB, odpC, odpD;
    Button submitButton;
    int wynik = 0;
    int totalQuestion = PytaniaOdpowiedzi.pytania.length;
    int currentQuestionIndex = 0;
    String wybranaOdpowiedz = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_2);

        totalQuestionTextView = findViewById(R.id.All_question);
        pytaniaTextView = findViewById(R.id.pytanie);
        odpA = findViewById(R.id.odpA);
        odpB = findViewById(R.id.odpB);
        odpC = findViewById(R.id.odpC);
        odpD = findViewById(R.id.odpD);
        submitButton= findViewById(R.id.submit_btn);
        odpA.setOnClickListener(this);
        odpB.setOnClickListener(this);
        odpC.setOnClickListener(this);
        odpD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionTextView.setText("Liczba pytań:" + totalQuestion);

        loadNewQuestion();


    }
    @Override
    public void onClick(View view) {

        odpA.setBackgroundColor(Color.WHITE);
        odpB.setBackgroundColor(Color.WHITE);
        odpC.setBackgroundColor(Color.WHITE);
        odpD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(wybranaOdpowiedz.equals(PytaniaOdpowiedzi.poprawneOdpowiedzi[currentQuestionIndex])){
                wynik++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else{
            //choices button clicked
            wybranaOdpowiedz  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }
    void loadNewQuestion() {

        if(currentQuestionIndex == totalQuestion) {
            quitQuiz();
            return;
        }
        pytaniaTextView.setText(PytaniaOdpowiedzi.pytania[currentQuestionIndex]);
        odpA.setText(PytaniaOdpowiedzi.wybory[currentQuestionIndex][0]);
        odpB.setText(PytaniaOdpowiedzi.wybory[currentQuestionIndex][1]);
        odpC.setText(PytaniaOdpowiedzi.wybory[currentQuestionIndex][2]);
        odpD.setText(PytaniaOdpowiedzi.wybory[currentQuestionIndex][3]);
    }



    void quitQuiz(){
        String Status = "";
        if(wynik>totalQuestion*0.60) {
            Status = "Zaliczyłeś Nasz Quiz";
        }else {
            Status = "Niestety, nie zaliczyłeś quizu. Spróbuj ponownie";
        }
        new AlertDialog.Builder(this)
                .setTitle(Status)
                .setMessage("Wynikt to "+ wynik)
                .setPositiveButton("Spróbuj ponownie",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        wynik = 0;
        currentQuestionIndex =0;
        loadNewQuestion();

    }

    public static class PytaniaOdpowiedzi {

        public static String pytania[]={
                "Rozwiń skrót WWW",
                "Który nie jest językiem programowania?",
                "Co to jest zip?",
                "Co zawiera plik AndroidManifest.xml?",
                "Czy dostaniemy 5tki?",

        };

        public static String wybory[][]={
                {"World Wide Web", "Wide World Web", "Whole World Web", "Web World Wide"},
                {"Java","Kotlin","Python","Android Studio"},
                {"rozszerzenie dźwięku", "format kompresji plików", "rozszerzenie obrazka","rozdzaj muzyki"},
                {"opisuje wszystkie składniki aplikacji na Androida","zapewnia dostęp do zasobów", "wczytuje dane Usera","ustawia aktualny czas"},
                {"tak", "oczywiście, że tak", "nie widzę innej możliwości", "let's do it!" },
        };

        public static String poprawneOdpowiedzi[] ={
                "World Wide Web",
                "Android Studio",
                "format kompresji plików",
                "opisuje wszystkie składniki aplikacji na Androida",
                "tak",



        };
    }
}
