package com.cesarbarrera.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.cesarbarrera.recordkeeper.cycling.CyclingFragment
import com.cesarbarrera.recordkeeper.databinding.ActivityMainBinding
import com.cesarbarrera.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private var currentFragment: String = "running"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.rest_cycling -> {
            resetCyclingRecords()
            Toast.makeText(this, "Cycling records Reset", Toast.LENGTH_SHORT).show()
            changeFragment()
            true
        }

        R.id.rest_running -> {
            resetRunningRecords()
            Toast.makeText(this, "Running Records Rest", Toast.LENGTH_SHORT).show()
            changeFragment()
            true
        }

        R.id.rest_all -> {
            resetCyclingRecords()
            resetRunningRecords()
            Toast.makeText(this, "All Records Reset ", Toast.LENGTH_SHORT).show()
            changeFragment()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    private fun changeFragment() {
        if(currentFragment == "running")
        {
            supportFragmentManager.commit { replace(R.id.frame_content, RunningFragment()) }
        }
        else{
            supportFragmentManager.commit { replace(R.id.frame_content, CyclingFragment()) }
        }
    }

    private fun resetCyclingRecords() {
        val cyclingData = getSharedPreferences("cycling", Context.MODE_PRIVATE)
        val editData = cyclingData.edit()
        editData.putString("Longest Ride record", null)
        editData.putString("Longest Ride date", null)
        editData.putString("Biggest Climb record", null)
        editData.putString("Biggest Climb date", null)
        editData.putString("Best Average Speed record", null)
        editData.putString("Best Average Speed date", null)
        editData.apply()
    }

    private fun resetRunningRecords() {
        val runningData = getSharedPreferences("running", Context.MODE_PRIVATE)
        val editData = runningData.edit()
        editData.putString("Half Marathon record", null)
        editData.putString("Half Marathon date", null)
        editData.putString("10km record", null)
        editData.putString("10km date", null)
        editData.putString("Marathon record", null)
        editData.putString("Marathon date", null)
        editData.putString("5km record", null)
        editData.putString("5km date", null)
        editData.apply()
    }


    private fun onRunningClicked(): Boolean {
        currentFragment = "running"
        supportFragmentManager.commit { replace(R.id.frame_content, RunningFragment()) }
        return true
    }

    private fun onCyclingClicked(): Boolean {
        currentFragment = "cycling"
        supportFragmentManager.commit { replace(R.id.frame_content, CyclingFragment()) }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_cycling -> onCyclingClicked()
        R.id.nav_running -> onRunningClicked()
        else -> false
    }


}