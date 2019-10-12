package passion.events.last_version;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(v,getAdapterPosition());

            }
        });

    }



public void setDetails(Context context, String title, String img,String status, String time, String date, String by, String location){
    CardView card ;
    TextView titleRow = (TextView)mView.findViewById(R.id.titleRow);
    TextView byRow = (TextView) mView.findViewById(R.id.byRow);
    TextView dateRow = (TextView) mView.findViewById(R.id.dateRow);
    TextView timeRow = (TextView) mView.findViewById(R.id.timeRow);
    TextView locRow = (TextView) mView.findViewById(R.id.location);
    ImageView imgRow = (ImageView) mView.findViewById(R.id.imgRow);
    ImageView statusColor = (ImageView) mView.findViewById(R.id.statusColor) ;


    titleRow.setText(title);
    byRow.setText(by);
    dateRow.setText(date);
    timeRow.setText(time);
    locRow.setText(location);
    Picasso.get()
            .load(img)
            .transform(new CropCircleTransformation())
            .into(imgRow);

    card = (CardView) mView.findViewById(R.id.cardV);

    int[] androidColors = context.getResources().getIntArray(R.array.rainbow);
    int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

    card.setRadius(50);

    card.setCardBackgroundColor(randomAndroidColor);
    if (status.equals("No"))
        statusColor.setImageResource(R.drawable.grey);
    else if (status.equals("Yes"))
        statusColor.setImageResource(R.drawable.green);
    else
        statusColor.setImageResource(R.drawable.grey);
}
    private ViewHolder.ClickListner mClick;



    public interface ClickListner{

        void onItemClick(View view, int position);
    }

    public void setOnClickListner(ViewHolder.ClickListner clickListner){
        mClick=clickListner;

    }

}
