package com.sky.xhl;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/*<--******************************************************************************************-->*/		

public class FileOperation {

    static Activity activity;
    
    public static void 写入(String Files, String content) {
        try {
            FileWriter utf = new FileWriter(Files);
            utf.write(content);
            utf.close();
        } catch (IOException ignored) {}
    }

	/*<--******************************************************************************************-->*/		


	//创建文件
	public static void 创建文件(String path) {
        //新建一个File类型的成员变量，传入文件名路径。
		File mFile = new File(path);
        //判断文件是否存在，存在就删除
        if (mFile.exists()) {
            mFile.delete();
        }
        try {
			//创建文件
			mFile.createNewFile();
			//给一个吐司提示，显示创建成功

		} catch (IOException e) {
			e.printStackTrace();
        }


    }
	/*<--******************************************************************************************-->*/				
	//创建文件夹
    public static void 创建文件夹(String path) {
        //新建一个File，传入文件夹目录
        File file = new File(path);
        //判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }

    }

    public static boolean deleteFile(String filename) {
        return new File(filename).delete();
    }

    public static boolean 删除文件(String folder) {
        if (folder == null || folder.length() == 0 || folder.trim().length() == 0) {
            return true;
        }
        File file = new File(folder);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static boolean 文件是否存在(String strFile) {  
        File wj=new File(strFile);  
        if (!wj.exists()) {         
            return false;
        } else {
            return true;
        }
    } 


	/*<--******************************************************************************************-->*/		

    /*
     * 写入文件内容
     */
    public static boolean 写入文件(String path, String txt) {
        byte[] sourceByte = txt.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(path); 
                if (!file.exists()) {   
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.close();  
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


	/*<--******************************************************************************************-->*/		    


    /*
     * 读取文件内容
     */
    public static String 读取文件(String path) {
        String str = "";
        try {
            File urlFile = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String mimeTypeLine = null;
            while ((mimeTypeLine = br.readLine()) != null) {
                str = str + mimeTypeLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String 读取指定行(String fileName, int readLine) throws IOException{
        String line = null;//读取每行的内容
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                if (i == readLine) {
                    br.close();
                    return line;
                }
            }
            br.close();
        } catch (NoSuchFileException e) {
            // 处理文件不存在异常
            System.out.println("文件不存在：" + fileName);
        } catch (IOException e) {
            // 处理IO异常
            System.out.println("读取文件时发生IO异常：" + e.getMessage());
        }
        return line;
    }

    public static void 重命名文件(String 旧名字,String 新名字) {
        // 创建File对象
        File oldFile = new File(旧名字);
        File newFile = new File(新名字);
        // 重命名文件
        boolean renamed = oldFile.renameTo(newFile);
    }

    public static void deleteContents(File folder) {//清空文件夹里内容
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteContents(file);  // 递归删除子文件夹内容
                    } else {
                        file.delete();  // 删除文件
                    }
                }
            }
        }
    }


    public static void uploadFile(String filePath, String url) {
        File file = new File(filePath);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody =
                new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file",file.getName(),RequestBody.create(MediaType.parse("application/octet-stream"), file))
                        .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // 处理请求失败的情况
                System.out.println("请求失败: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // 处理请求成功的情况
                    System.out.println("响应内容: " + responseBody);
                } else {
                    // 处理请求失败的情况
                    System.out.println("请求失败: " + response.code());
                }
            }
        });
    }

    public static String UrlPost(String ur, String byteString) {
        String str="获取失败";
        try {
            URL url=new URL(ur);
            HttpURLConnection HttpURLConnection=(HttpURLConnection) url.openConnection();
            HttpURLConnection.setReadTimeout(9000);
            HttpURLConnection.setRequestMethod("POST");
            OutputStream outputStream = HttpURLConnection.getOutputStream();
            outputStream.write(byteString.getBytes());
            BufferedReader BufferedReader=new BufferedReader(new InputStreamReader(HttpURLConnection.getInputStream()));
            String String="";
            StringBuffer StringBuffer=new StringBuffer();
            while ((String = BufferedReader.readLine()) != null) {
                StringBuffer.append(String);
            }
            str = StringBuffer.toString();
        } catch (IOException e) {}
        return str;
    }

    public static String getUrlContent(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                return content.toString();
            } else {
                System.out.println("请求失败，错误代码: " + connection.getResponseCode());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> parseFileList(String content) {
        try {
            // 解析文件名列表
            String[] fileArray = content.replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\"", "")
                    .split(",");
            List<String> fileList = Arrays.asList(fileArray);
            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /*<--******************************************************************************************-->*/


}

