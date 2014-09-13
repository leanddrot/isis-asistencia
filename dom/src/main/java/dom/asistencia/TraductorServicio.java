package dom.asistencia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TraductorServicio {

	
	public static String DateToString(Date date){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = df.format(date);
		return stringDate;
	}
	
	public static Date stringToDate(String dateString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(dateString);
		
		return date;
	}
	
}
