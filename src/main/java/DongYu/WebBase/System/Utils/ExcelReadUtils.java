package DongYu.WebBase.System.Utils;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelReadUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelReadUtils.class);
	
	public static byte[] createExcel(Map<String, List<List<String>>> modelMap) throws IOException {
		
		byte[] returnValue=null;
		if(modelMap!=null){
		
			
			HSSFWorkbook work = new HSSFWorkbook();
			for(Map.Entry<String, List<List<String>>> entry:modelMap.entrySet()){
				List<List<String>> sheetList = entry.getValue();
				//生成的excel中sheet的数目
				int sheetNO = sheetList.size() % 65530 == 0 ? sheetList.size() / 65530 : sheetList.size() / 65530 + 1;
				List<List<String>> tempSheetList = new ArrayList<List<String>>();
				//保存标题栏
				List<String> titleList = sheetList.get(0);
				if(sheetNO == 1){
					createSheetForExcel(work,sheetList,entry.getKey(),sheetNO);
				}else{
					for(int i=0;i<sheetNO;i++){
						if(i == sheetNO-1){
							tempSheetList = new ArrayList<List<String>>(sheetList.subList(i*65530, sheetList.size()));
							tempSheetList.add(0,titleList);
						}else{
							tempSheetList = new ArrayList<List<String>>(sheetList.subList(i*65530, (i+1)*65530));
							tempSheetList.add(0,titleList);
						}
						createSheetForExcel(work,tempSheetList,entry.getKey(),i+1);
					}
				}
			}
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
			    work.write(bos);
			} finally {
			    bos.close();
			}
			returnValue = bos.toByteArray();
		}
		
		return returnValue;
	}
	
	/**
	 * 给excel文件添加sheet
	 * @param work 目标excel文件
	 * @param sheetList sheet中的数据源
	 */
	public static void createSheetForExcel(HSSFWorkbook work,List<List<String>> sheetList,String title,int i){
		HSSFSheet sheet=work.createSheet(title+"_"+i);
		sheet.setDefaultColumnWidth(15);
		//设置内容样式
		HSSFCellStyle numStyle = work.createCellStyle(); 
		HSSFFont font  = work.createFont();
		font.setFontHeightInPoints((short) 11);// 字号   
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		numStyle.setFont(font);
//		numStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		//设置标题样式
		HSSFCellStyle numStyleHead = work.createCellStyle();
		HSSFFont fontHead  = work.createFont();
		fontHead.setFontHeightInPoints((short) 25);// 字号   
//		fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		numStyleHead.setFont(fontHead);
//		numStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
//		numStyleHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		
		int rowCounter=0;
		 //在sheet里增加合并单元格 
		CellRangeAddress cra =new CellRangeAddress(0, 2, 0, sheetList.get(0).size()-1);        
		sheet.addMergedRegion(cra);
		HSSFRow headerRow=sheet.createRow(rowCounter);
		HSSFCell cellHead = headerRow.createCell(rowCounter);
		cellHead.setCellValue(title);
		cellHead.setCellStyle(numStyleHead);
		rowCounter = 3;
		int columnCounter = 0;
		
		for(List<String> rowList:sheetList){
			HSSFRow row=sheet.createRow(rowCounter);
			int cellCounter=0;
			
			for(String cellValue:rowList){
				
				HSSFCell cell = row.createCell(cellCounter);
				cell.setCellStyle(numStyle);
				try{
				if(cellValue!=null && !cellValue.equals("") && Pattern.matches("^(\\d+)||(\\d+\\.\\d+)$",cellValue)){
					
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cell.setCellValue(Double.parseDouble(cellValue));
				}
				else{
					cell.setCellValue(cellValue);
				}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				cellCounter++;
			}
			rowCounter++;
			columnCounter++;
		}
	}
	
	public static byte[] createExcelForNetWork(Map<String, List<List<String>>> modelMap) throws IOException {
		
		byte[] returnValue=null;
		
		if(modelMap!=null){
			
			HSSFWorkbook work = new HSSFWorkbook();
			for(Map.Entry<String, List<List<String>>> entry:modelMap.entrySet()){
				HSSFSheet sheet=work.createSheet(entry.getKey());
				sheet.setDefaultColumnWidth(15);
				//设置内容样式
				HSSFCellStyle numStyle = work.createCellStyle(); 
				HSSFFont font  = work.createFont();
				font.setFontHeightInPoints((short) 11);// 字号   
//				font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
				numStyle.setFont(font);
//				numStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
				//设置标题样式
				HSSFCellStyle numStyleHead = work.createCellStyle();
				HSSFFont fontHead  = work.createFont();
				fontHead.setFontHeightInPoints((short) 25);// 字号   
//				fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
				numStyleHead.setFont(fontHead);
//				numStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
//				numStyleHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
				
				
				List<List<String>> sheetList=entry.getValue();
				
				int rowCounter=0;
//				 //在sheet里增加合并单元格 
//				CellRangeAddress cra =new CellRangeAddress(0, 2, 0, sheetList.get(0).size()-1);        
//				sheet.addMergedRegion(cra);
//				HSSFRow headerRow=sheet.createRow(rowCounter);
//				HSSFCell cellHead = headerRow.createCell(rowCounter);
//				cellHead.setCellValue(entry.getKey());
//				cellHead.setCellStyle(numStyleHead);
//				rowCounter = 3;
				int columnCounter = 0;
				for(List<String> rowList:sheetList){
					HSSFRow row=sheet.createRow(rowCounter);
					int cellCounter=0;
					
					for(String cellValue:rowList){
						
						HSSFCell cell = row.createCell(cellCounter);
						cell.setCellValue(cellValue);
						cell.setCellStyle(numStyle);
						cellCounter++;
					}
					rowCounter++;
					columnCounter++;
				}
				//处理集中器
				dealSheetGroupRowCcr("+",sheet);
				for(int i = 1; i < 8; i++) {
					dealSheetGroupRowDev("-["+i+"]",sheet,i);
				}
			}
			
			
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
			    work.write(bos);
			} finally {
			    bos.close();
			}
			returnValue = bos.toByteArray();
		}
		
		return returnValue;
	}

	public static void dealSheetGroupRowCcr(String key,HSSFSheet sheet) {
		int count = sheet.getLastRowNum();
		System.out.println("count:"+count);
		int startNum = 0;
		for(int i = 0; i <=count; i++) {
			String devName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			if(devName.startsWith(key) || count == i) {
				if(startNum==0) {
					startNum = i;
				}else {
					if(count == i) {
						sheet.groupRow(startNum+1, i);
					}else {
						sheet.groupRow(startNum+1, i-1);
					}
					startNum = i;
				}
			}
		}
	}
	
	/**
	 * 根据指定颜色索引给HSSFWorkbook创建对应的HSSFCellStyle对象
	 * @param wb 
	 * @param colorIndex 填充颜色索引
	 * @return HSSFCellStyle 指定填充颜色的HSSFCellStyle对象
	 */
	public static HSSFCellStyle getCellFillColor(HSSFWorkbook wb,short colorIndex){
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font  = wb.createFont();
		font.setFontHeightInPoints((short) 11);// 字号   
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		style.setFont(font);
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
	    style.setFillForegroundColor(colorIndex); //填充颜色
		return style;
	}
	
	public static void dealSheetGroupRowDev(String key,HSSFSheet sheet,int min) {
		int count = sheet.getLastRowNum();
		boolean ifDeal = false;
		int startNum = 0;
		for(int i = 0; i <=count; i++) {
			String devName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			
			
			int lev = 99;
			if(devName.contains("-[")){
				lev = Integer.parseInt(devName.substring(2, 3));
			}
			if(ifDeal) {
				if(lev <= min || devName.startsWith("+") || count == i) {
					if(count == i) {
						System.out.println("Start:"+(startNum+1));
						System.out.println("End:"+i);
						if(lev != min)
							sheet.groupRow(startNum+1, i);
						
					}else {
						System.out.println("Start:"+(startNum+1));
						System.out.println("End:"+(i-1));
						sheet.groupRow(startNum+1, i-1);
					}
//					sheet.groupRow(startNum+1, i-1);
					startNum = i;
					ifDeal = false;
				}
			}
			if(devName.startsWith(key)) {
				ifDeal = true;
				startNum = i;
			}
			
		}
	}
	
	/**
	 * @param title excel文档标题
	 * @param subTitle excel文档副标题
	 * @param datas 行数据
	 */
	public static byte[] createExcel(String title,List<String> subTitle,List<List<String>> datas) throws IOException {
		if(title == null || title == "" || subTitle == null || subTitle.size() == 0)
			return null;
		
		HSSFWorkbook work = new HSSFWorkbook();
		HSSFSheet sheet=work.createSheet(title);
		
		//设置内容样式
		HSSFFont font  = ExcelReadUtils.getContentFontStyle(work); 
		HSSFCellStyle numStyle = ExcelReadUtils.getContentCellStyle(work,font);
		
		//设置标题样式
		HSSFFont fontHead  = ExcelReadUtils.getHeadFontStyle(work);
		HSSFCellStyle numStyleHead = ExcelReadUtils.getHeadCellStyle(work,fontHead); 
		
		//写标题
		int rowCounter=0;
		CellRangeAddress cra =new CellRangeAddress(0, 2, 0, subTitle.size()-1);        
		sheet.addMergedRegion(cra);
		
		HSSFRow headerRow=sheet.createRow(rowCounter);
		HSSFCell cellHead = headerRow.createCell(rowCounter);
		cellHead.setCellValue(title);
		cellHead.setCellStyle(numStyleHead);
		
		//写入标题行的内容
		rowCounter = 3;
		HSSFRow row=sheet.createRow(rowCounter);
		for(int i=0;i<subTitle.size();i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(subTitle.get(i));
			cell.setCellStyle(numStyle);
		}
		
		//写入row数据
		rowCounter++;
		if(datas != null){
			for(List<String> oneRowData : datas){
				HSSFRow contentRow = sheet.createRow(rowCounter);
				int cellCounter = 0;
				for(String columnContent : oneRowData){
					HSSFCell cell = contentRow.createCell(cellCounter);
					cell.setCellValue(columnContent);
					cell.setCellStyle(numStyle);	
					cellCounter++;
				}
				rowCounter++;
			}
		}
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
		    work.write(bos);
		} finally {
		    bos.close();
		}
		
		return bos.toByteArray();
	}
	
	/**
	 * 返回标题单元格样式
	 * @param work
	 * @param fontHead
	 */
	private static HSSFCellStyle getHeadCellStyle(HSSFWorkbook work,
			HSSFFont fontHead) {
		HSSFCellStyle numStyleHead = getCellStyle(work,fontHead);
//		numStyleHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		return numStyleHead;
	}
	
	/**
	 * 返回内容单元格样式
	 * @param work
	 * @param font
	 */
	private static HSSFCellStyle getContentCellStyle(HSSFWorkbook work,
			HSSFFont font) {
		return getCellStyle(work,font);
	}
	
	private static HSSFCellStyle getCellStyle(HSSFWorkbook work,
			HSSFFont font) {
		HSSFCellStyle numStyle = work.createCellStyle(); 
		numStyle.setFont(font);
//		numStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		return numStyle;
	}
	
	/**
	 * 返回标题行字体样式
	 * @param work
	 * @return
	 */
	private static HSSFFont getHeadFontStyle(HSSFWorkbook work) {
		return getFontStyle(work,(short) 25,HSSFFont.DEFAULT_CHARSET);
	}

	/**
	 * 返回内容文本字体样式
	 * @param work
	 * @return
	 */
	private static HSSFFont getContentFontStyle(HSSFWorkbook work) {
		return getFontStyle(work,(short) 11,HSSFFont.DEFAULT_CHARSET);
	}
	
	private static HSSFFont getFontStyle(HSSFWorkbook work,short fontSize,short boldweight) {
		HSSFFont font  = work.createFont();
		font.setFontHeightInPoints(fontSize);// 字号   
//		font.setBoldweight(boldweight);// 加粗   
		return font;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }
        
        FileOutputStream out = new FileOutputStream("d:/sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
		
		
	}
	
	/**
	 * 读取Excel第一个Sheet，转成List<List<String>格式
	 * @param ins
	 * @return
	 */
	public static List<List<String>> readExcel(InputStream ins) {
		List<List<String>> records = new ArrayList<List<String>>();
		if (ins != null) {
			HSSFWorkbook work = null;
			try {
				work = new HSSFWorkbook(ins);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			if(work.getNumberOfSheets()>0){
				HSSFSheet sheet=work.getSheetAt(0);
				 records = readSheet(sheet);
			}
		} 
		else {
			logger.error("not_found_file");
		}
		return records;
	}

	private static List<List<String>> readSheet(HSSFSheet sheet) {
		
		List<List<String>> returnValue=new ArrayList<List<String>>();
		
		if(sheet!=null && sheet.getLastRowNum()>0){
						
			for(int i=0;i<sheet.getLastRowNum()+1;i++){
				
				HSSFRow row = sheet.getRow(i);
				if(row!=null){
					List<String> rowList=new ArrayList<String>();
	                
					for(int j=0;j<row.getLastCellNum();j++) {
						HSSFCell cell = row.getCell(j);
						if(cell!=null){
							cell.setCellType(Cell.CELL_TYPE_STRING);
							rowList.add(cell.getRichStringCellValue().toString());
						}
						else{
							rowList.add("");
						}
					}
					
					if(!isNullRow(rowList)){
						returnValue.add(rowList);
					}
				}
			}
		}

		return returnValue;
	}

	private static boolean isNullRow(List<String> rowList) {
		boolean returnValue=false;
		if(rowList!=null){
			if(rowList.size()>0){
				String regEx = "^\\[(,\\s){"+(rowList.size()-1)+"}\\]$"; 
				Pattern pattern = Pattern.compile(regEx);  
				Matcher matcher = pattern.matcher(rowList.toString());
				if(matcher.matches()){
					returnValue=true;
				}
			}
			else{
				returnValue=true;
			}
		}
		else{
			returnValue=true;
		}
		return returnValue;
	}

	/**
	 * 
	 * @param ins
	 * @param charCode  ex."GBK"
	 * @return
	 * @throws Exception
	 */
	public static List<List<String>> readCSV(InputStream ins,String charCode) {
		List<List<String>> records=new ArrayList<List<String>>();
		try{
			List<String[]> rows = new ArrayList<String[]>(); 
	        InputStreamReader isr=new InputStreamReader(ins,charCode);
	        CSVReader csvReader = new CSVReader(isr);  
	        rows=csvReader.readAll();
	        csvReader.close();
	        
	        for(int i=0;i<rows.size();i++){
	        	List<String> row=new ArrayList<String>();
	        	for(int j=0;j<rows.get(i).length;j++){
	        		row.add(rows.get(i)[j]);
	        	}
	        	records.add(row);
	        }
		}
		catch(UnsupportedEncodingException ex){
			logger.error(ex.getMessage());
		}
		catch( IOException ex){
			logger.error(ex.getMessage());
		}
		return records;
	}
	
	/**
	 * 读取Excel转成任意bean 
	 * @param bean
	 * @param ins
	 * @param proMapping
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T>  List<T> readExcel(T bean,InputStream ins, Map<String, String> proMapping) throws InstantiationException, IllegalAccessException {
			//字段-属性对应表
	//		 Map<String,String> mapping=new HashMap<String,String>();
	//	        mapping.put("表号", "meterNum");
	//	        mapping.put("规格型号", "meterModel");
	//	        mapping.put("检验员", "testUser1");
	//	        mapping.put("结论", "testResult");
	//	        mapping.put("Q3_误差", "q3");
	//	        mapping.put("Q2_误差", "q2");
	//	        mapping.put("Q1_误差", "q1");
	//	        mapping.put("Q3_流量", "qf3");
	//	        mapping.put("Q2_流量", "qf2");
	//	        mapping.put("Q1_流量", "qf1");
			 
			List<T> returnValue=new ArrayList<T>();
			List<String> header=new ArrayList<String>();
			List<List<String>> records=readExcel(ins);
			for(int i=0;i<records.size();i++){
				if(i==0){
					header=records.get(i);
				}
				if(header.equals(records.get(i))){
					continue;
				}
				List<String> row=records.get(i);
				if(row!=null && row.size()>0){
					T item=null;
					item=(T) bean.getClass().newInstance();
					for(int j=0;j<row.size();j++){
						String proName=proMapping.get(header.get(j));
						if(proName!=null){
							try {
								//自定义值转换
								String value=row.get(j);
								if(proName.contains("testResult") && value.equals("合格")){
									value="10";
								}
								BeanUtils.setProperty(item, proName, value);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
					returnValue.add(item);
				}
			}
			return returnValue;
		}
	
}




