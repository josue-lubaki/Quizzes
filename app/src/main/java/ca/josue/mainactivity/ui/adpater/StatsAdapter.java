package ca.josue.mainactivity.ui.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import ca.josue.mainactivity.R;
import ca.josue.mainactivity.domain.entity.StatEntity;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {
    private List<StatEntity> stats;
    private final Animation animationFadeIn;

    public StatsAdapter(Context context) {
        this.stats = new ArrayList<>();
        this.animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in_400);
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.stats_card, null);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        StatEntity stat = stats.get(position);
        holder.date.setText(stat.getDate());
        holder.score.setText(MessageFormat.format("{0} / {1}", stat.getScore(), stat.getTotal()));
        holder.tag.setText(stat.getTag());
        holder.cardViewStat.setAnimation(animationFadeIn);
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public void setStats(List<StatEntity> stats) {
        this.stats = stats;
    }

    public static class StatsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tag;
        private final TextView score;
        private final TextView date;
        private final CardView cardViewStat;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tv_tag);
            score = itemView.findViewById(R.id.tv_score);
            date = itemView.findViewById(R.id.tv_date);
            cardViewStat = itemView.findViewById(R.id.card_view_stat);
        }
    }
}
