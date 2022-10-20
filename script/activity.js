$(document).ready(function() {
	var activityOptions = document.querySelectorAll("#activityList option");
	for (var i = 0; i < activityOptions.length; i++) {
		if (activityOptions[i].getAttribute("value") == activityListID) {
			activityOptions[i].setAttribute("selected","");
		}
	}	
});