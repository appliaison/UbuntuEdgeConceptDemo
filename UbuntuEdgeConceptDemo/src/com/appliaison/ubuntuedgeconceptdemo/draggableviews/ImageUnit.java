package com.appliaison.ubuntuedgeconceptdemo.draggableviews;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This subclass of ImageView is used to display an image on an GridView. An
 * ImageCell knows which cell on the grid it is showing and which grid it is
 * attached to Cell numbers are from 0 to NumCells-1. It also knows if it is
 * empty.
 * 
 * <p>
 * Image cells are places where images can be dragged from and dropped onto.
 * Therefore, this class implements both the DragSource and DropTarget
 * interfaces.
 * 
 */

public class ImageUnit extends View
{
	private Bitmap mImage;
	private Region mRegion;
	private final Paint mPaint = new Paint();
	private final Point mSize = new Point();
	private final Point mStartPosition = new Point();

	public ImageUnit(Context context)
	{
		super(context);

	}

	public final Bitmap getImage()
	{
		return mImage;
	}

	public final void setImage(Bitmap image)
	{
		mImage = image;
		setSize(mImage.getWidth(), mImage.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		Point position = getPosition();
		canvas.drawBitmap(mImage, position.x, position.y, mPaint);
	}

	public final void setPosition(final Point position)
	{
		mRegion.set(position.x, position.y, position.x + mSize.x, position.y
				+ mSize.y);
	}

	public final Point getPosition()
	{
		Rect bounds = mRegion.getBounds();
		return new Point(bounds.left, bounds.top);
	}

	public final void setSize(int width, int height)
	{
		mSize.x = width;
		mSize.y = height;

		Rect bounds = mRegion.getBounds();
		mRegion.set(bounds.left, bounds.top, bounds.left + width, bounds.top
				+ height);
	}

	public final Point getSize()
	{
		return mSize;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// Is the event inside of this view?
		if (!mRegion.contains((int) event.getX(), (int) event.getY()))
		{
			return super.onTouchEvent(event);
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			mStartPosition.x = (int) event.getX();
			mStartPosition.y = (int) event.getY();
			bringToFront();
			// onSelected();
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			int x = 0, y = 0;

			x = (int) event.getX() - mStartPosition.x;
			/*
			 * if (mLock == DirectionLock.FREE || mLock ==
			 * DirectionLock.HORIZONTAL_ONLY) { x = (int) event.getX() -
			 * mStartPosition.x; }
			 * 
			 * if (mLock == DirectionLock.FREE || mLock ==
			 * DirectionLock.VERTICAL_ONLY) { y = (int) event.getY() -
			 * mStartPosition.y; }
			 */

			mRegion.translate(x, y);
			mStartPosition.x = (int) event.getX();
			mStartPosition.y = (int) event.getY();

			invalidate();

			return true;
		} else
		{
			return super.onTouchEvent(event);
		}
	}

	// -----------------

}
