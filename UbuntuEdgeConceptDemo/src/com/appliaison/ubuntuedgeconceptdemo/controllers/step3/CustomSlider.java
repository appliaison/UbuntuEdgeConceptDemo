package com.appliaison.ubuntuedgeconceptdemo.controllers.step3;


import com.appliaison.ubuntuedgeconceptdemo.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomSlider extends View
{
	interface CustomSliderPositionListener
	{
		public void onPositionChange(float newPosition);
	}

	private final Drawable mIndicator;
	private final Drawable mBackground;
	private float mMin;
	private float mMax;
	private float mPosition;
	private Rect mViewRect;
	private int mIndicatorOffset;
	private int mIndicatorMaxPos;
	private int mIndicatorMinPos;
	private CustomSliderPositionListener mPositionListener;
	private boolean mIsVertical;

	public CustomSlider(final Context context)
	{
		this(context, null, 0);
	}

	public CustomSlider(final Context context, final AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public CustomSlider(final Context context, final AttributeSet attrs,
			final int defStyle)
	{
		super(context, attrs, defStyle);

		final TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CustomSlider);
		final int vertical = a.getInt(R.styleable.CustomSlider_orientation, 0);
		mIsVertical = (vertical != 0);

		final float max = a.getFloat(R.styleable.CustomSlider_max, 1.0f);
		final float min = a.getFloat(R.styleable.CustomSlider_min, -1.0f);
		setMinMax(min, max);

		final Resources res = context.getResources();
		if (mIsVertical)
		{
			mIndicator = res.getDrawable(R.drawable.indicator_custom_slider);
			mBackground = res.getDrawable(R.drawable.background_vertical);
		} else
		{
			mIndicator = res.getDrawable(R.drawable.indicator_horizontal);
			mBackground = res.getDrawable(R.drawable.background_horizontal);
		}

		mPosition = (mMax - mMin) / 2 + mMin;
		mPositionListener = null;
		setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(final View v, final MotionEvent event)
			{
				final float pos;
				if (mIsVertical)
				{
					pos = (mMax - ((mMax - mMin) / (mIndicatorMinPos - mIndicatorMaxPos))
							* event.getY());
				} else
				{
					pos = (mMin + ((mMax - mMin) / (mIndicatorMaxPos - mIndicatorMinPos))
							* event.getX());
				}
				setPosition(pos);
				return true;
			}
		});
	}

	@Override
	protected void onDraw(final Canvas canvas)
	{
		if (mViewRect == null)
		{
			mViewRect = new Rect();
			getDrawingRect(mViewRect);
			if (mIsVertical)
			{
				mIndicatorOffset = mIndicator.getIntrinsicHeight();
				mIndicatorMaxPos = mViewRect.top + mIndicatorOffset;
				mIndicatorMinPos = mViewRect.bottom - mIndicatorOffset;
			} else
			{
				mIndicatorOffset = mIndicator.getIntrinsicWidth();
				mIndicatorMaxPos = mViewRect.right - mIndicatorOffset;
				mIndicatorMinPos = mViewRect.left + mIndicatorOffset;
			}
			mBackground.setBounds(mViewRect.left, mViewRect.top,
					mViewRect.right, mViewRect.bottom);
		}

		final float pos;
		final int left;
		final int right;
		final int top;
		final int bottom;
		if (mIsVertical)
		{
			pos = mIndicatorMaxPos
					+ ((mIndicatorMinPos - mIndicatorMaxPos) / (mMax - mMin))
					* (mMax - mPosition);
			left = mViewRect.centerX() - (mIndicator.getIntrinsicWidth() / 2);
			top = (int) pos - (mIndicator.getIntrinsicHeight() / 2);
		} else
		{
			pos = mIndicatorMinPos
					+ ((mIndicatorMaxPos - mIndicatorMinPos) / (mMax - mMin))
					* (mPosition - mMin);
			left = (int) pos - (mIndicator.getIntrinsicWidth() / 2);
			top = mViewRect.centerY() - (mIndicator.getIntrinsicHeight() / 2);
		}
		right = left + mIndicator.getIntrinsicWidth();
		bottom = top + mIndicator.getIntrinsicHeight();
		mIndicator.setBounds(left, top, right, bottom);

		mBackground.draw(canvas);
		mIndicator.draw(canvas);
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec,
			final int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (mIsVertical)
		{
			setMeasuredDimension(mIndicator.getIntrinsicWidth(),
					getMeasuredHeight());
		} else
		{
			setMeasuredDimension(getMeasuredWidth(),
					mIndicator.getIntrinsicHeight());
		}
	}

	public float getMin()
	{
		return mMin;
	}

	public float getMax()
	{
		return mMax;
	}

	public float getPosition()
	{
		return mPosition;
	}

	public void setPosition(float position)
	{
		position = within(position, mMin, mMax);
		if (position != mPosition)
		{
			mPosition = position;
			invalidate();
			if (mPositionListener != null)
			{
				mPositionListener.onPositionChange(mPosition);
			}
		}
	}

	private static float within(float position, final float min, final float max)
	{
		if (position < min)
		{
			position = min;
		}
		if (position > max)
		{
			position = max;
		}
		return position;
	}

	public void setMinMax(final float min, final float max)
	{
		if ((min != mMin) || (max != mMax))
		{
			if (min > max)
			{
				throw new IllegalArgumentException(
						"setMinMax: min must be smaller than max.");
			}
			mMin = min;
			mMax = max;
			setPosition(mPosition);
			invalidate();
		}
	}

	public void setPositionListener(final CustomSliderPositionListener listener)
	{
		mPositionListener = listener;
	}
}
