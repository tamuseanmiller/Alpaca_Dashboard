package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import xyz.klinker.android.article.ArticleIntent;
import xyz.klinker.android.article.ArticleUtils;

public class RecyclerViewAdapterNews extends RecyclerView.Adapter<RecyclerViewAdapterNews.ViewHolder> {

    private static List<JSONObject> mData;
    private final LayoutInflater mInflater;
    private Context context;

    // data is passed into the constructor
    RecyclerViewAdapterNews(Context context, ArrayList<JSONObject> data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = mInflater.inflate(R.layout.news_row, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterNews.ViewHolder holder, int position) {

        ArticleUtils utils = new ArticleUtils(Properties.getArticleAPIKey());

        try {
            if (mData.get(position).get("image") != null) {

                String url = mData.get(position).get("image").toString();

                Glide.with(holder.itemView.getContext())
                        .load(url)
                        .into(holder.newsImage);
                holder.newsImage.setShapeAppearanceModel(new ShapeAppearanceModel().withCornerSize(bounds -> 30));

            }
            holder.title.setText(mData.get(position).get("headline").toString());
            holder.source.setText(mData.get(position).get("source").toString());
//            holder.summary.setText(mData.get(position).getSummary());
//            holder.headlineContent.setText(mData.get(position).getTitle());

            utils.preloadArticle(mInflater.getContext(), mData.get(position).get("url").toString(), null);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // attach click listener to folding cell
//        holder.fc.setOnClickListener(v -> holder.fc.toggle(false));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView source;
        TextView summary;
        TextView headlineContent;
        ShapeableImageView newsImage;
        //        ImageView newsImage;
        FoldingCell fc;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
//            fc = itemView.findViewById(R.id.folding_cell);
//            fc.setOnClickListener(v -> fc.toggle(false));
//            fc.initialize(30, 1000, itemView.getSolidColor(), 0);
            source = itemView.findViewById(R.id.source);
            newsImage = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.headline);
//            summary = itemView.findViewById(R.id.summary);
//            headlineContent = itemView.findViewById(R.id.headlineContent);
        }

        @Override
        public void onClick(View view) {
            Bitmap back = BitmapFactory.decodeResource(view.getContext().getResources(),
                    R.drawable.arrow_left);
            TypedValue typedValue = new TypedValue();
            Activity activity = (Activity) view.getContext();
            activity.getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
            int color = ContextCompat.getColor(activity, typedValue.resourceId);

            ArticleIntent intent = null;

            // Check if theme is light or dark
            if (new SharedPreferencesManager(view.getContext()).retrieveInt("theme",
                    Utils.THEME_DEFAULT) == Utils.THEME_DARK) {
                intent = new ArticleIntent.Builder(view.getContext(), Properties.getArticleAPIKey())
                        .setTheme(ArticleIntent.THEME_DARK)
                        .setCloseButtonIcon(back)
                        .setToolbarColor(color)
                        .setTextSize(15)
                        .build();

            } else {
                intent = new ArticleIntent.Builder(view.getContext(), Properties.getArticleAPIKey())
                        .setTheme(ArticleIntent.THEME_LIGHT)
                        .setCloseButtonIcon(back)
                        .setToolbarColor(color)
                        .setTextSize(15)
                        .build();
            }

            // Launch article intent
            try {
                intent.launchUrl(view.getContext(), Uri.parse(mData.get(getAdapterPosition()).get("url").toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            builder.setCloseButtonIcon(back);
//            CustomTabsIntent customTabsIntent = builder.build();
//            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            customTabsIntent.launchUrl(view.getContext(), Uri.parse(mData.get(getAdapterPosition()).getUrl()));
//            }
        }
    }

    public Bitmap loadNewsImage(String url) throws IOException {

        // Loads from url
        URL newurl = new URL(url);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; //try to decrease decoded image
        Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream(), null, options);
//        Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

        // Round corners
        if (bitmap != null) {
//            Bitmap output = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.5), (int)(bitmap.getHeight()*0.5), true);
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                    .getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = 15;

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;
        }
//        return bitmap;
        return null;
    }

    // convenience method for getting data at click position
    JSONObject getItem(int id) {
        return mData.get(id);
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

