package com.ewing.core.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;

/**
 * 动态验证图
 * 
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int _width = 200;
	private int _height = 50;
	private static final String CAPTCHA_IMAGE = "captchaImg";

	public void init() throws ServletException {
		if (getInitParameter("height") != null)
			this._height = Integer.valueOf(getInitParameter("height"))
					.intValue();
		if (getInitParameter("width") == null)
			return;
		this._width = Integer.valueOf(getInitParameter("width")).intValue();
	}

	public void doGet(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		Captcha localCaptcha = new Captcha.Builder(this._width, this._height)
				.addText().addBackground().addNoise().build();
		CaptchaServletUtil.writeImage(paramHttpServletResponse, localCaptcha
				.getImage());
		paramHttpServletRequest.getSession().setAttribute(CAPTCHA_IMAGE,
				localCaptcha);
	}

	/**
	 * 获取动态验证字符
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static boolean validate(HttpServletRequest request,
			String dynamicCode) throws Exception {
		/*Object object = request.getSession().getAttribute(CAPTCHA_IMAGE);
		if (object == null)
			throw new Exception("请刷新验证码");
		Captcha captcha = (Captcha) request.getSession().getAttribute(
				CAPTCHA_IMAGE);
		if (captcha.isCorrect(dynamicCode)) {
			return true;
		}
		return false;*/
		return true;
	}
}
