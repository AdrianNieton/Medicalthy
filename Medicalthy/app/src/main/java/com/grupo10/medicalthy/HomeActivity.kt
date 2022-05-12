package com.grupo10.medicalthy

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
//import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_hor.*
import java.util.*

//Enum con los distintos proveedores de autenticación
enum class ProviderType {
    BASIC, //Email + password
    GOOGLE
}
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_hor)
        setup()
    }

    private fun setup(){

        title = getString(R.string.homeTitle)

        btnAddMedicine.setOnClickListener {
            goToAddMedicine()
        }

        btnShowShots.setOnClickListener {
            goToShowSots()
        }

        btnPharmacy.setOnClickListener {
            goToPharmacyMap()
        }

        btnMedicationHistory.setOnClickListener {
            goToMedicationHistory()
        }

        //Recuperar extras del intent de SignIn/SignUp Activity
        val bundle = intent.extras
        val email = bundle?.get(getString(R.string.intentEmail))
        val provider = bundle?.get(getString(R.string.provider))

        //Guardar el estado de la app
        val preferences = getSharedPreferences(getString(R.string.preferencesFile), Context.MODE_PRIVATE).edit()
        preferences.putString(getString(R.string.intentEmail), email.toString())
        preferences.putString(getString(R.string.provider), provider.toString())
        preferences.apply()
    }

    private fun goToAddMedicine(){
        val addMedicineIntent = Intent(this, AddMedicineActivity::class.java)
        startActivity(addMedicineIntent)
    }

    private fun goToShowSots(){
        val showShotsInIntent = Intent(this, ShowShotsActivity::class.java)
        startActivity(showShotsInIntent)
    }

    private fun goToPharmacyMap(){
        val pharmacyMapIntent = Intent(this, PharmacyMapActivity::class.java)
        startActivity(pharmacyMapIntent)
    }

    private fun goToMedicationHistory(){
        val medicationHistoryIntent = Intent(this, PlanMedicineActivity::class.java)
        startActivity(medicationHistoryIntent)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.onBackDialogTitle))
            setMessage(getString(R.string.onBackDialogMessage))

            setPositiveButton(getString(R.string.yesMessage)) { _, _ ->
                // Si pulsan si se cierra la app, LOCURA
                finishAffinity()
            }

            setNegativeButton(getString(R.string.noMessage)){_, _ ->
            }

            setCancelable(true)
        }.create().show()
    }
}