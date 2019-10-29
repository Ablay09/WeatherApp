package com.example.cityweatherapp

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var editTextCityName: AutoCompleteTextView
    lateinit var city: String
    lateinit var buttonSearch: Button

    val API: String = "30ce7b68e1171932d2042847a012c129"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextCityName = findViewById(R.id.editTextCityName)
        buttonSearch = findViewById(R.id.buttonSearch)

        var cities = arrayOf("Almaty", "Astana", "Shymkent", "Karagandy", "Semey", "Taraz",
            "Atyrau", "Turkestan", "Pavlodar", "Oskemen", "Aktau", "Kostanay",
            "Oral", "Kokshetau", "Baikonur", "Petropavl", "Taldykorgan", "Temirtau", "Balkhash")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this,
            R.layout.list_item, cities)

        editTextCityName.setAdapter(adapter)

        buttonSearch.setOnClickListener(View.OnClickListener {
            city = editTextCityName.text.toString()
            wheatherTask().execute()
        })
    }

    inner class wheatherTask(): AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                //val wind = jsonObj.getJSONObject("wind")


                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                /*
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                 */
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

        }
    }



}
