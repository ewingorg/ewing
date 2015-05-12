package com.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class StringUtil extends StringUtils {
	public static final Logger log = Logger.getLogger(StringUtil.class);
	public static final String TA_LEFT = "left";
	public static final String TA_RIGHT = "right";

	public static String iso2Utf8(String str)
			throws UnsupportedEncodingException {
		if (str == null || str.trim().length() == 0)
			return str;
		return new String(str.getBytes("iso8859-1"), "UTF-8");
	}

	public static String toStringAlign(String str, int len, String align) {
		String AlignText = new String(str);
		if (AlignText.length() < len) {
			int Count = len - AlignText.length();
			while (Count > 0) {
				if (equalsIgnoreCase(align, "left"))
					AlignText = AlignText + " ";
				else
					AlignText = " " + AlignText;
				--Count;
			}
		}
		return AlignText;
	}

	public static String toStringAlign(int iVal, int len, String align) {
		return toStringAlign(String.valueOf(iVal), len, align);
	}

	public static String toStringAlign(long lVal, int len, String align) {
		return toStringAlign(String.valueOf(lVal), len, align);
	}

	public static String remove(String str, char ch) {
		String temp = "";
		int Index = 0;
		while (Index < str.length()) {
			if (str.charAt(Index) != ch)
				temp = temp + String.valueOf(str.charAt(Index));
			++Index;
		}
		return temp;
	}

	public static String remove(String str, String RevStr) {
		return replace(str, RevStr, "");
	}

	public static String fullStringAlgin(String str, int len, String align,
			char full) {
		String temp = str;
		if (temp.length() < len) {
			int nCount = len - temp.getBytes().length;
			String fullstr = String.valueOf(full);
			while (nCount > 0) {
				if (equalsIgnoreCase(align, "left"))
					temp = temp + fullstr;
				else
					temp = fullstr + temp;
				--nCount;
			}
		}
		return temp;
	}

	public static boolean containsIgnoreCase(String str1, String[] str2) {
		boolean aEnable = false;
		for (int index = 0; index < str2.length; ++index)
			aEnable |= equalsIgnoreCase(str1, str2[index]);
		return aEnable;
	}

	public static boolean contains(String str1, String[] str2) {
		boolean aEnable = false;
		for (int index = 0; index < str2.length; ++index)
			aEnable |= equals(str1, str2[index]);
		return aEnable;
	}

	public static boolean endsWith(String str1, String str2) {
		if ((str1 == null) || (str2 == null))
			return false;
		if (str1.length() < str2.length())
			return false;
		for (int index = 0; index < str2.length(); ++index) {
			if (str1.charAt(str1.length() - index - 1) != str2.charAt(str2
					.length() - 1 - index))
				return false;
		}
		return true;
	}

	public static boolean endsIgnoreCaseWith(String str1, String str2) {
		if ((str1 == null) || (str2 == null))
			return false;
		if (str1.length() < str2.length())
			return false;
		return endsWith(str1.toUpperCase(), str2.toUpperCase());
	}

	public static boolean startWith(String str1, String str2) {
		if ((str1 == null) || (str2 == null))
			return false;
		if (str1.length() < str2.length())
			return false;
		for (int Index = 0; Index < str2.length(); ++Index)
			if (str1.charAt(Index) != str2.charAt(Index))
				return false;
		return true;
	}

	public static boolean startIgnoreCaseWith(String str1, String str2) {
		if ((str1 == null) || (str2 == null))
			return false;
		if (str1.length() < str2.length())
			return false;
		return startWith(str1.toUpperCase(), str2.toUpperCase());
	}

	public static boolean containsIgnoreCase(String lpBuffer,
			String containsbuff) {
		boolean RetCode = false;
		if ((containsbuff == null) || (containsbuff.length() <= 0))
			return true;
		if ((lpBuffer == null) || (lpBuffer.length() <= 0)
				|| (lpBuffer.length() < containsbuff.length()))
			return false;
		int Index = 0;

		while (Index < lpBuffer.length()) {
			String temp = mid(lpBuffer, Index, containsbuff.length());
			if (equalsIgnoreCase(temp, containsbuff) == true)
				return true;
			++Index;
		}
		return RetCode;
	}

	public static boolean containsIgnoreBlank(String lpBuffer,
			String containsbuff) {
		lpBuffer = remove(lpBuffer, ' ');
		containsbuff = remove(containsbuff, ' ');
		return contains(lpBuffer, containsbuff);
	}

	public static int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}

	public static int compareIgnoreCase(String str1, String str2) {
		return str1.compareToIgnoreCase(str2);
	}

	public static String[] split(String str, int length) {
		String[] retn = null;
		int nCount = str.length() / length + 1;
		retn = new String[nCount];
		for (int i = 0; i < nCount; ++i)
			retn[i] = substring(str, length * i, length * (i + 1));
		return retn;
	}

	public static String[] splitNoIgnoreBlank(String str, String splitChar) {
		List ElementLst = new ArrayList();
		int Index = 0;
		int Index1 = -splitChar.length();
		while (Index1 < str.length()) {
			Index = Index1 + splitChar.length();
			Index1 = indexOf(str, splitChar, Index);
			if (Index1 < 0)
				Index1 = str.length();
			ElementLst.add(substring(str, Index, Index1));
		}
		return ((String[]) (String[]) ElementLst.toArray(new String[0]));
	}

	public static String[][] splitExpr(String aITaskExpr) {
		List aVector = new Vector();
		if ((!(isBlank(aITaskExpr))) && (contains(aITaskExpr, "="))) {
			String[] aITaskExprL = split(aITaskExpr, ";");
			for (int index = 0; index < aITaskExprL.length; ++index) {
				String[] temp = split(aITaskExprL[index], "=");
				if ((temp != null) && (temp.length > 0)) {
					aVector.add(new String[] { temp[0],
							(temp.length > 1) ? temp[1] : "" });
				}
			}
		}
		return ((String[][]) (String[][]) aVector.toArray(new String[0][]));
	}

	public static String splitExpr(String aRefeValue, String aITaskExpr) {
		String aValue = aITaskExpr;
		String[][] aITaskExprL = splitExpr(aITaskExpr);
		if ((aITaskExprL != null) && (aITaskExprL.length > 0)) {
			aValue = null;
			for (int index = 0; index < aITaskExprL.length; ++index) {
				if (equals(aITaskExprL[index][0], aRefeValue)) {
					aValue = aITaskExprL[index][1];
					break;
				}
			}
		}
		return aValue;
	}

	public static void splitExpr(String aITaskExpr, Map aITaskExprList) {
		String[][] aITaskExprL = splitExpr(aITaskExpr);
		if ((aITaskExprL != null) && (aITaskExprL.length > 0))
			for (int index = 0; index < aITaskExprL.length; ++index)
				aITaskExprList
						.put(aITaskExprL[index][0], aITaskExprL[index][1]);
	}

	public static String replace(String str, int pos, char ch) throws Exception {
		if ((str == null) || (str.getBytes().length <= pos))
			throw new ArrayIndexOutOfBoundsException();
		byte[] SrcByte = str.getBytes();
		SrcByte[pos] = (byte) ch;
		return new String(SrcByte);
	}

	public static String DoEncrypt(String str) {
		StringBuffer enStrBuff = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			int tmpch = str.charAt(i);
			tmpch ^= 1;
			tmpch ^= 10;
			enStrBuff.append(Integer.toHexString(tmpch));
		}
		return enStrBuff.toString().toUpperCase();
	}

	public static String DoDecrypt(String str) {
		String deStr = str.toLowerCase();
		StringBuffer deStrBuff = new StringBuffer();
		for (int i = 0; i < deStr.length(); i += 2) {
			String subStr = deStr.substring(i, i + 2);
			int tmpch = Integer.parseInt(subStr, 16);
			tmpch ^= 1;
			tmpch ^= 10;
			deStrBuff.append((char) tmpch);
		}
		return deStrBuff.toString();
	}

	public static final boolean isSame(String str1, String str2) {
		if ((isBlank(str1)) && (isBlank(str2))) {
			return true;
		}
		return ((!(isBlank(str1))) && (!(isBlank(str2))) && (str1.equals(str2)));
	}

	public static final boolean isNull(String str) {
		return ((str == null) || (str.trim().equals("")));
	}

	public static final String DoEnginVariable(String aVariableValue) {
		if (aVariableValue == null)
			return null;
		return "$" + aVariableValue;
	}

	public static final String wildcardRight(String aPackage, String aMatch) {
		String[] aWildCard = split(aPackage, aMatch);
		return aWildCard[(aWildCard.length - 1)];
	}

	public static final String wildcardLeft(String aPackage, String aMatch) {
		StringBuffer aWildCard = new StringBuffer();
		String[] aWildMatch = split(aPackage, aMatch);
		for (int index = 0; index < aWildMatch.length - 1; ++index) {
			aWildCard.append(aWildMatch[index]);
			if (index >= aWildMatch.length - 2)
				continue;
			aWildCard.append(aMatch);
		}
		return aWildCard.toString();
	}

	public static final StringBuffer concatBinary(byte[][] aBinary) {
		StringBuffer aStringbin = new StringBuffer();
		for (int index = 0; index < aBinary.length; ++index) {
			aStringbin.append(new String(aBinary[index])).append(
					(index != aBinary.length - 1) ? "\n" : "");
		}
		return aStringbin;
	}

	public static final String trimRight(String str, int len) {
		if ((str == null) || (str.length() < len) || (len < 0))
			return str;
		return substring(str, str.length() - len);
	}

	public static final String trimLeft(String str, int len) {
		if ((str == null) || (str.length() < len) || (len < 0))
			return str;
		return substring(str, 0, len);
	}

	public static final String DoThrowable(Throwable t, int aVM_MAX_BYTE) {
		String aThrowable = null;
		try {
			Throwable tt = t;
			if (tt.getCause() != null)
				tt = tt.getCause();
			StringWriter aWrite = new StringWriter();
			PrintWriter Out = new PrintWriter(aWrite);
			tt.printStackTrace(Out);
			Out.flush();
			Out.close();
			Out = null;
			aThrowable = aWrite.toString();
			aWrite.close();
			aWrite = null;
			if (aThrowable.getBytes().length > aVM_MAX_BYTE)
				aThrowable = substring(aThrowable, 0, aVM_MAX_BYTE);
		} catch (Throwable e) {
		}
		return aThrowable;
	}

}