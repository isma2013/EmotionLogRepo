package bg.android.isma.emotionlog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EmotionsActivity extends Activity  {
    ListView listEmotions;
    String[] emotions;
    String[] descriptions;
    int[] images = {R.drawable.happiness, R.drawable.sadness, R.drawable.anger, R.drawable.thankful, R.drawable.disgust, R.drawable.love, R.drawable.confusion, R.drawable.fear, R.drawable.others};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);
        Resources res = getResources();/*Accessing the resources of the application*/
        emotions = res.getStringArray(R.array.EmotionsTitles);
        descriptions = res.getStringArray(R.array.EmotionsDescription);
        listEmotions = (ListView) findViewById(R.id.lvEmotions);
        EmoAdapter adapter = new EmoAdapter(this, emotions, images, descriptions);
        listEmotions.setAdapter(adapter);

        listEmotions.setOnItemClickListener(new AdapterView.OnItemClickListener() {/*When an item in the listview is clicked, the function onItemClick will perform the specified action.*/
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String Selecteditem= emotions[+position];
               Toast.makeText(getApplicationContext(),"You have selected "
                       + Selecteditem, Toast.LENGTH_SHORT).show();
               finish();
               startActivity(new Intent(EmotionsActivity.this, RegisterActivity.class));
           }
       });
    }
}

class EmoAdapter extends ArrayAdapter<String> {

    Context context;
    String[] emotionsArray;
    String[] descriptionArray;
    int[] images;

    EmoAdapter(Context c, String[] emotions, int imgs[], String[] EmoDescription) {

        super(c, R.layout.single_row, R.id.tvEmotions, emotions);
        this.context = c;
        this.images = imgs;
        this.emotionsArray = emotions;
        this.descriptionArray = EmoDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyViewHolder) row.getTag();
        }
        holder.emoticon.setImageResource(images[position]);
        holder.emotionName.setText(emotionsArray[position]);
        holder.emotionDescription.setText(descriptionArray[position]);

        return row;
    }

    class MyViewHolder {
        ImageView emoticon;
        TextView emotionName;
        TextView emotionDescription;

        MyViewHolder(View v) {
            emoticon = (ImageView) v.findViewById(R.id.ivEmoticon);
            emotionName = (TextView) v.findViewById(R.id.tvEmotions);
            emotionDescription = (TextView) v.findViewById(R.id.tvDescription);
        }
    }
}