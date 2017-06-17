package com.example.yuzelli.fluecuringmachine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.utils.AssetsCopyTOSDcard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfomationDetailActivity extends BaseActivity {


    @OnClick(R.id.rl_black)
    public void imgBlack(){
        finish();
    }
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.office)
    WebView view;
    private String nameStr = null;

    private Range range = null;
    private HWPFDocument hwpf = null;

    private String htmlPath;
    private String picturePath;

    private List pictures;

    private TableIterator tableIterator;

    private int presentPicture = 0;

    private int screenWidth;

    private FileOutputStream output;

    private File myFile;

    private Activity activity;

    @Override
    protected int layoutInit() {
        return R.layout.activity_infomation_detail;
    }

    @Override
    protected void binEvent() {
        findViewById(R.id.rl_black).setVisibility(View.VISIBLE);
        activity = this;
        Intent intent = getIntent();
        //WebView加载显示本地html文件
        tvTitle.setText(intent.getStringExtra("title"));

        screenWidth = this.getWindowManager().getDefaultDisplay().getWidth() - 10;

        nameStr = "app.doc";
        String path = "";
//        AssetsCopyTOSDcard assetsCopyTOSDcard=new AssetsCopyTOSDcard(getApplicationContext());
//        assetsCopyTOSDcard.AssetToSD(path,Environment.getExternalStorageDirectory().toString()+"/"+path);
        CopyAssets(this,nameStr,Environment.getExternalStorageDirectory().toString()+"/"+nameStr);
        getRange();

        makeFile();

        readAndWrite();

        // 获取WebView的设置
        WebSettings webSettings = view.getSettings();
        // 将JavaScript设置为可用，这一句话是必须的，不然所做一切都是徒劳的
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        // 给webview添加JavaScript接口
      //  view.addJavascriptInterface(new JsInterface(), "control");
     // view.loadUrl("content://com.android.htmlfileprovider" + htmlPath);
        webSettings.setDefaultTextEncodingName("UTF-8") ;
     view.loadUrl("file://"+htmlPath);
        System.out.println("htmlPath" + htmlPath);

    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, InfomationDetailActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void fillData() {

    }

    public void makeFile() {

        String sdStateString = android.os.Environment.getExternalStorageState();

        if (sdStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
            try {
                File sdFile = android.os.Environment.getExternalStorageDirectory();

                String path = sdFile.getAbsolutePath() + File.separator + "xiao";

                String temp = path + File.separator + "my.html";

                File dirFile = new File(path);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }

                File myFile = new File(path + File.separator + "my.html");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }

                htmlPath = myFile.getAbsolutePath();
            } catch (Exception e) {

            }
        }
    }

    /*用来在sdcard上创建图片*/
    public void makePictureFile() {
        String sdString = android.os.Environment.getExternalStorageState();

        if (sdString.equals(android.os.Environment.MEDIA_MOUNTED)) {
            try {
                File picFile = android.os.Environment.getExternalStorageDirectory();

                String picPath = picFile.getAbsolutePath() + File.separator + "xiao";

                File picDirFile = new File(picPath);

                if (!picDirFile.exists()) {
                    picDirFile.mkdir();
                }
                File pictureFile = new File(picPath + File.separator + presentPicture + ".jpg");

                if (!pictureFile.exists()) {
                    pictureFile.createNewFile();
                }

                picturePath = pictureFile.getAbsolutePath();

            } catch (Exception e) {
                System.out.println("PictureFile Catch Exception");
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /*读取word中的内容写到sdcard上的.html文件中*/
    public void readAndWrite() {

        try {
            myFile = new File(htmlPath);
            output = new FileOutputStream(myFile);
            String head = "<html><body>";
            String tagBegin = "<p>";
            String tagEnd = "</p>";


            output.write(head.getBytes());

            int numParagraphs = range.numParagraphs();

            for (int i = 0; i < numParagraphs; i++) {
                Paragraph p = range.getParagraph(i);

                if (p.isInTable()) {
                    int temp = i;
                    if (tableIterator.hasNext()) {
                        String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
                        String tableEnd = "</table>";
                        String rowBegin = "<tr>";
                        String rowEnd = "</tr>";
                        String colBegin = "<td>";
                        String colEnd = "</td>";

                        Table table = tableIterator.next();

                        output.write(tableBegin.getBytes());

                        int rows = table.numRows();

                        for (int r = 0; r < rows; r++) {
                            output.write(rowBegin.getBytes());
                            TableRow row = table.getRow(r);
                            int cols = row.numCells();
                            int rowNumParagraphs = row.numParagraphs();
                            int colsNumParagraphs = 0;
                            for (int c = 0; c < cols; c++) {
                                output.write(colBegin.getBytes());
                                TableCell cell = row.getCell(c);
                                int max = temp + cell.numParagraphs();
                                colsNumParagraphs = colsNumParagraphs + cell.numParagraphs();
                                for (int cp = temp; cp < max; cp++) {
                                    Paragraph p1 = range.getParagraph(cp);
                                    output.write(tagBegin.getBytes());
                                    writeParagraphContent(p1);
                                    output.write(tagEnd.getBytes());
                                    temp++;
                                }
                                output.write(colEnd.getBytes());
                            }
                            int max1 = temp + rowNumParagraphs;
                            for (int m = temp + colsNumParagraphs; m < max1; m++) {
                                Paragraph p2 = range.getParagraph(m);
                                temp++;
                            }
                            output.write(rowEnd.getBytes());
                        }
                        output.write(tableEnd.getBytes());
                    }
                    i = temp;
                } else {
                    output.write(tagBegin.getBytes());
                    writeParagraphContent(p);
                    output.write(tagEnd.getBytes());
                }
            }

            String end = "</body></html>";
            output.write(end.getBytes());
            output.close();
        } catch (Exception e) {
            System.out.println("readAndWrite Exception");
        }
    }

    /*以段落的形式来往html文件中写内容*/
    public void writeParagraphContent(Paragraph paragraph) {
        Paragraph p = paragraph;
        int pnumCharacterRuns = p.numCharacterRuns();

        for (int j = 0; j < pnumCharacterRuns; j++) {

            CharacterRun run = p.getCharacterRun(j);

            if (run.getPicOffset() == 0 || run.getPicOffset() >= 1000) {
                if (presentPicture < pictures.size()) {
                    writePicture();
                }
            } else {
                try {
                    String text = run.text();
                    if (text.length() >= 2 && pnumCharacterRuns < 2) {
                        output.write(text.getBytes());
                    } else {
                        int size = run.getFontSize();
                        int color = run.getColor();
                        String fontSizeBegin = "<font size=\"" + decideSize(size) + "\">";
                        String fontColorBegin = "<font color=\"" + decideColor(color) + "\">";
                        String fontEnd = "</font>";
                        String boldBegin = "<b>";
                        String boldEnd = "</b>";
                        String islaBegin = "<i>";
                        String islaEnd = "</i>";

                        output.write(fontSizeBegin.getBytes());
                        output.write(fontColorBegin.getBytes());

                        if (run.isBold()) {
                            output.write(boldBegin.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaBegin.getBytes());
                        }

                        output.write(text.getBytes());

                        if (run.isBold()) {
                            output.write(boldEnd.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaEnd.getBytes());
                        }
                        output.write(fontEnd.getBytes());
                        output.write(fontEnd.getBytes());
                    }
                } catch (Exception e) {
                    System.out.println("Write File Exception");
                }
            }
        }
    }

    /*将word中的图片写入到.jpg文件中*/
    public void writePicture() {
        Picture picture = (Picture) pictures.get(presentPicture);

        byte[] pictureBytes = picture.getContent();

        Bitmap bitmap = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);

        makePictureFile();
        presentPicture++;

        File myPicture = new File(picturePath);

        try {

            FileOutputStream outputPicture = new FileOutputStream(myPicture);

            outputPicture.write(pictureBytes);

            outputPicture.close();
        } catch (Exception e) {
            System.out.println("outputPicture Exception");
        }

        String imageString = "<img src=\"" + picturePath + "\"";

        if (bitmap.getWidth() > screenWidth) {
            imageString = imageString + " " + "width=\"" + screenWidth + "\"";
        }
        imageString = imageString + ">";

        try {
            output.write(imageString.getBytes());
        } catch (Exception e) {
            System.out.println("output Exception");
        }
    }

    /*处理word和html字体的转换*/
    public int decideSize(int size) {

        if (size >= 1 && size <= 8) {
            return 1;
        }
        if (size >= 9 && size <= 11) {
            return 2;
        }
        if (size >= 12 && size <= 14) {
            return 3;
        }
        if (size >= 15 && size <= 19) {
            return 4;
        }
        if (size >= 20 && size <= 29) {
            return 5;
        }
        if (size >= 30 && size <= 39) {
            return 6;
        }
        if (size >= 40) {
            return 7;
        }
        return 3;
    }

    /*处理word和html颜色的转换*/
    private String decideColor(int a) {
        int color = a;
        switch (color) {
            case 1:
                return "#000000";
            case 2:
                return "#0000FF";
            case 3:
            case 4:
                return "#00FF00";
            case 5:
            case 6:
                return "#FF0000";
            case 7:
                return "#FFFF00";
            case 8:
                return "#FFFFFF";
            case 9:
                return "#CCCCCC";
            case 10:
            case 11:
                return "#00FF00";
            case 12:
                return "#080808";
            case 13:
            case 14:
                return "#FFFF00";
            case 15:
                return "#CCCCCC";
            case 16:
                return "#080808";
            default:
                return "#000000";
        }
    }

    private void getRange() {
        FileInputStream in = null;
        POIFSFileSystem pfs = null;
        try {
//            AssetManager am = getResources().getAssets();
//            InputStream inputStream = am.open(nameStr);
//            File f = new File(Environment.getExternalStorageDirectory()+"test.doc");
//            String path = f.getAbsolutePath();
//            if (!f.exists()){
//                f.mkdir();//如果不存在文件，将创建
//            }
//            OutputStream os = new FileOutputStream(f);
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            inputStream.close();


            in = new FileInputStream(Environment.getExternalStorageDirectory().toString()+"/"+nameStr);
            //  in = (FileInputStream)inputStream;
            pfs = new POIFSFileSystem(in);
            hwpf = new HWPFDocument(pfs);
        } catch (Exception e) {

        }
        range = hwpf.getRange();

        pictures = hwpf.getPicturesTable().getAllPictures();

        tableIterator = new TableIterator(range);

    }
    public static void CopyAssets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(newPath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    CopyAssets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
