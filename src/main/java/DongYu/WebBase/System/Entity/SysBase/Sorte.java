package DongYu.WebBase.System.Entity.SysBase;

import net.sf.json.JSONArray;

import java.util.List;

public class Sorte {
	
	private String property;
	
	private String direction;
	
	private String prefix;
	

	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
	/**
	 * 解析排序json,格式如下:[{"property":"MEQ_LOCATION","direction":"ASC"}]
	 * @param sort
	 * @return
	 */
	public static Sorte[] parserSorte(String sort) {
		Sorte[] sortes=null;
		if(sort!=null && !sort.equals("")){
			JSONArray sortJson=JSONArray.fromObject(sort);
			List<Sorte> sorteList=JSONArray.toList(sortJson, Sorte.class);
			sortes=sorteList.toArray(new Sorte[0]);				
		}
		return sortes;
	}


	public static String getSqlOrderStr(Sorte[] sortes){
		return getSqlOrderStr(sortes, new SorteConfig() {
			public String splitJiont(Sorte sorte) {
				if(sorte!=null){
					StringBuilder strBuild=new StringBuilder();
					if(sorte.getPrefix()!=null&& !sorte.getPrefix().equals("")){
						strBuild.append(" "+sorte.getPrefix()+"."+sorte.getProperty());
					}
					else{
						strBuild.append(" "+sorte.getProperty());
						
					}
					strBuild.append(" "+sorte.getDirection());
					return strBuild.toString();
				}
				return "";
			}
		});
		
	}
	
	public static String getSqlOrderStr(Sorte[] sortes,String Prefix){
		if(sortes!=null){
			for(Sorte item:sortes){
				item.setPrefix(Prefix);
			}
		}
		return getSqlOrderStr(sortes, new SorteConfig() {
			public String splitJiont(Sorte sorte) {
				if(sorte!=null){
					StringBuilder strBuild=new StringBuilder();
					if(sorte.getPrefix()!=null&& !sorte.getPrefix().equals("")){
						strBuild.append(" "+sorte.getPrefix()+"."+sorte.getProperty());
					}
					else{
						strBuild.append(" "+sorte.getProperty());
					}
					strBuild.append(" "+sorte.getDirection());
					return strBuild.toString();
				}
				return "";
			}
		});
		
	}
	

	/**
	 * 构建以下语句：ORDER BY  m.SXE_EXPORT_USER ASC, b.SXE_EXPORT_USER ASC
	 * @param sortes
	 * @return
	 * 
	 */
	public static String getSqlOrderStr(Sorte[] sortes,SorteConfig sorteCF){
		String returnValue=null;
		if(sortes!=null && sortes.length>0){
			StringBuilder strBuild=new StringBuilder();
			int validCounter=0;
			for(int i=0;i<sortes.length;i++){
				Sorte sorte=sortes[i];
				if(sorte!=null){
					if(sorteCF!=null){
						if(sorteCF.splitJiont(sorte)!=null){
							if(validCounter>0){strBuild.append(",");}
							strBuild.append(sorteCF.splitJiont(sorte));
							validCounter++;
						}
					}					
				}
			}
			if(strBuild.length()>0){
				returnValue=" ORDER BY "+strBuild.toString();
			}
		}
		return returnValue;
	}


	public static String beanProperty2SqlColumn(String beanProperty){
		String returnValue=null;
		if(beanProperty!=null && !beanProperty.equals("")){
			String RexStr="(?=[A-Z])";
			String[] tempArray = beanProperty.split(RexStr);
	        for(int i = 0 ;i < tempArray.length; i ++){
	        	if(i==0){
	        		returnValue=tempArray[i].toUpperCase();
	        	}
	        	else{
	        		returnValue=returnValue+"_"+tempArray[i].toUpperCase();
	        	}
	        }
		}
		return returnValue;
		
	}
	
	
	public static void main(String[] args) {
		
		Sorte sorte=new Sorte();
		sorte.setProperty("sxeExportUser");
		sorte.setDirection("ASC");
		sorte.setPrefix("m");
		
		Sorte sorte1=new Sorte();
		sorte1.setProperty("sxeExportDate");
		sorte1.setDirection("ASC");
		sorte1.setPrefix("m");
		
		Sorte[] sortes=new Sorte[2];
		sortes[0]=sorte;
		sortes[1]=sorte;
		
		
		System.out.println(sorte.getSqlOrderStr(sortes));
		
//		System.out.println(sorte.beanProperty2SqlColumn(sorte.getProperty()));
		
		
	}

}
