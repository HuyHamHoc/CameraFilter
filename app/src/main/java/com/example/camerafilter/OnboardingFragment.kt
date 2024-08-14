package com.example.camerafilter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.camerafilter.adapter.ViewPagerAdapter
import com.example.camerafilter.databinding.FragmentOnBoardingBinding
import com.example.camerafilter.screens.FirstScreen
import com.example.camerafilter.screens.SecondScreen
import com.example.camerafilter.screens.ThirdScreen
import com.example.camerafilter.slide.DepthPageTransformer
import com.example.camerafilter.slide.ZoomOutPageTransformer
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private val depthPage = DepthPageTransformer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = fragmentList.size
        binding.viewPager.setPageTransformer(depthPage)
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}