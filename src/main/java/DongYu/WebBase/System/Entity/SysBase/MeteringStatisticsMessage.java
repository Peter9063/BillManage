package DongYu.WebBase.System.Entity.SysBase;

import java.util.List;

public class MeteringStatisticsMessage<T> {
    private  List<T> rows;
//    private List<MeteringStatistics> rows;
    private Long total;

    public  List<T> getRows(){
        return rows;
    }

    public void setRows(List<T> rows){
        this.rows=rows;
    }
//    public  List<MeteringStatistics> getRows() {
//        return rows;
//    }
//
//    public void setRows( List<MeteringStatistics> rows) {
//        this.rows = rows;
//    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
