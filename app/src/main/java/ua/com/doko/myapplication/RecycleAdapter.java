package ua.com.doko.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecViewHolder> {

    private PackageManager pm;
    private Context context;
    private int idLayout;
    private List<ResolveInfo> packages;

    public RecycleAdapter(Context context, List<ResolveInfo> packages, PackageManager pm) {
        this.context = context;
        this.packages = packages;
        this.pm = pm;
        setIdLayout(idLayout);
    }

    public void setIdLayout(int idLayout) {
        this.idLayout = idLayout;

    }


    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, idLayout, null);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {

        ResolveInfo data = packages.get(position);
        holder.imageView.setImageDrawable(data.loadIcon(pm));
        holder.textView.setText(data.loadLabel(pm));
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public RecViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
