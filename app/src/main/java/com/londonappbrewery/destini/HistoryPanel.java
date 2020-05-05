package com.londonappbrewery.destini;

class HistoryPanel {
    int historyTextId;
    int answerIdA;
    int answerIdB;
    int nextHistoryPanelIdA;
    int nextHistoryPanelIdB;
    HistoryPanel(
            int historyTextId,
            int answerIdA,
            int answerIdB,
            int nextHistoryPanelIdA,
            int nextHistoryPanelIdB
    ){

        this.historyTextId = historyTextId;
        this.answerIdA = answerIdA;
        this.answerIdB = answerIdB;
        this.nextHistoryPanelIdA = nextHistoryPanelIdA;
        this.nextHistoryPanelIdB = nextHistoryPanelIdB;
    }
}
