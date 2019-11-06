package com.example.cityweatherapp.main_activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cityweatherapp.R
import com.example.cityweatherapp.model.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var editTextCityName: AutoCompleteTextView
    private lateinit var buttonSearch: Button

    private lateinit var address: TextView
    private lateinit var updated_at: TextView
    private lateinit var status: TextView
    private lateinit var temp: TextView
    private lateinit var temp_min: TextView
    private lateinit var temp_max: TextView

    private lateinit var city: String
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initCities()

        presenter = MainPresenterImpl(this, GetWeatherImpl())

        buttonSearch.setOnClickListener(View.OnClickListener {
            city = editTextCityName.text.toString()
            city.let { city ->
                presenter.setCity(city)
                presenter.requestDataFromServer()
            }
        })
    }

    private fun initCities() {
        var cities = arrayOf("Almaty", "Astana", "Shymkent", "Karagandy", "Semey", "Taraz",
            "Atyrau", "Turkestan", "Pavlodar", "Oskemen", "Aktau", "Kostanay",
            "Oral", "Kokshetau", "Baikonur", "Petropavl", "Taldykorgan", "Temirtau", "Balkhash")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this,
            R.layout.list_item, cities)

        editTextCityName.setAdapter(adapter)
    }
    private fun initViews() {
        editTextCityName = findViewById(R.id.editTextCityName)
        buttonSearch = findViewById(R.id.buttonSearch)

        address = findViewById(R.id.address)
        updated_at = findViewById(R.id.updated_at)
        status = findViewById(R.id.status)
        temp = findViewById(R.id.temp)
        temp_min = findViewById(R.id.temp_min)
        temp_max = findViewById(R.id.temp_max)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setResult(weatherResponse: WeatherResponse) {
        displayData(weatherResponse)
    }

    private fun displayData(weatherResponse: WeatherResponse) {
        address.text = weatherResponse.name +", " + weatherResponse.sys.country
        updated_at.text =
            "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH)
                .format(Date(weatherResponse.dt*1000))
        status.text =
            weatherResponse.weather[0].description.capitalize()
        temp.text =
            weatherResponse.main.temp.toString() + "°C"
        temp_min.text =
            "Min Temp: " + weatherResponse.main.temp_min.toString() +"°C"
        temp_max.text =
            "Max Temp: " + weatherResponse.main.temp_max.toString() +"°C"
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(this,
            "Something went wrong...Error message: " + throwable.message,
            Toast.LENGTH_LONG).show()
    }
}
