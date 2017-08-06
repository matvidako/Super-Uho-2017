package hr.matvidako.lineupapp.festivalinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.matvidako.lineupapp.GlobalStorage;
import hr.matvidako.lineupapp.R;
import hr.matvidako.lineupapp.genre.GenresActivity;

public class FestivalInfoActivity extends AppCompatActivity {

    @BindView(R.id.budget)
    EditText budget;

    @BindView(R.id.date)
    EditText date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    void onNext() {
        GlobalStorage.budget = Integer.valueOf(budget.getText().toString());
        startActivity(new Intent(this, GenresActivity.class));
    }

}
