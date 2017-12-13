package cz.cas.ilaw.csrlaworigin.topicsscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.db.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TicketsAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private List<User> mUsers = new ArrayList<>();
    private TicketListener mTicketListener;

    interface TicketListener {

        void onTicketSelected(User user);

    }

    public TicketsAdapter(@NonNull Context context, TicketListener ticketListener) {
        super(context, R.layout.listview_topic_item);

        mContext = context;
        mTicketListener = ticketListener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.listview_topic_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(getItem(position).name);

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(getItem(position).id);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mTicketListener.onTicketSelected(getItem(position));
            }
        });

        return convertView;
    }

    public void setData(List<User> users) {
        mUsers.clear();
        clear();

        if (users != null) {
            mUsers.addAll(users);
            addAll(mUsers);
        }

        notifyDataSetChanged();
    }

    public void filter(String textToFilter) {
        String textToFilterLowerCase = textToFilter.toLowerCase();

        clear();

        List<User> filteredTopics = new ArrayList<>();

        if (textToFilterLowerCase.isEmpty()) {
            filteredTopics.addAll(mUsers);
        } else {
            for (User user: mUsers) {
                if (user.name.toLowerCase(Locale.getDefault()).contains(textToFilterLowerCase)) {
                    filteredTopics.add(user);
                }
            }
        }
        addAll(filteredTopics);

        notifyDataSetChanged();
    }
}