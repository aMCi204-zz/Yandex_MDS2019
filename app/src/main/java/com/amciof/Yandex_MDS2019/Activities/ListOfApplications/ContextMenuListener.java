package com.amciof.Yandex_MDS2019.Activities.ListOfApplications;


import android.content.Context;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.amciof.Yandex_MDS2019.R;



public class ContextMenuListener
        implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    private Context context;
    private ApplicationInfo applicationInfo;

    ContextMenuListener(Context context, ApplicationInfo applicationInfo) {
        this.context = context;
        this.applicationInfo = applicationInfo;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle(applicationInfo.getLabel());

        MenuItem delete = menu.add(Menu.NONE, R.id.action_delete, Menu.NONE, R.string.action_delete);
        delete.setOnMenuItemClickListener(this);

        MenuItem info = menu.add(Menu.NONE, R.id.action_info, Menu.NONE, R.string.action_info);
        info.setOnMenuItemClickListener(this);

        long launches = applicationInfo.getNumberLaunch();
        String action_runs = context.getResources().getString(R.string.action_runs);
        MenuItem nLaunch = menu.add(Menu.NONE, R.id.menu_launches, Menu.NONE, action_runs + Long.toString(launches));
        nLaunch.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_delete:
                context.startActivity(applicationInfo.getDeleteIntent());
                break;
            case R.id.action_info:
                context.startActivity(applicationInfo.getSettingsIntent());
                break;
            default:
                break;
        }
        return true;
    }
}
