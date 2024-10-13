package com.example.roomdatabass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomdatabass.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        val notes : List<Note> = NoteDataBase.getDb(requireContext()).getNoteDao().getAllData()

           var adapter =NoteAdapter()
           adapter.submitList(notes)


        binding.recyclerView.adapter = adapter





        binding.addBton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }








        return binding.root
    }


}