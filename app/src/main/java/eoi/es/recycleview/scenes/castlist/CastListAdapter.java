package eoi.es.recycleview.scenes.castlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import eoi.es.recycleview.data.entity.Cast;

public class CastListAdapter extends FragmentStatePagerAdapter {

    List<Cast> castList;

    public CastListAdapter(FragmentManager fm, List<Cast> castList) {
        super(fm);
        this.castList = castList;
    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return ProtaFragment.newInstance(castList.get(i));

        } else {

            return CastFragment.newInstance(castList.get(i));
        }
    }

    @Override
    public int getCount() {
        return castList.size();
    }
}
