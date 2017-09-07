package cz.cas.ilaw.csrlaworigin.topiceditor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;

public class RelatedItemsAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mItems = new ArrayList<>();


    public RelatedItemsAdapter(@NonNull Context context) {
        super(context, R.layout.listview_related_topic);

        mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.listview_related_topic, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(getItem(position));

        return convertView;
    }

    public void setData(List<String> relatedItems) {
        mItems.clear();
        clear();

        if (relatedItems != null) {
            mItems.addAll(relatedItems);
            addAll(mItems);
        }

        notifyDataSetChanged();
    }
}