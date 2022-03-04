package fr.epsi.rendu_epsi_b3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//import com.squareup.picasso.Picasso


class OfferAdaptater(val context: OfferFragment, private val offer: ArrayList<Offer>): RecyclerView.Adapter<OfferAdaptater.ViewHolder>()  {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.nameOffer)
        val textviewDescription = view.findViewById<TextView>(R.id.descriptionOffer)
        val imageViewOffer = view.findViewById<ImageView>(R.id.imageCellOffer)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_offer, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = offer.get(position)
        holder.textViewName.text=offer.name
        holder.textviewDescription.text=offer.description

        Picasso.get().load(offer.imgUrl).into(holder.imageViewOffer)

    }

    override fun getItemCount(): Int {
        return offer.size
    }

}