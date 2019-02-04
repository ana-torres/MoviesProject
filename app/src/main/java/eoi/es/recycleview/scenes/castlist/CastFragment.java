package eoi.es.recycleview.scenes.castlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eoi.es.recycleview.R;
import eoi.es.recycleview.data.api.RestClient;
import eoi.es.recycleview.data.entity.Cast;

/**
 * Fragment para representar la foto del actor
 */
public class CastFragment extends Fragment {

    protected static final String ARG_CAST = "cast";
    protected Cast cast;
    @BindView(R.id.ivCast)
    ImageView ivCast;
    private OnCastFragmentInteraction mListener;

    public CastFragment() {
        // Required empty public constructor
    }


    /**
     * Método factoría para crear la instancia del fragment recibiendo parámetros
     *
     * @param cast actor del cual vamos a mostrar su foto
     * @return la instancia del fragment
     */
    // TODO: Rename and change types and number of parameters
    public static CastFragment newInstance(Cast cast) {
        CastFragment fragment = new CastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAST, cast);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cast = (Cast) getArguments().getSerializable(ARG_CAST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cast, container, false);
        ButterKnife.bind(this, view);
        String url = RestClient.imageBaseUrl + cast.getProfileImageUrl();
        Glide.with(getActivity()).load(url).into(ivCast);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    @OnClick(R.id.ivCast)
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onClickButton();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCastFragmentInteraction) {
            mListener = (OnCastFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnCastFragmentInteraction {
        // TODO: Update argument type and name
        void onClickButton();
    }
}
