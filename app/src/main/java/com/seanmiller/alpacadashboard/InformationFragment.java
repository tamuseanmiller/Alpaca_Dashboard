package com.seanmiller.alpacadashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_information, container, false);

//        View aboutPage = new AboutPage(requireContext())
//                .isRTL(false)
//                .setDescription(" ")
//                .setImage(0)
//                .enableDarkMode(false)
//                .addEmail("beasto000lol@gmail.com")
//                .addWebsite("https://sean.millerfamily.tech", "Visit My Website")
//                .addTwitter("beastosean", "Follow me on Twitter")
//                .addPlayStore("com.seanmiller.alpacadashboard")
//                .addGitHub("tamuseanmiller", "Check Out My Other Open Source Projects")
//                .addInstagram("be_Defined")
//                .create();
//
//        return aboutPage;

        ShapeableImageView profileImage = mView.findViewById(R.id.profile_image);
        ShapeableImageView bannerImage = mView.findViewById(R.id.banner);
        Glide.with(requireActivity()).load(R.drawable.banner).centerCrop().into(bannerImage);
        Glide.with(requireActivity()).load(R.drawable.profile_image).override(450, 450).fitCenter().centerCrop().apply(RequestOptions.circleCropTransform()).into(profileImage);

        return mView;
    }


}