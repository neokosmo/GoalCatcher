package kosmo.neo.goalcatcher;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import java.util.List;


public class GoalListAdapter extends RecyclerView.Adapter<GoalListAdapter.MyViewHolder> {

    private List<Goal> goalList;

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View view) {
            super(view);
        }

    }

    GoalListAdapter(List<Goal> goalList){
        this.goalList = goalList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goal_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Goal goal = goalList.get(position);
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }
}
