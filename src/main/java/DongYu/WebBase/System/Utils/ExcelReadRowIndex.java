package DongYu.WebBase.System.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReadRowIndex {
	
	private Map<Integer,String> indexs; //<index,fieldName>
	private Map<String,Integer> keys;//<fieldName,index>

	public ExcelReadRowIndex(List<String> list) {
		if(list!=null && list.size()>0){
			indexs=new HashMap<Integer,String>();
			keys=new HashMap<String,Integer>();
			
			for(int i=0;i<list.size();i++){
				indexs.put(i, list.get(i));
				keys.put(list.get(i), i);
			}
		}
	}

	public String getVal(String fieldName, List<String> row) {
		String returnValue=null;
		if(fieldName!=null && !fieldName.equals("") && row!=null){
			Integer index=null;
			try{
				index=keys.get(fieldName);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			if(index!=null){
				if(index < row.size()){
					returnValue=row.get(index);
				}
				if(returnValue==null){
					returnValue="";
				}
			}
		}
		return returnValue;
	}
	
	public String getVal(int index, List<String> row) {
		String returnValue=null;
		if(row!=null && index>=0 && index<row.size()){
			returnValue=row.get(index);
			if(returnValue==null){
				returnValue="";
			}
		}
		return returnValue;
	}

	public void setVal(String fieldName, List<String> row, String value) {
		if(fieldName!=null && !fieldName.equals("") && row!=null){
			Integer index=keys.get(fieldName);
			row.set(index, value);
		}
	}
	
	public void setVal(int index, List<String> row, String value) {
		if(row!=null && index>=0 && index<row.size()){
			row.set(index, value);
		}
	}
	
	

}
