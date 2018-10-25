package activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import co.edu.konranlorenz.kpple.R;
import entities.Pictures;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView gridView;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myRefPictures;

    private FirebaseAuth usuarioAuth;

    private OnFragmentInteractionListener mListener;

    private String tag = "LOGDBUG";

    public GalleryFragment() {

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = view.findViewById(R.id.grid_pictures_profile);
        database = FirebaseDatabase.getInstance();
        myRefPictures = database.getReference("Pictures");
        List<String> photoProfile = new ArrayList<>();
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/1660_iconTAG_.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/1732_landscapeTAG0_.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/2429_landscapeTAG3_.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/perfil.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/perritu2.jpg");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/profilepics/1535185483207.jpg");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/1871_iconTAG_.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/2548_portraitTAG1_ (1).png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/default_75.png");
        photoProfile.add("gs://ingsoft2-65cc5.appspot.com/lyHDpM21QAc3dM1WZ3kb1ONgfGO2/profile/Imagen13.png");

        String id = "4Fy1OMGn9lOSkSkZctpjAgTp1Mp2";
        /*Pictures pictures = new Pictures(id, photoProfile, photoProfile);
        myRefPictures.child(id).setValue(pictures);
        myRefPictures.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ArrayList<String> lista = (ArrayList<String>) ds.getValue(Pictures.class).getPhotos_profile();
                        adapter = new GalleryCardAdapter(getContext(), lista);
                        gridView.setAdapter(adapter);
                        Log.i(tag, "" + lista.size());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
