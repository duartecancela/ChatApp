package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ContactListItemBinding
import com.example.chatapp.databinding.FragmentContactsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentContactsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // pode ser defenido no XML (fragment_contacts)
        //val llm = LinearLayoutManager(requireContext())
        val adapter = ContactsAdapter()

        binding.contactList.adapter = adapter
        //binding.contactList.layoutManager = llm

        val contacts = MutableList(2) {
            Contact(it.toLong(), "Contact #$it")
        }

        adapter.data = contacts
    }

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = ContactListItemBinding.bind(view)

        fun bind(contact: Contact) {
            binding.contactName.text = contact.name
        }
    }

    class ContactsAdapter : RecyclerView.Adapter<ContactViewHolder>(){

        var data : MutableList<Contact> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_item, parent, false)
            return ContactViewHolder(v)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = data[position]
            holder.bind(contact)
        }

        override fun getItemCount() = data.size

    }

}