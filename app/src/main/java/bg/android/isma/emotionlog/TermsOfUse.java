package bg.android.isma.emotionlog;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;


public class TermsOfUse extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);


        WebView view = (WebView) findViewById(R.id.wbTerms);
        String text, text1;
        text = "<html><body><p align=\"justify\">";
        text += "<p align=\"center\">To the emotion-logger-to-be<br><br></p>";
        text += "</p></body></html>";
        text1 = "<html><body><p align=\"justify\">";
        text1 += "By downloading this App to your mobile device," +
                "you are able to log your emotional experience " +
                "and their expressions, in time and space (if you wish to do so)." +
                " This implies the connection of this App-software " +
                "to your phone-inherent GPS and the base-map it features." +
                " Additionally, the pins that will show on said map" +
                "will be time-capped, so that your emotion-logs" +
                "add up to a spatial and temporal record of your emotional experiences.<br>" +
                " To further your tracking thereof, you can express" +
                "what you felt by taking photos that can be re-accessed via the map-pins.<br>" +
                " You are herewith agreeing to have the above-mentioned data" +
                "(logged emotion, position of your phone," +
                "and time of logging-activity, plus optional photo)" +
                "stored on your device for later retrieval.<br>" +
                " This App has been developed and delivered to you as part of a research-project," +
                "for which the maps will be consulted.<br>" +
                " Your download validates your consent to act as data-producer in the project," +
                "which has been explained to you by the principal researcher" +
                "and you declare that you are aware of being contacted again for data-analyses purposes.<br>" +
                " Any cooperation beyond the mapping is subject to negotiation, i.e." +
                "your use of the App is not automatically bound to further project-related activity from you," +
                "other than sharing the mapped results.<br>" +
                " Thank you, again, for partaking & enjoy the logging" +
                "(or experience any other emotion, if joy does not quite suit you)!";
        text1 += "</p></body></html>";
        view.loadData(text + text1, "text/html", "utf-8");


    }
}
