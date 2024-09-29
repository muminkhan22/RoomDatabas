package com.example.roomdatabass

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomdatabass.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    var showTime: String? = null
    var showDate: String? = null



    var noteId = 0
    lateinit var note: Note



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =FragmentAddBinding.inflate(layoutInflater,container,false)
        noteId = requireArguments().getInt("note")

        if (noteId != 0){

            note = NoteDataBase.getDb(requireContext()).getNoteDao().getAllDataWithID(listOf<Int>(noteId))[0]

            binding.apply {

                textTV.setText(note.title)
                timeBtn.setText(note.time)
                dateBtn.setText(note.date)

            }

        }

        binding.dateBtn.setOnClickListener {
            pickAData()
        }
        binding.timeBtn.setOnClickListener {
            pickATime()
        }
        binding.submitBtn.setOnClickListener {
            val titleStr = binding.textTV.text.toString()
            val timeStr = showTime ?: "00:00"
            val dateStr = showDate ?: "00/00/0000"

            val note = Note(title = titleStr, time = timeStr, date = dateStr)


            if (noteId==0){
                NoteDataBase.getDb(requireContext()).getNoteDao().insertData(note)
            }else{
                note.id = noteId
                NoteDataBase.getDb(requireContext()).getNoteDao().updateData(note)
            }


            findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        }



        return binding.root

    }

    private fun pickATime() {
        val calendar= Calendar.getInstance()

        val minute = calendar.get(java.util.Calendar.MINUTE)
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val timePicker = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

              val  showTime = "$hourOfDay : $minute"
                binding.timeBtn.text = showTime


            }, hour, minute, false

        )
        timePicker.show()




    }

    private fun pickAData() {

        val calendar=Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)


        val showDatePicker = DatePickerDialog(requireActivity(),
            DatePickerDialog.OnDateSetListener{_, year, month, dayOfMonth ->


          val  showDate="$dayOfMonth,${month + 1},$year"
            binding.dateBtn.text = showDate


        },year,month,day

        )


        showDatePicker.show()

    }


}