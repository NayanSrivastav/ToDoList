package adapter;

import com.nayan.todolist.ArchiveTaskFragment;
import com.nayan.todolist.PlannedTaskFragment;
import com.nayan.todolist.CurrentTaskFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerTestAdapter extends FragmentPagerAdapter {
	private final int numberOfPages=3;
	public PagerTestAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new CurrentTaskFragment();
		case 1:
			return new ArchiveTaskFragment();
		case 2:
			return new PlannedTaskFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return numberOfPages;
	}

}
