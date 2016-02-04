package com.ewing.busi.resource.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ewing.busi.resource.dto.ResParamGroupComparatorUtil.ResParamComparator;
import com.ewing.busi.resource.dto.ResParamGroupComparatorUtil.ResParamGroupComparator;
import com.ewing.busi.resource.model.WebResourceSpec;

/**
 * 资源规则排序
 * 
 * @author tanson lam
 * @creation 2016年1月5日
 */
public class ResSpecComparatorUtil {

	/**
	 * 排序
	 * 
	 * @param groupList
	 */
	public static void sortResGroupList(List<WebResourceSpecGroup> groupList) {
		ResSpecGroupComparator groupComparator = new ResSpecGroupComparator();
		ResSpecComparator resSpecComparator = new ResSpecComparator();
		Collections.sort(groupList, groupComparator);
		for (WebResourceSpecGroup group : groupList) {
			Collections.sort(group.getChildParamlist(), resSpecComparator);
		}
	}

	static public class ResSpecGroupComparator implements
			Comparator<WebResourceSpecGroup> {

		@Override
		public int compare(WebResourceSpecGroup o1, WebResourceSpecGroup o2) {

			int diff = o1.getRootWebResourceSpec().getRank()
					- o2.getRootWebResourceSpec().getRank();
			if (diff > 0)
				return 1;
			if (diff < 0)
				return -1;
			else
				return 0;
		}

	}

	static public class ResSpecComparator implements
			Comparator<WebResourceSpec> {

		@Override
		public int compare(WebResourceSpec o1, WebResourceSpec o2) {

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
