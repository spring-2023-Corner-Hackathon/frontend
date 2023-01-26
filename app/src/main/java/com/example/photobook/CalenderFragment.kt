package com.example.photobook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photobook.databinding.FragmentCalenderBinding
import com.example.photobook.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentCalenderBinding
    lateinit var bookAdapter : BookAdatper
    lateinit var recyclerView: RecyclerView
    val datas = ArrayList<BookData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_calender, container, false)
        binding = FragmentCalenderBinding.inflate(inflater, container, false)

        initRecycler()

        return binding.root
    }

    private fun initRecycler() {
        recyclerView = binding.recyclerBook
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        bookAdapter = BookAdatper(requireContext())
        binding.recyclerBook.adapter = bookAdapter

        bookAdapter.itemClick = object :  BookAdatper.ItemClick {
            override fun onClick(view: View, position: Int) {

                Toast.makeText(getActivity(), datas[position].title, Toast.LENGTH_SHORT).show()

                val intent = Intent(getActivity(), BookDetailActivity::class.java)
                intent.putExtra("title", datas[position].title.toString())
//                intent.putExtra("img", datas[position].img)
                startActivity(intent)
            }
        }

        datas.apply {
            add(BookData(img = R.drawable.bookcover1, title = "강릉여행" , isOpen = true))
            add(BookData(img = R.drawable.bookcover2,  title = "인생네컷", isOpen = false))
            add(BookData(img = R.drawable.bookcover3, title = "부산여행", isOpen = true))
            add(BookData(img = R.drawable.bookcover4, title = "f", isOpen = true))
            add(BookData(img = R.drawable.bookcover1_selected, title = "e", isOpen = false))

            bookAdapter.datas = datas
            bookAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}