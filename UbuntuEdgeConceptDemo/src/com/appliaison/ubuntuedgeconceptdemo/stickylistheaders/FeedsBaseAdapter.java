package com.appliaison.ubuntuedgeconceptdemo.stickylistheaders;

import java.util.ArrayList;

import com.appliaison.ubuntuedgeconceptdemo.HomeFragment;
import com.appliaison.ubuntuedgeconceptdemo.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;


public class FeedsBaseAdapter extends BaseAdapter implements
		StickyListHeadersAdapter, SectionIndexer {

	private String[] feedsdummydata;
	private int[] sectionIndices;
	private Character[] sectionsLetters;
	private LayoutInflater inflater;
	private Context context;

	public FeedsBaseAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		feedsdummydata = context.getResources().getStringArray(R.array.feedsdummydata);
		sectionIndices = getSectionIndices();
		sectionsLetters = getStartingLetters();
	}

	private Character[] getStartingLetters() {
		Character[] letters = new Character[sectionIndices.length];
		for (int i = 0; i < sectionIndices.length; i++) {
			letters[i] = feedsdummydata[sectionIndices[i]].charAt(0);
		}
		return letters;
	}

	private int[] getSectionIndices() {
		ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
		char lastFirstChar = feedsdummydata[0].charAt(0);
		sectionIndices.add(0);
		for (int i = 1; i < feedsdummydata.length; i++) {
			if (feedsdummydata[i].charAt(0) != lastFirstChar) {
				lastFirstChar = feedsdummydata[i].charAt(0);
				sectionIndices.add(i);
			}
		}
		int[] sections = new int[sectionIndices.size()];
		for (int i = 0; i < sectionIndices.size(); i++) {
			sections[i] = sectionIndices.get(i);
		}
		return sections;
	}

	@Override
	public int getCount() {
		return feedsdummydata.length;
	}

	@Override
	public Object getItem(int position) {
		return feedsdummydata[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.feedscontainer_list_item_layout,
					parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			
			Typeface tf = Typeface.createFromAsset(parent.getContext().getAssets(),
		            "Linotype - FrutigerLTPro-Roman.otf");
			Typeface tf2 = Typeface.createFromAsset(parent.getContext().getAssets(),
		            "BandaRegular.ttf");
			Typeface tf3 = Typeface.createFromAsset(parent.getContext().getAssets(),
		            "avenir-65-medium-1361499812.ttf");
			Typeface tf4 = Typeface.createFromAsset(parent.getContext().getAssets(),
		            "Ubuntu-R.ttf");
			holder.text.setTypeface(tf4);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(feedsdummydata[position]);

		return convertView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder;
		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = inflater.inflate(R.layout.feedscontainer_list_item_layout, parent, false);
			holder.text1 = (TextView) convertView.findViewById(R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}
		// set header text as first char in name
		char headerChar = feedsdummydata[position].subSequence(0, 1).charAt(0);
		
		String headerWord = feedsdummydata[position].subSequence(0, 5).toString().toUpperCase();
		
		String headerText = "";
;
		if (position==0)
		{
			headerText = "TODAY";
		}
		else if (position==1)
		{
			headerText = "WEEK";
		}
		else if (position==2)
		{
			headerText = "MONTH";
		}
		else if (position==3)
		{
			headerText = "ALL";
		}
		
		headerText = headerText + "\n" + "------------------------------------";
		
		/*
		if (headerChar % 2 == 0) {
			headerText = headerChar + "\n" + headerChar + "\n" + headerChar;
		} else {
			headerText = headerChar + "\n" + headerChar;
		}
		holder.text1.setText(headerText);
		*/
		holder.text1.setText(headerText);
		return convertView;
	}

	// remember that these have to be static, postion=1 should walys return the
	// same Id that is.
	@Override
	public long getHeaderId(int position) {
		// return the first character of the country as ID because this is what
		// headers are based upon
		return feedsdummydata[position].subSequence(0, 1).charAt(0);
	}

	class HeaderViewHolder {
		TextView text1;
	}

	class ViewHolder {
		TextView text;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= sectionIndices.length) {
			section = sectionIndices.length - 1;
		} else if (section < 0) {
			section = 0;
		}
		return sectionIndices[section];
	}

	@Override
	public int getSectionForPosition(int position) {
		for (int i = 0; i < sectionIndices.length; i++) {
			if (position < sectionIndices[i]) {
				return i - 1;
			}
		}
		return sectionIndices.length - 1;
	}

	@Override
	public Object[] getSections() {
		return sectionsLetters;
	}

	public void clear() {
		sectionIndices = new int[0];
		sectionsLetters = new Character[0];
		feedsdummydata = new String[0];
		notifyDataSetChanged();
	}

	public void restore() {
		feedsdummydata = context.getResources().getStringArray(R.array.feedsdummydata);
		sectionIndices = getSectionIndices();
		sectionsLetters = getStartingLetters();
		notifyDataSetChanged();
	}

	public int getSectionStart(int itemPosition) {
		return getPositionForSection(getSectionForPosition(itemPosition));
	}

}
