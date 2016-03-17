package com.cn.util;

public class StringBuilderEx {

	private StringBuilder builder;

	public StringBuilderEx() {
		builder = new StringBuilder();
	}

	public StringBuilderEx append(String str) {
		if (str != null) {
			builder.append(str);
		}
		return this;
	}

	public StringBuilderEx append(Object obj) {
		if (obj != null) {
			builder.append(obj.toString());
		}
		return this;
	}

	public String toString() {
		return builder.toString();
	}

	public StringBuilderEx clear() {
		builder.delete(0, builder.length());
		return this;
	}

	public StringBuilderEx replace(int start, int end, String str) {
		if (str != null) {
			builder.replace(start, end, str);
		}
		return this;

	}

	public int length() {
		return builder.length();
	}

	public StringBuilderEx deleteSubStringReverse(String str) {
		int index = builder.lastIndexOf(str);
		if (index != -1) {
			builder.delete(index, index + str.length());
		}

		return this;
	}

	public StringBuilderEx delete(int start, int end) {
		builder.delete(start, end);
		return this;


	}

}
