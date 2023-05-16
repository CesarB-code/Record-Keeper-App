package com.cesarbarrera.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cesarbarrera.recordkeeper.databinding.FragmentCyclingBinding
import com.cesarbarrera.recordkeeper.editRecord.EditRecordActivity

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }
    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)
        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride record", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride date", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb record", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb date", null)
        binding.textViewBestAverageSpeedValue.text = cyclingPreferences.getString("Best Average Speed record", null)
        binding.textViewBestAverageSpeedDate.text = cyclingPreferences.getString("Best Average Speed date", null)
    }

    private fun setupClickListeners() {
        binding.containerBiggestClimb.setOnClickListener { launchCyclingScreen("Biggest Climb") }
        binding.containerLongestRide.setOnClickListener { launchCyclingScreen("Longest Ride") }
        binding.containerBestAverageSpeed.setOnClickListener { launchCyclingScreen("Best Average Speed") }
    }

    private fun launchCyclingScreen(travel: String) {
        val screenData = EditRecordActivity.ScreenData("Travel","cycling")
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("Travel", travel)
        intent.putExtra("screen_data", screenData)
        startActivity(intent)
    }


}