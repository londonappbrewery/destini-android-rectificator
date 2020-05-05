package com.londonappbrewery.destini;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String HISTORY_PANEL_INDEX_KEY = "HistoryPanelIndex";

    // TODO: Steps 4 & 8 - Declare member variables here:
    int historyPanelIndex;

    HistoryPanel[] panels = new HistoryPanel[]{
            new HistoryPanel(R.string.T1_Story,R.string.T1_Ans1,R.string.T1_Ans2,R.string.T3_Story,R.string.T2_Story),
            new HistoryPanel(R.string.T2_Story,R.string.T2_Ans1,R.string.T2_Ans2,R.string.T3_Story,R.string.T4_End),
            new HistoryPanel(R.string.T3_Story,R.string.T3_Ans1,R.string.T3_Ans2,R.string.T6_End,R.string.T5_End),
            new HistoryPanel(R.string.T4_End,0,0,R.string.T1_Story,R.string.T1_Story),
            new HistoryPanel(R.string.T5_End,0,0,R.string.T1_Story,R.string.T1_Story),
            new HistoryPanel(R.string.T6_End,0,0,R.string.T1_Story,R.string.T1_Story)
    };

    TextView storyTextView;
    Button buttonTop;
    Button buttonBottom;
    Button restartButton;

    HistoryPanel actualPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            historyPanelIndex = savedInstanceState.getInt(HISTORY_PANEL_INDEX_KEY);
            Log.e("INDEX", Integer.toString(historyPanelIndex));
        }else {
            historyPanelIndex = 0;
        }

        storyTextView = findViewById(R.id.storyTextView);
        buttonTop = findViewById(R.id.buttonTop);
        buttonBottom = findViewById(R.id.buttonBottom);
        restartButton = findViewById(R.id.restartButton);

        buttonTop.setOnClickListener(this);
        buttonBottom.setOnClickListener(this);

        actualPanel = panels[historyPanelIndex];
        updateHistoryPanel();


    }

    void updateHistoryPanel(){

        storyTextView.setText(actualPanel.historyTextId);
        if (
                actualPanel.historyTextId == R.string.T4_End
                || actualPanel.historyTextId == R.string.T5_End
                || actualPanel.historyTextId == R.string.T6_End
        ) {
            buttonTop.setVisibility(View.GONE);
            buttonBottom.setVisibility(View.GONE);
            restartButton.setVisibility(View.VISIBLE);
        }
        if (actualPanel.answerIdA != 0 && actualPanel.answerIdB != 0){
            buttonTop.setText(actualPanel.answerIdA);
            buttonBottom.setText(actualPanel.answerIdB);
        }

    }

    public void restart(View view){
        historyPanelIndex = 0;
        actualPanel = panels[historyPanelIndex];
        updateHistoryPanel();
        buttonTop.setVisibility(View.VISIBLE);
        buttonBottom.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonTop){
            for (int panelIndex = 0; panelIndex < panels.length; panelIndex++){
                if (actualPanel.nextHistoryPanelIdA == panels[panelIndex].historyTextId){
                    historyPanelIndex = panelIndex;
                    actualPanel = panels[panelIndex];
                    updateHistoryPanel();
                    break;
                }
            }
        }
        if (v == buttonBottom){
            for (int panelIndex = 0; panelIndex < panels.length; panelIndex++){
                if (actualPanel.nextHistoryPanelIdB == panels[panelIndex].historyTextId){
                    historyPanelIndex = panelIndex;
                    actualPanel = panels[panelIndex];
                    updateHistoryPanel();
                    break;
                }
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(HISTORY_PANEL_INDEX_KEY, historyPanelIndex);
    }
}
