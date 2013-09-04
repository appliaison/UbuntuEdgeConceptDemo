package com.appliaison.ubuntuedgeconceptdemo.controllers.step1;

import com.actionbarsherlock.app.SherlockActivity;
import com.appliaison.ubuntuedgeconceptdemo.HomeActivity;
import com.appliaison.ubuntuedgeconceptdemo.HomeFragment;
import com.appliaison.ubuntuedgeconceptdemo.MainActivity;
import com.appliaison.ubuntuedgeconceptdemo.R;
import com.appliaison.ubuntuedgeconceptdemo.controllers.step3.Step3Activity;
import com.appliaison.ubuntuedgeconceptdemo.draggableviews.DeleteZone;
import com.appliaison.ubuntuedgeconceptdemo.draggableviews.DragController;
import com.appliaison.ubuntuedgeconceptdemo.draggableviews.DragLayer;
import com.appliaison.ubuntuedgeconceptdemo.draggableviews.DragSource;
import com.appliaison.ubuntuedgeconceptdemo.draggableviews.ImageCell;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity presents a screen with a grid on which images can be added and
 * moved around. It also defines areas on the screen where the dragged views can
 * be dropped. Feedback is provided to the user as the objects are dragged over
 * these drop zones.
 * 
 * <p>
 * Like the DragActivity in the previous version of the DragView example
 * application, the code here is derived from the Android Launcher code.
 * 
 * <p>
 * The original Launcher code required a long click (press) to initiate a
 * drag-drop sequence. If you want to see that behavior, set the variable
 * mLongClickStartsDrag to true. It is set to false below, which means that any
 * touch event starts a drag-drop.
 * 
 */

