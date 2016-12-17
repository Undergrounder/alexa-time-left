package com.tellospot.alexa.skills.timeleft;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrian Tello on 17.12.2016.
 */
public class TimeLeftRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<>();

    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.6c0f6bb2-e1af-4773-a47d-ec25c41fea69");
    }

    /**
     * When extending this class, use a zero argument constructor and pass the appropriate
     * {@code Speechlet} and {@code Set} of supported {@code ApplicationId}s to this method.
     *
     */
    public TimeLeftRequestStreamHandler() {
        super(new TileLeftSpeechlet(), supportedApplicationIds);
    }
}
