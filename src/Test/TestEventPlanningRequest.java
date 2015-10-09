package Test;

import java.util.Calendar;

import EventPlanningRequest.EventPlanningRequest;

public class TestEventPlanningRequest {
	
	public static void testCreateEventPlanningRequest(){
		Calendar fromDate = Calendar.getInstance();
		fromDate.set(2015, 1, 1);
		Calendar toDate = Calendar.getInstance();
		toDate.set(2015, 1, 2);
		EventPlanningRequest e = new EventPlanningRequest(
				123,
				"clientName",
				"eventType",
				fromDate,
				toDate,
				100,
				true,
				false,
				false,
				false,
				false,
				1000 );
		
		if (!((e.getClientRecordNumber() == 123) &&
				(e.getClientName().equals("clientName")) &&
				(e.getEventType().equals("eventType")) &&
				(e.getFromDate().equals(fromDate)) &&
				(e.getToDate().equals(toDate)) &&
				(e.getExpectedAttendeesNumber() == 100) &&
				(e.isDecorationPreference()) &&
				(!e.isDrinkPreference()) &&
				(!e.isFoodPreference()) &&
				(!e.isPartiesPreference()) &&
				(!e.isPhotoPreference()) &&
				(e.getExpectedBudget() == 1000)))
		{
			System.out.println("Error in creation of Event Planning Request");
		}
		else
		{
			System.out.println("Creation of EventPlanningRequest ok");
		}
		
	}
}
