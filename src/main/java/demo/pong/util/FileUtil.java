package demo.pong.util;


import java.io.*;

public class FileUtil {

    public static String getFileContent(String fileStr) {
        StringBuffer content = new StringBuffer("");
        try {
            File file = new File(fileStr);
            try (FileInputStream in = new FileInputStream(file)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = br.readLine()) != null) {
                    content.append(line);
                }
            }
        } catch (Exception e) {
        }
        return content.toString();
    }
}
