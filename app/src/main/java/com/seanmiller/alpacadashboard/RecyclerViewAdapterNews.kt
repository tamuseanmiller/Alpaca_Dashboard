package com.seanmiller.alpacadashboard

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.ramotion.foldingcell.FoldingCell
import org.json.JSONException
import org.json.JSONObject
import xyz.klinker.android.article.ArticleIntent
import xyz.klinker.android.article.ArticleUtils
import java.io.IOException
import java.net.URL
import java.util.*

class RecyclerViewAdapterNews internal constructor(context: Context?, data: ArrayList<JSONObject?>) : RecyclerView.Adapter<RecyclerViewAdapterNews.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = mInflater.inflate(R.layout.news_row, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val utils = ArticleUtils(Properties.articleAPIKey)
        try {
            val url = mData[position]?.get("image").toString()
            Glide.with(holder.itemView.context)
                    .load(url)
                    .into(holder.newsImage)
            holder.newsImage.shapeAppearanceModel = ShapeAppearanceModel().withCornerSize { 30F }
            holder.title.text = mData[position]?.get("headline").toString()
            holder.source.text = mData[position]?.get("source").toString()
            //            holder.summary.setText(mData.get(position).getSummary());
//            holder.headlineContent.setText(mData.get(position).getTitle());
            utils.preloadArticle(mInflater.context, mData[position]?.get("url").toString(), null)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // attach click listener to folding cell
//        holder.fc.setOnClickListener(v -> holder.fc.toggle(false));
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView
        var source: TextView
        var summary: TextView? = null
        var headlineContent: TextView? = null
        var newsImage: ShapeableImageView

        //        ImageView newsImage;
        var fc: FoldingCell? = null
        override fun onClick(view: View) {
            val back = BitmapFactory.decodeResource(view.context.resources,
                    R.drawable.arrow_left)
            val typedValue = TypedValue()
            val activity = view.context as Activity
            activity.theme.resolveAttribute(R.attr.color_positive, typedValue, true)
            val color = ContextCompat.getColor(activity, typedValue.resourceId)

            // Check if theme is light or dark
            val intent: ArticleIntent? = if (SharedPreferencesManager(view.context).retrieveInt("theme",
                            Utils.THEME_DEFAULT) == Utils.THEME_DARK) {
                ArticleIntent.Builder(view.context, Properties.articleAPIKey)
                        .setTheme(ArticleIntent.THEME_DARK)
                        .setCloseButtonIcon(back)
                        .setToolbarColor(color)
                        .setTextSize(15)
                        .build()
            } else {
                ArticleIntent.Builder(view.context, Properties.articleAPIKey)
                        .setTheme(ArticleIntent.THEME_LIGHT)
                        .setCloseButtonIcon(back)
                        .setToolbarColor(color)
                        .setTextSize(15)
                        .build()
            }

            // Launch article intent
            try {
                intent!!.launchUrl(view.context, Uri.parse(mData[adapterPosition]?.get("url").toString()))
            } catch (e: JSONException) {
                e.printStackTrace()
            }

//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            builder.setCloseButtonIcon(back);
//            CustomTabsIntent customTabsIntent = builder.build();
//            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            customTabsIntent.launchUrl(view.getContext(), Uri.parse(mData.get(getAdapterPosition()).getUrl()));
//            }
        }

        init {
            itemView.setOnClickListener(this)
            //            fc = itemView.findViewById(R.id.folding_cell);
//            fc.setOnClickListener(v -> fc.toggle(false));
//            fc.initialize(30, 1000, itemView.getSolidColor(), 0);
            source = itemView.findViewById(R.id.source)
            newsImage = itemView.findViewById(R.id.newsImage)
            title = itemView.findViewById(R.id.headline)
            //            summary = itemView.findViewById(R.id.summary);
//            headlineContent = itemView.findViewById(R.id.headlineContent);
        }
    }

    @Throws(IOException::class)
    fun loadNewsImage(url: String?): Bitmap? {

        // Loads from url
        val newurl = URL(url)
        val options = BitmapFactory.Options()
        options.inSampleSize = 2 //try to decrease decoded image
        val bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream(), null, options)
        //        Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

        // Round corners
        if (bitmap != null) {
//            Bitmap output = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.5), (int)(bitmap.getHeight()*0.5), true);
            val output = Bitmap.createBitmap(bitmap.width, bitmap
                    .height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)
            val color = -0xbdbdbe
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rectF = RectF(rect)
            val roundPx = 15f
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(bitmap, rect, rect, paint)
            return output
        }
        //        return bitmap;
        return null
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): JSONObject? {
        return mData[id]
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    companion object {
        private lateinit var mData: List<JSONObject?>
    }

    // data is passed into the constructor
    init {
        mData = data
    }
}