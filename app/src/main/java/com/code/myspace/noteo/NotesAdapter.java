package com.code.myspace.noteo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by myspace on 3/31/2018.
 */

class NotesAdapter extends ArrayAdapter<NotesData>{

    Context context;
    int resourceId;
    List<NotesData> notesList;
    DataHolder holder;

    public NotesAdapter(@NonNull Context context, int resource, @NonNull List<NotesData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.notesList = objects;
    }

    static class DataHolder{
        TextView notesTxt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.notes_card,null);
            holder = new DataHolder();
            holder.notesTxt = convertView.findViewById(R.id.noteTxt);
            convertView.setTag(holder);
        }else{
            holder = (DataHolder) convertView.getTag();
        }

        NotesData data = notesList.get(position);
        holder.notesTxt.setText(data.getNotes());
        return convertView;

    }
}
