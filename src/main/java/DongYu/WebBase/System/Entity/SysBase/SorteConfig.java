package DongYu.WebBase.System.Entity.SysBase;


public abstract class SorteConfig {
	
	/**
	 * 自定义实现排序语句拼接接口;默认已存在一个实现，名称为defaultSplitJiont;
	 * @param sorte
	 * @return
	 */
	public abstract String splitJiont(Sorte sorte);

	/**
	 * 排序语句拼接默认实现 
	 * @param sorte
	 * @return
	 */
	public  String defaultSplitJiont(Sorte sorte) {
		if(sorte!=null){
			StringBuilder strBuild=new StringBuilder();
			if(sorte.getPrefix()!=null&& !sorte.getPrefix().equals("")){
				strBuild.append(" "+sorte.getPrefix()+"."+Sorte.beanProperty2SqlColumn(sorte.getProperty()));
			}
			else{
				strBuild.append(" "+Sorte.beanProperty2SqlColumn(sorte.getProperty()));
				
			}
			strBuild.append(" "+sorte.getDirection());
			return strBuild.toString();
		}
		return "";
	}
}
