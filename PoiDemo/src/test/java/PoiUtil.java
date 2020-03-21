import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class PoiUtil {

    public static String getCellValue(Cell cell){
        //getCellTypeEnum方法在 3.15 中不推荐使用并且会在 4.0 版本中将会改名为：getCellType
        if(cell.getCellTypeEnum() == CellType.BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellTypeEnum() == CellType.NUMERIC){
            return String.valueOf(cell.getNumericCellValue());
        }else {
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
