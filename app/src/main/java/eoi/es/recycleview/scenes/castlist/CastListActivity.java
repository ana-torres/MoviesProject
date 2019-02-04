package eoi.es.recycleview.scenes.castlist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eoi.es.recycleview.R;
import eoi.es.recycleview.data.entity.Cast;

public class CastListActivity extends AppCompatActivity implements CastFragment.OnCastFragmentInteraction {

    @BindView(R.id.vpCast)
    ViewPager vpCast;
    ArrayList<Cast> castList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_list);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            castList = (ArrayList<Cast>) bundle.get("castList");
        }

        CastListAdapter adapter = new CastListAdapter(getSupportFragmentManager(), castList);
        vpCast.setAdapter(adapter);
    }

    @Override
    public void onClickButton() {
        finish();
    }
}
