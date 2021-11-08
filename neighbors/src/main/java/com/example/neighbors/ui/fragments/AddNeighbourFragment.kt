package com.example.neighbors.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor
import com.example.neighbors.repositories.NeighborRepository
import com.google.android.material.textfield.TextInputEditText

class AddNeighbourFragment : Fragment() {

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    @SuppressLint("Add")
    lateinit var button_save: Button
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)
        (activity as? NavigationListener)?.let {
            onViewCreated(view, Bundle())
            it.updateTitle(R.string.add)
        }
        button_save = view.findViewById(R.id.btn_enregistrer)
        button_save.isEnabled=false
        var name = view.findViewById<TextInputEditText>(R.id.Name)
        var avatarUrl = view.findViewById<TextInputEditText>(R.id.url)
        var address = view.findViewById<TextInputEditText>(R.id.adress)
        var phoneNumber = view.findViewById<TextInputEditText>(R.id.phone)
        var aboutMe = view.findViewById<TextInputEditText>(R.id.about)
        var webSite = view.findViewById<TextInputEditText>(R.id.web)
        name.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())


            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        avatarUrl.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())

            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        address.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())

            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        phoneNumber.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())

            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        aboutMe.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        webSite.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_save.isEnabled = validateweb(webSite.text.toString()) and
                        validateabout(aboutMe.text.toString()) and
                        validateadress(address.text.toString()) and
                        validateurl(avatarUrl.text.toString()) and
                        validatename(name.text.toString()) and
                        validatephone(phoneNumber.text.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        button_save.setOnClickListener{
            val nei = Neighbor(
                id = 0,
                name = name.text.toString(),
                avatarUrl = avatarUrl.text.toString(),
                address = address.text.toString(),
                phoneNumber = phoneNumber.text.toString(),
                aboutMe = aboutMe.text.toString(),
                favorite = false,
                webSite = webSite.text.toString()
            )
            NeighborRepository.getInstance().createNeighbour(nei)
            (activity as? NavigationListener)?.let {
                onViewCreated(view, Bundle())
                it.showFragment(ListNeighborsFragment())
            }
        }
        return view
    }
    fun validateabout(about:String):Boolean{
        val a:Boolean= about.isNotEmpty()
        val b:Boolean=about.length<=30
        //println("about"+(a and b))
        return a and b
    }
    fun validateweb(webSite:String):Boolean{
        val a:Boolean= URLUtil.isValidUrl(webSite)
        //println("avatar"+a)
        return a
    }
    fun validatephone(phone:String):Boolean{
        val a:Boolean= phone.isNotEmpty()
        val b:Boolean=phone.startsWith("06") or phone.startsWith("07")
        val c:Boolean=(phone.length==10)
        //println("phone"+(a and b and c))
        return a and b and c
    }
    fun validateadress(address:String):Boolean{
        val a:Boolean=address.isNotEmpty()
        //println("adress"+ a)
        return a
    }
    fun validateurl(url:String):Boolean{
        val a:Boolean= URLUtil.isValidUrl(url)
        //println("url"+a)
        return a
    }
    fun validatename(name:String):Boolean {
        val a: Boolean = name.isNotEmpty()
        //println("name" + a)
        return a
    }

}
