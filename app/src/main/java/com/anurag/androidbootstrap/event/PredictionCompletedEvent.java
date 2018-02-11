package com.anurag.androidbootstrap.event;

/**
 * Created by anurag on 21/04/17.
 */
public class PredictionCompletedEvent {
    private String mPredectionResult;

    public PredictionCompletedEvent(String predectionResult) {
        mPredectionResult = predectionResult;
    }

    public String getPredictionResult() {
        return mPredectionResult;
    }
}
