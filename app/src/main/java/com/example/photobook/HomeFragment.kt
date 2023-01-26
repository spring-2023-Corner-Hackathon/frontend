package com.example.photobook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photobook.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentHomeBinding
    lateinit var bookAdapter : BookAdatper
    lateinit var recyclerView:RecyclerView
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.makeBook.setOnClickListener {
            val intent = Intent(getActivity(), MakeActivity::class.java)
            startActivity(intent)
        }
        //recyclerView 동작
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
            add(BookData(img = R.drawable.bookcover1, title = "강릉여행" ))
            add(BookData(img = R.drawable.bookcover2,  title = "인생네컷"))
            add(BookData(img = R.drawable.bookcover3, title = "부산여행"))
            add(BookData(img = R.drawable.bookcover4, title = "f"))
            add(BookData(img = R.drawable.bookcover1_selected, title = "e"))

            bookAdapter.datas = datas
            bookAdapter.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //spinner 적용
        val spinner = binding.sort
        val menu = resources.getStringArray(R.array.spinner_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, menu)
        spinner.adapter = adapter


        bookAdapter = BookAdatper(requireContext())

        //spinner 선택 이벤트 처리
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                //카테고리 선택
                when(p2){

                    0 -> {
                        //아이디가 작은 것부터 recyclerview 나열(백)
                        Toast.makeText(context, "${spinner.getSelectedItem().toString()}으로 선택 되었습니다", Toast.LENGTH_SHORT).show()

                    }


                    1 -> {
                        //이름순으로 recyclerview 나열(백)
                        Toast.makeText(context, "${spinner.getSelectedItem().toString()}으로 선택 되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }
        }

        // 포토북 만들러 가기
        binding.makeBook.setOnClickListener {
            val intent = Intent(getActivity(), MakeActivity::class.java)
            startActivity(intent)
        }

    }

        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}