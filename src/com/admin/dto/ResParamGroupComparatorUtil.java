package com.admin.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.admin.model.WebResourceParam;

/**
 * 参数组排序
 * 
 * @author tanson lam
 * @creation 2015年12月24日
 */
public class ResParamGroupComparatorUtil {
	/**
	 * 排序
	 * @param groupList
	 */
	public static void sortResGroupList(List<WebResourceParamGroup> groupList) {
		ResParamGroupComparator groupComparator = new ResParamGroupComparator();
		Collections.sort(groupList, groupComparator);
		for (WebResourceParamGroup group : groupList) {
			ResParamComparator paramComparator = new ResParamComparator();
			Collections.sort(group.getChildParamlist(), paramComparator);
		}
	}

	static public class ResParamGroupComparator implements
			Comparator<WebResourceParamGroup> {

		@Override
		public int compare(WebResourceParamGroup o1, WebResourceParamGroup o2) {

			int diff = o1.getRootResourceParam().getRank()
					- o2.getRootResourceParam().getRank();
			if (diff > 0)
				return 1;
			if (diff < 0)
				return -1;
			else
				return 0;
		}

	}

	static public class ResParamComparator implements
			Comparator<WebResourceParam> {

		@Override
		public int compare(WebResourceParam o1, WebResourceParam o2) {

			int diff = o1.getRank() - o2.getRank();
			if (diff > 0)
				return 1;
			if (diff < 0)
				return -1;
			else
				return 0;
		}

	}
}
