package com.example.ritwik.geneticalgorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView generations,allPhrases,target,popMax,mutRate,bestAnswer;
    String TARGET = "care";
    int PopMax = 1000;
    double MutRate = 0.01;
    Button buGenerate;
    String ALL="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        buGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Population pop = new Population(TARGET,MutRate,PopMax);
                while (!pop.getBest().equals(TARGET)){
                    pop.naturalSelection();
                    pop.generate();
                    pop.calcFitness();
                    pop.getBest();
                    generations.setText(Integer.toString(pop.getGenerations()));
                    bestAnswer.setText(pop.getBest());
                    ALL += pop.allPhrases();
                    allPhrases.setText(ALL);
                }
            }
        });
    }

    private void initialize(){
        target = findViewById(R.id.tvTarget);
        generations = findViewById(R.id.tvGen);
        allPhrases = findViewById(R.id.tvPhrases);
        popMax = findViewById(R.id.tvPopMax);
        mutRate = findViewById(R.id.tvMut);
        buGenerate = findViewById(R.id.buGenerate);
        bestAnswer = findViewById(R.id.tvBest);

        target.setText(String.format("Target : %s", TARGET));
        popMax.setText(String.format("Max Population : %s", PopMax));
        mutRate.setText(String.format("Mutation Rate : %s", MutRate));
        allPhrases.setMovementMethod(new ScrollingMovementMethod());
    }
}