public class Step1Activity extends SherlockActivity implements View.OnLongClickListener,
		View.OnClickListener, View.OnTouchListener // ,
													// AdapterView.OnItemClickListener
{

	/**
 */
	// Constants

	private static final int HIDE_TRASHCAN_MENU_ID = Menu.FIRST;
	private static final int SHOW_TRASHCAN_MENU_ID = Menu.FIRST + 1;
	private static final int ADD_OBJECT_MENU_ID = Menu.FIRST + 2;
	private static final int CHANGE_TOUCH_MODE_MENU_ID = Menu.FIRST + 3;

	/**
 */
	// Variables

	private DragController mDragController; // Object that handles a drag-drop
											// sequence. It intersacts with
											// DragSource and DropTarget
											// objects.
	private DragLayer mDragLayer; // The ViewGroup within which an object can be
									// dragged.
	private DeleteZone mDeleteZone; // A drop target that is used to remove
									// objects from the screen.
	private int mImageCount = 0; // The number of images that have been added to
									// screen.
	private ImageCell mLastNewCell = null; // The last ImageCell added to the
											// screen when Add Image is clicked.
	private boolean mLongClickStartsDrag = false; // If true, it takes a long
													// click to start the drag
													// operation.
													// Otherwise, any touch
													// event starts a drag.

	public static final boolean Debugging = false; // Use this to see extra
													// toast messages.
	
	private Button buttonSkip;
	private Button buttonContinue;
	private TextView headerwelcometext;
	private TextView headertext;
	private TextView headermessagetext;
	/**
 */
	// Methods

	/**
	 * Add a new image so the user can move it around. It shows up in the
	 * image_source_frame part of the screen.
	 * 
	 * @param resourceId
	 *            int - the resource id of the image to be added
	 */

	public void addNewImageToScreen(int resourceId)
	{
		if (mLastNewCell != null)
			mLastNewCell.setVisibility(View.GONE);

		FrameLayout imageHolder = (FrameLayout) findViewById(R.id.image_source_frame_1);
		FrameLayout imageHolder2 = (FrameLayout) findViewById(R.id.image_source_frame2);
		FrameLayout imageHolder3 = (FrameLayout) findViewById(R.id.image_source_frame3);
		FrameLayout imageHolder4 = (FrameLayout) findViewById(R.id.image_source_frame4);
		if ( (imageHolder != null) && (imageHolder2 != null)&& (imageHolder3 != null) && (imageHolder4 != null))
		{
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, Gravity.CENTER);
			ImageCell newView = new ImageCell(this);
			newView.setImageResource(R.drawable.pictogram1);
			imageHolder.addView(newView, lp);
			
			newView.mEmpty = false;
			newView.mCellNumber = -1;
			newView.setTag("face1");
			mLastNewCell = newView;
			mImageCount++;

			// Have this activity listen to touch and click events for the view.
			newView.setOnClickListener(this);
			newView.setOnLongClickListener(this);
			newView.setOnTouchListener(this);
			
			ImageCell newView2 = new ImageCell(this);
			newView2.setImageResource(R.drawable.pictogram2);
			imageHolder2.addView(newView2, lp);
			newView2.mEmpty = false;
			newView2.mCellNumber = -1;
			newView2.mCellId = 222;
			newView2.setTag("face2");
			// mLastNewCell = newView2;
			mImageCount++;

			// Have this activity listen to touch and click events for the view.
			newView2.setOnClickListener(this);
			newView2.setOnLongClickListener(this);
			newView2.setOnTouchListener(this);
			
			ImageCell newView3 = new ImageCell(this);
			newView3.setImageResource(R.drawable.pictogram3);
			imageHolder3.addView(newView3, lp);
			newView3.mEmpty = false;
			newView3.mCellNumber = -1;
			newView3.mCellId = 333;
			newView3.setTag("face3");
			// mLastNewCell = newView2;
			mImageCount++;

			// Have this activity listen to touch and click events for the view.
			newView3.setOnClickListener(this);
			newView3.setOnLongClickListener(this);
			newView3.setOnTouchListener(this);
			
			
			
			ImageCell newView4 = new ImageCell(this);
			newView4.setImageResource(R.drawable.pictogram4);
			imageHolder4.addView(newView4, lp);
			newView4.mEmpty = false;
			newView4.mCellNumber = -1;
			newView4.mCellId = 444;
			newView4.setTag("face4");
			// mLastNewCell = newView2;
			mImageCount++;

			// Have this activity listen to touch and click events for the view.
			newView4.setOnClickListener(this);
			newView4.setOnLongClickListener(this);
			newView4.setOnTouchListener(this);
			
			/* 
			ImageCell newView5 = new ImageCell(this);
			newView5.setImageResource(R.drawable.face5x);
			imageHolder5.addView(newView5, lp);
			newView5.mEmpty = false;
			newView5.mCellNumber = -1;
			newView5.mCellId = 555;
			newView5.setTag("face5");
			mImageCount++;
			newView5.setOnClickListener(this);
			newView5.setOnLongClickListener(this);
			newView5.setOnTouchListener(this);
			*/

		}
	}

	/**
	 * Add one of the images to the screen so the user has a new image to move
	 * around. See addImageToScreen.
	 * 
	 */

	public void addNewImageToScreen()
	{
		int resourceId = R.drawable.hello;

		int m = mImageCount % 3;
		if (m == 1)
			resourceId = R.drawable.pictogram4x;
		else if (m == 2)
			resourceId = R.drawable.pictogram2x;
		addNewImageToScreen(resourceId);
	}

	/**
	 * Handle a click on a view.
	 * 
	 */

	public void onClick(View v)
	{
		if (mLongClickStartsDrag)
		{
			// Tell the user that it takes a long click to start dragging.
			toast("Press and hold to drag an image.");
		}
	}

	/**
	 * Handle a click of the Add Image button
	 * 
	 */

	public void onClickAddImage(View v)
	{
		addNewImageToScreen();
	}

	/**
	 * onCreate - called when the activity is first created.
	 * 
	 * Creates a drag controller and sets up three views so click and long click
	 * on the views are sent to this activity. The onLongClick method starts a
	 * drag sequence.
	 * 
	 */

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_step1);
		
		buttonSkip = (Button) this.findViewById(R.id.buttonSkip);
		buttonContinue = (Button) this.findViewById(R.id.buttonContinue);
		headerwelcometext = (TextView) this.findViewById(R.id.headerwelcometext);
		headertext = (TextView) this.findViewById(R.id.headertext);
		headermessagetext = (TextView) this.findViewById(R.id.headermessagetext);
		
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
				"Linotype - FrutigerLTPro-Roman.otf");
		Typeface tf2 = Typeface.createFromAsset(this.getAssets(), "BandaRegular.ttf");
		Typeface tf3 = Typeface.createFromAsset(this.getAssets(), "avenir-65-medium-1361499812.ttf");
		Typeface tf4 = Typeface.createFromAsset(this.getAssets(), "Ubuntu-R.ttf");
		headerwelcometext.setTypeface(tf4);
		headertext.setTypeface(tf4);
		headermessagetext.setTypeface(tf4);
		
		buttonSkip.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Step1Activity.this, HomeActivity.class);
				
				startActivity(intent);
			}
		});
		
		
		buttonContinue.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
