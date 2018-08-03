/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

/**
 * Class:DemoStringUtils.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-07
 *
 */
public class DemoStringUtils extends StringUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 验证内容长度
	 * 
	 * @param content
	 * @param vlength
	 * @return Boolean
	 */
	public static Boolean validateLength(String content, Integer vlength) {

		if (StringUtils.isNotBlank(content) && vlength == content.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 去除字串中的填充字符
	 * 
	 * @param str 字串
	 * @param filling 填充字符
	 * @param leftJustified true：字串左对齐；false：字串右对齐
	 * @return 去除填充字符后的字串
	 */
	public static String trimFilling(String str, char filling, boolean leftJustified) {
		if (str == null || str.length() == 0)
			return str;
		if (leftJustified) {
			int pos = str.length() - 1;
			for (; pos >= 0; pos--) {
				if (str.charAt(pos) != filling)
					break;
			}
			return str.substring(0, pos + 1);
		}
		int pos = 0;
		for (; pos < str.length(); pos++) {
			if (str.charAt(pos) != filling)
				break;
		}
		return str.substring(pos);
	}

	/**
	 * 生成16位唯一标识
	 * 
	 * @autor longgh
	 * @Date 2012-8-3下午03:21:04
	 * @return
	 */
	public static String generateUniqueString() {
		String uniqueKey = null;
		String str = java.util.UUID.randomUUID().toString();
		uniqueKey = System.currentTimeMillis() + "" + str.substring(0, str.length() - 33);
		return uniqueKey;
	}

	/**
	 * 去除HTML字串中的控制字符及不可视字符
	 * 
	 * @param str HTML字串
	 * @return 返回的字串
	 */
	public static String escapeHTML(String str) {
		int length = str.length();
		int newLength = length;
		boolean someCharacterEscaped = false;
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32)
				switch (c) {
				case 11:
				default:
					newLength--;
					someCharacterEscaped = true;
					break;

				case '\t':
				case '\n':
				case '\f':
				case '\r':
					break;
				}
			else
				switch (c) {
				case '"':
					newLength += 5;
					someCharacterEscaped = true;
					break;

				case '&':
				case '\'':
					newLength += 4;
					someCharacterEscaped = true;
					break;

				case '<':
				case '>':
					newLength += 3;
					someCharacterEscaped = true;
					break;
				}
		}
		if (!someCharacterEscaped)
			return str;

		StringBuffer sb = new StringBuffer(newLength);
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32)
				switch (c) {
				case '\t':
				case '\n':
				case '\f':
				case '\r':
					sb.append(c);
					break;
				}
			else
				switch (c) {
				case '"':
					sb.append("&quot;");
					break;

				case '\'':
					sb.append("&apos;");
					break;

				case '&':
					sb.append("&amp;");
					break;

				case '<':
					sb.append("&lt;");
					break;

				case '>':
					sb.append("&gt;");
					break;

				default:
					sb.append(c);
					break;
				}
		}
		return sb.toString();
	}

	/**
	 * 去除SQL字串中的控制字符
	 * 
	 * @param str SQL字串
	 * @return 返回的字串
	 */
	public static String escapeSQL(String str) {
		int length = str.length();
		int newLength = length;
		for (int i = 0; i < length;) {
			char c = str.charAt(i);
			switch (c) {
			case 0:
			case '"':
			case '\'':
			case '\\':
				newLength++;
			default:
				i++;
				break;
			}
		}
		if (length == newLength)
			return str;

		StringBuffer sb = new StringBuffer(newLength);
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\\':
				sb.append("\\\\");
				break;

			case '"':
				sb.append("\\\"");
				break;

			case '\'':
				sb.append("\\'");
				break;

			case 0:
				sb.append("\\0");
				break;

			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 将一个字符串分解成几个段
	 * 
	 * @param str 字符串
	 * @param segLen 每段限长
	 * @param segNum 分解段数
	 * @return 分解后的字串组
	 */
	public static String[] split(String str, int segLen, int segNum) {
		String[] result = new String[segNum];
		if (str == null || str.length() == 0)
			return result;

		byte[] strByte;
		try {
			strByte = str.getBytes("GBK");
		} catch (UnsupportedEncodingException ex) {
			strByte = str.getBytes();
		}
		int pos = 0;
		for (int i = 0; i < segNum; i++) {
			int actLen = ((strByte.length - pos) < segLen) ? (strByte.length - pos) : segLen;
			byte[] b = new byte[actLen];
			System.arraycopy(strByte, pos, b, 0, actLen);
			result[i] = new String(b);
			pos += actLen;
			if (pos >= strByte.length)
				break;
		}
		return result;
	}

	/**
	 * 将一个字符串分解成几个段，每段都能正常转换为IBM935字符集
	 * 
	 * @param str 字符串
	 * @param segLen 每段限长
	 * @param segNum 分解段数
	 * @return 分解后的字串组
	 */
	public static String[] split4Cp935(String str, int segLen, int segNum) {

		String[] result = new String[segNum];

		byte[] strByte;
		try {
			strByte = str.getBytes("GBK");
		} catch (UnsupportedEncodingException ex) {
			strByte = str.getBytes();
		}
		int strLen = strByte.length;
		byte[] tmpByte = new byte[2 * strLen + segLen];

		int head;
		int flag;
		int count;

		int strCount = 0;
		int segCount = 0;
		int lastStrCount = 0;

		for (flag = 0, head = 0, count = 0; strCount < strLen && segCount < segNum; count++) {
			if ((strByte[strCount] & 0x80) != 0) {
				head = head == 1 ? 0 : 1;
				if (flag == 0) {
					tmpByte[count] = (byte) ' ';
					flag = 1;
					count++;
				}

				if ((count == ((count / segLen) + 1) * segLen - 2) && (head == 1)) {
					tmpByte[count] = (byte) ' ';
					tmpByte[count + 1] = (byte) ' ';
					result[segCount] = str.substring(lastStrCount, strCount);
					lastStrCount = strCount;
					segCount++;
					tmpByte[count + 2] = (byte) ' ';
					flag = 1;
					count = count + 3;
				} else if (((segLen * ((count + 1) / segLen) - 1) == count) && (head == 1)) {
					tmpByte[count] = (byte) ' ';
					byte[] tmp = new byte[strCount - lastStrCount];
					System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
					result[segCount] = new String(tmp);
					lastStrCount = strCount;
					segCount++;
					tmpByte[count + 1] = (byte) ' ';
					count = count + 2;
					flag = 1;
				} else if (((segLen * (count / segLen)) == count) && (head == 1)) {
					byte[] tmp = new byte[strCount - lastStrCount];
					System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
					result[segCount] = new String(tmp);
					lastStrCount = strCount;
					segCount++;
					tmpByte[count] = (byte) ' ';
					count++;
					flag = 1;
				}
			} else {
				if (flag == 1) {
					tmpByte[count] = (byte) ' ';
					count++;
					flag = 0;
					if (count == (count / segLen) * segLen) {
						byte[] tmp = new byte[strCount - lastStrCount];
						System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
						result[segCount] = new String(tmp);
						lastStrCount = strCount;
						segCount++;
					} else {
						if ((segLen * ((count + 1) / segLen) - 1) == count) {
							byte[] tmp = new byte[strCount - lastStrCount + 1];
							System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
							result[segCount] = new String(tmp);
							lastStrCount = strCount + 1;
							segCount++;
						}
					}
				} else {
					if ((segLen * ((count + 1) / segLen) - 1) == count) {
						byte[] tmp = new byte[strCount - lastStrCount + 1];
						System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
						result[segCount] = new String(tmp);
						lastStrCount = strCount + 1;
						segCount++;
					}
				}

			}
			tmpByte[count] = strByte[strCount];
			strCount++;
			if (strCount >= strLen && segCount < segNum) {
				byte[] tmp = new byte[strCount - lastStrCount];
				System.arraycopy(strByte, lastStrCount, tmp, 0, tmp.length);
				result[segCount] = new String(tmp);
				lastStrCount = strCount;
				segCount++;
			}

			if ((count + 1) % segLen == 0) {
				flag = 0;
			}
		}

		return result;
	}

	/**
	 * 取得字符串字节长度,使用缺省字符集
	 * 
	 * @param str
	 * @return length
	 */
	public static int getByteLength(String str) {
		return str.getBytes().length;
	}

	/**
	 * 得到以head开头,tail结尾的子串
	 * 
	 * @param reqBuffer String
	 * @return String
	 */
	public static String subString(String buffer, String head, String tail) {
		if (buffer == null || head == null || tail == null)
			return buffer;
		int startNum = buffer.indexOf(head);
		int endNum = buffer.lastIndexOf(tail);

		startNum = startNum >= 0 ? startNum : 0;
		endNum = endNum >= 0 ? endNum + tail.length() : buffer.length();
		return buffer.substring(startNum, endNum);
	}

	/**
	 * 去除字符串中空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String filterSBCCase(String str) {
		char[] ch = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ch.length; i++) {
			if (!Character.isWhitespace(ch[i])) {
				sb.append(String.valueOf(ch[i]));
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串是否全数字（无符号、小数点）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}

	/**
	 * 字符串是否全数字或英文字母（无符号、小数点）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAlphaDigit(String str) {
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c < '0' || c > '9' && c < 'A' || c > 'Z' && c < 'a' || c > 'z')
				return false;
		}
		return true;
	}

	/**
	 * get substring in bytes length
	 * 
	 * @param orgString original string
	 * @param lengthInBytes bytes length
	 * @return substring
	 */
	public static final String subStringInBytes(String orgString, int startPos, int lengthInBytes) {

		if (orgString == null)
			return null;

		byte[] orgBytes;
		try {
			orgBytes = orgString.getBytes("GBK");
		} catch (UnsupportedEncodingException ex) {
			orgBytes = orgString.getBytes();
		}
		if (startPos < 0 || startPos > orgBytes.length)
			return null;
		else if (lengthInBytes < startPos)
			return null;

		byte[] newBytes;
		int newLength = orgBytes.length - startPos;
		if (lengthInBytes < newLength)
			newLength = lengthInBytes;

		newBytes = new byte[newLength];
		System.arraycopy(orgBytes, startPos, newBytes, 0, newLength);

		return new String(newBytes);
	}

	/**
	 * 按千位分割格式格式化数字
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static String parseStringPattern(Object v, int scale) {
		String temp = "###,###,###,###,###,###,###,##0";
		if (scale > 0)
			temp += ".";
		for (int i = 0; i < scale; i++)
			temp += "0";
		DecimalFormat format = new DecimalFormat(temp);
		return format.format(v).toString();
	}

	/**
	 * 转为String[]
	 * 
	 * @param object
	 * @return
	 */
	public static String[] getStringArray(Object object) {
		if (object instanceof String[])
			return (String[]) object;
		if (object instanceof String) {
			String tmpStrs[] = new String[1];
			tmpStrs[0] = (String) object;
			return tmpStrs;
		}
		return null;
	}

	public static String ADEncoding(String str) {
		if (str == null || str.length() == 0 || isDigit(str) && !str.startsWith("99"))
			return str;
		StringBuffer buf = new StringBuffer("99");
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c > '\u00ff')
				buf.append("99");
			else if (c > '~')
				buf.append("98");
			else if (c == '\t')
				buf.append("95");
			else if (c == '\r' || c == '\n')
				buf.append("96");
			else if (c < ' ')
				buf.append("97");
			else if (c >= '0' && c <= '9')
				buf.append('0').append(c);
			else if (c >= 'A' && c <= 'Z')
				buf.append((char) ((c - 'A' + 10) / 10 + '0')).append((char) ((c - 'A' + 10) % 10 + '0'));
			else if (c >= 'a' && c <= 'z')
				buf.append((char) ((c - 'a' + 40) / 10 + '0')).append((char) ((c - 'a' + 40) % 10 + '0'));
			else if (c < '0')
				buf.append((char) ((c - ' ' + 66) / 10 + '0')).append((char) ((c - ' ' + 66) % 10 + '0'));
			else if (c < 'A')
				buf.append((char) ((c - ':' + 82) / 10 + '0')).append((char) ((c - ':' + 82) % 10 + '0'));
			else if (c < 'a')
				buf.append((char) ((c - '[' + 89) / 10 + '0')).append((char) ((c - '[' + 89) % 10 + '0'));
			else
				// if (c >= '{')
				buf.append((char) ((c - '{' + 36) / 10 + '0')).append((char) ((c - '{' + 36) % 10 + '0'));
		}
		return buf.toString();
	}

	public static String ADDecoding(String str) {
		if (str == null || str.length() < 4 || !str.startsWith("99") || (str.length() & 1) == 1)
			return str;
		StringBuffer buf = new StringBuffer();
		for (int i = 2; i < str.length(); i += 2) {
			int n = (str.charAt(i) - '0') * 10 + str.charAt(i + 1) - '0';
			if (n < 10)
				buf.append(n);
			else if (n < 36)
				buf.append((char) (n - 10 + 'A'));
			else if (n < 40)
				buf.append((char) (n - 36 + '{'));
			else if (n < 66)
				buf.append((char) (n - 40 + 'a'));
			else if (n < 82)
				buf.append((char) (n - 66 + ' '));
			else if (n < 89)
				buf.append((char) (n - 82 + ':'));
			else if (n < 95)
				buf.append((char) (n - 89 + '['));
			else if (n == 95)
				buf.append('\t');
			else if (n == 96)
				buf.append('\n');
			else if (n == 97)
				buf.append('\u00a9');
			else if (n == 98)
				buf.append('\u00c5');
			else
				// if (n == 99)
				buf.append('\u2592');
		}
		return buf.toString();
	}

	/**
	 * 取得类的简单名
	 * 
	 * @param obj
	 * @return 类的简单名
	 */
	public static String getSimpleName(Object obj) {
		if (obj == null)
			return null;
		String name = obj.getClass().getName();
		if (name.toLowerCase().indexOf("$proxy") >= 0) {
			name = obj.toString();
			int idx = name.lastIndexOf("@");
			if (idx > 0)
				name = name.substring(0, idx);
		}
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * 截字串
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str, int length) {
		if (str == null) {
			return "";
		}
		int end = str.length();
		if (length > end) {
			end = length;
		}
		return str.substring(0, end);
	}

	/**
	 * 数字字符串数组转换成整型数组
	 * 
	 * Written Date: 2007-12-1
	 * 
	 * @param strArr 数字字符串数组
	 * @return 整型数组
	 */
	public static Integer[] strArr2IntArr(String[] strArr) {
		if (strArr == null || strArr.length == 0)
			return null;
		Integer[] intArr = new Integer[strArr.length];
		for (int i = 0; i < strArr.length; i++)
			intArr[i] = new Integer(strArr[i]);
		return intArr;
	}

	public static final String removeLeftZero(String str) {
		if (str == null)
			return str;
		String rtnStr = str.trim();

		boolean b = true;
		while (b) {
			if (rtnStr.length() == 1) {
				b = false;
			} else {
				String tmp = rtnStr.substring(0, 1);
				if (tmp.equals("0"))
					rtnStr = rtnStr.substring(1, rtnStr.length());
				else {
					b = false;
				}
			}
		}
		return rtnStr;
	}

	public static final byte[] readFromBytes(byte[] source, int point, int length) {
		if ((point < 0) || (source.length <= point)) {
			return null;
		}
		if ((length <= 0) || (source.length < point + length)) {
			return null;
		}
		byte[] result = new byte[length];
		System.arraycopy(source, point, result, 0, length);
		return result;
	}

	public static final boolean isAmtNumber(String amt, int dotNum) {
		if (amt == null)
			return false;
		if ((amt != null) && (!"".equals(amt.trim()))) {
			if (dotNum <= 0) {
				return amt.matches("^[0-9]*$");
			}
			return amt.matches("^[+-]?\\d*\\.?\\d{" + dotNum + "}$");
		}
		return false;
	}

	public static final String charFill(String src, char fillChar, int fillType, int removeType, int totallength) {
		if (src == null)
			src = "";
		int j = totallength - src.length();
		if (j == 0)
			return src;
		if (j > 0) {
			if (fillType == 1) {
				char[] prefix = new char[j];
				Arrays.fill(prefix, fillChar);
				return new String(prefix) + src;
			}
			if (fillType == 2) {
				char[] prefix = new char[j];
				Arrays.fill(prefix, fillChar);
				return src + new String(prefix);
			}
		} else if (j < 0) {
			if (removeType == 3)
				return src.substring(-j, src.length());
			if (removeType == 4)
				return src.substring(0, totallength);
		}
		return null;
	}

	/**
	 * 16进制字符串转换为byte[]
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase().replace(" ", "");
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c 字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c) throws UnsupportedEncodingException {

		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		return String.valueOf(c).getBytes("utf-8").length > 1;
	}

	/**
	 * 计算当前String字符串所占的总Byte长度
	 * 
	 * @param args 要截取的字符串
	 * @return 返回值int型，字符串所占的字节长度，如果args为空或者“”则返回0
	 * @throws UnsupportedEncodingException
	 */
	public static int getStringByteLenths(String args) throws UnsupportedEncodingException {
		return args != null && args != "" ? args.getBytes("utf-8").length : 0;
	}

	/**
	 * 获取与字符串每一个char对应的字节长度数组
	 * 
	 * @param args 要计算的目标字符串
	 * @return int[] 数组类型，返回与字符串每一个char对应的字节长度数组
	 * @throws UnsupportedEncodingException
	 */
	public static int[] getByteLenArrays(String args) throws UnsupportedEncodingException {
		char[] strlen = args.toCharArray();
		int[] charlen = new int[strlen.length];
		for (int i = 0; i < strlen.length; i++) {
			charlen[i] = String.valueOf(strlen[i]).getBytes("utf-8").length;
		}
		return charlen;
	}

	/**
	 * 按字节截取字符串 ，指定截取起始字节位置与截取字节长度
	 * 
	 * @param orignal 要截取的字符串
	 * @param offset 截取Byte长度；
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
	 */
	public static String substringByte(String orignal, int start, int count) {

		// 如果目标字符串为空，则直接返回，不进入截取逻辑；
		if (orignal == null || "".equals(orignal)) {
			return orignal;
		}

		// 截取Byte长度必须>0
		if (count <= 0) {
			return orignal;
		}
		// 截取的起始字节数必须比
		if (start < 0) {
			start = 0;
		}
		// 目标char Pull buff缓存区间；
		StringBuffer buff = new StringBuffer();

		try {

			// 截取字节起始字节位置大于目标String的Byte的length则返回空值
			if (start >= getStringByteLenths(orignal)) {
				return null;
			}
			// int[] arrlen=getByteLenArrays(orignal);
			int len = 0;
			char c;

			// 遍历String的每一个Char字符，计算当前总长度
			// 如果到当前Char的的字节长度大于要截取的字符总长度，则跳出循环返回截取的字符串。
			for (int i = 0; i < orignal.toCharArray().length; i++) {

				c = orignal.charAt(i);

				// 当起始位置为0时候
				if (start == 0) {

					len += String.valueOf(c).getBytes("utf-8").length;
					if (len <= count) {
						buff.append(c);
					} else {
						break;
					}
				} else {

					// 截取字符串从非0位置开始
					len += String.valueOf(c).getBytes("utf-8").length;
					if (len >= start && len <= start + count) {
						buff.append(c);
					}
					if (len > start + count) {
						break;
					}
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 返回最终截取的字符结果;
		// 创建String对象，传入目标char Buff对象
		return new String(buff);
	}

	/**
	 * 截取指定长度字符串
	 * 
	 * @param orignal 要截取的目标字符串
	 * @param count 指定截取长度
	 * @return 返回截取后的字符串
	 */
	public static String substringByte(String orignal, int count) {
		return substringByte(orignal, 0, count);
	}
}
