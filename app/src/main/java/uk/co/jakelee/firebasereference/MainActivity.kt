package uk.co.jakelee.firebasereference

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.jakelee.firebasereference.firebase.Analytics
import uk.co.jakelee.firebasereference.firebase.Base


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var firebaseClasses: MutableList<Base> = mutableListOf()
    private var firebaseNames: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createServiceLists()
        setupSpinner()
        setupOnClicks()
    }

    private fun createServiceLists() {
        firebaseClasses.add(Analytics(this))
        firebaseClasses.forEach {
            firebaseNames.add(getString(it.name))
        }
    }

    private fun setupOnClicks() {
        button_documentation.setOnClickListener {
            openUrl(firebaseClasses[service_picker.selectedItemPosition].documentation)
        }
        button_source.setOnClickListener {
            openUrl(firebaseClasses[service_picker.selectedItemPosition].source)
        }
        button_test.setOnClickListener {
            firebaseClasses[service_picker.selectedItemPosition].testService()
        }
    }

    private fun setupSpinner() {
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, firebaseNames)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        service_picker.adapter = dataAdapter
        service_picker.onItemSelectedListener = this
    }

    private fun openUrl(id: Int) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(id)))
        startActivity(browserIntent)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        service_desc.text = getString(firebaseClasses[position].description)
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {}
}
