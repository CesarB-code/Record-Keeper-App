package com.cesarbarrera.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.cesarbarrera.recordkeeper.databinding.FragmentRunningBinding
import com.cesarbarrera.recordkeeper.editRecord.EditRecordActivity

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
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
        val runningPreferences =
            requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.textView5kmValue.text = runningPreferences.getString("5km record", null)
        binding.textView5kmDate.text = runningPreferences.getString("5km date", null)
        binding.textView10kmValue.text = runningPreferences.getString("10km record", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km date", null)
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("Half Marathon date", null)
        binding.textViewMarathonValue.text = runningPreferences.getString("Marathon record", null)
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon date", null)

    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchRunningScreen("5km") }
        binding.container10km.setOnClickListener { launchRunningScreen("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRunningScreen("Marathon") }
    }

    private fun launchRunningScreen(distance: String) {
        val screenData = EditRecordActivity.ScreenData("Distance","running")
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        intent.putExtra("screen_data", screenData)
        startActivity(intent)
    }

}