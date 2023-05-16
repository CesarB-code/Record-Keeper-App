package com.cesarbarrera.recordkeeper.editRecord

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.cesarbarrera.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private  val screenData: ScreenData by lazy { intent.getSerializableExtra("screen_data")as ScreenData }
    private val record by lazy { intent.getStringExtra(screenData.record) }
    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferencesName, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupUI()
        displayRecord()
    }

    private fun setupUI() {
        title = "$record Record"
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("$record record")
            remove("$record date")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("$record record", null))
        binding.editTextDate.setText(recordPreferences.getString("$record date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        recordPreferences.edit {
            putString("${this@EditRecordActivity.record} record", record)
            putString("${this@EditRecordActivity.record} date", date)
        }

    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
    ) : Serializable


}