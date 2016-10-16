package ua.com.doko.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class AppActivity extends Activity {
    private RecycleAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        findViewById(R.id.button_list).setOnClickListener(onClickListener);
        findViewById(R.id.button_grid).setOnClickListener(onClickListener);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> packages = getApps(pm);
        adapter = new RecycleAdapter(this, packages, pm);
        adapter.setIdLayout(R.layout.item_grid);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getGrid());
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        View actView;
        @Override
        public void onClick(View view) {
            view.setEnabled(false);
            if (actView!=null)
                actView.setEnabled(true);
            actView = view;
            switch (view.getId()) {
                case R.id.button_list:
                    adapter.setIdLayout(R.layout.item_list);
                    recyclerView.setLayoutManager(getList());
                    break;
                case R.id.button_grid:
                    adapter.setIdLayout(R.layout.item_grid);
                    recyclerView.setLayoutManager(getGrid());
                    break;
            }
        }
    };

    private GridLayoutManager getGrid() {
        return new GridLayoutManager(this, 4);
    }

    private LinearLayoutManager getList() {
        return new LinearLayoutManager(this);
    }

    private List<ResolveInfo> getApps(PackageManager pm) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return pm.queryIntentActivities(intent, 0);
    }
}




