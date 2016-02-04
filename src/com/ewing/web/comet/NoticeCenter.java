package com.ewing.web.comet;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import org.apache.log4j.Logger;

public class NoticeCenter {
	private static Logger logger = Logger.getLogger(NoticeCenter.class);

	static public class NoticeEventPullSource extends EventPullSource {

	 

		@Override
		protected long getSleepTime() {
			return 3000;
		}

		@Override
		protected Event pullEvent() {
			Event event = Event.createDataEvent("/notice");
			// 公告通知
			 

			return event;
		}
	}

}