//				Intent intent = new Intent(Step1Activity.this, HomeActivity.class);
				Intent intent = new Intent(Step1Activity.this, Step3Activity.class);
				startActivity(intent);
			}
		});
		
		
		/*
		GridView gridView = (GridView) findViewById(R.id.image_grid_view);
		
		if (gridView == null)
			toast("Unable to find GridView");
		else
		{
			gridView.setAdapter(new ImageCellAdapter(this));
			// gridView.setOnItemClickListener (this);
		}
		*/

		mDragController = new DragController(this);
		mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
		mDragLayer.setDragController(mDragController);
		// mDragLayer.setGridView(gridView);

		mDragController.setDragListener(mDragLayer);
		// mDragController.addDropTarget (mDragLayer);

		mDeleteZone = (DeleteZone) findViewById(R.id.delete_zone_view);

		// Give the user a little guidance.
		/* Toast.makeText(getApplicationContext(),
				getResources().getString(R.string.instructions),
				Toast.LENGTH_LONG).show();
		*/
		addNewImageToScreen();
	}

	/**
	 * Build a menu for the activity.
	 * 
	 */
	/*
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);

		menu.add(0, HIDE_TRASHCAN_MENU_ID, 0, "Hide").setShortcut('1',
				'c');
		menu.add(0, SHOW_TRASHCAN_MENU_ID, 0, "Show").setShortcut('2',
				'c');
		menu.add(0, ADD_OBJECT_MENU_ID, 0, "Add View").setShortcut('9', 'z');
		menu.add(0, CHANGE_TOUCH_MODE_MENU_ID, 0, "Change Touch Mode");

		return true;
	}
	*/

	/**
	 * Handle a click of an item in the grid of cells.
	 * 
	 */

	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	{
		ImageCell i = (ImageCell) v;
		trace("onItemClick in view: " + i.mCellNumber);
		
		
	}

	/**
	 * Handle a long click. If mLongClick only is true, this will be the only
	 * way to start a drag operation.
	 * 
	 * @param v
	 *            View
	 * @return boolean - true indicates that the event was handled
	 */

	public boolean onLongClick(View v)
	{
		if (mLongClickStartsDrag)
		{

			// trace ("onLongClick in view: " + v + " touchMode: " +
			// v.isInTouchMode ());

			// Make sure the drag was started by a long press as opposed to a
			// long click.
			// (Note: I got this from the Workspace object in the Android
			// Launcher code.
			// I think it is here to ensure that the device is still in touch
			// mode as we start the drag operation.)
			if (!v.isInTouchMode())
			{
				toast("isInTouchMode returned false. Try touching the view again.");
				return false;
			}
			return startDrag(v);
		}

		// If we get here, return false to indicate that we have not taken care
		// of the event.
		return false;
	}

	/**
	 * Perform an action in response to a menu item being clicked.
	 * 
	 */
	/*
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case HIDE_TRASHCAN_MENU_ID:
			if (mDeleteZone != null)
				mDeleteZone.setVisibility(View.INVISIBLE);
			return true;
		case SHOW_TRASHCAN_MENU_ID:
			if (mDeleteZone != null)
				mDeleteZone.setVisibility(View.VISIBLE);
			return true;
		case ADD_OBJECT_MENU_ID:
			// Add a new object to the screen;
			addNewImageToScreen();
			return true;
		case CHANGE_TOUCH_MODE_MENU_ID:
			mLongClickStartsDrag = !mLongClickStartsDrag;
			String message = mLongClickStartsDrag ? "Changed touch mode. Drag now starts on long touch (click)."
					: "Changed touch mode. Drag now starts on touch (click).";
			Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
					.show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	*/
	/**
	 * This is the starting point for a drag operation if mLongClickStartsDrag
	 * is false. It looks for the down event that gets generated when a user
	 * touches the screen. Only that initiates the drag-drop sequence.
	 * 
	 */

	public boolean onTouch(View v, MotionEvent ev)
	{
		// If we are configured to start only on a long click, we are not going
		// to handle any events here.
		if (mLongClickStartsDrag)
			return false;

		boolean handledHere = false;

		final int action = ev.getAction();

		// In the situation where a long click is not needed to initiate a drag,
		// simply start on the down event.
		if (action == MotionEvent.ACTION_DOWN)
		{
			handledHere = startDrag(v);
		}

		return handledHere;
	}

	/**
	 * Start dragging a view.
	 * 
	 */

	public boolean startDrag(View v)
	{
		DragSource dragSource = (DragSource) v;

		// We are starting a drag. Let the DragController handle it.
		mDragController.startDrag(v, dragSource, dragSource,
				DragController.DRAG_ACTION_MOVE);
		

		
		return true;
	}

	/**
	 * Show a string on the screen via Toast.
	 * 
	 * @param msg
	 *            String
	 * @return void
	 */

	public void toast(String msg)
	{
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	} // end toast

	/**
	 * Send a message to the debug log. Also display it using Toast if Debugging
	 * is true.
	 */

	public void trace(String msg)
	{
		Log.d("DragActivity", msg);
		if (!Debugging)
			return;
		toast(msg);
	}

} // end class
