package es.solop.dateservice.impl;
import java.text.DateFormat;
import java.util.Date;
import es.solop.dateservice.DateService;
public class DateServiceImpl implements DateService {
	public String getFormattedDate(Date date) {
		return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
	}
}