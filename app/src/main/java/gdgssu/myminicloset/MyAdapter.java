package gdgssu.myminicloset;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MyAdapter extends BaseAdapter {
	private Bitmap[] list;
	private Context context;


	public MyAdapter(Context context, Bitmap[] list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView holder = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, null);
			holder = (ImageView)convertView.findViewById(R.id.userimage);

		}

		final Bitmap img = list[position];

        if (holder != null) {
            holder.setImageBitmap(img);
        }

        return convertView;
	}

}
