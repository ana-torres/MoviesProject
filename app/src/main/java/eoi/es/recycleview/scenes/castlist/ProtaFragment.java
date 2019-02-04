package eoi.es.recycleview.scenes.castlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import eoi.es.recycleview.R;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.entity.Cast;


public class ProtaFragment extends CastFragment {

    @BindView(R.id.tvName)
    TextView tvName;

    public ProtaFragment() {
        // Required empty public constructor
    }

    public static CastFragment newInstance(Cast cast) {
        ProtaFragment fragment = new ProtaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAST, cast);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prota, container, false);
        ButterKnife.bind(this, view);

        String url = RestClient.imageBaseUrl + cast.getProfileImageUrl();
        Glide.with(getActivity()).load(url).into(ivCast);

        tvName.setText(cast.getName());
        return view;

    }

}
