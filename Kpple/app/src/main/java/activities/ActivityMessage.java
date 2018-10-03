package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.databind.JsonNode;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import co.edu.konranlorenz.kpple.R;

public class ActivityMessage extends AppCompatActivity implements RoomListener {

    private String channelID = "WaleAHsR6U7CZFlx";
    private String roomName = "kpple_chat";
    private EditText txtMessage;
    private Scaledrone scaledrone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        txtMessage = findViewById(R.id.txtMessage);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void sendMessage(View view) {
        String message = txtMessage.getText().toString();
        if (message.length() > 0) {
            txtMessage.getText().clear();
        }
    }

    @Override
    public void onOpen(Room room) {
        System.out.println("Conneted to room");
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    @Override
    public void onMessage(Room room, JsonNode message, Member member) {

    }
}
