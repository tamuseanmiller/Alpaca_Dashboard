package com.seanmiller.alpacadashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

/**
 * A simple [Fragment] subclass.
 * Use the [InformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_information, container, false)

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
        val profileImage: ShapeableImageView = mView.findViewById(R.id.profile_image)
        val bannerImage: ShapeableImageView = mView.findViewById(R.id.banner)
        Glide.with(requireActivity()).load(R.drawable.banner).centerCrop().into(bannerImage)
        Glide.with(requireActivity()).load(R.drawable.profile_image).override(450, 450).fitCenter().centerCrop().apply(RequestOptions.circleCropTransform()).into(profileImage)
        return mView
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InformationFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): InformationFragment {
            val fragment = InformationFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}