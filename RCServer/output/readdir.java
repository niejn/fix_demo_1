package output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class readdir {

    public static boolean deleteFile(String delpath) throws FileNotFoundException, IOException {

        //PropertyConfigurator.configure("log4j.properties");
        //Logger logger = Logger.getLogger(ReadDir.class);

        try {

            File file = new File(delpath);
            if (!file.isDirectory()) {
                System.out.println(file + "not found");
                file.delete();
            } else if (file.isDirectory()) {
                System.out.println("�found");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "" + filelist[i]);
                    if (!delfile.isDirectory()) {
                            System.out.println("path=" + delfile.getPath());
                            System.out.println("absolutepath=" + delfile.getAbsolutePath());
                            System.out.println("name=" + delfile.getName());
                            delfile.delete();
                            System.out.println("delete");
                    } else if (delfile.isDirectory()) {
                            deleteFile(delpath + "" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (FileNotFoundException e) {
            System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }


    public static List<String> txtFiles = new ArrayList<String>();;
    
    public List<String> readFile(String filepath) throws FileNotFoundException,
                    IOException {

        // PropertyConfigurator.configure("log4j.properties");
        //Logger logger = Logger.getLogger(ReadDir.class);
        List<String> tempList = null;
        File file = null;

        try {
            file = new File(filepath);
            if (file.isDirectory()) {
                tempList = new ArrayList<String>();
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "//" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        String pathstring = readfile.getPath();
                        if (isTxtFile(pathstring)) {
                                tempList.add(pathstring);
                                txtFiles.add(pathstring);
                        }
                    } else if (readfile.isDirectory()) {
                        readFile(filepath + "//" + filelist[i]);
                    }
                }
            }
            if (tempList.isEmpty()) {
                    // just record whether this folder has txt files directly.
                    //logger.info("error:" + file.getPath() + "  Ŀ¼��û��txt�����ļ�������");
            }
            return txtFiles;
        } catch (FileNotFoundException e) {
            //logger.info("readfile() Exception" + e.getMessage());
        }
        return null;
    }

    /**
     * @param pathstring
     * @return
     */
    private boolean isTxtFile(String pathstring) 
    {

            return pathstring.substring(pathstring.length() - 4, pathstring.length()).equals(".txt");
    }

    public static void main(String[] args) {
            try {
                    List<String> excelfile = new ArrayList<String>();
                    readdir readdir = new readdir();
                    excelfile = readdir.readFile("D:\\txt");
                    // readdir.deleteFile("D:/file");
                    System.out.println("11111111");
                    System.out.println(excelfile);
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
            System.out.println("ok");
    }
}
