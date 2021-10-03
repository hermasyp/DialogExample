package com.catnip.dialogexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.catnip.dialogexample.databinding.ActivityMainBinding
import com.catnip.dialogexample.databinding.LayoutDialogCustomBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnSimpleDialog.setOnClickListener {
            openSimpleAlertDialog()
        }
        binding.btnAdButton.setOnClickListener {
            openButtonAlertDialog()
        }
        binding.btnAdComboBox.setOnClickListener {
            openComboBoxAlertDialog()
        }
        binding.btnAdCustom.setOnClickListener {
            openCustomAlertDialog()
        }
        binding.btnCustomDialog.setOnClickListener {
            openDialogFragment()
        }
    }

    private fun openDialogFragment() {
        CustomDialogFragment("Hi Hermas Disini !"){
            openComboBoxAlertDialog()
        }.show(supportFragmentManager,"custom_dialog")
    }

    private fun openCustomAlertDialog() {
        // in fragment, val dialogBinding = LayoutDialogCustomBinding.inflate(LayoutInflater.from(context))
        val dialogBinding = LayoutDialogCustomBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .apply {
                setView(dialogBinding.root)
            }
            .create()
        dialogBinding.tvTitleDialog.text = "This is text set by Programmatically"
        dialogBinding.btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.btnShowAd.setOnClickListener {
            openComboBoxAlertDialog()
        }
        dialog.show()
    }

    private fun openComboBoxAlertDialog() {
        val selectedItems = ArrayList<Int>()
        AlertDialog.Builder(this)
            .apply {
                setTitle("Pick some Toppings")
                setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(this@MainActivity, selectedItems.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "No Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setMultiChoiceItems(
                    R.array.burger_toppings_array,
                    booleanArrayOf(true, false, false, true, true, false, true)
                ) { dialog, which, isChecked ->
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        // Else, if the item is already in the array, remove it
                        selectedItems.remove(which)
                    }
                }

            }
            .create()
            .show()
    }

    private fun openButtonAlertDialog() {
        AlertDialog.Builder(this)
            .apply {
                setTitle("Hello ! this is Dialog Title")
                setMessage("and this is a Message")
                /*
                setPositiveButton("Yes", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "Yes Button Clicked", Toast.LENGTH_SHORT).show()
                    }
                })
                */
                setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Yes Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "No Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setNeutralButton("Probably") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Probably Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .create()
            .show()
    }

    private fun openSimpleAlertDialog() {
        //in fragment, AlertDialog.Builder(context) // context -> getContext()
        AlertDialog.Builder(this)
            .apply {
                setTitle("Hello ! this is Dialog Title")
                setMessage("and this is a Message")
            }
            .create()
            .show()
    }
}