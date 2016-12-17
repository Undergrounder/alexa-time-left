package com.tellospot.alexa.skills.timeleft.speechlets;

import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Adrian Tello on 17.12.2016.
 */
public class TileLeftSpeechlet implements Speechlet{

    private static final Logger log = LoggerFactory.getLogger(TileLeftSpeechlet .class);

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return createWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        return createNotImplementedResponse();
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }

    private SpeechletResponse createWelcomeResponse(){
        final String speechText = "Welcome to the Time Left Skill, you can ask for example „“how many days are left to Christmas?“.";

        // Create the plain text output.
        final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        final Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }

    private SpeechletResponse createNotImplementedResponse(){
        final String speechText = "This feature is not yet implemented.";//TODO remove before going to PROD!

        // Create the plain text output.
        final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);
    }
}
