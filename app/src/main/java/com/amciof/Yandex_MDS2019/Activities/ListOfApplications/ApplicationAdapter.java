package com.amciof.Yandex_MDS2019.Activities.ListOfApplications;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;



class ApplicationAdapter
        extends RecyclerView.Adapter<ApplicationHolder> {

    private Context context;
    private List<ApplicationInfo> applicationList;
    private int layout;

    ApplicationAdapter (Context context, List<ApplicationInfo> applicationList, int layout) {
        this.context = context;
        this.applicationList = applicationList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ApplicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ApplicationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationHolder holder, int position) {
        final ApplicationInfo applicationInfo = applicationList.get(position);
        holder.setData(
                applicationInfo.getIcon(),
                applicationInfo.getLabel()
        );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicationInfo.setNumberLaunch(applicationInfo.getNumberLaunch() + 1);
                context.startActivity(applicationInfo.getLaunchIntent());
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnCreateContextMenuListener(new ContextMenuListener(context, applicationInfo));
    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }

}