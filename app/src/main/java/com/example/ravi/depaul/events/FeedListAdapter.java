package com.example.ravi.depaul.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.ravi.depaul.R;
import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.ravi.depaul.app.AppController;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.MyViewHolder> {
	private Context context;
	private LayoutInflater inflater;
	private List<FeedItem> feedItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public FeedListAdapter(Context context, List<FeedItem> feedItems) {
		this.context = context;
		this.feedItems = feedItems;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		public TextView name, timestamp, status, url;
		public ImageView profilepic;
		public ProgressBar progressBar;
		public FeedImageView feedImageView;
		public MyViewHolder(View view) {
			super(view);
			name = (TextView) view.findViewById(R.id.name);
			timestamp = (TextView) view
					.findViewById(R.id.timestamp);
			status = (TextView) view
					.findViewById(R.id.txtStatusMsg);
			feedImageView = (FeedImageView) view
					.findViewById(R.id.feedImage1);
			progressBar= (ProgressBar) view.findViewById(R.id.progress);
			feedImageView = (FeedImageView) view
					.findViewById(R.id.feedImage1);
		}
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.event_feed_item, parent, false);

		return new MyViewHolder(itemView);
	}


	@Override
	public void onBindViewHolder(final FeedListAdapter.MyViewHolder holder, int position) {
		FeedItem item = feedItems.get(position);
		/*Glide
				.with(context)
				.load(item.getProfilePic())
				.diskCacheStrategy(DiskCacheStrategy.NONE)
				.skipMemoryCache(true)
				.into(holder.profilepic);
		holder.profilepic.setVisibility(View.VISIBLE);*/

		// Feed image
		if (item.getImge() != null) {
			holder.feedImageView.setImageUrl(item.getImge(), imageLoader);
			holder.feedImageView.setVisibility(View.VISIBLE);
			holder.feedImageView
					.setResponseObserver(new FeedImageView.ResponseObserver() {
						@Override
						public void onError() {
							holder.progressBar.setVisibility(View.GONE);
						}

						@Override
						public void onSuccess() {
							holder.progressBar.setVisibility(View.GONE);
						}
					});
		} else {
			holder.feedImageView.setVisibility(View.GONE);
		}
		//holder.feedImageView.setVisibility(View.VISIBLE);

		holder.name.setText(item.getName());

		// Converting timestamp into x ago format
		CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
				Long.parseLong(item.getTimeStamp()),
				System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
		holder.timestamp.setText(timeAgo);

		// Chcek for empty status message
		if (!TextUtils.isEmpty(item.getStatus())) {
			holder.status.setText(item.getStatus());
			holder.status.setVisibility(View.VISIBLE);
		} else {
			// status is empty, remove from view
			holder.status.setVisibility(View.GONE);
		}

		}


	@Override
	public int getItemCount() {
		return feedItems.size();
	}


}