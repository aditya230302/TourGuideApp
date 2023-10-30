package com.example.tourguideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.tourguideapp.Model
import com.example.tourguideapp.MyAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Model>()


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){

                R.id.profile ->{
                    setContent("Profile")
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Private List Section accessed", Toast.LENGTH_LONG).show()

                    true
                }
                else -> false
            }
        }




        list.add(Model("Manali","Manali is a high-altitude Himalayan resort town in India’s northern Himachal Pradesh state. It has a reputation as a backpacking center and honeymoon destination. Set on the Beas River, it’s a gateway for skiing in the Solang Valley and trekking in Parvati Valley. It's also a jumping-off point for paragliding, rafting and mountaineering in the Pir Panjal mountains, home to 4,000m-high Rohtang Pass.", R.drawable.manali))
        list.add(Model("Red Fort","The Red Fort or Lal Qila is a historic fort in the Old Delhi neighbourhood of Delhi, India, that historically served as the main residence of the Mughal emperors. Emperor Shah Jahan commissioned construction of the Red Fort on 12 May 1638, when he decided to shift his capital from Agra to Delhi.", R.drawable.redfort))
        list.add(Model("Amritsar","Amritsar is a city in the northwestern Indian state of Punjab, 28 kilometers from the border with Pakistan. At the center of its walled old town, the gilded Golden Temple (Harmandir Sahib) is the holiest gurdwara (religious complex) of the Sikh religion. It’s at the end of a causeway, surrounded by the sacred Amrit Sarovar tank (lake), where pilgrims bathe.", R.drawable.amritser))
        list.add(Model("Rishikesh","Rishikesh is a city in India’s northern state of Uttarakhand, in the Himalayan foothills beside the Ganges River. The river is considered holy, and the city is renowned as a center for studying yoga and meditation. Temples and ashrams (centers for spiritual studies) line the eastern bank around Swarg Ashram, a traffic-free, alcohol-free and vegetarian enclave upstream from Rishikesh town.", R.drawable.rishikesh))
        list.add(Model("Jaipur","Jaipur is the capital of India’s Rajasthan state. It evokes the royal family that once ruled the region and that, in 1727, founded what is now called the Old City, or “Pink City” for its trademark building color. At the center of its stately street grid (notable in India) stands the opulent, colonnaded City Palace complex. With gardens, courtyards and museums, part of it is still a royal residence.", R.drawable.jaipur))
        list.add(Model("Shimla","Shimla is the capital of the northern Indian state of Himachal Pradesh, in the Himalayan foothills. Once the summer capital of British India, it remains the terminus of the narrow-gauge Kalka-Shimla Railway, completed in 1903. It’s also known for the handicraft shops that line The Mall, a pedestrian avenue, as well as the Lakkar Bazaar, a market specializing in wooden toys and crafts.", R.drawable.shimla))
        list.add(Model("Amer Fort","Amer Fort or Amber Fort is a fort located in Amer, Rajasthan, India. Amer is a town with an area of 4 square kilometres located 11 kilometres from Jaipur, the capital of Rajasthan. Located high on a hill, it is the principal tourist attraction in Jaipur. Amer Fort is known for its artistic style elements", R.drawable.amerfort))




        listView.adapter = MyAdapter(this,R.layout.adapter, list)


        listView.setOnItemClickListener{ parent: AdapterView<*>, view: View, position:Int, id:Long ->
            if(position==0)
                Toast.makeText(applicationContext, "Facebook", Toast.LENGTH_SHORT).show()
            if(position==1)
                Toast.makeText(applicationContext, "Skype", Toast.LENGTH_SHORT).show()
            if(position==2)
                Toast.makeText(applicationContext, "Twitter", Toast.LENGTH_SHORT).show()
            if(position==3)
                Toast.makeText(applicationContext, "Whatsapp", Toast.LENGTH_SHORT).show()
            if(position==4)
                Toast.makeText(applicationContext, "Youtube", Toast.LENGTH_SHORT).show()
            if(position==5)
                Toast.makeText(applicationContext, "Instagram", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setContent(content: String){
        title = content
    }
}