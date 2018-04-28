package aisino.reportform.util.base.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportExcel<T> {


    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
            OutputStream out) {
        exportExcel("导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String title, String[] headers,
            Collection<T> dataset, OutputStream out) {
        exportExcel(title, headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
            OutputStream out, String pattern) {
        exportExcel("导出EXCEL文档", headers, dataset, out, pattern);
    }

    /**
     * 这是一个通用的方法
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    public void exportExcel(String title, String[] headers,
            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(CellStyle.BORDER_THIN);
        style2.setBorderLeft(CellStyle.BORDER_THIN);
        style2.setBorderRight(CellStyle.BORDER_THIN);
        style2.setBorderTop(CellStyle.BORDER_THIN);
        style2.setAlignment(CellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        int sheetCount = 1000;
        if (dataset.size() > sheetCount) {
            Iterator<T> it = dataset.iterator();
            for (int i = 0; i <= 4; i++) {
                int index = 0;
                List<T> list = new ArrayList<T>();
                while (it.hasNext()) {
                    index++;
                    if (index < sheetCount) {
                        list.add(it.next());
                    } else {
                        break;
                    }
                }
                generateSheet(list, style, style2, workbook, pattern, headers,
                        title + "_" + (i + 1));
            }
        } else {
            generateSheet(dataset, style, style2, workbook, pattern, headers,
                    title);
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateSheet(Collection<T> dataset, HSSFCellStyle style,
            HSSFCellStyle style2, HSSFWorkbook workbook, String pattern,
            String[] headers, String title) {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("author");

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.toString().contains("static")) {
                    continue;
                }
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "男";
                        if (!bValue) {
                            textValue = "女";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, Workbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value == null) {
                            value = "";
                        }
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLACK.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
    }

     public static void main(String[] args) {
     // 测试学生
     ExportExcel<Student> ex = new ExportExcel<Student>();
     String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
     List<Student> dataset = new ArrayList<Student>();
     dataset.add(new Student(10000001, "张三", 20,  new Date()));
     dataset.add(new Student(20000002, "李四", 24, new Date()));
     dataset.add(new Student(30000003, "王五", 22, new Date()));
     // 测试图书
     ExportExcel<Book> ex2 = new ExportExcel<Book>();
     String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
     "图书出版社", "封面图片" };
     List<Book> dataset2 = new ArrayList<Book>();
     try {
//     BufferedInputStream bis = new BufferedInputStream(
//     new FileInputStream("book.jpg"));
//     byte[] buf = new byte[bis.available()];
//     while ((bis.read(buf)) != -1) {
//     //
//     }
     dataset2.add(new Book("1", "aa", "作者", 32.2, "11", "出版社", "蜂蜜"));
     
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
     java.util.Date date=new java.util.Date();  
     String str=sdf.format(date);  
     OutputStream out = new FileOutputStream("E://a"+str+".xls");
     OutputStream out2 = new FileOutputStream("E://b"+str+".xls");
     ex.exportExcel(headers, dataset, out);
     ex2.exportExcel(headers2, dataset2, out2);
     out.close();
     JOptionPane.showMessageDialog(null, "导出成功!");
     System.out.println("excel导出成功！");
     } catch (FileNotFoundException e) {
     e.printStackTrace();
     } catch (IOException e) {
     e.printStackTrace();
     }
     }
}
