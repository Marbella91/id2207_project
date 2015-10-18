package Test;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.EventPlanningRequestStatus;

public class TestEventPlanningRequest {
	
	public static void testEventPlanningRequest(){
		EventPlanningRequest epr = 
				new EventPlanningRequest("clientName", "eventType", "fromDate", "toDate", 100,
				true, true, true, true, true, 1000);
		if (epr.getClientName().equals("clientName") &&
				epr.getEventType().equals("eventType") &&
				epr.getFromDate().equals("fromDate") &&
				epr.getToDate().equals("toDate") &&
				epr.getExpectedAttendeesNumber() == 100 &&
				epr.isDecorationPreference() &&
				epr.isDrinkPreference() &&
				epr.isFoodPreference() &&
				epr.isPartiesPreference() &&
				epr.isPhotoPreference() &&
				epr.getExpectedBudget() == 1000 &&
				epr.getStatus().equals(EventPlanningRequestStatus.PendingSCSOComments)){
			System.out.println("Event planning request creation ok");
		} else {
			System.out.println("Error in creation of event planning request");
		}
	}
	
	public static void testEventPlanningRequestToString(){
		EventPlanningRequest epr = 
				new EventPlanningRequest("clientName", "eventType", "fromDate", "toDate", 100,
				true, true, true, true, true, 1000);
		if (epr.toString().equals(epr.getEventType() + " for " + epr.getClientName()))
			System.out.println("Event planning request function toString() ok");
		else
			System.out.println("Error in Event planning request function toString()");
	}
	
	public static void testToFromXml(){
		EventPlanningRequest epr = 
				new EventPlanningRequest("clientName", "eventType", "fromDate", "toDate", 100,
				true, true, true, true, true, 1000);
		String eprXML = epr.toXml();
		EventPlanningRequest eprBis = EventPlanningRequest.fromXml(eprXML);
		if (eprBis.getClientName().equals(epr.getClientName()) &&
				eprBis.getEventType().equals(epr.getEventType()) &&
				eprBis.getFromDate().equals(epr.getFromDate()) &&
				eprBis.getToDate().equals(epr.getToDate()) &&
				eprBis.getExpectedAttendeesNumber() == epr.getExpectedAttendeesNumber() &&
				!(eprBis.isDecorationPreference() ^ epr.isDecorationPreference()) &&
				!(eprBis.isDrinkPreference() ^ epr.isDrinkPreference()) &&
				!(eprBis.isFoodPreference() ^ epr.isFoodPreference()) &&
				!(eprBis.isPartiesPreference() ^ epr.isPartiesPreference()) &&
				!(eprBis.isPhotoPreference() ^ epr.isPhotoPreference()) &&
				eprBis.getStatus().equals(epr.getStatus())){
			System.out.println("Event planning request to/from xml functions ok");
		} else {
			System.out.println("Error in Event planning request to/from xml functions");
		}
		
	}
}
