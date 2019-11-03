package myapp.anatoleallain.com.android_avance.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_games_list.*
import myapp.anatoleallain.com.android_avance.Game


import myapp.anatoleallain.com.android_avance.R
import myapp.anatoleallain.com.android_avance.adapters.GameAdapter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Games_list.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Games_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class Games_list : Fragment() {
    private var mListener: ViewDetails? = null

    var gameList: ArrayList<Game> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameAdapter(gameList) {id ->
            onButtonPressed(id)
        }
        activity?.let {
            fetchData(it)
        }
    }

    fun onButtonPressed(id: Int) {
        mListener?.viewDetails(id)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ViewDetails) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun fetchData(context: Context) {
        val queue = Volley.newRequestQueue(context)
        gameList.clear()
        val request = JsonArrayRequest(
                Request.Method.GET,
                "https://my-json-server.typicode.com/bgdom/cours-android/games/",
                null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {
                        gameList.add(
                                Game(
                                        response.getJSONObject(i).getInt("id"),
                                        response.getJSONObject(i).getString("name"),
                                        response.getJSONObject(i).getString("description"),
                                        response.getJSONObject(i).getString("link"),
                                        response.getJSONObject(i).getString("img")
                                )
                        )
                    }
                    initRecyclerView(gameList)
                },
                Response.ErrorListener { error ->
                    Log.e("test", error.localizedMessage)
                }
        )

        queue.add(request)
    }

    fun initRecyclerView(gameList: ArrayList<Game>) {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    interface ViewDetails {
        fun viewDetails(id: Int)
    }
}// Required empty public constructor
