/*
 * Zirco Browser for Android
 * 
 * Copyright (C) 2010 J. Devauchelle and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.cn.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件操作工具类 Utilities for I/O reading and writing.
 */
public class IOUtils {

	private static String APPLICATION_FOLDER;
	private static String DOWNLOAD_FOLDER;



	public boolean isFileExist(String dir, String fileName) {
		String SDCardRoot = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
		File file = new File(SDCardRoot + dir + File.separator + fileName);

		return file.exists();
	}
	
	public static void copyAssetDirToFiles(Context context, String dirname)
			throws IOException {
		File dir = new File(context.getFilesDir() + "/" + dirname);
		dir.mkdir();
		
		AssetManager assetManager = context.getAssets();
		String[] children = assetManager.list(dirname);
		for (String child : children) {
			child = dirname + '/' + child;
			String[] grandChildren = assetManager.list(child);
			if (0 == grandChildren.length)
				copyAssetFileToFiles(context, child);
			else
				copyAssetDirToFiles(context, child);
		}
	}
	
	public static void copyAssetFileToFiles(Context context, String filename)
			throws IOException {
		InputStream is = context.getAssets().open(filename);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		//拷贝到根目录
		String strNewFileName=filename.replace("model/", "");
		File of = new File(context.getFilesDir() + "/" + strNewFileName);
		of.createNewFile();
		FileOutputStream os = new FileOutputStream(of);
		os.write(buffer);
		os.close();
		MyLogger.showLog(filename+" 拷贝成功");

	}

	/**
	 * assets目录下面的文件拷贝到程序数据目录下
	 * @param applicationContext
	 * @param fromRelativePath，如 assets/test/abc.txt,    test/abc.txt
	 * @param toRelaytivePath ,如 data/data/com.cn.pppcar/files+ ,  toRelaytivePath
     */
	public static void copyAssetFile2ContextDir(Context applicationContext,String fromRelativePath,String toRelaytivePath){
		int index = fromRelativePath.lastIndexOf("/");
		String fileName="";
		if(index!=-1){
			fileName=fromRelativePath.substring(index,fromRelativePath.length());
		}else{
			fileName=fromRelativePath;
		}
		AssetManager assetManager =applicationContext.getAssets();
		try {
			InputStream inputStream = assetManager.open(fileName);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();

			String fileDir=applicationContext.getFilesDir() + "/"+toRelaytivePath+"/" + fileName;
			File of = new File(applicationContext.getFilesDir() + "/"+toRelaytivePath);
			of.mkdir();
			File file=new File(fileDir);
			file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(buffer);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
