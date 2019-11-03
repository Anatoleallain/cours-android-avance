package myapp.anatoleallain.com.android_avance.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_game_description.*

import myapp.anatoleallain.com.android_avance.R

private const val ID_PARAM = "gameId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Game_description.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Game_description.newInstance] factory method to
 * create an instance of this fragment.
 */
class Game_description : Fragment() {
    private var gameId: Int? = null
    private var url: VisitWebsite? = null
    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameId = it.getInt(ID_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_description, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            fetchData(it)
        }
        button.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(link)
            startActivity(openURL)
        }
    }

    private fun fetchData(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
                Request.Method.GET,
                "https://my-json-server.typicode.com/bgdom/cours-android/games/" + gameId,
                null,
                Response.Listener { response ->
                    title.text = response.getString("name")
                    description.text = response.getString("description")
                    Picasso.get().load(response.getString("img")).into(imageView)
                    link = response.getString("link")

                },
                Response.ErrorListener { error ->
                    Log.e("test", error.localizedMessage)
                }
        )

        queue.add(request)
    }

    interface VisitWebsite {
        fun visitWebsite(link: String)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Game_description.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(gameId: Int): Game_description {
            val fragment = Game_description()
            val args = Bundle()
            args.putInt(ID_PARAM, gameId)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
