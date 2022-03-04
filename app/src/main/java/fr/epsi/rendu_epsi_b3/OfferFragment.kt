package fr.epsi.rendu_epsi_b3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class OfferFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offers = arrayListOf<Offer>()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://djemam.com/epsi/offers.json"
        val request =Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                val a=1
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null){
                    val jsonObject = JSONObject(data)
                    val jsArray = jsonObject.getJSONArray("items")
                    for (i in 0 until jsArray.length()) {
                        val jsOffer = jsArray.getJSONObject(i)
                        val name = jsOffer.optString("name", "")
                        val description = jsOffer.optString("description", "")
                        val picture_url = jsOffer.optString("picture_url", "")
                        val offer = Offer(name, description, picture_url)
                        offers.add(offer)
                    }
                }

            }
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewOffer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val offerAdaptater = OfferAdaptater(this, offers)
        recyclerView.adapter = offerAdaptater
        val button = view.findViewById<Button>(R.id.refresh_button)

        // button used to refresh the fragment
        button.setOnClickListener(View.OnClickListener {
            button.visibility = View.GONE
            Handler(Looper.getMainLooper()).post(Runnable {
                offerAdaptater.notifyDataSetChanged()
            })
        })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            OfferFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}