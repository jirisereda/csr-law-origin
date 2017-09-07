package cz.cas.ilaw.csrlaworigin.mainscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;

public class TopicsAdapter extends ArrayAdapter<RecordTopic> {
    private Context mContext;
    private List<RecordTopic> mRecordTopics = new ArrayList<>();

    public TopicsAdapter(@NonNull Context context) {
        super(context, R.layout.listview_topic_item);

        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.listview_topic_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(getItem(position).getTitle());

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(getItem(position).getDescription());

        return convertView;
    }

    public void setData(List<RecordTopic> recordTopics) {
        mRecordTopics.clear();
        clear();

        if (recordTopics != null) {
            mRecordTopics.addAll(recordTopics);
            addAll(mRecordTopics);
        }

        notifyDataSetChanged();
    }

    public void filter(String textToFilter) {
        clear();

        List<RecordTopic> filteredTopics = new ArrayList<>();

        if (textToFilter.isEmpty()) {
            filteredTopics.addAll(mRecordTopics);
        } else {
            for (RecordTopic recordTopic: mRecordTopics) {
                if (recordTopic.getTitle().toLowerCase(Locale.getDefault()).contains(textToFilter)) {
                    filteredTopics.add(recordTopic);
                }
            }
        }
        addAll(filteredTopics);

        notifyDataSetChanged();
    }
}